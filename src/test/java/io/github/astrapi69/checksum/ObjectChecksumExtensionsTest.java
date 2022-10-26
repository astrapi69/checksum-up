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
 * The unit test class for the class {@link ObjectChecksumExtensions}
 */
public class ObjectChecksumExtensionsTest extends AbstractTestCase<Long, Long>
{


	/**
	 * Test method for {@link ObjectChecksumExtensions#getChecksum(String, Serializable[])}
	 *
	 * @throws NoSuchAlgorithmException
	 *             is thrown if instantiation of the SecretKeyFactory object fails.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetChecksumsStringAlgorithmObjects()
		throws NoSuchAlgorithmException, IOException
	{
		String expected;
		String actual;
		Person person;
		Person anotherPerson;

		person = TestObjectFactory.newPerson();

		anotherPerson = Person.builder().gender(Gender.FEMALE).name("Anton").married(false)
			.about("I'm a dev guy").nickname("admin").build();
		actual = ObjectChecksumExtensions.getChecksum(MdAlgorithm.MD2.getAlgorithm(), person,
			anotherPerson);
		expected = "8cb07185f0ca40fb0294514fe88ad3cd";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link ObjectChecksumExtensions#getChecksum(Serializable, Serializable, String)}
	 *
	 * @throws NoSuchAlgorithmException
	 *             is thrown if instantiation of the SecretKeyFactory object fails.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetChecksumObjectObjectAlgorithm() throws IOException, NoSuchAlgorithmException
	{
		String expected;
		String actual;
		Person person;
		Factory factory;

		person = TestObjectFactory.newPerson();

		factory = TestObjectFactory.newFactory();

		actual = ObjectChecksumExtensions.getChecksum(person, factory,
			MdAlgorithm.MD2.getAlgorithm());
		expected = "0dc30316b12e8c7c359bf86d58aee2ef";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ObjectChecksumExtensions#getChecksumHexString(Serializable, boolean)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetChecksumObjectBooleanHexString() throws IOException
	{
		String expected;
		String actual;
		Person person;

		person = Person.builder().gender(Gender.FEMALE).name("Anna").married(false)
			.about("I'm a beast and beautiful").nickname("beast").build();

		expected = "ee3d9073";
		actual = ObjectChecksumExtensions.getChecksumHexString(person, true);
		assertEquals(expected, actual);

		expected = "cb8f7d70";
		actual = ObjectChecksumExtensions.getChecksumHexString(person, false);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ObjectChecksumExtensions#getChecksum(Serializable, boolean)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetChecksumObjectBoolean() throws IOException
	{
		long expected;
		long actual;
		Person person;

		person = Person.builder().gender(Gender.FEMALE).name("Anna").married(false)
			.about("I'm a beast and beautiful").nickname("beast").build();

		expected = 3997012083L;
		System.err.println(Long.toHexString(expected));
		actual = ObjectChecksumExtensions.getChecksum(person, true);
		assertEquals(expected, actual);

		expected = 3415178608L;
		System.err.println(Long.toHexString(expected));
		actual = ObjectChecksumExtensions.getChecksum(person, false);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ObjectChecksumExtensions#getChecksum(Serializable, Algorithm)}
	 *
	 * @throws NoSuchAlgorithmException
	 *             is thrown if instantiation of the SecretKeyFactory object fails.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetChecksumSerializableAlgorithm() throws IOException, NoSuchAlgorithmException
	{
		String expected;
		String actual;
		Person person;
		int expectedLength;
		int actualLength;

		person = Person.builder().gender(Gender.FEMALE).name("Anna").married(false)
			.about("I'm a beast and beautiful").nickname("beast").build();

		expected = "b1def362c48394716231bb7e42c14b3c";
		actual = ObjectChecksumExtensions.getChecksum(person, MdAlgorithm.MD2);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "6b80d6c08539433402f8b11775854717";
		actual = ObjectChecksumExtensions.getChecksum(person, MdAlgorithm.MD5);
		assertEquals(expected, actual);
		actualLength = actual.length();
		assertEquals(expectedLength, actualLength);

		expected = "36a96d39dab8e137aebd72fed5f71ff3ddfc1cc5";
		actual = ObjectChecksumExtensions.getChecksum(person, HashAlgorithm.SHA_1);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "1c8b7990650072c991ceaed72639e4c7416356af225d52d796c5d1aeb14818b1";
		actual = ObjectChecksumExtensions.getChecksum(person, HashAlgorithm.SHA_256);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "72d25f5e70e0a5423d2d1629fe67950d00cec139b43f7714a74aea27f6eaab29162f65ed6776bcc360f97310498fed15";
		actual = ObjectChecksumExtensions.getChecksum(person, HashAlgorithm.SHA_384);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "e586ea0a18c6b6cb500700d589fa382d4d6cfd94b3a79ae8381115c4713528429bdcdcc0c57157fad133b3af2f98177783aee6a19582d8ceed6df9d3ad2f6299";
		actual = ObjectChecksumExtensions.getChecksum(person, HashAlgorithm.SHA_512);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);

	}

	/**
	 * Test method for {@link ObjectChecksumExtensions#getChecksum(Serializable, String)}
	 *
	 * @throws NoSuchAlgorithmException
	 *             is thrown if instantiation of the SecretKeyFactory object fails.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetChecksumSerializableAlgorithmAsString()
		throws IOException, NoSuchAlgorithmException
	{
		String expected;
		String actual;
		Person person;
		int expectedLength;
		int actualLength;

		person = Person.builder().gender(Gender.FEMALE).name("Anna").married(false)
			.about("I'm a beast and beautiful").nickname("beast").build();

		expected = "b1def362c48394716231bb7e42c14b3c";
		actual = ObjectChecksumExtensions.getChecksum(person, MdAlgorithm.MD2.getAlgorithm());
		actual = ObjectChecksumExtensions.getChecksum(MdAlgorithm.MD2.getAlgorithm(), person);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "6b80d6c08539433402f8b11775854717";
		actual = ObjectChecksumExtensions.getChecksum(person, MdAlgorithm.MD5.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		assertEquals(expectedLength, actualLength);

		expected = "36a96d39dab8e137aebd72fed5f71ff3ddfc1cc5";
		actual = ObjectChecksumExtensions.getChecksum(person, HashAlgorithm.SHA_1.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "1c8b7990650072c991ceaed72639e4c7416356af225d52d796c5d1aeb14818b1";
		actual = ObjectChecksumExtensions.getChecksum(person, HashAlgorithm.SHA_256.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "72d25f5e70e0a5423d2d1629fe67950d00cec139b43f7714a74aea27f6eaab29162f65ed6776bcc360f97310498fed15";
		actual = ObjectChecksumExtensions.getChecksum(person, HashAlgorithm.SHA_384.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "e586ea0a18c6b6cb500700d589fa382d4d6cfd94b3a79ae8381115c4713528429bdcdcc0c57157fad133b3af2f98177783aee6a19582d8ceed6df9d3ad2f6299";
		actual = ObjectChecksumExtensions.getChecksum(person, HashAlgorithm.SHA_512.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);
	}

	/**
	 * Test method for {@link ObjectChecksumExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ObjectChecksumExtensions.class);
	}

}
