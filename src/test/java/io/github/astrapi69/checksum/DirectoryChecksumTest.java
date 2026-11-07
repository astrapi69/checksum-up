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
import static org.testng.Assert.expectThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
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
						false },
				{ "only a subdirectory, other content in it",
						tree("sub", null, "sub/a.txt", "alpha"),
						tree("sub", null, "sub/b.txt", "beta"), false },
				{ "four levels deep, other content in the deepest file",
						tree("a", null, "a/b", null, "a/b/c", null, "a/b/c/d", null,
							"a/b/c/d/e.txt", "alpha"),
						tree("a", null, "a/b", null, "a/b/c", null, "a/b/c/d", null,
							"a/b/c/d/e.txt", "omega"),
						false },
				{ "two empty directories", tree(), tree(), true },
				{ "four levels deep, same names and same content",
						tree("a", null, "a/b", null, "a/b/c", null, "a/b/c/d", null,
							"a/b/c/d/e.txt", "alpha"),
						tree("a", null, "a/b", null, "a/b/c", null, "a/b/c/d", null,
							"a/b/c/d/e.txt", "alpha"),
						true },
				{ "same tree, created in the reverse order",
						tree("a.txt", "alpha", "m", null, "m/b.txt", "beta", "z.txt", "omega"),
						tree("z.txt", "omega", "m", null, "m/b.txt", "beta", "a.txt", "alpha"),
						true },
				{ "a file beside a subdirectory, other content in the file",
						tree("a.txt", "alpha", "z", null, "z/b.txt", "beta"),
						tree("a.txt", "omega", "z", null, "z/b.txt", "beta"), false },
				{ "a file moved into a subdirectory", tree("sub", null, "a.txt", "alpha"),
						tree("sub", null, "sub/a.txt", "alpha"), false },
				{ "a subdirectory renamed", tree("one", null, "one/a.txt", "alpha"),
						tree("two", null, "two/a.txt", "alpha"), false },
				{ "an empty subdirectory more", tree("a.txt", "alpha"),
						tree("a.txt", "alpha", "empty", null), false },
				{ "an empty file and an empty directory of the same name", tree("x", ""),
						tree("x", null), false },
				{ "content moved from one file to the next", tree("a", "ab", "b", "c"),
						tree("a", "a", "b", "bc"), false } };
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
		expectThrows(AccessDeniedException.class,
			() -> new DirectoryChecksum(SHA_256).update(directory));
	}

	/**
	 * Test method for {@link DirectoryChecksum#update(Path)}: a subdirectory that cannot be listed
	 * fails the computation with the {@link IOException} of the walk, not with the
	 * {@link java.io.UncheckedIOException} that carries it
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testUpdateWithUnlistableSubdirectory() throws IOException
	{
		Path directory = createTree(tree("sub", null, "sub/a.txt", "alpha"));
		Path subdirectory = directory.resolve("sub");
		subdirectory.toFile().setReadable(false);
		try
		{
			if (Files.isReadable(subdirectory))
			{
				throw new SkipException(
					"the directory stays readable for this user, root for example");
			}
			expectThrows(AccessDeniedException.class,
				() -> new DirectoryChecksum(SHA_256).update(directory));
		}
		finally
		{
			subdirectory.toFile().setReadable(true);
		}
	}

	/**
	 * Test method for {@link DirectoryChecksum#update(Path)}: the checksum of a known tree is the
	 * one its documented form gives. The expected value was computed outside this library, with
	 * Python's hashlib over the sorted entries, each as its marker 'D' or 'F', the length of its
	 * UTF-8 relative path as four bytes big-endian, the path and, for a file, the SHA-256 digest of
	 * its content
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported
	 */
	@Test
	public void testUpdateWithKnownTree() throws IOException, NoSuchAlgorithmException
	{
		Path directory = createTree(tree("sub", null, "sub/b.txt", "beta", "a.txt", "alpha"));
		assertEquals(checksum(directory),
			"47c0522975ebe49076c9dae201c9a80c8e07e08e1b669e56a0f7775a6c2819dd");
	}

	/**
	 * Test method for {@link DirectoryChecksum#update(Path)}: the same object gives the same
	 * checksum for the same tree when it is called again
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 * @throws NoSuchAlgorithmException
	 *             Is thrown if the algorithm is not supported
	 */
	@Test
	public void testUpdateTwiceWithTheSameObject() throws IOException, NoSuchAlgorithmException
	{
		Path directory = createTree(tree("a.txt", "alpha", "sub", null, "sub/b.txt", "beta"));
		DirectoryChecksum directoryChecksum = new DirectoryChecksum(SHA_256);
		String first = directoryChecksum.update(directory);
		assertEquals(directoryChecksum.update(directory), first);
	}

	/**
	 * Test method for {@link DirectoryChecksum#update(Path)}: a path that is not a directory is
	 * refused
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testUpdateWithFile() throws IOException
	{
		Path file = createTree(tree("a.txt", "alpha")).resolve("a.txt");
		expectThrows(NotDirectoryException.class,
			() -> new DirectoryChecksum(SHA_256).update(file));
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
