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
import io.github.astrapi69.crypto.algorithm.ChecksumAlgorithm;
import io.github.astrapi69.crypto.algorithm.HashAlgorithm;
import io.github.astrapi69.crypto.algorithm.MdAlgorithm;

/**
 * The unit test class for the class {@link ByteArrayChecksumExtensions}
 */
public class ChecksumExtensionsTest extends AbstractTestCase<Long, Long>
{

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
		ChecksumAlgorithm expectedAlgorithm;
		ChecksumAlgorithm actualAlgorithm;
		final String secretMessage = "secret Message";
		final byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
		expected = "5cc16e663491726545c13ec2012f4601";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, MdAlgorithm.MD2);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);
		actualAlgorithm = ChecksumExtensions.resolveChecksumAlgorithm(actual);
		expectedAlgorithm = ChecksumAlgorithm.MD5;
		assertEquals(expectedAlgorithm, actualAlgorithm);

		expected = "25659bd9db98ecc3c2077d44e69607b8";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, MdAlgorithm.MD5);
		assertEquals(expected, actual);
		actualLength = actual.length();
		assertEquals(expectedLength, actualLength);
		actualAlgorithm = ChecksumExtensions.resolveChecksumAlgorithm(actual);
		assertEquals(expectedAlgorithm, actualAlgorithm);

		expected = "874026e54b67d4f9aaf87cb14a683fb51de6f9cb";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, HashAlgorithm.SHA_1);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);
		actualAlgorithm = ChecksumExtensions.resolveChecksumAlgorithm(actual);
		expectedAlgorithm = ChecksumAlgorithm.SHA_1;
		assertEquals(expectedAlgorithm, actualAlgorithm);

		expected = "8a3b3c92a8b0eb00da917c23201a9407ef7963373464076aec4c54c066e8b7aa";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, HashAlgorithm.SHA_256);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);
		actualAlgorithm = ChecksumExtensions.resolveChecksumAlgorithm(actual);
		expectedAlgorithm = ChecksumAlgorithm.SHA_256;
		assertEquals(expectedAlgorithm, actualAlgorithm);

		expected = "b58a362687ab42b9bf0d8af0b4860ed262d1fd128e16ab0082723e7785a862cd129b03577312452cc24aecdb36d5406d";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, HashAlgorithm.SHA_384);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);
		actualAlgorithm = ChecksumExtensions.resolveChecksumAlgorithm(actual);
		expectedAlgorithm = ChecksumAlgorithm.SHA_384;
		assertEquals(expectedAlgorithm, actualAlgorithm);

		expected = "ab29b34a26547ca4ce517d776885a5642929d9ed571a990fc764f7d0b854d6546276ca9aa45b3d88db3dc3dbf3c2f2152017d3e3e054ed6cd7a38a1f7925a746";
		actual = ByteArrayChecksumExtensions.getChecksum(secretMessageBytes, HashAlgorithm.SHA_512);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);
		actualAlgorithm = ChecksumExtensions.resolveChecksumAlgorithm(actual);
		expectedAlgorithm = ChecksumAlgorithm.SHA_512;
		assertEquals(expectedAlgorithm, actualAlgorithm);
		actual = actualAlgorithm.getAlgorithm();
		expected = "SHA-512";
		assertEquals(expected, actual);
		actualAlgorithm = ChecksumExtensions.resolveChecksumAlgorithm("noChecksum");
		expectedAlgorithm = ChecksumAlgorithm.UNDEFINED;
		assertEquals(expectedAlgorithm, actualAlgorithm);
	}

	/**
	 * Test method for {@link ChecksumExtensions#matchesMD5(String)}.
	 */
	@Test
	public void testMatchesMD5()
	{
		boolean expected;
		boolean actual;
		actual = ChecksumExtensions.matchesMD5("3a37a2c10a590785dbfb9ce3b15b0464");
		expected = true;
		assertEquals(expected, actual);

		actual = ChecksumExtensions.matchesMD5("496dfa0ecf50cc6e3eda41fd3258272c2f2f0ff1");
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ChecksumExtensions#matchesSHA1(String)}.
	 */
	@Test
	public void testMatchesSHA1()
	{
		boolean expected;
		boolean actual;
		actual = ChecksumExtensions.matchesSHA1("496dfa0ecf50cc6e3eda41fd3258272c2f2f0ff1");
		expected = true;
		assertEquals(expected, actual);

		actual = ChecksumExtensions.matchesSHA1("3a37a2c10a590785dbfb9ce3b15b0464");
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ChecksumExtensions#matchesSHA512(String)}.
	 */
	@Test
	public void testMatchesSHA512()
	{
		boolean expected;
		boolean actual;
		actual = ChecksumExtensions.matchesSHA512(
			"4d0c14f299254e58dcea1f524ca08af5f0776b1f5070919a859b92c2ab350635375862ab0727fd5e34ff35da837bd836a17047544db8df63adc4912211ea7f02");
		expected = true;
		assertEquals(expected, actual);

		actual = ChecksumExtensions.matchesSHA512("496dfa0ecf50cc6e3eda41fd3258272c2f2f0ff1");
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ChecksumExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ChecksumExtensions.class);
	}

}
