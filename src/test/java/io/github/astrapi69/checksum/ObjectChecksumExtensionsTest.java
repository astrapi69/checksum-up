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

import io.github.astrapi69.crypt.api.algorithm.Algorithm;
import io.github.astrapi69.crypt.api.algorithm.ChecksumAlgorithm;
import io.github.astrapi69.test.base.AbstractTestCase;
import io.github.astrapi69.test.object.Factory;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumeration.Gender;
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
		actual = ObjectChecksumExtensions.getChecksum(ChecksumAlgorithm.MD2.getAlgorithm(), person,
			anotherPerson);
		expected = "43d19d64b7b5bb2a7cf6ef71da6c8708";
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
			ChecksumAlgorithm.MD2.getAlgorithm());
		expected = "3db5178700737b0d69cf80d7578c083d";
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

		expected = "3a56ff48";
		actual = ObjectChecksumExtensions.getChecksumHexString(person, true);
		assertEquals(expected, actual);

		expected = "35017fd6";
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

		expected = 978779976L;
		System.err.println(Long.toHexString(expected));
		actual = ObjectChecksumExtensions.getChecksum(person, true);
		assertEquals(expected, actual);

		expected = 889290710L;
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

		expected = "a76ef019de43590e6e757d3d20e79114";
		actual = ObjectChecksumExtensions.getChecksum(person, ChecksumAlgorithm.MD2);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "527e71bbbe3c9f2bee9b9d2be731766d";
		actual = ObjectChecksumExtensions.getChecksum(person, ChecksumAlgorithm.MD5);
		assertEquals(expected, actual);
		actualLength = actual.length();
		assertEquals(expectedLength, actualLength);

		expected = "3d3be624807b7eea2b461499db79c6f071165c08";
		actual = ObjectChecksumExtensions.getChecksum(person, ChecksumAlgorithm.SHA_1);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "fab94b59c4d731b256cd5b5f6cb75dc216b8af1643b27ae8ab93dc625fab0360";
		actual = ObjectChecksumExtensions.getChecksum(person, ChecksumAlgorithm.SHA_256);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "a014924a8e0aaac5d458b494b669fa0adf256ce3a53f20d9634b249daeba70896713ef5670e1a1616eb590c638903bf1";
		actual = ObjectChecksumExtensions.getChecksum(person, ChecksumAlgorithm.SHA_384);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "9c151e3632b3197ad13b9118270f42bfbfa63208461d26c3947cde253f9d6db06d59fc12b7a24c511b3b5b2a31c5859e430c33c2e9c54abfa4ddce72460ca1e2";
		actual = ObjectChecksumExtensions.getChecksum(person, ChecksumAlgorithm.SHA_512);
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
	public void testGetChecksumSerializableString() throws IOException, NoSuchAlgorithmException
	{
		String expected;
		String actual;
		Person person;
		int expectedLength;
		int actualLength;

		person = Person.builder().gender(Gender.FEMALE).name("Anna").married(false)
			.about("I'm a beast and beautiful").nickname("beast").build();

		expected = "a76ef019de43590e6e757d3d20e79114";
		actual = ObjectChecksumExtensions.getChecksum(person, ChecksumAlgorithm.MD2.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "527e71bbbe3c9f2bee9b9d2be731766d";
		actual = ObjectChecksumExtensions.getChecksum(person, ChecksumAlgorithm.MD5.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		assertEquals(expectedLength, actualLength);

		expected = "3d3be624807b7eea2b461499db79c6f071165c08";
		actual = ObjectChecksumExtensions.getChecksum(person,
			ChecksumAlgorithm.SHA_1.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "fab94b59c4d731b256cd5b5f6cb75dc216b8af1643b27ae8ab93dc625fab0360";
		actual = ObjectChecksumExtensions.getChecksum(person,
			ChecksumAlgorithm.SHA_256.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "a014924a8e0aaac5d458b494b669fa0adf256ce3a53f20d9634b249daeba70896713ef5670e1a1616eb590c638903bf1";
		actual = ObjectChecksumExtensions.getChecksum(person,
			ChecksumAlgorithm.SHA_384.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "9c151e3632b3197ad13b9118270f42bfbfa63208461d26c3947cde253f9d6db06d59fc12b7a24c511b3b5b2a31c5859e430c33c2e9c54abfa4ddce72460ca1e2";
		actual = ObjectChecksumExtensions.getChecksum(person,
			ChecksumAlgorithm.SHA_512.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);
	}

	/**
	 * Test method for {@link ObjectChecksumExtensions#getChecksum(String, Serializable[])}
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

		expected = "a76ef019de43590e6e757d3d20e79114";
		actual = ObjectChecksumExtensions.getChecksum(ChecksumAlgorithm.MD2.getAlgorithm(), person);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "527e71bbbe3c9f2bee9b9d2be731766d";
		actual = ObjectChecksumExtensions.getChecksum(ChecksumAlgorithm.MD5.getAlgorithm(), person);
		assertEquals(expected, actual);
		actualLength = actual.length();
		assertEquals(expectedLength, actualLength);

		expected = "3d3be624807b7eea2b461499db79c6f071165c08";
		actual = ObjectChecksumExtensions.getChecksum(ChecksumAlgorithm.SHA_1.getAlgorithm(),
			person);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "fab94b59c4d731b256cd5b5f6cb75dc216b8af1643b27ae8ab93dc625fab0360";
		actual = ObjectChecksumExtensions.getChecksum(ChecksumAlgorithm.SHA_256.getAlgorithm(),
			person);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "a014924a8e0aaac5d458b494b669fa0adf256ce3a53f20d9634b249daeba70896713ef5670e1a1616eb590c638903bf1";
		actual = ObjectChecksumExtensions.getChecksum(ChecksumAlgorithm.SHA_384.getAlgorithm(),
			person);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "9c151e3632b3197ad13b9118270f42bfbfa63208461d26c3947cde253f9d6db06d59fc12b7a24c511b3b5b2a31c5859e430c33c2e9c54abfa4ddce72460ca1e2";
		actual = ObjectChecksumExtensions.getChecksum(ChecksumAlgorithm.SHA_512.getAlgorithm(),
			person);
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
