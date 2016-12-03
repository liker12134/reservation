package edu.zju.reservation.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.zju.reservation.exceptions.DataAccessException;

public class ReadFromFile {
	/**
	 * 把二进制文件读入字节数组，如果没有内容，字节数组为null
	 */
	public static byte[] readBinary(String filePath) {
		byte[] data = null;
		try {
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(filePath));

			try {
				data = new byte[in.available()];
				in.read(data);
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 读取指定路径文本文件
	 */
	public static String readText(String filePath) {
		StringBuilder str = new StringBuilder();
		BufferedReader in = null;

		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(
					filePath), "utf-8"));
			String s;
			try {
				while ((s = in.readLine()) != null)
					str.append(s + '\n');
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		}
		return str.toString();
	}
}
