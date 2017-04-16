package edu.zju.reservation.test;

import edu.zju.reservation.utils.ReadFromFile;
import edu.zju.reservation.utils.WriteToFile;

public class FileTest {
    public static void main(String[] args) {

        String get = ReadFromFile.readText(FileTest.class.getResource("/")
                .getPath().replace("%20", " ")
                + "helptext.txt");
        // get += "lalala哈哈哈";
        WriteToFile.writeText(FileTest.class.getResource("/").getPath()
                .replace("%20", " ")
                + "helptext.txt", get, false);

        get = ReadFromFile.readText(FileTest.class.getResource("/").getPath()
                .replace("%20", " ")
                + "helptext.txt");

        System.out.println(get);

    }
}
