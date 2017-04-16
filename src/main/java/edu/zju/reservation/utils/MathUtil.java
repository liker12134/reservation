package edu.zju.reservation.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

/**
 * 数学运算工具类
 *
 * @author panye
 */
public class MathUtil {
    /**
     * 保留double的scale位小数，scale必须大于0
     *
     * @param data
     * @param scale
     * @return
     */
    public static double doubleScale(double data, int scale) {
        if (scale <= 0) {
            throw new RuntimeException("scale必须大于0");
        }
        String bit = "";
        for (int i = 0; i < scale; i++) {
            bit += "0";
        }
        DecimalFormat df = new DecimalFormat("#." + bit);
        String get = df.format(data);
        // System.out.println(get);
        double result = Double.parseDouble(get);
        return result;

    }

    /**
     * 保留double的2位小数
     *
     * @param data
     * @return
     */
    public static double doubleScale(double data) {
        return doubleScale(data, 2);
    }

    /**
     * MD5加密
     *
     * @param clear
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String toMD5(String clear) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] buff = md.digest(clear.getBytes());
        // 把密文转换成十六进制的字符串形式
        int j = buff.length;
        char str[] = new char[j * 2];
        int k = 0;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        for (int i = 0; i < j; i++) {
            byte byte0 = buff[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str).trim();
    }

}
