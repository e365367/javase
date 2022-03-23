package test.basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDate {
    public static void main(String[] args) {
        Date now = new Date();
        System.out.println(now);
        Date date = new Date(5000);
        System.out.println(date);
        System.out.printf("%,d%n", now.getTime());
        System.out.printf("%,d%n", System.currentTimeMillis());
        Date date1 = new Date(0);
        System.out.println(date1);
        System.out.printf("%,d%n", date1.getTime());

        // 练习-日期
        long startTime, endTime, randomTime;
        startTime = new Date(95, 0, 1).getTime();
        endTime = new Date(96, 0, 1).getTime();
        System.out.println(new Date(endTime));
        System.out.println("——————————————————————————");
        randomTime = startTime + (long) (Math.random() * (endTime - startTime));
        System.out.println(new Date(randomTime));

        // SimpleDateFormat 日期格式化类
        //y 代表年
        //M 代表月
        //d 代表日
        //H 代表24进制的小时
        //h 代表12进制的小时
        //m 代表分钟
        //s 代表秒
        //S 代表毫秒

        // 日期转字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String str = sdf.format(now);
        System.out.println(str);
        System.out.println(sdf.format(startTime));
        System.out.println(sdf.format(endTime));

        // 字符串转日期
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

        String str1 = "2022/1/5 12:12:12";
        String str2 = "20220322";
        try {
            Date date2 = sdf1.parse(str1);
            Date date3 = sdf2.parse(str2);
            System.out.println(sdf.format(date2));
            System.out.println(sdf.format(date3));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Calendar类

        // 采用单例模式获取日历对象Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();

        Date date2 = calendar.getTime();
        System.out.println(sdf.format(date2));

        Date date3 = new Date(0);
        calendar.setTime(date3); // 把这个日历，调成日期 : 1970.1.1 08:00:00

        Date date4 = calendar.getTime();
        System.out.println(sdf.format(date4));

        // 下个月的今天
        calendar.setTime(now);
        calendar.add(Calendar.MONTH, 1);
        System.out.println(format(calendar.getTime()));

        // 去年的今天
        calendar.setTime(now);
        calendar.add(Calendar.YEAR, -1);
        System.out.println(format(calendar.getTime()));

        // 上个月的后三天
        calendar.setTime(now);
        calendar.add(Calendar.MONTH, -1);
        calendar.add(Calendar.DATE, 3);
        System.out.println(format(calendar.getTime()));

        System.out.println("——————————————————————");
        calendar.setTime(now);
        System.out.println(format(calendar.getTime()));

        Date date5=new Date((2016-1900),(6+2-1),1);
        calendar.setTime(date5);
        calendar.add(Calendar.DATE,-3);
        System.out.println(format(calendar.getTime()));

    }

    private static String str = "yyyy-MM-dd HH:mm:ss SSS";

    private static String format(Date time) {
        return new SimpleDateFormat(str).format(time);
    }
}
