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

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.astrapi69.crypt.api.algorithm.Algorithm;
import io.github.astrapi69.crypt.api.algorithm.ChecksumAlgorithm;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.test.base.AbstractTestCase;

/**
 * The unit test class for the class {@link FileChecksumExtensions}
 */
public class FileChecksumExtensionsTest extends AbstractTestCase<Long, Long>
{
	File checksumDir;
	File testFile;
	File checksumDirExtern;
	File testFileExtern;

	/**
	 * Sets up method will be invoked before every unit test method in this class
	 */
	@Override
	@BeforeMethod
	protected void setUp()
	{
		String checksumDirExternName = System.getProperty("user.home");
		checksumDir = new File(PathFinder.getProjectDirectory(), "src/test/resources/checksum");
		testFile = new File(checksumDir, "testReadFileInput.txt");
		checksumDirExtern = new File(checksumDirExternName, "/apps/monero");
		testFileExtern = new File(checksumDirExtern, "monero-linux-x64-v0.15.0.5.tar.bz2");
	}

	/**
	 * Test method for {@link FileChecksumExtensions#getCheckSumAdler32(File)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetCheckSumAdler32File() throws IOException
	{
		long expected;
		long actual;
		actual = FileChecksumExtensions.getCheckSumAdler32(testFile);
		expected = 3296728756L;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link FileChecksumExtensions#getCheckSumAdler32HexString(File)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetCheckSumAdler32HexStringFile() throws IOException
	{
		String expected;
		String actual;
		actual = FileChecksumExtensions.getCheckSumAdler32HexString(testFile);
		expected = "c48016b4";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link FileChecksumExtensions#getCheckSumCRC32HexString(File)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetCheckSumCRC32FileHexString() throws IOException
	{
		String expected;
		String actual;
		actual = FileChecksumExtensions.getCheckSumCRC32HexString(testFile);
		expected = "0bbedb29";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link FileChecksumExtensions#getCheckSumCRC32(File)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetCheckSumCRC32File() throws IOException
	{
		long expected;
		long actual;
		actual = FileChecksumExtensions.getCheckSumCRC32(testFile);
		expected = 197057321L;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link FileChecksumExtensions#getChecksum(File, Algorithm)}
	 *
	 * @throws NoSuchAlgorithmException
	 *             is thrown if instantiation of the SecretKeyFactory object fails.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetChecksumFileAlgorithm() throws NoSuchAlgorithmException, IOException
	{
		String expected;
		String actual;
		int expectedLength;
		int actualLength;

		expected = "f57f8379e8c62db6135f14d93a84ffd3";
		actual = FileChecksumExtensions.getChecksum(testFile, ChecksumAlgorithm.MD2);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "3a37a2c10a590785dbfb9ce3b15b0464";
		actual = FileChecksumExtensions.getChecksum(testFile, ChecksumAlgorithm.MD5);
		assertEquals(expected, actual);
		actualLength = actual.length();
		assertEquals(expectedLength, actualLength);

		expected = "496dfa0ecf50cc6e3eda41fd3258272c2f2f0ff1";
		actual = FileChecksumExtensions.getChecksum(testFile, ChecksumAlgorithm.SHA_1);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "94151a5c66422a9adf706937eeb7fafec25032c380b55b0e92695baf297fb747";
		actual = FileChecksumExtensions.getChecksum(testFile, ChecksumAlgorithm.SHA_256);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "c1bc0091901a944828ca56f236f068d318086a55b96e045b1e7415df1449eb9c8e54546fec4b759ad2c6f7e3fbab7561";
		actual = FileChecksumExtensions.getChecksum(testFile, ChecksumAlgorithm.SHA_384);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "4d0c14f299254e58dcea1f524ca08af5f0776b1f5070919a859b92c2ab350635375862ab0727fd5e34ff35da837bd836a17047544db8df63adc4912211ea7f02";
		actual = FileChecksumExtensions.getChecksum(testFile, ChecksumAlgorithm.SHA_512);
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);

	}

	/**
	 * Test method for {@link FileChecksumExtensions#getChecksum(File, boolean)}
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetChecksumFileBoolean() throws IOException
	{
		long expected;
		long actual;

		expected = 197057321L;
		actual = FileChecksumExtensions.getChecksum(testFile, true);
		assertEquals(expected, actual);

		expected = 3296728756L;
		actual = FileChecksumExtensions.getChecksum(testFile, false);
		assertEquals(expected, actual);

	}

	/**
	 * Test method for {@link FileChecksumExtensions#getChecksum(File, String)}
	 *
	 * @throws NoSuchAlgorithmException
	 *             is thrown if instantiation of the SecretKeyFactory object fails.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetChecksumFileString() throws NoSuchAlgorithmException, IOException
	{
		String expected;
		String actual;
		int expectedLength;
		int actualLength;

		expected = "f57f8379e8c62db6135f14d93a84ffd3";
		actual = FileChecksumExtensions.getChecksum(testFile, ChecksumAlgorithm.MD2.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "3a37a2c10a590785dbfb9ce3b15b0464";
		actual = FileChecksumExtensions.getChecksum(testFile, ChecksumAlgorithm.MD5.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		assertEquals(expectedLength, actualLength);

		expected = "496dfa0ecf50cc6e3eda41fd3258272c2f2f0ff1";
		actual = FileChecksumExtensions.getChecksum(testFile,
			ChecksumAlgorithm.SHA_1.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "94151a5c66422a9adf706937eeb7fafec25032c380b55b0e92695baf297fb747";
		actual = FileChecksumExtensions.getChecksum(testFile,
			ChecksumAlgorithm.SHA_256.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "c1bc0091901a944828ca56f236f068d318086a55b96e045b1e7415df1449eb9c8e54546fec4b759ad2c6f7e3fbab7561";
		actual = FileChecksumExtensions.getChecksum(testFile,
			ChecksumAlgorithm.SHA_384.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "4d0c14f299254e58dcea1f524ca08af5f0776b1f5070919a859b92c2ab350635375862ab0727fd5e34ff35da837bd836a17047544db8df63adc4912211ea7f02";
		actual = FileChecksumExtensions.getChecksum(testFile,
			ChecksumAlgorithm.SHA_512.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);
	}

	/**
	 * Test method for {@link FileChecksumExtensions#getChecksum(File, String)}
	 *
	 * @throws NoSuchAlgorithmException
	 *             is thrown if instantiation of the SecretKeyFactory object fails.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetChecksumFileStringWithDirectory()
		throws NoSuchAlgorithmException, IOException
	{
		String expected;
		String actual;
		int expectedLength;
		int actualLength;
		File srcTestResourcesDir = PathFinder.getSrcTestResourcesDir();
		expected = "8350e5a3e24c153df2275c9f80692773";
		actual = FileChecksumExtensions.getChecksum(srcTestResourcesDir,
			ChecksumAlgorithm.MD2.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "d41d8cd98f00b204e9800998ecf8427e";
		actual = FileChecksumExtensions.getChecksum(srcTestResourcesDir,
			ChecksumAlgorithm.MD5.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		assertEquals(expectedLength, actualLength);

		expected = "da39a3ee5e6b4b0d3255bfef95601890afd80709";
		actual = FileChecksumExtensions.getChecksum(srcTestResourcesDir,
			ChecksumAlgorithm.SHA_1.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
		actual = FileChecksumExtensions.getChecksum(srcTestResourcesDir,
			ChecksumAlgorithm.SHA_256.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "38b060a751ac96384cd9327eb1b1e36a21fdb71114be07434c0cc7bf63f6e1da274edebfe76f65fbd51ad2f14898b95b";
		actual = FileChecksumExtensions.getChecksum(srcTestResourcesDir,
			ChecksumAlgorithm.SHA_384.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e";
		actual = FileChecksumExtensions.getChecksum(srcTestResourcesDir,
			ChecksumAlgorithm.SHA_512.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);
	}

	/**
	 * Test method for {@link FileChecksumExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(FileChecksumExtensions.class);
	}

}
