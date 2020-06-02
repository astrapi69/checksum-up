/**
 * The MIT License
 * <p>
 * Copyright (C) 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.checksum;

import de.alpharogroup.checksum.api.ChecksumAlgorithm;

/**
 * The class {@link ChecksumExtensions} is a utility class for computing checksum from byte
 * arrays
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public final class ChecksumExtensions
{
	/**
	 * The constant HEXADECIMAL_CHARACTER_CLASS.
	 */
	private static final String HEXADECIMAL_CHARACTER_CLASS = "[a-fA-F0-9]";

	/**
	 * The constant REGEX_VALIDATION_MD5.
	 */
	private static final String REGEX_VALIDATION_MD5 = HEXADECIMAL_CHARACTER_CLASS + "{32}";

	/**
	 * The constant REGEX_VALIDATION_SHA1.
	 */
	private static final String REGEX_VALIDATION_SHA1 = HEXADECIMAL_CHARACTER_CLASS + "{40}";

	/**
	 * The constant REGEX_VALIDATION_SHA256
	 */
	private static final String REGEX_VALIDATION_SHA256 = HEXADECIMAL_CHARACTER_CLASS + "{64}";

	/**
	 * The constant REGEX_VALIDATION_SHA384.
	 */
	private static final String REGEX_VALIDATION_SHA384 = HEXADECIMAL_CHARACTER_CLASS + "{96}";

	/**
	 * The constant REGEX_VALIDATION_SHA512.
	 */
	private static final String REGEX_VALIDATION_SHA512 = HEXADECIMAL_CHARACTER_CLASS + "{128}";

	private ChecksumExtensions()
	{
	}

	/**
	 * Checks if the given value matches a MD5 value pattern.
	 *
	 * @param value the checksum value
	 * @return true, the given value matches a MD5 value otherwise false
	 */
	public static boolean matchesMD5(final String value)
	{
		return value.matches(REGEX_VALIDATION_MD5);
	}

	/**
	 * Checks if the given value matches a SHA1 value pattern.
	 *
	 * @param value
	 *            the checksum value
	 * @return true, the given value matches a SHA1 value otherwise false
	 */
	public static boolean matchesSHA1(final String value)
	{
		return value.matches(REGEX_VALIDATION_SHA1);
	}

	/**
	 * Checks if the given value matches a SHA384 value pattern.
	 *
	 * @param value
	 *            the checksum value
	 * @return true, the given value matches a SHA384 value otherwise false
	 */
	public static boolean matchesSHA384(final String value)
	{
		return value.matches(REGEX_VALIDATION_SHA384);
	}

	/**
	 * Checks if the given value matches a SHA256 value pattern.
	 *
	 * @param value
	 *            the checksum value
	 * @return true, the given value matches a SHA256 value otherwise false
	 */
	public static boolean matchesSHA256(final String value)
	{
		return value.matches(REGEX_VALIDATION_SHA256);
	}

	/**
	 * Checks if the given value matches a SHA512 value pattern.
	 *
	 * @param value
	 *            the checksum value
	 * @return true, the given value matches a SHA1 value otherwise false
	 */
	public static boolean matchesSHA512(final String value)
	{
		return value.matches(REGEX_VALIDATION_SHA512);
	}

	/**
	 * Resolves the checksum algorithm from the given value, if no match is found the undefined
	 * value will be returned
	 *
	 * @param value the checksum value
	 * @return the checksum algorithm from the given value, if no match is found the undefined
	 * value will be returned
	 */
	public static ChecksumAlgorithm resolveChecksumAlgorithm(final String value)
	{
		if (matchesMD5(value))
		{
			return ChecksumAlgorithm.MD5;
		}
		if (matchesSHA1(value))
		{
			return ChecksumAlgorithm.SHA_1;
		}
		if (matchesSHA256(value))
		{
			return ChecksumAlgorithm.SHA_256;
		}
		if (matchesSHA384(value))
		{
			return ChecksumAlgorithm.SHA_384;
		}
		if (matchesSHA512(value))
		{
			return ChecksumAlgorithm.SHA_512;
		}
		return ChecksumAlgorithm.UNDEFINED;
	}

}
