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

import static io.github.astrapi69.checksum.ByteArrayChecksumExtensions.encodeHex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * The class {@link DirectoryChecksum} provides algorithms for computing checksum for directories
 *
 * @author Asterios Raptis
 * @version 1.0
 */
public class DirectoryChecksum
{

	/**
	 * The {@link MessageDigest} object
	 */
	MessageDigest messageDigest;

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
	}

	/**
	 * Updates the digest using the specified array of bytes and return the result as hexadecimal
	 * {@link String} object
	 *
	 * @param dirPath
	 *            the directory path
	 * @return the hexadecimal {@link String} object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public String update(Path dirPath) throws IOException
	{
		Files.newDirectoryStream(dirPath).forEach(currentFile -> {
			if (!Files.isDirectory(currentFile))
			{
				try
				{
					messageDigest.update(Files.readAllBytes(currentFile));
				}
				catch (IOException e)
				{
					throw new RuntimeException(e);
				}
			}
			else
			{
				try
				{
					update(currentFile);
				}
				catch (IOException e)
				{
					throw new RuntimeException(e);
				}
			}
		});
		return encodeHex(messageDigest.digest());
	}

}
