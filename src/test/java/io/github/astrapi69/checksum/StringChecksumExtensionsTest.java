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

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import io.github.astrapi69.AbstractTestCase;
import io.github.astrapi69.crypt.api.algorithm.Algorithm;
import io.github.astrapi69.crypt.api.algorithm.HashAlgorithm;
import io.github.astrapi69.crypt.api.algorithm.MdAlgorithm;
import io.github.astrapi69.test.object.Factory;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumtype.Gender;
import io.github.astrapi69.test.object.factory.TestObjectFactory;

/**
 * The unit test class for the class {@link StringChecksumExtensions}
 */
public class StringChecksumExtensionsTest extends AbstractTestCase<Long, Long>
{

	/**
	 * Test method for {@link StringChecksumExtensions#getChecksum(String, Algorithm)}
	 *
	 * @throws NoSuchAlgorithmException
	 *             is thrown if instantiation of the SecretKeyFactory object fails.
	 */
	@Test
	public void testGetChecksumStringAlgorithm() throws NoSuchAlgorithmException
	{
		String expected;
		String actual;
		String text;
		int expectedLength;
		int actualLength;

		text = "Lorem ipsum dolor sit amet, sea consul verterem perfecto id. Alii prompta electram te nec, at minimum copiosae quo. Eos iudico nominati oportere ei, usu at dicta legendos. In nostrum insolens disputando pro, iusto equidem ius id.";

		expected = "2016860f2f742ecce65a6c246922cdba";
		actual = StringChecksumExtensions.getChecksum(text, MdAlgorithm.MD2);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "56a3c95014fb72a9adbeb7f16c474bba";
		actual = StringChecksumExtensions.getChecksum(text, MdAlgorithm.MD5);
		assertEquals(expected, actual);
		actualLength = actual.length();
		assertEquals(expectedLength, actualLength);

		expected = "e41a81525dbfb95f254e0558c8d15969b661ccc0";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_1);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "b930e875225e5ec0291e1ccc39ab3ddf9e5de10b54f48d8470620174eea5491c";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_256);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "1f9d7250a02aa6ad4f61425921430714e333185b4555e6c15b7911cbf096c16e649bf2891d3003ef51d65644423b3dfd";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_384);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "4ce3fd741dc5459beda2c7ffed7e7561667e84612651935f2cf50e973f03b3094ad83e225f0ce340b688929d5fee9d6785a362a9af5b12350b4e85b9e8d3a8c4";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_512);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);

		text = "secret Message";

		expected = "5cc16e663491726545c13ec2012f4601";
		actual = StringChecksumExtensions.getChecksum(text, MdAlgorithm.MD2);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "25659bd9db98ecc3c2077d44e69607b8";
		actual = StringChecksumExtensions.getChecksum(text, MdAlgorithm.MD5);
		assertEquals(expected, actual);
		actualLength = actual.length();
		assertEquals(expectedLength, actualLength);

		expected = "874026e54b67d4f9aaf87cb14a683fb51de6f9cb";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_1);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "8a3b3c92a8b0eb00da917c23201a9407ef7963373464076aec4c54c066e8b7aa";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_256);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "b58a362687ab42b9bf0d8af0b4860ed262d1fd128e16ab0082723e7785a862cd129b03577312452cc24aecdb36d5406d";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_384);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "ab29b34a26547ca4ce517d776885a5642929d9ed571a990fc764f7d0b854d6546276ca9aa45b3d88db3dc3dbf3c2f2152017d3e3e054ed6cd7a38a1f7925a746";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_512);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);

	}

	/**
	 * Test method for {@link StringChecksumExtensions#getChecksum(String, String)}
	 *
	 * @throws NoSuchAlgorithmException
	 *             is thrown if instantiation of the SecretKeyFactory object fails.
	 */
	@Test
	public void testGetChecksumStringAlgorithmAsString() throws NoSuchAlgorithmException
	{
		String expected;
		String actual;
		String text;
		int expectedLength;
		int actualLength;

		text = "Lorem ipsum dolor sit amet, sea consul verterem perfecto id. Alii prompta electram te nec, at minimum copiosae quo. Eos iudico nominati oportere ei, usu at dicta legendos. In nostrum insolens disputando pro, iusto equidem ius id.";

		expected = "2016860f2f742ecce65a6c246922cdba";
		actual = StringChecksumExtensions.getChecksum(text, MdAlgorithm.MD2.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "56a3c95014fb72a9adbeb7f16c474bba";
		actual = StringChecksumExtensions.getChecksum(text, MdAlgorithm.MD5.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		assertEquals(expectedLength, actualLength);

		expected = "e41a81525dbfb95f254e0558c8d15969b661ccc0";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_1.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "b930e875225e5ec0291e1ccc39ab3ddf9e5de10b54f48d8470620174eea5491c";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_256.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "1f9d7250a02aa6ad4f61425921430714e333185b4555e6c15b7911cbf096c16e649bf2891d3003ef51d65644423b3dfd";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_384.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "4ce3fd741dc5459beda2c7ffed7e7561667e84612651935f2cf50e973f03b3094ad83e225f0ce340b688929d5fee9d6785a362a9af5b12350b4e85b9e8d3a8c4";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_512.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);

		text = "secret Message";

		expected = "5cc16e663491726545c13ec2012f4601";
		actual = StringChecksumExtensions.getChecksum(text, MdAlgorithm.MD2.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "25659bd9db98ecc3c2077d44e69607b8";
		actual = StringChecksumExtensions.getChecksum(text, MdAlgorithm.MD5.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		assertEquals(expectedLength, actualLength);

		expected = "874026e54b67d4f9aaf87cb14a683fb51de6f9cb";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_1.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "8a3b3c92a8b0eb00da917c23201a9407ef7963373464076aec4c54c066e8b7aa";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_256.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "b58a362687ab42b9bf0d8af0b4860ed262d1fd128e16ab0082723e7785a862cd129b03577312452cc24aecdb36d5406d";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_384.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "ab29b34a26547ca4ce517d776885a5642929d9ed571a990fc764f7d0b854d6546276ca9aa45b3d88db3dc3dbf3c2f2152017d3e3e054ed6cd7a38a1f7925a746";
		actual = StringChecksumExtensions.getChecksum(text, HashAlgorithm.SHA_512.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);

	}

	/**
	 * Test method for {@link StringChecksumExtensions#getChecksum(String, boolean)}
	 */
	@Test
	public void testGetChecksumStringBoolean()
	{
		long expected;
		long actual;
		String text;

		text = "Lorem ipsum dolor sit amet, sea consul verterem perfecto id. Alii prompta electram te nec, at minimum copiosae quo. Eos iudico nominati oportere ei, usu at dicta legendos. In nostrum insolens disputando pro, iusto equidem ius id.";

		expected = 1715371676L;
		actual = StringChecksumExtensions.getChecksum(text, true);
		assertEquals(expected, actual);

		expected = 2358596523L;
		actual = StringChecksumExtensions.getChecksum(text, false);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ObjectChecksumExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(StringChecksumExtensions.class);
	}

}
