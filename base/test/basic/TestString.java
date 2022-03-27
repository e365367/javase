package test.basic;


import character.basic.Hero;
import com.sun.xml.internal.fastinfoset.util.CharArray;

import java.util.Arrays;

public class TestString {

    public static void main(String[] args) {
        String garen = "盖伦"; // 字面值，虚拟机碰到字面值就会创建一个字符串对象
        String teemo = new String("提莫"); // 该表达式创建了两个字符串对象

        char[] chars = new char[]{'2', '占', 'd'};
        String s1 = new String(chars);
        System.out.println(s1);

        String s2 = garen + teemo;
        System.out.println(s2);

        String sentence = String.format("%s 第三 %d 方的事件%s发生看到%n", "张二", 5, "周五");
        System.out.println(sentence);

        // 练习-随机字符串
        /*  缩小随机范围：
            起始值加上0-1随机数*剩下的取值范围=起始值开始到取值末端的一个随机数。
            因为随机数取整后，小数点后部分会丢失，
            最小随机永远是0，最大随机永远取不到设定的范围末端，左闭右开
            所以要想取到随机数包含最后一位，必须+1    */
        short start = '0';
        short end = 'z' + 1;

        char[] cs = new char[5];
        for (int i = 0; i < 5; i++) {
            while (true) {
                short randomNum = (short) (Math.random() * (end - start) + start);
                if (Character.isDigit(randomNum) || Character.isLetter(randomNum)) {
                    cs[i] = (char) randomNum;
                    break;
                }
            }
        }

        String s = new String(cs);
        System.out.println(s);


        // 练习-字符串数组排序
        String[] stringArray = new String[8];
        for (int i = 0; i < 8; i++) {
            stringArray[i] = TestString.getRandomString();
        }
        TestString.stringArraySort(stringArray);
        System.out.println(Arrays.toString(stringArray));

        String sentence2 = "盖伦,在进行了连续8次击杀后,获得了超神 的称号";

        char[] cs2 = sentence2.toCharArray(); //获取对应的字符数组

        System.out.println(sentence2.length() == cs2.length);

        String s3 = "     都会发个生的拔火   罐哈迪斯有多少个          ";
        String substring = s3.substring(3);
        System.out.println(substring);
        String substring1 = s3.substring(5, 7);
        System.out.println(substring1);
        System.out.println(s3.trim());

        String s4 = "adaSAADASDfgvfd";
        System.out.println(s4);
        System.out.println(s4.toLowerCase());
        System.out.println(s4.toUpperCase());

        System.out.println(s3.indexOf("都会"));
        System.out.println(s3.lastIndexOf("个"));
        System.out.println(s3.indexOf("个"));
        System.out.println(s3.indexOf('个', 9));

        char[] chars1 = new char[]{'拔', '火'};
        CharArray cc = new CharArray(chars1, 0, chars1.length, true);
        System.out.println(s3.contains("拔火"));
        System.out.println(s3.contains(cc));

        String s5 = "决定是否接受的加速度和反的馈的及时答复";
        String temp = s5.replaceAll("的", "de");
        String temp1 = s5.replaceFirst("的", "de");
        String temp2 = s5.replace("的", "de");
        System.out.println(temp);
        System.out.println(temp1);
        System.out.println(temp2);

        String s6 = "Abc";
        String s7 = "abc";
        System.out.println((s6 == s7));
        System.out.println(s6.equalsIgnoreCase(s7));

        System.out.println("——————————————");
        System.out.println(s6.startsWith("Ab"));
        System.out.println(s6.endsWith("c"));

        StringBuffer sb = new StringBuffer(s6);
        System.out.println(sb);
        System.out.println(sb.append("sdfsdf"));

        System.out.println(sb.delete(sb.lastIndexOf("d"), sb.length()));

        System.out.println(sb.insert(sb.indexOf("c") + 1, "qqqweqes"));

        System.out.println(sb.reverse());

        System.out.println(sb.length());
        System.out.println(sb.capacity());

        String s8 = "";
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();
        long startTime, endTime;

        // 速度：StringBuilder>StringBuffer>String
        // String速度测试
        /*startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s8 += TestString.getRandomString();
        }
        endTime = System.currentTimeMillis();
        System.out.printf("%dms%n", endTime - startTime);*/

        // StringBuffer速度测试
        /*startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            stringBuffer.append(getRandomString());
        }
        endTime = System.currentTimeMillis();
        System.out.printf("%dms%n", endTime - startTime);*/

        /*try {
            startTime = System.currentTimeMillis();
            stringBuffer.append(getRandomString());
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                stringBuffer.append(stringBuffer);
            }
            endTime = System.currentTimeMillis();
            System.out.printf("%dms%n", endTime - startTime);
        }catch (Error error){
            error.printStackTrace();
        }*/

        // StringBuilder速度测试
        /*startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            stringBuilder.append(TestString.getRandomString());
        }
        endTime = System.currentTimeMillis();
        System.out.printf("%dms%n", endTime - startTime);*/

        System.out.println(s3);
        System.out.println(TestString.stringReverse(s3));

        String s9="123";
        test1(s9);
        System.out.println(s9);

        int[] aa=new int[]{1,2,3};
        test2(aa);
        System.out.println(Arrays.toString(aa));

        Hero hero=new Hero("QQ");
        test3(hero);
        System.out.println(hero);

        Hero[] heroes=new Hero[1];
        test4(heroes);
        System.out.println(heroes.length);
    }

    public static void test1(String str){
        str="25";
    }
    public static void test2(int[] a){
        a=new int[]{2,5};
//        a[0]=5;
    }
    public static void test3(Hero hero){
        hero=new Hero();
    }

    public static void test4(Hero[] heroes){
        heroes=new Hero[4];
    }


    public static String stringReverse(String string) {
        char[] charArray = string.toCharArray();
        char temp;
        for (int i = 0; i < charArray.length / 2; i++) {
            temp = charArray[i];
            charArray[i] = charArray[charArray.length - 1 - i];
            charArray[charArray.length - 1 - i] = temp;
        }
        return new String(charArray);
    }

    public static void stringArraySort(String[] stringArray) {
        int length = stringArray.length;
        // 改进版冒泡排序
        for (int i = length - 1; i >= 0; i--) { //反向遍历
            for (int j = 0; j < i; j++) { // 由于最右侧的值已经有序，不再比较，每次都减少遍历次数
                if (Character.toUpperCase(stringArray[j].toCharArray()[0]) > Character.toUpperCase(stringArray[j + 1].toCharArray()[0])) {
                    String temp = stringArray[j];
                    stringArray[j] = stringArray[j + 1];
                    stringArray[j + 1] = temp;
                }
            }
        }
    }

    public static String getRandomString() {
        // 练习-随机字符串
        // 创建一个长度是5的随机字符串，随机字符有可能是数字，大写字母或者小写字母
        /*  缩小随机范围：
            起始值加上0-1随机数*剩下的取值范围=起始值开始到取值末端的一个随机数。
            因为随机数取整后，小数点后部分会丢失，
            最小随机永远是0，最大随机永远取不到设定的范围末端，左闭右开
            所以要想取到随机数包含最后一位，必须+1    */
        short start = '0';
        short end = 'z' + 1;

        char[] cs = new char[5];
        for (int i = 0; i < 5; i++) {
            while (true) {
                short randomNum = (short) (Math.random() * (end - start) + start);
                if (Character.isDigit(randomNum) || Character.isLetter(randomNum)) {
                    cs[i] = (char) randomNum;
                    break;
                }
            }
        }

        String s = new String(cs);
        return s;
    }

}
