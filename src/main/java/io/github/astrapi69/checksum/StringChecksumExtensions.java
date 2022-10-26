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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import io.github.astrapi69.crypt.api.algorithm.Algorithm;

/**
 * The class {@link StringChecksumExtensions} is a utility class for computing checksum from objects
 *
 * @author Asterios Raptis
 * @version 1.0
 */
public final class StringChecksumExtensions
{
	private StringChecksumExtensions()
	{
	}

	/**
	 * Gets the checksum from the given string with an instance of the given algorithm
	 *
	 * @param text
	 *            the string
	 * @param algorithm
	 *            the algorithm to get the checksum. This could be for instance "MD4", "MD5",
	 *            "SHA-1", "SHA-256", "SHA-384" or "SHA-512".
	 * @return The checksum from the file as a String object.
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exists.
	 *             {@link MessageDigest} object.
	 */
	public static String getChecksum(final String text, final Algorithm algorithm)
		throws NoSuchAlgorithmException
	{
		return getChecksum(text, algorithm.getAlgorithm());
	}

	/**
	 * Gets the checksum from the given string. If the flag crc is true than the checksum is
	 * constructed with an instance of <code>java.util.zip.CRC32</code> otherwise with an instance
	 * of <code>java.util.zip.Adler32</code>.
	 *
	 * @param text
	 *            the string
	 * @param crc
	 *            the crc flag
	 * @return The checksum from the given string as long
	 */
	public static long getChecksum(final String text, final boolean crc)
	{
		return crc
			? ByteArrayChecksumExtensions.getCheckSumCRC32(text.getBytes())
			: ByteArrayChecksumExtensions.getCheckSumAdler32(text.getBytes());
	}

	/**
	 * Gets the checksum from the given string with an instance of the given algorithm.
	 *
	 * @param text
	 *            the string
	 * @param algorithm
	 *            the algorithm to get the checksum. This could be for instance "MD4", "MD5",
	 *            "SHA-1", "SHA-256", "SHA-384" or "SHA-512".
	 * @return The checksum from the file as a String object.
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exists.
	 *             {@link MessageDigest} object.
	 */
	public static String getChecksum(final String text, final String algorithm)
		throws NoSuchAlgorithmException
	{
		return ByteArrayChecksumExtensions.getChecksum(text.getBytes(), algorithm);
	}

}
