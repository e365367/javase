package test.mid;

import character.basic.Hero;
import character.mid.Battle;
import character.mid.KillThread;
import character.mid.MyStack;
import character.mid.ThreadPool;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {
    public static void main(String[] args) {
        final Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 6160;
        gareen.damage = 1;

        final Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 3000;
        teemo.damage = 1;

        final Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 5000;
        bh.damage = 1;

        final Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 4505;
        leesin.damage = 1;

        // 正常不能并行
        //盖伦攻击提莫
       /* while (!teemo.isDead()) {
            gareen.attackHero(teemo);
        }*/
        //赏金猎人攻击盲僧
        /*while (!leesin.isDead()) {
            bh.attackHero(leesin);
        }*/

        // KillThread类继承Thread类，并重写run方法，实现多线程
        /*KillThread k1=new KillThread(gareen,teemo);
        k1.start();
        KillThread k2=new KillThread(bh,leesin);
        k2.start();*/

        // Battle实现Runnable接口，重写run方法，通过Thread类调用Battle类实习，实现多线程
        /*Battle battle1=new Battle(gareen,teemo);
        new Thread(battle1).start();

        Battle battle2=new Battle(bh,leesin);
        new Thread(battle2).start();*/

        // 匿名类重写run方法，实现多线程
        Thread t1 = new Thread() {
            @Override
            public void run() {
                // 匿名类中用到外部的局部变量teemo，必须把teemo声明为final(匿名类不能修改外部变量）
                // 但是在JDK7以后，就不是必须加final的了
                while (!teemo.isDead()) {
                    gareen.attackHero(teemo);
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (!leesin.isDead()) {
                    // 临时暂停，使得t1可以占用CPU资源
//                    Thread.yield();

                    bh.attackHero(leesin);
                }
            }
        };

        // 设置线程优先级
        t1.setPriority(10);
        t2.setPriority(5);

        // 设为守护线程：当一个进程里，只有守护线程的时候，结束当前进程。
        t2.setDaemon(true);

        /*t1.start();
        t2.start();*/

        // 破解密码
        String code = getRandomString(3);

        Thread crackThread = new Thread() {
            @Override
            public void run() {
                crackCode(code);
            }
        };
        Thread logThread = new Thread() {
            @Override
            public void run() {
                printLogs();
            }
        };
        // 日志线程设为守护线程
        logThread.setDaemon(true);
        crackThread.start();
        logThread.start();
    }

    public static LinkedList<String> myAttempt = new LinkedList<>();

    // 练习-破解密码(穷举法)
    // 破解密码
    public static void crackCode(String code) {
        // 生成字典
        StringBuffer dictionary = new StringBuffer();
        for (int i = '0'; i <= 'z'; i++) {
            if (Character.isLetterOrDigit(i)) {
                dictionary.append((char) i);
            }
        }
        char[] myCode = new char[3];
        for (int i = 0; i < dictionary.length(); i++) {
            for (int j = 0; j < dictionary.length(); j++) {
                for (int k = 0; k < dictionary.length(); k++) {
                    myCode[0] = dictionary.charAt(i);
                    myCode[1] = dictionary.charAt(j);
                    myCode[2] = dictionary.charAt(k);
                    String myCodeStr = new String(myCode);
                    myAttempt.offer(myCodeStr);
                    if (myCodeStr.equals(code)) {
                        System.out.println("破解成功！");
                        System.out.println("密码是:" + myCodeStr);
                        System.out.println(myAttempt.size());
                        return;
                    }
                }
            }
        }
    }

    // 打印日志
    public static void printLogs() {
        while (true) {
            if (myAttempt.isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("尝试" + myAttempt.poll());
        }
    }

    // 生成长度是n的随机字符串(为字母或数字)
    public static String getRandomString(int n) {
        char[] chars = new char[n];
        for (int i = 0; i < chars.length; ) {
            char randomChar = (char) (Math.random() * (int) ('z' + 1 - '0') + '0');
            if (Character.isLetterOrDigit(randomChar)) {
                chars[i] = randomChar;
                i++;
            }
        }
        return new String(chars);
    }

    static class NewTestThread {
        public static void main(String[] args) {

            // 如果一个类，其方法都是有synchronized修饰的，那么该类就叫做线程安全的类
            // 通过修改Hero类，使得recover方法和hurt方法都有synchronized修饰，从而实现下面的操作线程安全
            // 练习-使用AtomicInteger来替换Hero类中的synchronized(未操作)
            final Hero teemo = new Hero("teemo");
            final Hero gareen = new Hero("gareen");
            /*gareen.name = "盖伦";
            gareen.hp = 10000;

            System.out.printf("盖伦的初始血量是 %.0f%n", gareen.hp);

            //多线程同步问题指的是多个线程同时修改一个数据的时候，导致的问题

            //假设盖伦有10000滴血，并且在基地里，同时又被对方多个英雄攻击

            //用JAVA代码来表示，就是有多个线程在减少盖伦的hp
            //同时又有多个线程在恢复盖伦的hp

            //n个线程增加盖伦的hp

            int n = 100000;

            Thread[] addThreads = new Thread[n];
            Thread[] reduceThreads = new Thread[n];

            for (int i = 0; i < n; i++) {
                Thread t = new Thread() {
                    public void run() {
                        // recover自带synchronized
                        gareen.recover();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                addThreads[i] = t;
            }
            //n个线程减少盖伦的hp
            for (int i = 0; i < n; i++) {
                Thread t = new Thread() {
                    public void run() {
                        // hurt自带synchronized
                        gareen.hurt();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                reduceThreads[i] = t;
            }

            //等待所有增加线程结束
            for (Thread t : addThreads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //等待所有减少线程结束
            for (Thread t : reduceThreads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //代码执行到这里，所有增加和减少线程都结束了

            //增加和减少线程的数量是一样的，每次都增加，减少1.
            //那么所有线程都结束后，盖伦的hp应该还是初始值

            //但是事实上观察到的是：

            System.out.printf("%d个增加线程和%d个减少线程结束后%n盖伦的血量变成了 %.0f%n", n, n, gareen.hp);*/

            // ArrayList为非线程安全的类
            List<Integer> list1 = new ArrayList<>();
            // 通过工具类Collections转换为线程安全的类
            List<Integer> list2 = Collections.synchronizedList(list1);


            // 演示死锁
            // 用Lock的tryLock的有限等待时间解除死锁
            Lock lock = new ReentrantLock();

            Thread t1 = new Thread() {
                @Override
                public void run() {
                    synchronized (gareen) {
                        System.out.println("已占用gareen");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("等待占用teemo");
                        synchronized (teemo) {
                            System.out.println("已占用teemo...");
                        }
                    }

                }
            };
            Thread t2 = new Thread() {
                @Override
                public void run() {
                    synchronized (teemo) {
                        System.out.println("已占用teemo");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("等待占用gareen");
                        synchronized (gareen) {
                            System.out.println("已占用gareen...");
                        }
                    }

                }
            };
            /*t1.start();
            t2.start();*/
//            getDeadlock();

            // 线程交互
            gareen.hp = 61;
            Thread t3 = null;
            for (int i = 0; i < 5; i++) {
                t3 = new Thread() {
                    public void run() {
                        while (true) {

                            //因为减血更快，所以盖伦的血量迟早会到达1
                            //使用while循环判断是否是1，如果是1就不停的循环
                            //直到加血线程回复了血量
                            /*while(gareen.hp==1){   // 使用交互，无需循环判断
                                System.out.println("cont");
                                continue;
                            }*/

                            gareen.hurt();
                            //                        System.out.printf("t3 为%s 减血1点,减少血后，%s的血量是%.0f%n",gareen.name,gareen.name,gareen.hp);
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                    }
                };
//                t3.start();
            }


            for (int i = 0; i < 2; i++) {
                Thread t4 = new Thread() {
                    public void run() {
                        while (true) {
                            gareen.recover();
                            //                        System.out.printf("t4 为%s 回血1点,增加血后，%s的血量是%.0f%n",gareen.name,gareen.name,gareen.hp);

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                    }
                };
//                t4.start();
            }

            // 练习-生产者消费者问题
//             PACThread();

            // 使用自定义线程池
            /*ThreadPool pool = new ThreadPool();
            int sleep = 1000;
            while (true) {
                pool.add(new Runnable() {
                    @Override
                    public void run() {
                        //System.out.println("执行任务");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
                try {
                    Thread.sleep(sleep);
                    sleep = sleep > 100 ? sleep - 100 : sleep;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }*/

            // 使用java自带线程池
            ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

            /*threadPool.execute(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    System.out.println("执行任务1");
                }

            });*/

            // 使用Lock对象实现同步效果

            // 创建Lock对象来进行线程同步
            /*Lock lock = new ReentrantLock();
            // 由Lock对象获得Condition对象来进行线程交互
            Condition condition = lock.newCondition();

            t1 = new Thread() {
                public void run() {
                    try {
                        log("线程启动");
                        log("试图占有对象：lock");

                        lock.lock();

                        log("占有对象：lock");
                        log("进行5秒的业务操作");

                        Thread.sleep(5000);

                        log("临时释放对象lock，并等待");
                        // 使用condition对象进行线程交互
                        condition.await();

                        log("重新占有对象，并进行5秒操作");
                        Thread.sleep(5000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        log("释放对象：lock");
                        lock.unlock();
                    }
                    log("线程结束");
                }
            };
            t1.setName("t1");
            t1.start();
            try {
                //先让t1飞2秒
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            t2 = new Thread() {

                public void run() {
                    boolean locked = false;
                    try {
                        log("线程启动");
                        log("试图占有对象：lock");
                        // 一直试图占用
                        lock.lock();
                        // 在指定时间范围内视图占用
//                        locked = lock.tryLock(1, TimeUnit.SECONDS);
                        log("占有对象：lock");
                        log("进行5秒的业务操作");
                        Thread.sleep(5000);
                        log("唤醒一个等待中的线程");
                        condition.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        log("释放对象：lock");
                        lock.unlock();
                    }
                    log("线程结束");
                }
            };
            t2.setName("t2");
            t2.start();*/

            // 原子类进行原子操作
            AtomicInteger atomicI = new AtomicInteger();
            int ii = atomicI.incrementAndGet();
            int j = atomicI.decrementAndGet();
            int k = atomicI.addAndGet(-3);
            System.out.println(ii);
            System.out.println(j);
            System.out.println(k);

            // 同步测试（原子性操作和非原子性操作
            int number = 100000;
            Thread[] ts1 = new Thread[number];
            for (int i = 0; i < number; i++) {
                Thread t = new Thread() {
                    public void run() {
                        value++;
                    }
                };
                t.start();
                ts1[i] = t;
            }

            //等待这些线程全部结束
            for (Thread t : ts1) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            System.out.printf("%d个线程进行value++后，value的值变成:%d%n", number, value);
            Thread[] ts2 = new Thread[number];
            for (int i = 0; i < number; i++) {
                Thread t = new Thread() {
                    public void run() {
                        atomicValue.incrementAndGet();
                    }
                };
                t.start();
                ts2[i] = t;
            }

            //等待这些线程全部结束
            for (Thread t : ts2) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            System.out.printf("%d个线程进行atomicValue.incrementAndGet();后，atomicValue的值变成:%d%n", number, atomicValue.intValue());
        }

        // 同步操作
        private static AtomicInteger atomicValue = new AtomicInteger();
        private static int value = 0;

        // 练习-生产者消费者问题
        public static MyStack myStack = new MyStack();

        public static void PACThread() {

            // 三个消费者线程
            for (int i = 1; i <= 3; i++) {
                Thread consumerThread = new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            myStack.pull();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                consumerThread.setName("Consumer" + i);
                consumerThread.start();
            }

            // 两个生产者线程
            for (int i = 1; i <= 2; i++) {
                Thread producerThread = new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            myStack.push();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                producerThread.setName("Producer" + i);
                producerThread.start();
            }
        }


        // 练习-死锁
        // 三个线程占用三个对象造成死锁
        public static void getDeadlock() {
            String a = "a";
            String b = "b";
            String c = "c";

            Thread t1 = new Thread() {
                public void run() {
                    synchronized (a) {
                        System.out.println("已占用a");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("等待占用b");
                        synchronized (b) {
                            System.out.println("已占用b...");
                        }
                    }

                }
            };

            Thread t2 = new Thread() {
                public void run() {
                    synchronized (b) {
                        System.out.println("已占用b");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("等待占用c");
                        synchronized (c) {
                            System.out.println("已占用c...");
                        }
                    }
                }
            };

            Thread t3 = new Thread() {
                public void run() {
                    synchronized (c) {
                        System.out.println("已占用c");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("等待占用a");
                        synchronized (a) {
                            System.out.println("已占用a...");
                        }
                    }
                }
            };

            t1.start();
            t2.start();
            t3.start();
        }

    }

    // 返回当前时间
    public static String now() {
        return new SimpleDateFormat("HH:mm:ss SSSS").format(new Date());
    }

    // 打印当前线程信息
    public static void log(String msg) {
        System.out.printf("%s %s %s %n", now(), Thread.currentThread().getName(), msg);
    }
}

