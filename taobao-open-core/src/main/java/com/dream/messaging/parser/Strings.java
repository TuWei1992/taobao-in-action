
package com.dream.messaging.parser;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class Strings.
 * 
 * @author anders liang
 */


public class Strings {
	
	/**
	 * return the string filled with specified char.
	 * 
	 * @param src the src
	 * @param len the len
	 * 
	 * @return string
	 */
	public static String fill(char src, int len) {
		return Strings.fill(String.valueOf(src), len);
	}

	/**
	 * Fill.
	 * 
	 * @param src the src
	 * @param times the times
	 * 
	 * @return the string
	 */
	public static String fill(String src, int times) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < times; i++) {
			sb.append(src);
		}
		return sb.toString();
	}

	/**
	 * get the index of repeated n times, if the gived times is larger than real
	 * repeated times,then retrun the index of last times appeared if the given
	 * times samll than 1 or the regex can not be found then return -1.
	 * 
	 * @param source the source
	 * @param regex the regex
	 * @param beginIndex the begin index
	 * @param times the times
	 * 
	 * @return index
	 */
	public static int getIndexOf(String source, String regex, int beginIndex,
			int times) {
		if (times < 1) {
			return -1;
		}
		int index = source.indexOf(regex, beginIndex);
		if (index < 0) {
			return -1;
		}
		for (int i = 0; i < times - 1; i++) {
			int preIndex = index;
			index = source.indexOf(regex, index + regex.length());
			if (index < 0) {
				return preIndex;
			}
		}
		return index;
	}

	/**
	 * Replace all.
	 * 
	 * @param source the source
	 * @param regex the regex
	 * @param replacement the replacement
	 * 
	 * @return the string
	 */
	public static String replaceAll(String source, String regex,
			String replacement) {
		if (regex.equals(replacement)) {
			return source;
		}
		int index = source.indexOf(regex);
		String result = "";
		while (index >= 0) {
			String pre = source.substring(0, index);
			String suffix = source.substring(index + regex.length());
			result += pre + replacement;
			source = suffix;
			index = source.indexOf(regex, 0);
		}
		;
		return result + source;
	}

	/**
	 * Replace esc.
	 * 
	 * @param src the src
	 * 
	 * @return the string
	 */
	public static String replaceESC(String src) {
		src = Strings.replaceAll(src, "\\r", "\r");
		src = Strings.replaceAll(src, "\\n", "\n");
		src = Strings.replaceAll(src, "\\t", "\t");
		return src;
	}

	/**
	 * Replace first.
	 * 
	 * @param source the source
	 * @param regex the regex
	 * @param replacement the replacement
	 * 
	 * @return the string
	 */
	public static String replaceFirst(String source, String regex,
			String replacement) {
		if (regex.equals(replacement)) {
			return source;
		}
		int index = source.indexOf(regex);
		if (index >= 0) {
			String pre = source.substring(0, index);
			String suffix = source.substring(index + regex.length());
			source = pre + replacement + suffix;
		}
		return source;
	}

	/**
	 * Split the source by regex.
	 * 
	 * @param source the source
	 * @param regex the regex
	 * 
	 * @return the string[]
	 */
	public static String[] split(String source, String regex) {
		List<String> al = new ArrayList<String>();
		int index = source.indexOf(regex);
		while (index >= 0) {
			String pre = source.substring(0, index);
			al.add(pre);
			source = source.substring(index + regex.length());
			index = source.indexOf(regex);
		}
		if (!source.equals("")) {
			al.add(source);
		}
		String[] strings = new String[al.size()];
		for (int i = 0; i < al.size(); i++) {
			strings[i] = al.get(i);
		}
		return strings;
	}
	
	public static String getStringNoNull(Object obj) {
		if (obj == null)
			return "";
		return obj.toString();
	}
}