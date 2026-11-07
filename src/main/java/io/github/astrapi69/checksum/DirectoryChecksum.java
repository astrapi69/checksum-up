/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.astrapi69.checksum;

import static io.github.astrapi69.checksum.ByteArrayChecksumExtensions.encodeHex;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * The class {@link DirectoryChecksum} provides algorithms for computing checksum for directories.
 * The checksum covers the whole tree below the directory: the relative path of each file and
 * subdirectory, whether it is a file or a directory, and the content of each file. Two trees have
 * the same checksum if they hold the same paths with the same contents, wherever they are and in
 * whatever order the file system lists them. Symbolic links are not followed into directories
 *
 * @author Asterios Raptis
 * @version 1.0
 */
public class DirectoryChecksum
{

	/**
	 * The marker of a directory entry in the digest
	 */
	private static final byte DIRECTORY = 'D';

	/**
	 * The marker of a file entry in the digest
	 */
	private static final byte FILE = 'F';

	/**
	 * The size of the buffer the content of a file is read with
	 */
	private static final int BUFFER_SIZE = 8192;

	/**
	 * The {@link MessageDigest} object
	 */
	MessageDigest messageDigest;

	/**
	 * The {@link MessageDigest} object that computes the digest of the content of each file
	 */
	private final MessageDigest contentDigest;

	/**
	 * Instantiates a new {@link DirectoryChecksum} object
	 *
	 * @param algorithm
	 *            the algorithm for the {@link MessageDigest} object
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exists
	 *             {@link MessageDigest} object
	 */
	DirectoryChecksum(String algorithm) throws NoSuchAlgorithmException
	{
		Objects.requireNonNull(algorithm, "Given algorithm is null");
		this.messageDigest = MessageDigest.getInstance(algorithm);
		this.messageDigest.reset();
		this.contentDigest = MessageDigest.getInstance(algorithm);
	}

	/**
	 * Computes the checksum of the tree below the given directory and returns it as hexadecimal
	 * {@link String} object. The entries below the directory are taken in the order of their
	 * relative paths, with a slash between the names, and each goes into one digest as the byte
	 * {@code 'D'} for a directory or {@code 'F'} for a file, the length of its relative path in
	 * UTF-8 as four bytes big-endian, the path itself and, for a file, the digest of its content
	 * with the same algorithm. The directory itself and its name do not enter the checksum, so an
	 * empty directory gives the digest of empty input
	 *
	 * @param dirPath
	 *            the directory path
	 * @return the hexadecimal {@link String} object
	 * @throws NotDirectoryException
	 *             Is thrown if the given path is not a directory
	 * @throws IOException
	 *             Signals that an I/O exception has occurred, for instance if a file of the tree
	 *             cannot be read
	 */
	public String update(Path dirPath) throws IOException
	{
		if (!Files.isDirectory(dirPath))
		{
			throw new NotDirectoryException(dirPath.toString());
		}
		Map<String, Path> entries = new TreeMap<>();
		try (Stream<Path> paths = Files.walk(dirPath))
		{
			paths.filter(path -> !path.equals(dirPath))
				.forEach(path -> entries.put(relativePath(dirPath, path), path));
		}
		catch (UncheckedIOException exception)
		{
			throw exception.getCause();
		}
		messageDigest.reset();
		for (Map.Entry<String, Path> entry : entries.entrySet())
		{
			boolean directory = Files.isDirectory(entry.getValue());
			byte[] name = entry.getKey().getBytes(StandardCharsets.UTF_8);
			messageDigest.update(directory ? DIRECTORY : FILE);
			messageDigest.update(ByteBuffer.allocate(Integer.BYTES).putInt(name.length).array());
			messageDigest.update(name);
			if (!directory)
			{
				messageDigest.update(digestContent(entry.getValue()));
			}
		}
		return encodeHex(messageDigest.digest());
	}

	/**
	 * Gets the path of the given entry relative to the given directory, with a slash between its
	 * names on every platform
	 *
	 * @param dirPath
	 *            the directory path
	 * @param path
	 *            the path of an entry below the directory
	 * @return the relative path of the entry
	 */
	private static String relativePath(Path dirPath, Path path)
	{
		Path relative = dirPath.relativize(path);
		StringBuilder builder = new StringBuilder();
		for (Path name : relative)
		{
			if (!builder.isEmpty())
			{
				builder.append('/');
			}
			builder.append(name);
		}
		return builder.toString();
	}

	/**
	 * Computes the digest of the content of the given file
	 *
	 * @param file
	 *            the file
	 * @return the digest of the content of the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	private byte[] digestContent(Path file) throws IOException
	{
		contentDigest.reset();
		try (InputStream inputStream = Files.newInputStream(file))
		{
			byte[] buffer = new byte[BUFFER_SIZE];
			int read;
			while ((read = inputStream.read(buffer)) != -1)
			{
				contentDigest.update(buffer, 0, read);
			}
		}
		return contentDigest.digest();
	}

}
