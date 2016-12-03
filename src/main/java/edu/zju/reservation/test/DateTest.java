package edu.zju.reservation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		Date date = sdf.parse("07:30");

		System.out.println(date);
	}
}
