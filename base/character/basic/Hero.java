package character.basic;

import property.Item;
import property.MagicPotion;
import property.Weapon;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

public class Hero implements Serializable, Comparable<Hero> {
    protected int id;
    public String name = "some hero";
    public float damage = 40;
    public float hp;
    private float maxHP = 1000;
    float armor;
    int moveSpeed = 200;

    //物品栏的容量
    public static int itemCapacity = 8; //声明的时候 初始化

    {
        itemCapacity = 6;
    }

    {
        maxHP = 999;
        armor = 300;
    }

    @Override
    public int compareTo(Hero anotherHero) {
        /*if(this.getHp()>=anotherHero.getHp()){
            return 1;
        }
        return -1;*/
        return (int) (hp - anotherHero.hp);
    }

    // 为Lambda表达式而用
    public boolean matched() {
        return this.hp > 100 && this.armor > 50;
    }

    // 非静态内部类
    class BattleScore {
        int kill;
        int die;
        int assist;
    }

    // 静态内部类
    static class Ec {
        int hp = 5000;
    }

    public void useItem(Item item) {
        System.out.println("Hero use item");
        item.effect();
    }

    public void recover(int xp) {
        hp += xp;
        xp = 0;
    }

    // 回血(同步和交互）
    // wait和notify一定是在同步对象synchronized块里
    public synchronized void recover() {
        // 当血量达到上限时，this.wait(),让占用这个对象的线程等待，并临时释放锁
        while (hp == 100) {  // 把if改成while，当被唤醒后也要判断hp的值
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hp = hp + 1;
        System.out.printf("%s 回血1点,增加血后，%s的血量是%.0f%n", name, name, hp);
        // 通知一个等待在this对象上的线程，可以醒过来了
        this.notify();
    }

    // 掉血(同步和交互）
    // wait和notify一定是在同步对象synchronized块里
    public synchronized void hurt() {
        // 当血量达到下限1时，this.wait()，让占用这个对象的线程等待，并临时释放锁
        while (hp == 1) {  // 把if改成while，当被唤醒后也要判断hp的值
            try {
                // 让占有this的减血线程，暂时释放对this的占有，并等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hp = hp - 1;
        System.out.printf("%s 减血1点,减少血后，%s的血量是%.0f%n", name, name, hp);
        // 通知一个等待在this对象上的线程，可以醒过来了
        this.notify();
    }

    // 攻击
    public void attackHero(Hero h) {
//        try {
//            //为了表示攻击需要时间，每次攻击暂停1000毫秒
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        h.hp -= damage;
        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n", name, h.name, h.name, h.hp);

        if (h.isDead())
            System.out.println(h.name + "死了！");
    }

    // 是否死亡
    public boolean isDead() {
        return 0 >= hp ? true : false;
    }

    // 复活
    public void revive(Hero hero) {
        System.out.println(hero);
        hero = new Hero("复活英雄", 350);
        System.out.println(hero);
    }

    public static void battleWin() {
        System.out.println("hero battle win");
    }

    // 垃圾回收
    @Override
    public void finalize() {
        System.out.println("Hero 正在被回收");
    }


    public Hero() {
//        System.out.println("Hero 的无参构造");
    }

    public Hero(String name) {
        this.name = name;
//        System.out.println("Hero的有参构造");
    }

    public Hero(String name, float hp) {
        this(name);
        this.hp = hp;
    }


    public Hero(String s, String s2) {
        System.out.println("两个参数构造方法");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getMaxHP() {
        return maxHP;
    }

    public float getArmor() {
        return armor;
    }

    public void setArmor(float armor) {
        this.armor = armor;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public static int getItemCapacity() {
        return itemCapacity;
    }

    public static void setItemCapacity(int itemCapacity) {
        Hero.itemCapacity = itemCapacity;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", maxHP=" + maxHP +
                ", armor=" + armor +
                ", moveSpeed=" + moveSpeed +
                '}';
    }

    public static void main(String[] args) {
        Hero teemo = new Hero("提莫", 383);
        Hero garen = new Hero("盖伦", 500);
        //血瓶，其值是100
        int xueping = 100;

        //提莫通过这个血瓶回血
        teemo.recover(xueping);
        System.out.println(xueping);
        System.out.println(teemo.hp);

        /*teemo.attack(garen, 100);
        System.out.println(garen.hp);*/

        System.out.println(teemo);
        teemo.revive(teemo);
        System.out.println(teemo.hp);

        Weapon weapon = new Weapon();
        Hero.battleWin();
        System.out.println(teemo);
        System.out.println(Hero.itemCapacity);

        // 通过new实例化会报错
//      GiantDragon giantDragon=new GiantDragon();

        // 只能通过getInstance得到对象
        GiantDragon giantDragon = GiantDragon.getInstance();
        GiantDragon giantDragon2 = GiantDragon.getInstance();
        GiantDragon giantDragon3 = GiantDragon.getInstance();

        System.out.println(giantDragon);
        System.out.println(giantDragon2);
        System.out.println(giantDragon3);

        System.out.println("second commit");

        Hero hero = new AdHero();
        Item item = new MagicPotion();
        hero.useItem(item);
        Hero.battleWin();
        AdHero.battleWin();

        hero.battleWin();

        AdHero adHero = new AdHero();
        System.out.println("——————————————————————");
        adHero.battleWin();

        // 测试jvm的垃圾回收
        /*for (int i = 0; i < 1000000; i++) {
            h = new Hero();
        }*/

        // 实例化非静态内部类
        // BattleScore对象只有在一个英雄对象存在的时候才有意义
        // 所以其实例化必须建立在一个外部类对象的基础之上
        BattleScore battleScore = garen.new BattleScore();
        battleScore.die = 5;
        battleScore.kill = 6;
        System.out.println(battleScore.kill);

        // 实例化静态内部类
        Hero.Ec ec = new Hero.Ec();
        ec.hp = 300;
        System.out.println(ec.hp);

        char[] chars = {'s', 'd'};
        System.out.println(Arrays.toString(chars));
    }
}
