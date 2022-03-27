package test.mid;

import character.basic.Hero;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestStream {
    public static void main(String[] args) throws IOException {
        File file = new File("d:/LOLFolder/DOTA.exe");
        File file1 = new File("d:/LOLFolder/123.txt");
        try {
            FileInputStream fis = new FileInputStream(file1);
            byte[] all = new byte[(int) file1.length()];
            fis.read(all);
            String str = new String(all);
            System.out.println(str);
            System.out.println(Arrays.toString(all));

            // 每次使用完流，都应该关闭
            fis.close();

            byte[] data = new byte[]{'a', 'k', '4', '7'};
            FileOutputStream fos = new FileOutputStream(file1);
            fos.write(data);
            // 关闭输出流
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 练习-拆分文件
        File file2 = new File("d:/LOLFolder/test.jpg");
        FileInputStream fileInputStream = new FileInputStream(file2);
        byte[] all = new byte[(int) file2.length()];
        fileInputStream.read(all);
        fileInputStream.close();

        long num = file2.length() / (1024 * 100) + 1;
        System.out.println(num);

        byte[] bytes = {};
        for (int i = 0; i < num; i++) {
            File file3 = new File("d:/LOLFolder/test-" + i + ".jpg");
            if (i == num - 1) {
                bytes = Arrays.copyOfRange(all, i * 1024 * 100, all.length);
            } else {
                bytes = Arrays.copyOfRange(all, i * 1024 * 100, (i + 1) * 1024 * 100);
            }
            System.out.println(i);
            FileOutputStream fileOutputStream = new FileOutputStream(file3);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        }

        // 练习-合并文件
        File file3 = new File("d:/LOLFolder/test-new.jpg");
        byte[] bytes_all = new byte[(int) file2.length()];
        for (int i = 0; i < 4; i++) {
            File file4 = new File("d:/LOLFolder/test-" + i + ".jpg"); // 一个分文件对象
            FileInputStream fis2 = new FileInputStream(file4);
            byte[] bytes2 = new byte[(int) file4.length()];
            // 将该分文件的字节码输入到bytes2中
            fis2.read(bytes2);
            fis2.close();
            // 将bytes2中的字节码复制到bytes_all中
            System.arraycopy(bytes2, 0, bytes_all, i * 1024 * 100, bytes2.length);
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file3)) {  //从1.7开始支持
            fileOutputStream.write(bytes_all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File file4 = new File("d:/LOLFolder/456.txt");
        FileInputStream fis1 = new FileInputStream(file4);
        byte[] bytes1 = new byte[(int) file4.length()];
        fis1.read(bytes1);
        fis1.close();
        System.out.println(Arrays.toString(bytes1));

        for (byte b : bytes1) {
            int i = b & 0x000000ff; // 只取16进制的后两位
            System.out.println(Integer.toHexString(i));
        }
        String str = new String(bytes1, "UTF-8");
        System.out.println(str);
        System.out.println("——————————————————————");

        // FileReader使用默认编码方式为UTF-8,识别GBK文件file5，中文变为乱码
        System.out.println(Charset.defaultCharset());
        File file5 = new File("d:/LOLFolder/abc.txt");
        try (FileReader fileReader = new FileReader(file5)) {
            char[] chars = new char[(int) file5.length()];
            fileReader.read(chars);
            System.out.println(new String(chars).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FileReader不能手动设置编码，为了使用其他的编码方式，只能使用InputStreamReader来代替
        //并且使用new InputStreamReader(new FileInputStream(f),Charset.forName("UTF-8")); 这样的形式
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file5), Charset.forName("GBK"))) {
            char[] chars = new char[(int) file5.length()];
            isr.read(chars);
            System.out.println(new String(chars).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 练习-数字对应的中文: E5 B1 8C 这3个十六进制对应UTF-8编码的汉字
        bytes = new byte[]{(byte) 0xe5, (byte) 0xb1, (byte) 0x8c};
        System.out.println(str = new String(bytes, "UTF-8"));

        // 找出字符串str对应UTF-8编码的十六进制数字
        str = "啊这";
        byte[] bytes2 = str.getBytes("GBK");
        for (int i = 0; i < bytes2.length; i++) {
            System.out.println(Integer.toHexString(bytes2[i] & 0x000000ff));
        }
        System.out.println("KK");
        // 练习-移除BOM
        try (InputStreamReader isr = new InputStreamReader
                (new FileInputStream(new File("d:/LOLFolder/aa.txt")), Charset.forName("UTF-8"))) {
            char[] chars = new char[20];
            isr.read(chars);
            System.out.println(new String(chars).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class TestStream_new {
        public static void main(String[] args) {
            File file = new File("d:/LOLFolder/123.txt");
            File file1 = new File("d:/LOLFolder/abc.txt");
            File file2 = new File("d:/LOLFolder/HelloWorld.java");
            File file3 = new File("d:/LOLFolder/HelloWorld_encoded.java");
            File file4 = new File("d:/LOLFolder/HelloWorld_decoded.java");

            // 加密
            encodeFile(file2, file3);

            // 解密
            decodeFile(file3, file4);

            try (FileReader fr = new FileReader(file)) {
                char[] chars = new char[(int) file.length()];
                fr.read(chars);
                System.out.println(Arrays.toString(chars));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try (FileWriter fw = new FileWriter(file1)) {
                String str = "abcde123456正打算";
                char[] chars = str.toCharArray();
                fw.write(chars);
                System.out.println((int) chars[chars.length - 2]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Character.isLetter('啊'));
            // 缓存流读数据
            file = new File("d:/LOLFolder/qq.txt");

            // 创建文件字符流
            // 缓存流必须建立在一个存在的流的基础上
            try (FileReader fileReader = new FileReader(file);
                 BufferedReader br = new BufferedReader(fileReader);
            ) {
                while (true) {
                    String line = br.readLine();
                    if (null == line) {
                        break;
                    }
                    System.out.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // PrintWriter 缓存字符输出流， 可以一次写出一行数据
            File file5 = new File("d:/LOLFolder/123.txt");

            try (
                    // 创建文件字符流
                    FileWriter fw = new FileWriter(file5);
                    // 缓存流必须建立在一个存在的流的基础上
                    PrintWriter pw = new PrintWriter(fw);
            ) {
                // 会覆盖原有数据
                pw.println("0324");

                //强制把缓存中的数据写入硬盘，无论缓存是否已满
                pw.flush();

                pw.println("12234");
                pw.println("0123士大夫");
            } catch (Exception e) {
                e.printStackTrace();
            }


            // 练习-移除注释
            File file6 = new File("D:\\LOLFolder\\removeComments.txt");

            // 读取文件
            StringBuilder lines = new StringBuilder("");
            try (FileReader fr = new FileReader(file6);
                 BufferedReader br = new BufferedReader(fr);
            ) {
                String line;
                while (null != (line = br.readLine())) {

                    // 如果该行去空格后不以“//”开头，则添加到lines
                    if (!line.trim().startsWith("//")) {
                        lines.append(line).append(System.getProperty("line.separator"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(lines);
            }
            // 将去除注释代码后的文本写入
            File file7 = new File("D:\\LOLFolder\\newCode.txt");
            try (FileWriter fw = new FileWriter(file7);
                 PrintWriter pw = new PrintWriter(fw);
            ) {
                pw.write(lines.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 数据流  必须顺序写入和顺序读取！！
            // 练习-向文件中写入两个数字，然后把这两个数字分别读取出来

            // 数据流写入
            try (FileOutputStream fos = new FileOutputStream(file3);
                 DataOutputStream dos = new DataOutputStream(fos);
            ) {
                dos.writeInt(15);
                dos.writeDouble(5.25);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 数据流读取
            try (
                    FileInputStream fis = new FileInputStream(file3);
                    DataInputStream dis = new DataInputStream(fis);
            ) {
                System.out.println(dis.readInt());
                System.out.println(dis.readDouble());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(file3);

            // 对象流
            // 创建一个Hero对象，把该对象保存在文件上，该类必须实现Serializable接口
            Hero hero = new Hero("garen");
            hero.setHp(1500);
            hero.setArmor(500);
            hero.setId(999999);
            // 一个用来保存对象的文件
            File file8 = new File(file1.getParent() + "\\garen.lol");


            try (FileOutputStream fos = new FileOutputStream(file8);
                 FileInputStream fis = new FileInputStream(file8);

                 // 对象输出流，用来保存对象到文件
                 ObjectOutputStream oos = new ObjectOutputStream(fos);
                 // 对象输入流，用来读取文件中的对象
                 ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                oos.writeObject(hero);
                Hero heroRead = (Hero) ois.readObject();
                System.out.println(heroRead);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 练习-序列化数组
            // 一个用来保存对象的文件
            File file9 = new File(file1.getParent() + "\\heroes.lol"); // "\\"或者"/"
            System.out.println(file9);
            Hero[] heroes = new Hero[10];
            for (int i = 0; i < heroes.length; i++) {
                hero = new Hero("garen");
                hero.setId(i);
                hero.setHp(324);
                heroes[i] = hero;
            }
            // 对象的保存与读取
            try (FileOutputStream fos = new FileOutputStream(file9);
                 FileInputStream fis = new FileInputStream(file9);

                 // 对象输出流，用来保存对象到文件
                 ObjectOutputStream oos = new ObjectOutputStream(fos);
                 // 对象输入流，用来读取文件中的对象
                 ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                oos.writeObject(heroes);
                Hero[] heroesRead = (Hero[]) ois.readObject();
                System.out.println(Arrays.toString(heroesRead));
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 练习-自动创建类
          /*  String className;
            String type;
            String property;
            String Uproperty;

            // 读取参数
            Scanner scanner = new Scanner(System.in);
            className = scanner.nextLine();
            type = scanner.nextLine();
            property = scanner.nextLine();
            char[] chars = property.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            Uproperty = new String(chars);

            File file10 = new File(file1.getParent() + "/model.txt");
            File file11 = new File(file1.getParent() + "/myClass.java");

            try (FileReader fr = new FileReader(file10);
                 BufferedReader br = new BufferedReader(fr);
                 FileWriter fw = new FileWriter(file11);
                 PrintWriter pw = new PrintWriter(fw);
            ) {

                // 逐行读取model.txt的文本内容
                String line;
                while (null != (line = br.readLine())) {
                    line = line.replaceAll("@class@", className);
                    line = line.replaceAll("@type@", type);
                    line = line.replaceAll("@property@", property);
                    line = line.replaceAll("@Uproperty@", Uproperty);
                    pw.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            System.out.println("——————————————————————————");
            // 练习-复制文件
            File sFile = new File(file2.getAbsolutePath());
            File dFile = new File(file2.getParent() + "/dFile" +
                    sFile.getAbsolutePath().substring(sFile.getAbsolutePath().lastIndexOf('.')));
            copyFile(sFile.getAbsolutePath(), dFile.getAbsolutePath());
            System.out.println(sFile);
            System.out.println(sFile.getName());

            // 练习-复制文件夹
            File sFolder = new File(file1.getParent());
            File dFolder = new File("d:/java/ioFiles");
            copyFolder(sFolder.getAbsolutePath(), dFolder.getAbsolutePath());

            //查找文件内容
            FILE_TYPE = ".txt";
            File searchFolder = new File("D:\\下载\\文档");
            String searchString = "abc";
            // 练习-查找文件内容
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    long statTime1 = System.currentTimeMillis();
                    search(searchFolder, searchString);
                    long endTime1 = System.currentTimeMillis();
                    System.out.println("单线程花费" + (endTime1 - statTime1) + "ms");
                }

            };
            t1.start();

            // 练习-同步查找文件内容
            Thread t2 = new Thread() {
                @Override
                public void run() {
                    long statTime2 = System.currentTimeMillis();
                    multipleThreadSearch(searchFolder, searchString);
                    long endTime2 = System.currentTimeMillis();
                    System.out.println("多线程花费" + (endTime2 - statTime2) + "ms");
                }

            };
            t2.start();

            // 练习-借助线程池同步查找文件内容
            long statTime3 = System.currentTimeMillis();
            multipleThreadSearchByThreadPool(searchFolder, searchString);
            long endTime3 = System.currentTimeMillis();
            System.out.println("线程池花费" + (endTime3 - statTime3) + "ms");

        }
    }

    // 要查找的文件类型（例如".java"）
    public static String FILE_TYPE;

    // 练习-借助线程池同步查找文件内容
    // 初始化线程池
    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 15, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void multipleThreadSearchByThreadPool(File folder, String search) {
        File[] files = folder.listFiles();
        if (files == null) return;
        for (File file : files) {
            // 如果是所找类型的文件
            if (file.isFile() && (file.getName().lastIndexOf('.')) != -1
                    && file.getName().substring(file.getName().lastIndexOf('.')).equals(FILE_TYPE)) {
                // 创建任务，使用线程池执行任务
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try (FileReader fr = new FileReader(file);
                             BufferedReader br = new BufferedReader(fr);
                        ) {
                            String line;
                            while (null != (line = br.readLine())) {
                                if (line.contains(search)) {
                                    System.out.println("找到目标字符串(线程池):" + search + "，在文件:" + file.getAbsolutePath());
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
            // 如果是文件夹
            if (file.isDirectory()) {
                multipleThreadSearchByThreadPool(file, search);
            }
        }
    }

    // 练习-同步查找文件内容 (多线程)
    public static void multipleThreadSearch(File folder, String search) {
        File[] files = folder.listFiles();
        if (files == null) return;
        for (File file : files) {
            // 如果是所找类型的文件
            if (file.isFile() && (file.getName().lastIndexOf('.')) != -1
                    && file.getName().substring(file.getName().lastIndexOf('.')).equals(FILE_TYPE)) {
                // 使用匿名类启动新线程，查找文件内容
                Thread searchThread = new Thread() {
                    @Override
                    public void run() {
                        try (FileReader fr = new FileReader(file);
                             BufferedReader br = new BufferedReader(fr);
                        ) {
                            String line;
                            while (null != (line = br.readLine())) {
                                if (line.contains(search)) {
                                    System.out.println("找到目标字符串(多线程):" + search + "，在文件:" + file.getAbsolutePath());
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                searchThread.start();
            }
            // 如果是文件夹
            if (file.isDirectory()) {
                multipleThreadSearch(file, search);
            }
        }
    }

    // 练习-查找文件内容
    public static void search(File folder, String search) {
        File[] files = folder.listFiles();
        if (files == null) return;
//        System.out.println("正在查找文件夹" + folder.getAbsolutePath());
        for (File file : files) {
            // 如果是所找类型的文件
            if (file.isFile() && (file.getName().lastIndexOf('.')) != -1
                    && file.getName().substring(file.getName().lastIndexOf('.')).equals(FILE_TYPE)) {
                try (FileReader fr = new FileReader(file);
                     BufferedReader br = new BufferedReader(fr);
                ) {
                    String line;
                    while (null != (line = br.readLine())) {
                        if (line.contains(search)) {
                            System.out.println("找到目标字符串(单线程):" + search + "，在文件:" + file.getAbsolutePath());
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 如果是文件夹
            if (file.isDirectory()) {
                search(file, search);
            }
        }
    }

    // 练习-复制文件夹
    public static void copyFolder(String srcFolder, String destFolder) {
        File sFolder = new File(srcFolder);
        File dFolder = new File(destFolder);
        dFolder.mkdirs();

        File[] listFiles = sFolder.listFiles();
        for (File file : listFiles) {
            if (file.isFile()) {
                copyFile(file.getAbsolutePath(), destFolder + "\\" + file.getName());
            }
            if (file.isDirectory()) {
                copyFolder(file.getAbsolutePath(), destFolder + "\\" + file.getName());
            }
        }
    }

    // 练习-复制文件
    public static void copyFile(String srcFile, String destFile) {
        File sFile = new File(srcFile);
        File dFile = new File(destFile);

        try (FileInputStream fis = new FileInputStream(sFile);
             BufferedInputStream bis = new BufferedInputStream(fis);
             FileOutputStream fos = new FileOutputStream(dFile);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            // 创建目标文件及文件夹
            if (!dFile.exists()) {
                dFile.getParentFile().mkdirs();
                dFile.createNewFile();
            }

            byte[] bytes = new byte[(int) sFile.length()];
            bis.read(bytes);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 加密方法
    public static void encodeFile(File encodingFile, File encodedFile) {
        System.out.println("————————————————————");
        char[] chars = new char[(int) encodingFile.length()];
        // 加密encodingFile中的内容
        try (FileReader fr = new FileReader(encodingFile)) {
            fr.read(chars);
            for (int i = 0; i < chars.length; i++) {
                if (Character.isDigit(chars[i])) { // 如果是数字
                    chars[i] = (char) (((chars[i] - '0') + 1) % 10 + '0');
                } else if (Character.isLowerCase(chars[i]) || Character.isUpperCase(chars[i])) { // 如果是字母
                    if (chars[i] == 'z' || chars[i] == 'Z') {
                        chars[i] = (char) (chars[i] - 25);
                    } else {
                        chars[i] = (char) (chars[i] + 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 保存加密后的内容到encodedFile
        try (FileWriter fw = new FileWriter(encodedFile)) {
            // 去掉末尾空格
            String str = new String(chars);
            chars = str.trim().toCharArray();

            fw.write(chars);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 解密方法
    public static void decodeFile(File decodingFile, File decodedFile) {
        System.out.println("————————————————————");
        char[] chars = new char[(int) decodingFile.length()];
        // 解密encodingFile中的内容
        try (FileReader fr = new FileReader(decodingFile)) {
            fr.read(chars);
            for (int i = 0; i < chars.length; i++) {
                if (Character.isDigit(chars[i])) { // 如果是数字
                    chars[i] = (char) (((chars[i] - '0') + 9) % 10 + '0');
                } else if (Character.isLowerCase(chars[i]) || Character.isUpperCase(chars[i])) { // 如果是字母
                    if (chars[i] == 'a' || chars[i] == 'A') {
                        chars[i] = (char) (chars[i] + 25);
                    } else {
                        chars[i] = (char) (chars[i] - 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 保存解密后的内容到decodedFile
        try (FileWriter fw = new FileWriter(decodedFile)) {
            // 去掉末尾空格
            String str = new String(chars);
            chars = str.trim().toCharArray();

            fw.write(chars);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
