/**
 * The MIT License
 * <p>
 * Copyright (C) 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.checksum;

import io.github.astrapi69.crypto.algorithm.Algorithm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * The class {@link ObjectChecksumExtensions} is a utility class for computing checksum from objects
 *
 * @author Asterios Raptis
 * @version 1.0
 */
public final class ObjectChecksumExtensions
{
	private ObjectChecksumExtensions()
	{
	}

	/**
	 * Gets the checksum from the given file with an instance of the given algorithm.
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
		return crc ?
			ByteArrayChecksumExtensions.getCheckSumCRC32(text.getBytes()) :
			ByteArrayChecksumExtensions.getCheckSumAdler32(text.getBytes());
	}

	/**
	 * Gets the checksum from the given file with an instance of the given algorithm.
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

	/**
	 * Gets the checksum from the given file with an instance of the given algorithm
	 *
	 * @param <T>
	 *            the generic type of the serializable object
	 *
	 * @param serializableObject
	 *            the serializable object
	 * @param algorithm
	 *            the algorithm to get the checksum. This could be for instance "MD4", "MD5",
	 *            "SHA-1", "SHA-256", "SHA-384" or "SHA-512".
	 * @return The checksum from the file as a String object.
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exists.
	 *             {@link MessageDigest} object.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T extends Serializable> String getChecksum(final T serializableObject,
		final Algorithm algorithm) throws NoSuchAlgorithmException, IOException
	{
		return getChecksum(serializableObject, algorithm.getAlgorithm());
	}

	/**
	 * Gets the checksum from the given serializable object. If the flag crc is true than the
	 * checksum is constructed with an instance of <code>java.util.zip.CRC32</code> otherwise with
	 * an instance of <code>java.util.zip.Adler32</code>.
	 *
	 * @param <T>
	 *            the generic type of the serializable object
	 * @param serializableObject
	 *            the serializable object from what to get the checksum
	 * @param crc
	 *            the crc flag
	 * @return The checksum from the given serializable object as long
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T extends Serializable> long getChecksum(final T serializableObject,
		final boolean crc) throws IOException
	{
		return crc ?
			ByteArrayChecksumExtensions.getCheckSumCRC32(toByteArray(serializableObject)) :
			ByteArrayChecksumExtensions.getCheckSumAdler32(toByteArray(serializableObject));
	}

	/**
	 * Gets the checksum from the given file with an instance of the given algorithm
	 *
	 * @param <T>
	 *            the generic type of the serializable object
	 *
	 * @param serializableObject
	 *            the serializable object
	 * @param algorithm
	 *            the algorithm to get the checksum. This could be for instance "MD4", "MD5",
	 *            "SHA-1", "SHA-256", "SHA-384" or "SHA-512".
	 * @return The checksum from the file as a String object.
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exists.
	 *             {@link MessageDigest} object.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T extends Serializable> String getChecksum(final T serializableObject,
		final String algorithm) throws NoSuchAlgorithmException, IOException
	{
		return ByteArrayChecksumExtensions.getChecksum(toByteArray(serializableObject), algorithm);
	}

	/**
	 * Copies the given object to a byte array
	 *
	 * @param <T>
	 *            the generic type of the given object
	 * @param object
	 *            The object to copy
	 * @return the byte array
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T extends Serializable> byte[] toByteArray(final T object) throws IOException
	{
		Objects.requireNonNull(object);
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream))
		{
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
			return byteArrayOutputStream.toByteArray();
		}
	}

}
