package edu.zju.reservation.utils;

import edu.zju.reservation.exceptions.DataAccessException;

import java.io.*;

public class WriteToFile {

    /**
     * 写入指定的文本文件，append为true表示追加，false表示重头开始写，text是要写入的文本字符串，text为null时直接返回
     */
    public static void writeText(String filePath, String content,
                                 boolean isAppend) {
        if (content == null)
            return;
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath), "utf-8"));
            try {
                out.write(content);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * 把字节数组为写入二进制文件，数组为null时直接返回
     *
     * @param filePath
     * @param data
     */
    public static void write(String filePath, byte[] data) {
        if (data == null)
            return;
        try {
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(filePath));
            try {
                out.write(data);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
