package character.mid;

import test.mid.TestThread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 练习-生产者消费者问题
// 支持线程安全（使用synchronized或Lock）
// 交互和同步
public class MyStack {
    private LinkedList<Character> list = new LinkedList<>();

    /*// 使用synchronized
    public synchronized void push() {
        while (list.size() == 200) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        char randomChar = (char) (Math.random() * (int) ('Z' + 1 - 'A') + 'A');
        list.addFirst(randomChar);
        System.out.println(Thread.currentThread().getName() + " 压入:" + randomChar + " 栈中还有" + list.size() + "个字符");
        this.notifyAll();
    }

    // 使用synchronized
    public synchronized void pull() {
        while (list.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Character character = list.removeFirst();
        System.out.println(Thread.currentThread().getName() + " 弹出:" + character + " 栈中还有" + list.size() + "个字符");
        this.notifyAll();
    }*/
    // 创建Lock类对象
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // 使用Lock
    public void push() {
        try {
            lock.lock();
            while (list.size() == 200) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            char randomChar = (char) (Math.random() * (int) ('Z' + 1 - 'A') + 'A');
            list.addFirst(randomChar);
            System.out.println(Thread.currentThread().getName() + " 压入:" + randomChar + " 栈中还有" + list.size() + "个字符");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    // 使用Lock
    public void pull() {
        try {
            lock.lock();
            while (list.isEmpty()) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Character character = list.removeFirst();
            System.out.println(Thread.currentThread().getName() + " 弹出:" + character + " 栈中还有" + list.size() + "个字符");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
