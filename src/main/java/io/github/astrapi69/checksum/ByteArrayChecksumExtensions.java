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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import io.github.astrapi69.crypt.api.algorithm.Algorithm;

/**
 * The class {@link ByteArrayChecksumExtensions} provides algorithms for computing checksum from
 * byte arrays and {@link Byte} object arrays
 *
 * @author Asterios Raptis
 * @version 1.0
 */
public final class ByteArrayChecksumExtensions
{

	private ByteArrayChecksumExtensions()
	{
	}

	/**
	 * Gets the checksum from the given byte array
	 *
	 * @param bytes
	 *            the byte array
	 * @param algorithm
	 *            the {@link Algorithm} object that provides the algorithm as {@link String} object
	 *            to get the checksum. This can be for instance "MD2", "MD5", "SHA-1", "SHA-256",
	 *            "SHA-384" or "SHA-512"
	 * @return The checksum from the given byte array as {@link String} object
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exist
	 */
	public static String getChecksum(final byte[] bytes, final Algorithm algorithm)
		throws NoSuchAlgorithmException
	{
		return getChecksum(bytes, algorithm.getAlgorithm());
	}

	/**
	 * Gets the checksum from the given byte array
	 *
	 * @param bytes
	 *            the byte array
	 * @param algorithm
	 *            the algorithm to get the checksum. This can be for instance "MD2", "MD5", "SHA-1",
	 *            "SHA-256", "SHA-384" or "SHA-512"
	 * @return The checksum from the given byte array as {@link String} object
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exist
	 */
	public static String getChecksum(final byte[] bytes, final String algorithm)
		throws NoSuchAlgorithmException
	{
		return getChecksum(algorithm, bytes);
	}

	/**
	 * Gets the checksum from the given {@link Byte} object array
	 *
	 * @param bytes
	 *            the {@link Byte} object array
	 * @param algorithm
	 *            the {@link Algorithm} object that provides the algorithm as {@link String} object
	 *            to get the checksum. This can be for instance "MD2", "MD5", "SHA-1", "SHA-256",
	 *            "SHA-384" or "SHA-512"
	 * @return The checksum from the given {@link Byte} object array as {@link String} object
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exist
	 */
	public static String getChecksum(final Byte[] bytes, final Algorithm algorithm)
		throws NoSuchAlgorithmException
	{
		return getChecksum(bytes, algorithm.getAlgorithm());
	}

	/**
	 * Gets the checksum from the given {@link Byte} object array
	 *
	 * @param bytes
	 *            the {@link Byte} object array
	 * @param algorithm
	 *            the algorithm to get the checksum. This can be for instance "MD2", "MD5", "SHA-1",
	 *            "SHA-256", "SHA-384" or "SHA-512"
	 * @return The checksum from the given {@link Byte} object array as {@link String} object
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exist
	 */
	public static String getChecksum(final Byte[] bytes, final String algorithm)
		throws NoSuchAlgorithmException
	{
		return getChecksum(toByteArray(bytes), algorithm);
	}

	/**
	 * Gets the checksum from the given byte arrays with the given algorithm
	 *
	 * @param algorithm
	 *            the {@link Algorithm} object that provides the algorithm as {@link String} object
	 *            to get the checksum. This can be for instance "MD2", "MD5", "SHA-1", "SHA-256",
	 *            "SHA-384" or "SHA-512"
	 * @param byteArrays
	 *            the array of byte arrays
	 * @return The checksum from the given byte arrays as {@link String} object
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exist
	 */
	public static String getChecksum(final Algorithm algorithm, final byte[]... byteArrays)
		throws NoSuchAlgorithmException
	{
		return getChecksum(algorithm.getAlgorithm(), byteArrays);
	}

	/**
	 * Gets the checksum from the given byte arrays with the given algorithm
	 *
	 * @param algorithm
	 *            the algorithm to get the checksum. This can be for instance "MD2", "MD5", "SHA-1",
	 *            "SHA-256", "SHA-384" or "SHA-512".
	 * @param byteArrays
	 *            the array of byte arrays
	 * @return The checksum from the given byte arrays as {@link String} object
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exist
	 */
	public static String getChecksum(final String algorithm, final byte[]... byteArrays)
		throws NoSuchAlgorithmException
	{
		final MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
		messageDigest.reset();
		for (byte[] byteArray : byteArrays)
		{
			messageDigest.update(byteArray);
		}
		return encodeHex(messageDigest.digest());
	}

	/**
	 * Gets the checksum from the given byte array with an instance of {@link Adler32} object
	 *
	 * @param bytes
	 *            The byte array
	 * @return The checksum from the byte array as long value
	 */
	public static long getCheckSumAdler32(final byte[] bytes)
	{
		final Checksum checksum = new Adler32();
		checksum.update(bytes, 0, bytes.length);
		return checksum.getValue();
	}

	/**
	 * Gets the checksum as hexadecimal string from the given byte array
	 *
	 * @param bytes
	 *            The byte array
	 * @return The checksum from the byte array as long value
	 */
	public static String getCheckSumAdler32HexString(final byte[] bytes)
	{
		return Long.toHexString(getCheckSumAdler32(bytes));
	}

	/**
	 * Gets the checksum from the given byte array with an instance of {@link CRC32} object
	 *
	 * @param bytes
	 *            The byte array
	 * @return The checksum from the byte array as long value
	 */
	public static long getCheckSumCRC32(final byte[] bytes)
	{
		final Checksum checksum = new CRC32();
		checksum.update(bytes, 0, bytes.length);
		return checksum.getValue();
	}

	/**
	 * Gets the checksum from the given byte array with an instance of {@link CRC32} object
	 *
	 * @param bytes
	 *            The byte array
	 * @return The checksum from the byte array as hex {@link String} object
	 */
	public static String getCheckSumCRC32HexString(final byte[] bytes)
	{
		return normalizeCheckSumCRC32HexStringLength(Long.toHexString(getCheckSumCRC32(bytes)));
	}

	/**
	 * Encode the given byte array to hex string
	 *
	 * @param bytes
	 *            the byte array
	 * @return the hex {@link String} object from the given byte array
	 */
	public static String encodeHex(byte[] bytes)
	{
		final StringBuilder hexView = new StringBuilder();
		for (final byte currentByte : bytes)
		{
			final String intAsHex = Integer.toHexString(0xFF & currentByte);
			if (intAsHex.length() == 1)
			{
				hexView.append('0');
			}
			hexView.append(intAsHex);
		}
		return hexView.toString();
	}

	private static byte[] toByteArray(Byte[] bytes)
	{
		byte[] byteArray = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++)
		{
			byteArray[i] = bytes[i];
		}
		return byteArray;
	}

	private static String normalizeCheckSumCRC32HexStringLength(String hexString)
	{
		StringBuilder hexStringBuilder = new StringBuilder(hexString);
		while (hexStringBuilder.length() != 8)
		{
			hexStringBuilder.insert(0, "0");
		}
		hexString = hexStringBuilder.toString();
		return hexString;
	}

}
