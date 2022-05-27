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

import static org.testng.AssertJUnit.assertEquals;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import io.github.astrapi69.AbstractTestCase;
import io.github.astrapi69.crypto.algorithm.Algorithm;
import io.github.astrapi69.crypto.algorithm.HashAlgorithm;
import io.github.astrapi69.crypto.algorithm.MdAlgorithm;

/**
 * The unit test class for the class {@link ByteArrayChecksumExtensions}
 */
public class ByteArrayChecksumExtensionsTest extends AbstractTestCase<Long, Long>
{

	private static Byte[] toBytes(byte[] bytes)
	{
		Byte[] byteArray = new Byte[bytes.length];
		for (int i = 0; i < bytes.length; i++)
		{
			byteArray[i] = bytes[i];
		}
		return byteArray;
	}

	/**
	 * Test method for {@link ByteArrayChecksumExtensions#getCheckSumAdler32(byte[])}
	 */
	@Test
	public void testGetCheckSumAdler32ByteArray()
	{
		long expected;
		long actual;
		final String secretMessage = "secret Message";
		final byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
		actual = ByteArrayChecksumExtensions.getCheckSumAdler32(secretMessageBytes);
		expected = 685966700L;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ByteArrayChecksumExtensions#getChecksum(Algorithm, byte[]...)}
	 *
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exists.
	 *             {@link java.security.MessageDigest} object.
	 */
	@Test
	public void testGetChecksumAlgorithmByteArrays() throws NoSuchAlgorithmException
	{
		String expected;
		String actual;
		int expectedLength;
		int actualLength;
		final String secretMessage = "secret Message";
		String secondMessage = "foo";
		final byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
		final byte[] secondMessageBytes = secondMessage.getBytes(StandardCharsets.UTF_8);
		expected = "5cc16e663491726545c13ec2012f4601d11f8ce29210b4b50c5e67533b699d02";
		actual = ByteArrayChecksumExtensions.getChecksum(MdAlgorithm.MD2, secretMessageBytes,
			secondMessageBytes);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "25659bd9db98ecc3c2077d44e69607b8acbd18db4cc2f85cedef654fccc4a4d8";
		actual = ByteArrayChecksumExtensions.getChecksum(MdAlgorithm.MD5, secretMessageBytes,
			secondMessageBytes);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "874026e54b67d4f9aaf87cb14a683fb51de6f9cb0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a33";
		actual = ByteArrayChecksumExtensions.getChecksum(HashAlgorithm.SHA_1, secretMessageBytes,
			secondMessageBytes);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 80;
		assertEquals(expectedLength, actualLength);

		expected = "8a3b3c92a8b0eb00da917c23201a9407ef7963373464076aec4c54c066e8b7aa2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae";
		actual = ByteArrayChecksumExtensions.getChecksum(HashAlgorithm.SHA_256, secretMessageBytes,
			secondMessageBytes);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);

		expected = "b58a362687ab42b9bf0d8af0b4860ed262d1fd128e16ab0082723e7785a862cd129b03577312452cc24aecdb36d5406d98c11ffdfdd540676b1a137cb1a22b2a70350c9a44171d6b1180c6be5cbb2ee3f79d532c8a1dd9ef2e8e08e752a3babb";
		actual = ByteArrayChecksumExtensions.getChecksum(HashAlgorithm.SHA_384, secretMessageBytes,
			secondMessageBytes);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 192;
		assertEquals(expectedLength, actualLength);

		expected = "ab29b34a26547ca4ce517d776885a5642929d9ed571a990fc764f7d0b854d6546276ca9aa45b3d88db3dc3dbf3c2f2152017d3e3e054ed6cd7a38a1f7925a746f7fbba6e0636f890e56fbbf3283e524c6fa3204ae298382d624741d0dc6638326e282c41be5e4254d8820772c5518a2c5a8c0c7f7eda19594a7eb539453e1ed7";
		actual = ByteArrayChecksumExtensions.getChecksum(HashAlgorithm.SHA_512, secretMessageBytes,
			secondMessageBytes);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 256;
		assertEquals(expectedLength, actualLength);
	}

	/**
	 * Test method for {@link ByteArrayChecksumExtensions#getChecksum(byte[], Algorithm)}.
	 *
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exists.
	 *             {@link java.security.MessageDigest} object.
	 */
	@Test
	public void testGetChecksumByteArrayAlgorithm() throws NoSuchAlgorithmException
	{
		String expected;
		String actual;
		int expectedLength;
		int actualLength;
		final String secretMessage = "secret Message";
		final byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
		expected = "5cc16e663491726545c13ec2012f4601";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, MdAlgorithm.MD2);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "25659bd9db98ecc3c2077d44e69607b8";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, MdAlgorithm.MD5);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "874026e54b67d4f9aaf87cb14a683fb51de6f9cb";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, HashAlgorithm.SHA_1);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "8a3b3c92a8b0eb00da917c23201a9407ef7963373464076aec4c54c066e8b7aa";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, HashAlgorithm.SHA_256);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "b58a362687ab42b9bf0d8af0b4860ed262d1fd128e16ab0082723e7785a862cd129b03577312452cc24aecdb36d5406d";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, HashAlgorithm.SHA_384);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "ab29b34a26547ca4ce517d776885a5642929d9ed571a990fc764f7d0b854d6546276ca9aa45b3d88db3dc3dbf3c2f2152017d3e3e054ed6cd7a38a1f7925a746";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, HashAlgorithm.SHA_512);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);
	}

	/**
	 * Test method for {@link ByteArrayChecksumExtensions#getChecksum(byte[], String)}.
	 *
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exists.
	 *             {@link java.security.MessageDigest} object.
	 */
	@Test
	public void testGetChecksumByteArrayString() throws NoSuchAlgorithmException
	{
		String expected;
		String actual;
		int expectedLength;
		int actualLength;
		final String secretMessage = "secret Message";
		final byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
		expected = "5cc16e663491726545c13ec2012f4601";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes,
			MdAlgorithm.MD2.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "25659bd9db98ecc3c2077d44e69607b8";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes,
			MdAlgorithm.MD5.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "874026e54b67d4f9aaf87cb14a683fb51de6f9cb";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes,
			HashAlgorithm.SHA_1.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "8a3b3c92a8b0eb00da917c23201a9407ef7963373464076aec4c54c066e8b7aa";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes,
			HashAlgorithm.SHA_256.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "b58a362687ab42b9bf0d8af0b4860ed262d1fd128e16ab0082723e7785a862cd129b03577312452cc24aecdb36d5406d";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes,
			HashAlgorithm.SHA_384.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "ab29b34a26547ca4ce517d776885a5642929d9ed571a990fc764f7d0b854d6546276ca9aa45b3d88db3dc3dbf3c2f2152017d3e3e054ed6cd7a38a1f7925a746";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes,
			HashAlgorithm.SHA_512.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);
	}

	/**
	 * Test for {@link ByteArrayChecksumExtensions#getChecksum(Byte[], Algorithm)}.
	 *
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exists.
	 *             {@link java.security.MessageDigest} object.
	 */
	@Test
	public void testGetChecksumByteObjectArrayAlgorithm() throws NoSuchAlgorithmException
	{
		String expected;
		String actual;
		int expectedLength;
		int actualLength;
		final String secretMessage = "secret Message";
		final byte[] sbytes = secretMessage.getBytes(StandardCharsets.UTF_8);
		final Byte[] secretMessageBytes = toBytes(sbytes);
		expected = "5cc16e663491726545c13ec2012f4601";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, MdAlgorithm.MD2);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "25659bd9db98ecc3c2077d44e69607b8";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, MdAlgorithm.MD5);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "874026e54b67d4f9aaf87cb14a683fb51de6f9cb";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, HashAlgorithm.SHA_1);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "8a3b3c92a8b0eb00da917c23201a9407ef7963373464076aec4c54c066e8b7aa";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, HashAlgorithm.SHA_256);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "b58a362687ab42b9bf0d8af0b4860ed262d1fd128e16ab0082723e7785a862cd129b03577312452cc24aecdb36d5406d";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, HashAlgorithm.SHA_384);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "ab29b34a26547ca4ce517d776885a5642929d9ed571a990fc764f7d0b854d6546276ca9aa45b3d88db3dc3dbf3c2f2152017d3e3e054ed6cd7a38a1f7925a746";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, HashAlgorithm.SHA_512);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);

	}

	/**
	 * Test for {@link ByteArrayChecksumExtensions#getChecksum(Byte[], String)}.
	 *
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported or does not exists.
	 *             {@link java.security.MessageDigest} object.
	 */
	@Test
	public void testGetChecksumByteObjectArrayString() throws NoSuchAlgorithmException
	{
		String expected;
		String actual;
		int expectedLength;
		int actualLength;
		String secretMessage;
		byte[] sbytes;
		Byte[] secretMessageBytes;

		secretMessage = "secret Message";
		sbytes = secretMessage.getBytes(StandardCharsets.UTF_8);
		secretMessageBytes = toBytes(sbytes);

		expected = "5cc16e663491726545c13ec2012f4601";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes,
			MdAlgorithm.MD2.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "25659bd9db98ecc3c2077d44e69607b8";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes,
			MdAlgorithm.MD5.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "874026e54b67d4f9aaf87cb14a683fb51de6f9cb";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes,
			HashAlgorithm.SHA_1.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "8a3b3c92a8b0eb00da917c23201a9407ef7963373464076aec4c54c066e8b7aa";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes,
			HashAlgorithm.SHA_256.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "b58a362687ab42b9bf0d8af0b4860ed262d1fd128e16ab0082723e7785a862cd129b03577312452cc24aecdb36d5406d";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes,
			HashAlgorithm.SHA_384.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "ab29b34a26547ca4ce517d776885a5642929d9ed571a990fc764f7d0b854d6546276ca9aa45b3d88db3dc3dbf3c2f2152017d3e3e054ed6cd7a38a1f7925a746";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes,
			HashAlgorithm.SHA_512.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);
	}

	/**
	 * Test method for {@link ByteArrayChecksumExtensions#getCheckSumCRC32(byte[])}
	 */
	@Test
	public void testGetCheckSumCRC32ByteArray()
	{
		long expected;
		long actual;
		final String secretMessage = "secret Message";
		final byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
		actual = ByteArrayChecksumExtensions.getCheckSumCRC32(secretMessageBytes);
		expected = 711998200L;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ByteArrayChecksumExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ByteArrayChecksumExtensions.class);
	}

}
