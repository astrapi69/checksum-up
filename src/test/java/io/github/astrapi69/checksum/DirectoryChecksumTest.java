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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link DirectoryChecksum}, which builds its directory trees at
 * runtime in temporary directories
 */
public class DirectoryChecksumTest
{

	/** The algorithm the tests compute the checksums with */
	private static final String SHA_256 = "SHA-256";

	/** The temporary directories of the current test, deleted after it */
	private final List<Path> temporaryDirectories = new ArrayList<>();

	/**
	 * Deletes the temporary directories of the test that just ran
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@AfterMethod
	public void tearDown() throws IOException
	{
		for (Path directory : temporaryDirectories)
		{
			directory.toFile().setReadable(true);
			try (Stream<Path> paths = Files.walk(directory))
			{
				for (Path path : paths.sorted(Comparator.reverseOrder()).toList())
				{
					path.toFile().setReadable(true);
					Files.delete(path);
				}
			}
		}
		temporaryDirectories.clear();
	}

	/**
	 * Provides pairs of directory trees and whether their checksums are equal. A tree maps the
	 * relative path of each entry to the content of the file, or to null for a directory
	 *
	 * @return the cases, each with its name, the first tree, the second tree and whether the two
	 *         checksums are equal
	 */
	@DataProvider
	public Object[][] treePairs()
	{
		return new Object[][] {
				{ "flat, same names and same content", tree("a.txt", "alpha", "b.txt", "beta"),
						tree("a.txt", "alpha", "b.txt", "beta"), true },
				{ "flat, same name, other content", tree("a.txt", "alpha"), tree("a.txt", "omega"),
						false },
				{ "flat, other names, same content", tree("a.txt", "alpha"), tree("z.txt", "alpha"),
						true },
				{ "only a subdirectory, other content in it",
						tree("sub", null, "sub/a.txt", "alpha"),
						tree("sub", null, "sub/b.txt", "beta"), true },
				{ "four levels deep, other content in the deepest file",
						tree("a", null, "a/b", null, "a/b/c", null, "a/b/c/d", null,
							"a/b/c/d/e.txt", "alpha"),
						tree("a", null, "a/b", null, "a/b/c", null, "a/b/c/d", null,
							"a/b/c/d/e.txt", "omega"),
						true },
				{ "two empty directories", tree(), tree(), true } };
	}

	/**
	 * Test method for {@link DirectoryChecksum#update(Path)}: two trees give equal checksums
	 * exactly when the case says so
	 *
	 * @param name
	 *            the name of the case
	 * @param first
	 *            the first tree
	 * @param second
	 *            the second tree
	 * @param equal
	 *            whether the two checksums are equal
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported
	 */
	@Test(dataProvider = "treePairs")
	public void testUpdateComparesTwoTrees(String name, Map<String, String> first,
		Map<String, String> second, boolean equal) throws IOException, NoSuchAlgorithmException
	{
		String firstChecksum = checksum(createTree(first));
		String secondChecksum = checksum(createTree(second));
		if (equal)
		{
			assertEquals(firstChecksum, secondChecksum, name);
		}
		else
		{
			assertNotEquals(firstChecksum, secondChecksum, name);
		}
	}

	/**
	 * Test method for {@link DirectoryChecksum#update(Path)}: a file that cannot be read fails the
	 * computation
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testUpdateWithUnreadableFile() throws IOException
	{
		Path directory = createTree(tree("a.txt", "alpha"));
		Path file = directory.resolve("a.txt");
		file.toFile().setReadable(false);
		if (Files.isReadable(file))
		{
			throw new SkipException("the file stays readable for this user, root for example");
		}
		RuntimeException exception = expectThrows(RuntimeException.class,
			() -> new DirectoryChecksum(SHA_256).update(directory));
		assertTrue(exception.getCause() instanceof AccessDeniedException);
	}

	/**
	 * Computes the checksum of the given directory with a new {@link DirectoryChecksum} object
	 *
	 * @param directory
	 *            the directory
	 * @return the checksum as hexadecimal {@link String} object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported
	 */
	private static String checksum(Path directory) throws IOException, NoSuchAlgorithmException
	{
		return new DirectoryChecksum(SHA_256).update(directory);
	}

	/**
	 * Creates the given tree in a new temporary directory, in the order of the given map
	 *
	 * @param tree
	 *            the tree, which maps the relative path of each entry to the content of the file,
	 *            or to null for a directory
	 * @return the temporary directory that holds the tree
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	private Path createTree(Map<String, String> tree) throws IOException
	{
		Path directory = Files.createTempDirectory("directory-checksum");
		temporaryDirectories.add(directory);
		for (Map.Entry<String, String> entry : tree.entrySet())
		{
			Path path = directory.resolve(entry.getKey());
			if (entry.getValue() == null)
			{
				Files.createDirectories(path);
			}
			else
			{
				Files.createDirectories(path.getParent());
				Files.writeString(path, entry.getValue(), StandardCharsets.UTF_8);
			}
		}
		return directory;
	}

	/**
	 * Builds a tree from pairs of a relative path and the content of the file, or null for a
	 * directory
	 *
	 * @param pathsAndContents
	 *            the relative path and the content of each entry, one after the other
	 * @return the tree in the given order
	 */
	private static Map<String, String> tree(String... pathsAndContents)
	{
		Map<String, String> tree = new LinkedHashMap<>();
		for (int i = 0; i < pathsAndContents.length; i += 2)
		{
			tree.put(pathsAndContents[i], pathsAndContents[i + 1]);
		}
		return tree;
	}

}
