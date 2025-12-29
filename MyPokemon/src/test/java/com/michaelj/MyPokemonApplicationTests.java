package com.michaelj;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


//@SpringBootTest
class MyPokemonApplicationTests {
	@Test
	public void test() throws ParseException {
		String strDate = "2023-02-29"; // 这是你要解析的日期字符串
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // 定义日期格式
		Date date = formatter.parse(strDate);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		// 使用Calendar的add方法来添加一年
		calendar.add(Calendar.YEAR, 1);

		// 获取加一年后的日期
		Date oneYearLater = calendar.getTime();

		// 打印当前时间和加一年后的日期
		System.out.println("当前时间: " + date);
		System.out.println("加一年后的时间: " + oneYearLater);
//		System.out.println(String.format("%s账号：%s，失败", "1", null));
	}

	@Test
	public void testPrint() {
		System.out.println(String.format("%s账号：%s，失败", "1", null));
	}
}
