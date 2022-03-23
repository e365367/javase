package test.basic;

import java.util.Locale;

public class TestNumber {
    public static void main(String[] args) {
        int i = 5;
        String str = String.valueOf(i);
        System.out.println(str);
        int i2 = 8;
        Integer integer = i2;
        String str2 = integer.toString();
        System.out.println(str2);

        String string = "98712";
        int i3 = Integer.parseInt(string);
        System.out.println(i3);

        System.out.println(Math.round(5.4));
        System.out.println(Math.round(5.5));
        System.out.println(Math.random());

        int maxValue = Integer.MAX_VALUE;
        Double myE = Math.pow(1 + 1d / maxValue, maxValue);
        System.out.println(myE);
        System.out.println(Math.E);

        // 使用格式化输出
        System.out.printf("经典%n服饰 水电 %s 费缴纳%d%n","交交",1234);
        System.out.printf("%,8d%n",2500*10000000L);
        System.out.printf("%.4f%n",Math.PI);

        // 不同国家的千位分隔符
        System.out.format(Locale.FRANCE,"%,.4f%n",Math.PI*10000);
        System.out.format(Locale.US,"%,.4f%n",Math.PI*10000);
        System.out.format(Locale.UK,"%,.4f%n",Math.PI*10000);
        System.out.format(Locale.CHINA,"%,.4f%n",Math.PI*10000);
    }
}
