package test.mid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestException {

    private static int method() {
        try {
//            throw new FileNotFoundException();
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }

    public static void main(String[] args) {
        File file = new File("d:/asd.exe");
        try {
            new FileInputStream(file);
            Date date = new SimpleDateFormat().parse("sdfsdf");
        } catch (Throwable e) {  // 从jdk7开始支持
            e.printStackTrace();
        } finally {
            System.out.println("无论是否出现异常，都会执行");
        }
        System.out.println(method());

        try {
            int a=5/0;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("出现异常");
        }
    }
}
