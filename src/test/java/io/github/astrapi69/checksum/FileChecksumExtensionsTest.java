/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.astrapi69.checksum;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
	 * Test method for {@link FileChecksumExtensions#getChecksum(File, String)} with a directory
	 * tree built at runtime. The expected values were computed outside this library, with the
	 * hashlib of Python over the form {@link DirectoryChecksum} documents. Python has no MD2, so
	 * for MD2 the test checks the length and that the checksum is not the one of empty input
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
		File directory = newDirectoryTree();
		expected = "8350e5a3e24c153df2275c9f80692773";
		actual = FileChecksumExtensions.getChecksum(directory,
			ChecksumAlgorithm.MD2.getAlgorithm());
		assertFalse("the checksum of empty input", expected.equals(actual));
		actualLength = actual.length();
		expectedLength = 32;
		assertEquals(expectedLength, actualLength);

		expected = "5716a9b88562adec2b345a26960e37e3";
		actual = FileChecksumExtensions.getChecksum(directory,
			ChecksumAlgorithm.MD5.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		assertEquals(expectedLength, actualLength);

		expected = "c78522deab117c693122d5d977c1e482438a10f9";
		actual = FileChecksumExtensions.getChecksum(directory,
			ChecksumAlgorithm.SHA_1.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 40;
		assertEquals(expectedLength, actualLength);

		expected = "47c0522975ebe49076c9dae201c9a80c8e07e08e1b669e56a0f7775a6c2819dd";
		actual = FileChecksumExtensions.getChecksum(directory,
			ChecksumAlgorithm.SHA_256.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 64;
		assertEquals(expectedLength, actualLength);

		expected = "d406692df0ed610047d4ad6896a7cd024c0d2be897ff39a9bcca6a2c824c0cd5411cb391736feb27ffe08f6ecc4fefef";
		actual = FileChecksumExtensions.getChecksum(directory,
			ChecksumAlgorithm.SHA_384.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 96;
		assertEquals(expectedLength, actualLength);

		expected = "9a63280e3999717e989cc7220864737331719d0e14794faf2a8bfced9d6914e793c2e61043993bb32c6b95c1eb70ac275cd9bc99009bbc00e48138c412a2b17c";
		actual = FileChecksumExtensions.getChecksum(directory,
			ChecksumAlgorithm.SHA_512.getAlgorithm());
		assertEquals(expected, actual);
		actualLength = actual.length();
		expectedLength = 128;
		assertEquals(expectedLength, actualLength);
	}

	/**
	 * Creates the tree of a file a.txt with the content "alpha" and a directory sub with a file
	 * b.txt with the content "beta" in a new temporary directory, deleted when the JVM exits
	 *
	 * @return the temporary directory that holds the tree
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static File newDirectoryTree() throws IOException
	{
		Path directory = Files.createTempDirectory("directory-checksum");
		directory.toFile().deleteOnExit();
		Path subdirectory = Files.createDirectory(directory.resolve("sub"));
		subdirectory.toFile().deleteOnExit();
		Files.writeString(directory.resolve("a.txt"), "alpha", StandardCharsets.UTF_8).toFile()
			.deleteOnExit();
		Files.writeString(subdirectory.resolve("b.txt"), "beta", StandardCharsets.UTF_8).toFile()
			.deleteOnExit();
		return directory.toFile();
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
