package cn.imexue.ec.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

/**
 * 字符串实用类
 * 
 * @author Li Jianfeng
 * @version 1.2
 * @update 2012-7-19 下午4:58:34
 */
public class StringUtil extends StringUtils {

	// Automatically generated variable: INT_39

	private static final int INT_39 = 39;

	// Automatically generated variable: INT_34

	private static final int INT_34 = 34;

	/**
	 * Utility class should not have public constructor.
	 */
	private StringUtil() {
	}

	/**
	 * Capitalizes a string, i.e. changing its first letter to uppercase.
	 * 
	 * @param str
	 *            The String that needs to be capitalized.
	 * @return The capitalized string.
	 */
	public static String capitalize(String str) {
		if (str == null || str.length() == 0)
			return str;
		else
			return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * Combines the strings values in the string array into one single string,
	 * delimited by the specified delimiter. An emtpy String is returned if the
	 * given values array is of size 0.
	 * 
	 * @param values
	 *            The strings to be combined.
	 * @param delimiter
	 *            The delimiter used to separate the different strings.
	 * @return The resultant string combined from the string array separated by
	 *         the specified delimiter. Return an emtpy String if the given
	 *         values array is of size 0.
	 */
	public static String combine(String[] values, String delimiter) {

		if (values == null) {
			throw new NullPointerException("values array is null");
		}

		if (values.length == 0) {
			return "";
		}

		StringBuffer result = new StringBuffer();

		for (int i = 1; i < values.length; i++) {
			result.append(delimiter);
			result.append(values[i]);
		}

		result.insert(0, values[0]);

		return result.toString();
	}

	/**
	 * Removes redundant spaces (the second consecutive space onwards) from a
	 * String.
	 * 
	 * @param str
	 *            The String that needs to be compacted.
	 * @return The String which has been compacted.
	 */
	public static String compact(String str) {
		if (str == null || str.length() == 0)
			return str;

		int len = str.length();
		char[] buf = new char[len];
		StringBuffer sb = new StringBuffer();
		str.getChars(0, len, buf, 0);
		int i = 0;

		while (i < len) {
			if (buf[i] != ' ') /* Found the first space */
				sb.append(buf[i++]);
			else {
				sb.append(' ');
				while (i < len && buf[i] == ' ')
					/* Skip the rest of the spaces */
					i++;
			}
		}

		return sb.toString();
	}

	/**
	 * If a string is null, return it as "".
	 * 
	 * @param str
	 *            The String that needs to be checked for null value.
	 * @return The String that is converted to appropriate string value.
	 */
	public static String deNull(String str) {
		if (str == null)
			return "";
		return str;
	}

	/**
	 * To return a string which is filled with a specified string. e.g.
	 * duplicate("*", 5) returns "*****", duplicate("OK", 3) returns "OKOKOK"
	 * repeated for given number of times
	 * 
	 * @param str
	 *            String to be repeated/duplicated
	 * @param times
	 *            Number of time the string to be repeated/duplicated
	 * @return The resulted string with <code>str</code> repeated the specified
	 *         number of times.
	 */
	public static String duplicate(String str, int times) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < times; i++) {
			result.append(str);
		}
		return (result.toString());
	}

	/**
	 * Get the count of occurrences of the character in the target string.
	 * 
	 * @param str
	 *            The String used to check for the character occurrenct count.
	 * @param ch
	 *            The character to be counted in the string.
	 * @return Number of occurrences of the character in the target string.
	 */
	public static int getCount(String str, char ch) {
		int pos;
		int count = 0;

		do {
			pos = str.indexOf(ch);

			if (pos != -1) {
				count++;

				if (pos != str.length())
					str = str.substring(pos + 1, str.length());
				else
					pos = -1;
			}

		} while (pos != -1);

		return count;
	}

	/**
	 * Checks if the length of the string is of the length specified.
	 * 
	 * @param str
	 *            The string to test for the length.
	 * @param len
	 *            The length that the string should conform to.
	 * @return A boolean value that indicates if the string is of the length
	 *         specified.
	 */
	public static boolean isLengthEqual(String str, int len) {
		if (str == null) {
			return false;
		} // if (str == null)

		return (str.length() == len) ? true : false;
	}

	/**
	 * Tests whether the specified string's length is less than or equal to the
	 * specified length.
	 * 
	 * @param str
	 *            The string to test for the length.
	 * @param len
	 *            The length that the string should conform to.
	 * @return A boolean value that indicates if the string is at most the
	 *         length specified.
	 */
	public static boolean isLengthLessThan(String str, int len) {
		if (str == null) {
			return false;
		} // if (str == null)

		return (str.length() <= len) ? true : false;
	}

	/**
	 * Returns true if the data is null or empty string.
	 * 
	 * @param data
	 *            data
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String data) {
		return data == null || data.length() == 0;
	}

	/**
	 * Returns true if the data is null or empty string array (length == 0).
	 * 
	 * @param data
	 *            data
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String[] data) {
		return data == null || data.length == 0;
	}

	/**
	 * Returns true if the data is null or blank string (with only whitespaces).
	 * 
	 * @param data
	 *            data
	 * @return boolean
	 */
	public static boolean isNullOrBlank(String data) {
		return data == null || isBlank(data);
	}

	/**
	 * Returns true if the data equals to data2 or both are null.
	 * 
	 * @param data1
	 *            data1
	 * @param data2
	 *            data2
	 * @return boolean
	 */
	public static boolean isEqual(String data1, String data2) {
		if (data1 == null && data2 == null)
			return true;
		else if (data1 != null)
			return data1.equals(data2);
		else
			return data2.equals(data1);
	}

	/**
	 * Returns true if the data equals to data2 or both are null.
	 * 
	 * @param data1
	 *            data1
	 * @param data2
	 *            data2
	 * @return boolean
	 */
	public static boolean isEqualTrim(String data1, String data2) {
		data1 = data1 == null ? data1 : data1.trim();
		data2 = data2 == null ? data2 : data2.trim();
		return isEqual(data1, data2);

	}

	/**
	 * Returns true if the data equals to data2.
	 * 
	 * @param data1
	 *            data1
	 * @param data2
	 *            data2
	 * @return boolean
	 */
	public static boolean isEqualNotNull(String data1, String data2) {
		if (data1 == null || data1.length() == 0 || data2 == null || data2.length() == 0)
			return false;
		else
			return data2.equals(data1);
	}

	/**
	 * Returns true if the data equals to data2.
	 * 
	 * @param data1
	 *            data1
	 * @param data2
	 *            data2
	 * @return boolean
	 */
	public static boolean isBiggerThanZero(int data1, int data2) {
		return data1 > 0 || data2 > 0;

	}

	/**
	 * add left space for input string.
	 * 
	 * @param data
	 *            data
	 * @return formatted string.
	 */
	public static String padZeroFront(String data) {
		if (data.length() < 2) {
			data = "0" + data;
		}
		return data;
	}

	/**
	 * Returns true if the data equals int 0.
	 * 
	 * @param data1
	 *            data1
	 * @param data2
	 *            data2
	 * @return boolean
	 */
	public static boolean isEqualZero(int data1, int data2) {

		return data1 == 0 && data2 == 0;
	}

	/**
	 * Returns true if the data equals to data2 or both are null.
	 * 
	 * @param data1
	 *            data1
	 * @param data2
	 *            data2
	 * @return boolean
	 */
	public static boolean isEqualIgnoreCase(String data1, String data2) {
		if (data1 == null && data2 == null)
			return true;
		else if (data1 != null)
			return data1.equalsIgnoreCase(data2);
		else
			return data2.equalsIgnoreCase(data1);
	}

	/**
	 * To pad the given string with a user specified character on the left up to
	 * the given length. e.g. lPad("ABCD", 10, 'X') returns "XXXXXXABCD" which
	 * has a length of 10. This method has built-in 'intelligence' to handle
	 * cases where calling method If <I>str</I> already longer than
	 * <I>length</I>, return <I>str</I> itself. tries to be funny and supply the
	 * following : - lPad("abc", 10, "123") it will return, "1231231abc"
	 * 
	 * @param str
	 *            String to be padded
	 * @param length
	 *            he required length of the resulted string.
	 * @param padString
	 *            The required padding string
	 * @return The padded string
	 */
	public static String lPad(String str, int length, String padString) {
		int lOriginal = str.length();
		int lPadStr = padString.length();
		int times2Pad = 0;
		int lPadded = 0;

		if (lOriginal >= length)
			return str;

		StringBuffer sb = new StringBuffer();
		String padded;

		times2Pad = (length - lOriginal) / lPadStr; // will give (1) if 3/2

		padded = duplicate(padString, times2Pad);
		lPadded = padded.length();
		sb.append(padded); // pad in the repetitive characters

		// if still insufficient by the modulus e.g. 30/20 is 10
		if (lOriginal + lPadded < length) {
			int more = length - (lOriginal + lPadded);

			// add in the difference which is less entire length of padStr
			sb.append(padString.substring(0, more));
		}

		sb.append(str); // pad the original string behind

		return sb.toString();
	}

	/**
	 * Pads the string with prevailing spaces.
	 * 
	 * @param str
	 *            String to be padded with spaces on the left.
	 * @param len
	 *            The number of spaces to pad to the left of the string.
	 * @return The space-padded string.
	 */
	public static String lPad(String str, int len) {
		return lPad(str, len, " ");
	}

	/**
	 * Remove all occurrences of the match in the target string.
	 * 
	 * @param str
	 *            The String to be checked and have the occurrences of the
	 *            matching String removed.
	 * @param match
	 *            The matching string.
	 * @return The resultant string with all matching string removed.
	 */
	public static String removeAllMatch(String str, String match) {

		if (str == null || match == null || str.length() == 0 || match.length() == 0) {
			return "";
		}

		StringBuffer newStr = new StringBuffer();

		int endpos = 0;
		for (int startpos = str.indexOf(match, endpos); startpos != -1; startpos = str.indexOf(match, endpos)) {
			newStr.append(str.substring(endpos, startpos));
			endpos = startpos + match.length();
		}

		newStr.append(str.substring(endpos));

		return newStr.toString();
	}

	/**
	 * Replace the occurrence of a key within the existing string with the
	 * required value.
	 * 
	 * @param str
	 *            Existing String to be replace
	 * @param key
	 *            Key within the String to be searched and replaced
	 * @param replacement
	 *            The replaced value
	 * @return The resulted string
	 */
	public static String replaceAll(String str, String key, String replacement) {

		// Split the string with the key as the delimiter
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotEmpty(str)) {
			String[] parts = StringUtil.split(str, key);
			sb.append(parts[0]);
			for (int i = 1; i < parts.length; i++) {
				sb.append(replacement + parts[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * Replaces the first substring of this string that matches the given key
	 * with the given replacement.
	 * 
	 * @param str
	 *            The String to be replaced
	 * @param key
	 *            Key within the String to be searched and replaced
	 * @param replacement
	 *            The String used to replace
	 * @return The String with the first occurence of the key value replaced.
	 */
	public static String replaceFirst(String str, String key, String replacement) {
		StringBuffer result = new StringBuffer(str);

		int pos = str.indexOf(key);

		if (pos >= 0) {
			result.replace(pos, pos + key.length(), replacement);
			// System.out.println( "result = " + result );
		}
		return result.toString();
	}

	/**
	 * Replaces the last substring of this string that matches the given key
	 * with the given replacement.
	 * 
	 * @param str
	 *            The String to be replaced
	 * @param key
	 *            Key within the String to be searched and replaced
	 * @param replacement
	 *            The String used for replacement
	 * @return The String with the last occurence of the key value replaced.
	 */
	public static String replaceLast(String str, String key, String replacement) {
		StringBuffer result = new StringBuffer(str);

		int pos = str.lastIndexOf(key);

		if (pos >= 0) {
			result.replace(pos, pos + key.length(), replacement);
		}
		return result.toString();
	}

	/**
	 * To pad the given string with spaces up to the given length. <br>
	 * e.g. rPad("ABCD", 10, ' ') returns "ABCD      " which has a length of 10.
	 * <p/>
	 * This method has built-in 'intelligence' to handle cases where calling
	 * method tries to be funny and supply the following<br>
	 * - rPad("abc", 10, "123") it will return, "abc1231231"
	 * 
	 * @param str
	 *            String to be padded
	 * @param length
	 *            The required length of the resulted string
	 * @param padString
	 *            The required padding string.
	 * @return The padded string. If str already <I>longer</I> than
	 *         <I>length</I>, return str itself.
	 */
	public static String rPad(String str, int length, String padString) {
		int lOriginal = str.length();
		int lPadStr = padString.length();
		int times2Pad = 0;
		int lPadded = 0;

		if (lOriginal >= length)
			return str;

		StringBuffer sb = new StringBuffer(str); // add the original str first
		String padded;

		times2Pad = (length - lOriginal) / lPadStr; // will give (1) if 3/2

		padded = duplicate(padString, times2Pad);
		lPadded = padded.length();
		sb.append(padded); // pad in the repetitive characters

		// if still insufficient by the modulus e.g. 30/20 is 10
		if (lOriginal + lPadded < length) {
			int more = length - (lOriginal + lPadded);

			// add in the difference which is less entire length of padStr
			sb.append(padString.substring(0, more));
		}

		return sb.toString();
	}

	/**
	 * Pads the string with following spaces.
	 * 
	 * @param str
	 *            The String to be padded with spaces on the right.
	 * @param len
	 *            The number of spaces to pad to the right of the string.
	 * @return The resultant string with spaces padded on the right.
	 */
	public static String rPad(String str, int len) {
		return rPad(str, len, " ");
	}

	/**
	 * check is the String array contain an input String.
	 * 
	 * @param array
	 *            input array
	 * @param s
	 *            input String
	 * @return boolean
	 */
	public static boolean contains(String[] array, String s) {
		return (indexOf(array, s) > -1);
	}

	/**
	 * get the index for input String from array.
	 * 
	 * @param array
	 *            array
	 * @param s
	 *            string
	 * @return index
	 */
	public static int indexOf(String[] array, String s) {
		for (int i = 0; i < array.length; i++) {
			if (s != null && s.equals(array[i]))
				return i;
		}
		return -1;
	}

	/**
	 * merge 2 input array.
	 * 
	 * @param array1
	 *            array1
	 * @param array2
	 *            array2
	 * @return merged array
	 */
	public static String[] unite(String[] array1, String[] array2) {
		String[] result = new String[(array1 == null ? 0 : array1.length) + (array2 == null ? 0 : array2.length)];
		for (int i = 0; i < array1.length; i++)
			result[i] = array1[i];
		for (int i = 0; i < array2.length; i++)
			result[array1.length + i] = array2[i];

		return result;
	}

	/**
	 * This method is used to escape SQL string.
	 * 
	 * @param oldString
	 *            oldString
	 * @return escaped string
	 */

	public static String escapeSQLString(String oldString) {
		if (oldString == null)
			return oldString;
		StringBuffer newString = new StringBuffer();
		char c;
		for (int i = 0; i < oldString.length(); i++) {
			c = oldString.charAt(i);
			switch (c) {
			case '\'':
				// if( i+1== oldString.length() || i+1< oldString.length()
				// &&
				// oldString.charAt(i+1)!='\'')
				newString.append("''");
				break;
			case '%':
				newString.append("/%");
				break;
			case '/':
				newString.append("//");
				break;
			default:
				newString.append(c);
			}
		}
		return newString.toString();
	}

	/**
	 * escape xml.
	 * 
	 * @param obj
	 *            input obj
	 * @return formated String
	 */
	public static String escapeXml(Object obj) {
		if (obj == null)
			return "";
		String data = String.valueOf(obj);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < data.length(); i++) {
			char c = data.charAt(i);
			switch (c) {
			case '>': // 62
				buffer.append("&gt;");
				break;
			case '<': // 60
				buffer.append("&lt;");
				break;
			case '&': // 38
				buffer.append("&amp;");
				break;
			case INT_34:
				buffer.append("&#034;");
				break;
			case INT_39:
				buffer.append("&#039;");
				break;
			default:
				buffer.append(c);
			}
		}
		return buffer.toString();

	}

	/**
	 * " => \" , \ => \\.
	 * 
	 * @param s
	 *            s
	 * @return formated String
	 */
	public static String escapeJSONString(String s) {
		if (s == null)
			return null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}// for
		return sb.toString();
	}

	/**
	 * filter input String without convert.
	 * 
	 * @param input
	 *            input
	 * @return String
	 */
	public static String filter(String input) {
		return StringUtil.filter(input, false);
	}

	/**
	 * filter input String with convert.
	 * 
	 * @param input
	 *            input
	 * @param convert
	 *            convert character.
	 * @return formated String
	 */
	public static String filter(String input, boolean convert) {
		if (input != null) {
			StringBuffer filtered = new StringBuffer(input.length());
			char c;
			for (int i = 0; i < input.length(); i++) {
				c = input.charAt(i);
				if (c == '<') {
					filtered.append("&lt;");
				} else if (c == '>') {
					filtered.append("&gt;");
				} else if (c == '"') {
					filtered.append("&quot;");
				} else if (c == '&') {
					filtered.append("&amp;");
				} else if (convert && c == '\n') {
					filtered.append("<br>");
				} else {
					filtered.append(c);
				}
			}
			return (filtered.toString());
		} else {
			return null;
		}
	}

	/**
	 * shift last character.
	 * 
	 * @param id
	 *            id
	 * @return value after shift.
	 */
	public static String shiftLastAlphabets(String id) {
		if (id == null || "".equals(id) || id.length() <= 1)
			return id;

		int firstNumberPosition = 0;
		int lastNumberPosition = id.length() - 1;

		while (firstNumberPosition < id.length() && Character.isLetter(id.charAt(firstNumberPosition)))
			firstNumberPosition++;
		while (lastNumberPosition >= 0 && Character.isLetter(id.charAt(lastNumberPosition)))
			lastNumberPosition--;

		if (firstNumberPosition > lastNumberPosition)
			return id;

		StringBuffer sb = new StringBuffer();
		sb.append(id.substring(0, firstNumberPosition));
		sb.append(id.substring(lastNumberPosition + 1, id.length()));
		sb.append(id.substring(firstNumberPosition, lastNumberPosition + 1));
		return sb.toString();
	}

	/**
	 * check is the String can convert to integer.
	 * 
	 * @param s
	 *            s
	 * @return boolean
	 */
	public static boolean isConvertableToInteger(String s) {
		if (isNullOrBlank(s))
			return false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!Character.isDigit(c))
				return false;
		}

		return true;
	}

	/**
	 * convert to uppercase.
	 * 
	 * @param list
	 *            input list
	 * @return converted array
	 */
	public static String[] toUpperCase(String[] list) {
		if (isNullOrEmpty(list))
			return list;
		String[] newList = new String[list.length];
		for (int i = 0; i < list.length; i++) {
			newList[i] = list[i].toUpperCase();
		}
		return newList;
	}

	/**
	 * The format method that for the String.
	 * 
	 * @param value
	 *            the string value
	 * @return the string format value, when the value is null, it returns empty
	 *         string("")
	 */
	public static String formatString(String value) {
		if (value == null) {
			value = "";
		}
		return value.trim();
	}

	/**
	 * convert empty value to null.
	 * 
	 * @param str
	 *            input String
	 * @return converted value
	 */
	public static String convertEmpty2Null(String str) {
		if (str == null || str.trim().equalsIgnoreCase(""))
			return null;
		return str;
	}

	/**
	 * format String, use for common validation.
	 * 
	 * @param sa
	 *            input string
	 * @return formated string
	 */
	public static String formatSToNPrecision(String sa) {
		// such as 123.0
		if (sa.substring(sa.length() - 2).equals(".0")) {
			sa = sa.substring(0, sa.length() - 2);
		}

		String sig = "";
		String saTmp = sa;
		// such as +/-123
		if ((sa.charAt(0) == '-') || (sa.charAt(0) == '+')) {
			sig += sa.charAt(0);
			saTmp = sa.substring(1);
		}

		int index = saTmp.indexOf("E");
		// such as 123
		if (index < 0) {
			String result = sa;
			// such as 0.123
			if (result.indexOf("0.") == 0) {
				// such as 0.123000
				while (result.charAt(result.length() - 1) == '0') {
					result = result.substring(0, result.length() - 1);
				}
			}
			return result;
		} else {
			// such as 123E9
			String si = saTmp.substring(index + 1);
			int ii = Integer.parseInt(si);

			// such as E9
			if (ii > 0) {
				int indexDot = saTmp.indexOf(".");
				// such as 123E9
				if (indexDot < 0) {
					String trailer = "";
					for (int i = 0; i < ii; ++i) {
						trailer += "0";
					}
					return sig + saTmp.substring(0, index) + trailer;
				} else {
					// such as 1.23E9
					int countCen = index - indexDot - 1;
					// such as 1.23E3
					if (ii - countCen >= 0) {
						String trailer = "";
						for (int i = 0; i < ii - countCen; ++i) {
							trailer += "0";
						}
						return sig + saTmp.substring(0, indexDot) + saTmp.substring(indexDot + 1, index) + trailer;
					} else {
						// such as 1.23E1
						return sig + saTmp.substring(0, indexDot) + saTmp.substring(indexDot + 1, indexDot + 1 + ii) + "." + saTmp.substring(indexDot + 1 + ii, index);
					}
				}

			} else if (ii < 0) {
				// such as E-9
				int indexDot = saTmp.indexOf(".");
				// such as 123E-9
				if (indexDot < 0) {
					// such as 123E-1
					if (index + ii > 0) {
						return sig + saTmp.substring(0, index + ii) + "." + saTmp.substring(index + ii, index);
					} else {
						// such as 123E-4
						int countCen = 0 - (index + ii);
						String header = "0.";
						for (int i = 0; i < countCen; ++i) {
							header += "0";
						}
						return sig + header + saTmp.substring(0, index);
					}
				} else {
					// such as 1.23E-9
					// such as 12.3E-1
					if (indexDot + ii > 0) {
						return sig + saTmp.substring(0, indexDot + ii) + "." + saTmp.substring(indexDot + ii, indexDot) + saTmp.substring(indexDot + 1, index);
					} else {
						// such as 12.3E-4
						int countCen = 0 - (indexDot + ii);
						String header = "0.";
						for (int i = 0; i < countCen; ++i) {
							header += "0";
						}
						return sig + header + saTmp.substring(0, indexDot) + saTmp.substring(indexDot + 1, index);
					}
				}
			} else {
				// such as E0
				return "";
			}
		}
	}

	/**
	 * check the input is money or not.
	 * 
	 * @param str
	 *            input string
	 * @return boolean
	 */
	public static boolean isMoney(String str) {
		PatternCompiler compiler = new Perl5Compiler();
		Pattern pattern = null;
		PatternMatcher matcher = new Perl5Matcher();
		try {
			pattern = compiler.compile("^(\\-?|\\+?)((\\d{1,3}((,\\d{3})*))|(\\d+))((\\.\\d{1,2}){0,1})$");
		} catch (MalformedPatternException e) {
			e.printStackTrace();
		}
		return matcher.matches(str, pattern);
	}

	public static boolean isEmail(String str) {
		PatternCompiler compiler = new Perl5Compiler();
		Pattern pattern = null;
		PatternMatcher matcher = new Perl5Matcher();
		try {
			pattern = compiler.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		} catch (MalformedPatternException e) {
			e.printStackTrace();
		}
		return matcher.matches(str, pattern);
	}
	
	public static boolean isMobile(String str) {
		PatternCompiler compiler = new Perl5Compiler();
		Pattern pattern = null;
		PatternMatcher matcher = new Perl5Matcher();
		try {
			pattern = compiler.compile("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+[0-9]{8})$");
			
		} catch (MalformedPatternException e) {
			e.printStackTrace();
		}
		return matcher.matches(str, pattern);
	}

	public static boolean isPassword(String str) {
		PatternCompiler compiler = new Perl5Compiler();
		Pattern pattern = null;
		PatternMatcher matcher = new Perl5Matcher();
		try {
			pattern = compiler.compile("^[a-zA-Z0-9 -=_+|~!@#$%^&*;:<>?`\\/,.\"]{6,255}$");
		} catch (MalformedPatternException e) {
			e.printStackTrace();
		}
		return matcher.matches(str, pattern);
	}

	public static boolean isLoginId(String str) {
		PatternCompiler compiler = new Perl5Compiler();
		Pattern pattern = null;
		PatternMatcher matcher = new Perl5Matcher();
		try {
			pattern = compiler.compile("^[a-zA-Z_]\\w{3,63}$");
		} catch (MalformedPatternException e) {
			e.printStackTrace();
		}
		return matcher.matches(str, pattern);
	}

	public static String urlEncode(String str) {
		if (str == null)
			return null;

		StringBuffer tmp = new StringBuffer();

		for (int i = 0; i < str.length(); ++i) {
			char a = str.charAt(i);
			if (((a < ':') && (a > '/')) || ((a < '[') && (a > '@')) || ((a < '{') && (a > '`')) || (a == '_'))
				tmp.append(a);
			else if (a < '\16')
				tmp.append("%0" + Integer.toHexString(a));
			else
				tmp.append("%" + Integer.toHexString(a));
		}

		return tmp.toString();
	}

	/**
	 * Return the dashed string "-" for Report when the data string is empty, or
	 * return data string.
	 * 
	 * @param data
	 *            the data string
	 * @return String
	 */
	public static String returnDashedWhenStrIsEmpty(String data) {
		return StringUtil.isNullOrBlank(data) ? "-" : data;
	}

	public static String covertHtmlStr(String source) {
		if (source == null)
			return null;

		return source.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("'", "&apos;").replaceAll("\"", "&quote;").replaceAll(" ", "&nbsp;").replaceAll("\n", "<br>");
	}

	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	public static String getRandomStringIgnoreSensitive(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

//	public static void main(String[] args) {
//		System.out.println(md5("123"));
//		String str = "'%_-ddddd";
//		System.out.println(StringEscapeUtils.escapeSql(str));
	// for(int i=0;i<30;i++){
	// System.out.println(StringUtil.getRandomString(32));
	// }
	// System.out.println(StringUtil.isLoginId("abc@c"));
	// System.out.println(StringUtil.isLoginId("abcd"));
	// System.out.println(StringUtil.isLoginId("abcde"));
	// System.out.println(StringUtil.isLoginId("1ab"));
	// System.out.println(StringUtil.isLoginId("1abc"));
	// System.out.println(StringUtil.isLoginId("abc:"));
	// System.out.println(StringUtil.isLoginId("Abc1"));
	// System.out.println(StringUtil.isLoginId("a123"));
	// System.out.println(StringUtil.isLoginId("a12c"));
	// System.out.println(StringUtil.isLoginId("_abc"));
	// System.out.println(StringUtil.isLoginId("ab_c"));
	// System.out.println("-----------------");
	// System.out.println(StringUtil.isPassword("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234"));
	// System.out.println(StringUtil.isPassword("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345"));
	// System.out.println(StringUtil.isPassword("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456"));
	// System.out.println(StringUtil.isPassword("abcdef"));
	// System.out.println(StringUtil.isPassword("abc123"));
	// System.out.println(StringUtil.isPassword("cc===="));
	// System.out.println(StringUtil.isPassword("Abc1"));
	// System.out.println(StringUtil.isPassword("a123 "));
	// System.out.println(StringUtil.isPassword("a12c"));
	// System.out.println(StringUtil.isPassword("_abc?"));
	// System.out.println(StringUtil.isPassword("ab_c`"));

//	}

	public static boolean validateMobile(String mobile) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+[0-9]{8})$");
		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();
	}

	public static boolean validateIdCardNo(String idCardNo) {
		if(StringUtil.isNotEmpty(idCardNo)){
			java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)");
			Matcher matcher = pattern.matcher(idCardNo);
			return matcher.matches();
		}
		return false;
	}
	/**
	 * Used building output as Hex
	 */
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * Saas对字符串进行MD5加密
	 * 
	 * @param text
	 *            明文
	 * 
	 * @return 密文
	 */
	public static String md5(String text) {

		MessageDigest msgDigest = null;

		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("System doesn't support MD5 algorithm.");
		}

		try {
			msgDigest.update(text.getBytes("utf-8"));

		} catch (UnsupportedEncodingException e) {

			throw new IllegalStateException("System doesn't support your  EncodingException.");

		}

		byte[] bytes = msgDigest.digest();

		String md5Str = new String(encodeHex(bytes));

		return md5Str;
	}

	public static char[] encodeHex(byte[] data) {

		int l = data.length;

		char[] out = new char[l << 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}

		return out;
	}

	/**
	 * 拆分登录账号
	 * 
	 * @author Dai Dong
	 * @param loginId
	 * @return
	 * @return String[]
	 */
	public static String[] splitAt(String loginId) {
		if (StringUtil.isNotEmpty(loginId)) {
			return loginId.split("@");
		}
		return null;
	}

	public static String MD5(String inStr) {
		try {
			MessageDigest md5 = null;
			md5 = MessageDigest.getInstance("MD5");

			char[] charArray = inStr.toCharArray();
			byte[] byteArray = new byte[charArray.length];

			for (int i = 0; i < charArray.length; i++)
				byteArray[i] = (byte) charArray[i];

			byte[] md5Bytes = md5.digest(byteArray);

			StringBuffer hexValue = new StringBuffer();

			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}

			return hexValue.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	// 截取字节
	public static String split(String str, int byteNum) {
		// 将字符串转换为字节数组
		byte array[] = str.getBytes();
		// 汉字字节长度
		int k = 0;
		int cnByteNum = 0;
		// 累计汉字字节数
		for (int i = 0; i < byteNum; i++) {
			if (array[i] < 0) {// 汉字字节
				++cnByteNum;
			}
			if (array[i] >= 0 && array[i] <= 9) {
				k++;
			}
			if (array[i] >= 65 && array[i] <= 128) {
				k++;
			}
		}
		// 如果汉字字节长度不是2的倍数，则舍去一个字节
		int result = (cnByteNum % 2 == 0) ? byteNum : byteNum - 1;

		return str.substring(0, result / 2 + k);
	}

	public static String delHTMLTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		java.util.regex.Pattern p_script = java.util.regex.Pattern.compile(regEx_script, java.util.regex.Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		java.util.regex.Pattern p_style = java.util.regex.Pattern.compile(regEx_style, java.util.regex.Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		java.util.regex.Pattern p_html = java.util.regex.Pattern.compile(regEx_html, java.util.regex.Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		return htmlStr.trim(); // 返回文本字符串
	}

	/**
	 * 对传入的HTML格式的字符串进行解码，如包含空格、&、<、>、'、"等字符 在文字中展示的需要解码，在input或textarea中一般不需要
	 * 
	 * @param html
	 *            特殊的html字符串
	 * @return 解码后的字符串
	 */
	public static String decode(String html) {
		if (StringUtil.isNotEmpty(html)) {
			return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;");
		} else {
			return "";
		}
	}

	/**
	 * 对传入的HTML格式的字符串进行解码，包括<script>,</script>等字符
	 * 
	 * @param html
	 *            特殊的html字符串
	 * @return 解码后的字符串
	 */
	public static String decodeJSCode(String html) {
		return html.replaceAll("<script>", "&lt;script&gt;").replaceAll("</script>", "/&lt;script&gt;");
	}

	public static String decodeHref(String html) {
		if (StringUtil.isNotEmpty(html)) {
			if (html.indexOf("</a>") == -1) {
				return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;");
			} else {
				return html;
			}
		} else {
			return "";
		}
	}

	public static String toString(Object obj) {
		return obj == null ? null : obj.toString();
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	public static String byteArrayToHexString(byte[] byteArray) {
		String result = "";
		for (byte ba : byteArray) {
			result += byteToHexString(ba);
		}
		return result;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	public static String byteToHexString(byte mByte) {
		char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] charArray = new char[2];
		charArray[0] = digit[(mByte >>> 4) & 0X0F];
		charArray[1] = digit[mByte & 0X0F];
		return new String(charArray);
	}
	
	public static String getRandomDigit(int length) { // length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static String getRandomStringIngnoreCase(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 判断是否为excel格式
	 *@param 
	 *@return 
	 */
	public static boolean isExcelExt(String originalFilename) {
		String prefix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		return "xls".equalsIgnoreCase(prefix) ||"xlsx".equalsIgnoreCase(prefix);
	}
	
	public static boolean isImageExt(String originalFilename) {
		String prefix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		return "jpg".equalsIgnoreCase(prefix) ||"jpeg".equalsIgnoreCase(prefix)
				|| "png".equalsIgnoreCase(prefix) || "gif".equalsIgnoreCase(prefix)
				|| "bmp".equalsIgnoreCase(prefix);
	}

	/**
	 * 格式化货币，如1,000,000.00
	 * @param statAmount
	 * @return
	 */
	public static String formatCurrency(BigDecimal amount) {
		NumberFormat nf = new DecimalFormat("￥,###.##");
		return nf.format(amount);
	}
	
	/**
	 * 驼峰转换
	 * @param str
	 * @return
	 */
	public static String humpToLine(String str){  
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();  
    }  
	
	public static void main(String[] args) {
		System.out.println(humpToLine("abbc"));
	}
	
}
