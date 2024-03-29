/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.checksum;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import io.github.astrapi69.crypt.api.algorithm.Algorithm;

/**
 * The class {@link FileChecksumExtensions} provides algorithms for computing checksum from files
 *
 * @author Asterios Raptis
 * @version 1.0
 */
public final class FileChecksumExtensions
{
	private FileChecksumExtensions()
	{
	}

	/**
	 * Gets the checksum from the given {@link File} object with an instance of the given
	 * {@link Algorithm} object
	 *
	 * @param file
	 *            the {@link File} object
	 * @param algorithm
	 *            the {@link Algorithm} object that provides the algorithm as {@link String} object
	 *            to get the checksum. This can be for instance "MD2", "MD5", "SHA-1", "SHA-256",
	 *            "SHA-384" or "SHA-512"
	 * @return The checksum from the given {@link File} object as {@link String} object
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exist
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static String getChecksum(final File file, final Algorithm algorithm)
		throws NoSuchAlgorithmException, IOException
	{
		return getChecksum(file, algorithm.getAlgorithm());
	}

	/**
	 * Gets the checksum from the given {@link File} object. If the flag crc is true then the
	 * CheckedInputStream is constructed with an instance of <code>java.util.zip.CRC32</code>
	 * otherwise with an instance of <code>java.util.zip.Adler32</code>
	 *
	 * @param file
	 *            The {@link File} object to resolve the checksum
	 * @param crc
	 *            if the flag crc is true then the CheckedInputStream is constructed with an
	 *            instance of {@link java.util.zip.CRC32} object otherwise it is constructed with an
	 *            instance of {@link java.util.zip.Adler32} object
	 * @return The checksum from the given {@link File} object as long value
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static long getChecksum(final File file, final boolean crc) throws IOException
	{
		try (CheckedInputStream cis = crc
			? new CheckedInputStream(Files.newInputStream(file.toPath()), new CRC32())
			: new CheckedInputStream(Files.newInputStream(file.toPath()), new Adler32()))
		{
			final int length = (int)file.length();
			final byte[] buffer = new byte[length];
			long checksum;
			while (cis.read(buffer) >= 0)
			{
				checksum = cis.getChecksum().getValue();
			}
			checksum = cis.getChecksum().getValue();
			return checksum;
		}
	}

	/**
	 * Gets the checksum from the given {@link File} object with an instance of the given algorithm
	 *
	 * @param file
	 *            the {@link File} object
	 * @param algorithm
	 *            the algorithm to get the checksum. This can be for instance "MD2", "MD5", "SHA-1",
	 *            "SHA-256", "SHA-384" or "SHA-512".
	 * @return The checksum from the given {@link File} object as {@link String} object
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exist
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static String getChecksum(final File file, final String algorithm)
		throws NoSuchAlgorithmException, IOException
	{
		if (file.isDirectory())
		{
			DirectoryChecksum directoryChecksum = new DirectoryChecksum(algorithm);
			return directoryChecksum.update(file.toPath());
		}
		return ByteArrayChecksumExtensions.getChecksum(Files.readAllBytes(file.toPath()),
			algorithm);
	}

	/**
	 * Gets the checksum from the given {@link File} object with an instance of {@link Adler32}
	 * object
	 *
	 * @param file
	 *            The {@link File} object
	 * @return The checksum from the {@link File} object as long value
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static long getCheckSumAdler32(final File file) throws IOException
	{
		return ByteArrayChecksumExtensions.getCheckSumAdler32(Files.readAllBytes(file.toPath()));
	}

	/**
	 * Gets the checksum from the given {@link File} object with an instance of {@link Adler32}
	 * object
	 *
	 * @param file
	 *            The {@link File} object
	 * @return The checksum from the given {@link File} object as {@link String} object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static String getCheckSumAdler32HexString(final File file) throws IOException
	{
		return ByteArrayChecksumExtensions
			.getCheckSumAdler32HexString(Files.readAllBytes(file.toPath()));
	}

	/**
	 * Gets the checksum from the given {@link File} object with an instance of {@link CRC32} object
	 *
	 * @param file
	 *            The {@link File} object
	 * @return The checksum from the {@link File} object as long value
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static long getCheckSumCRC32(final File file) throws IOException
	{
		return ByteArrayChecksumExtensions.getCheckSumCRC32(Files.readAllBytes(file.toPath()));
	}

	/**
	 * Gets the checksum from the given {@link File} object with an instance of {@link CRC32} object
	 *
	 * @param file
	 *            The {@link File} object
	 * @return The checksum from the given {@link File} object as {@link String} object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static String getCheckSumCRC32HexString(final File file) throws IOException
	{
		return ByteArrayChecksumExtensions
			.getCheckSumCRC32HexString(Files.readAllBytes(file.toPath()));
	}

}
