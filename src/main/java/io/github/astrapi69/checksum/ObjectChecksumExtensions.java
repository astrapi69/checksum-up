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
	 * Gets the checksum from the given object with an instance of the given algorithm
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
		return crc
			? ByteArrayChecksumExtensions.getCheckSumCRC32(toByteArray(serializableObject))
			: ByteArrayChecksumExtensions.getCheckSumAdler32(toByteArray(serializableObject));
	}

	/**
	 * Gets the checksum as hexadecimal string from the given serializable object. If the flag crc
	 * is true then the checksum is constructed with an instance of <code>java.util.zip.CRC32</code>
	 * otherwise with an instance of <code>java.util.zip.Adler32</code>.
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
	public static <T extends Serializable> String getChecksumHexString(final T serializableObject,
		final boolean crc) throws IOException
	{
		return crc
			? ByteArrayChecksumExtensions.getCheckSumCRC32HexString(toByteArray(serializableObject))
			: ByteArrayChecksumExtensions
				.getCheckSumAdler32HexString(toByteArray(serializableObject));
	}

	/**
	 * Copies the given objects to a byte array
	 *
	 * @param <T>
	 *            the generic type of the given object
	 * @param objects
	 *            The object to copy
	 * @return the byte array from the given objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SafeVarargs
	public static <T extends Serializable> byte[] toByteArray(final T... objects) throws IOException
	{
		Objects.requireNonNull(objects);
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream))
		{
			for (T serializableObject : objects)
			{
				objectOutputStream.writeObject(serializableObject);
			}
			objectOutputStream.flush();
			return byteArrayOutputStream.toByteArray();
		}
	}

	/**
	 * Gets the checksum from the given serializable objects with an instance of the given algorithm
	 *
	 * @param <T>
	 *            the generic type of the first given serializable object
	 * @param <E>
	 *            the generic type of the second given serializable object
	 *
	 * @param serializableObject
	 *            the serializable object
	 * @param anotherSerializableObject
	 *            the other serializable object
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
	public static <T extends Serializable, E extends Serializable> String getChecksum(
		final T serializableObject, final E anotherSerializableObject, final String algorithm)
		throws NoSuchAlgorithmException, IOException
	{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream.write(toByteArray(serializableObject));
		outputStream.write(toByteArray(anotherSerializableObject));
		return ByteArrayChecksumExtensions.getChecksum(outputStream.toByteArray(), algorithm);
	}

	/**
	 * Gets the checksum from the given file with an instance of the given algorithm
	 *
	 * @param <T>
	 *            the generic type of the serializable object
	 *
	 * @param serializableObjects
	 *            the array with serializable objects
	 * @param algorithm
	 *            the algorithm to get the checksum. This could be for instance "MD4", "MD5",
	 *            "SHA-1", "SHA-256", "SHA-384" or "SHA-512".
	 * @return The checksum from the file as a String object.
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exist.
	 *             {@link MessageDigest} object.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SafeVarargs
	public static <T extends Serializable> String getChecksum(final String algorithm,
		final T... serializableObjects) throws NoSuchAlgorithmException, IOException
	{
		return ByteArrayChecksumExtensions.getChecksum(toByteArray(serializableObjects), algorithm);
	}

	/**
	 * Gets the checksum from the given serializable object with an instance of the given algorithm
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

}
