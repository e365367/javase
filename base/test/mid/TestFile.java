package test.mid;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class TestFile {
    public static void main(String[] args) throws IOException {
        File file = new File("d:/LOLFolder");
        System.out.println(file.getAbsoluteFile());
        File file1 = new File("LOL.exe");
        System.out.println(file1.getAbsoluteFile());
        File file2 = new File(file, "LOL.exe");
        System.out.println(file2.getAbsoluteFile());
        System.out.println("————————————————");
        System.out.println(file2);
        System.out.println(file2.exists());
        System.out.println(file2.isDirectory());
        System.out.println(file.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file2.length());

        long time = file2.lastModified();
        Date date = new Date(time);
        System.out.println(date);
        File file3 = new File("d:/LOLFolder/DOTA.exe");

//        file2.renameTo(file3);
        System.out.println(file2);

        // 字符串数组
        System.out.println(Arrays.toString(file.list()));

        // 文件数组
        System.out.println(Arrays.toString(file.listFiles()));
        System.out.println(file2);
        System.out.println("22222222222222");
        System.out.println(file2.getParent());

        File file4 = new File("d:/LOLFolder/aasss/skin/DOTA.exe");

        // 创建文件夹，如果父文件夹skin不存在，创建就无效
//        System.out.println(file4.mkdir());

        // 创建文件夹，如果父文件skin不存在，就会创建父文件
//        System.out.println(file4.mkdirs());

        // 所以创建一个空文件之前，通常都会创建父目录
        System.out.println(file4.getParentFile().mkdirs());

        // 创建一个空文件,如果父文件夹skin不存在，就会抛出异常
        System.out.println(file4.createNewFile());

        System.out.println(Arrays.toString(file.listRoots()));

//        System.out.println(file4.delete());

        // JVM结束的时候，刪除文件，常用于临时文件的删除
        file4.deleteOnExit();

        // 创建文件
//        File file5 = new File("c:/windows");
        File file5 = new File("D:\\Program Files (x86)");

        // 初始化min
        File[] files = file5.listFiles();
        for (File file6 : files) {
            if (file6.length() > 0) {
                min = file6;
                break;
            }
        }

        // 遍历文件夹
        /*for (File file6 : files) {
            if (file6.length() > max.length()) {
                max = file6;
            } else if ((file6.length() < min.length()) && file6.length() != 0) {
                min = file6;
            }
        }*/

        // 遍历子文件夹
        lookUpSubFile(file5);

        System.out.println(max);
        System.out.println(max.length());
        System.out.println(min);
        System.out.println(min.length());
    }

    private static File max = new File(""), min = new File("");

    public static void lookUpSubFile(File file) {
        if (file==null||file.length()==0)return;
        if (file.isFile()){ // 如果file是文件
            if (file.length()>max.length()){  //进行长度判断
                max=file;
            }else if (file.length()<min.length()&&file.length()!=0){
                min=file;
            }
            return;
        }
        try {


            File[] files = file.listFiles(); // file是文件夹
            if(files==null||file.getParent().equals("c:\\windows\\servicing"))return;
            System.out.println(file);
            for (int i = 0; i <files.length ; i++) {
                lookUpSubFile(files[i]);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(file);
            System.out.println(file.length());
            System.out.println(Arrays.toString(file.listFiles()));
        }
    }
}