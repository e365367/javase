package character;

import property.Item;
import property.MagicPotion;
import property.Weapon;

public class Hero {
    protected int id;
    public String name = "some hero";
    float hp;
    float maxHP;
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
    // 非静态内部类
    class BattleScore{
        int kill;
        int die;
        int assist;
    }
    // 静态内部类
    static class Ec{
        int hp=5000;
    }

    public void useItem(Item item) {
        System.out.println("Hero use item");
        item.effect();
    }


    public static void battleWin() {
        System.out.println("hero battle win");
    }

    @Override
    public void finalize() {  // 垃圾回收
        System.out.println("hero 正在被回收");
    }


    public Hero() {
//        System.out.println("Hero 的无参构造");
    }

    public Hero(String name) {
        this.name = name;
        System.out.println("Hero的有参构造");
    }

    public Hero(String name, float hp) {
        this(name);
        this.hp = hp;
    }

    public void recover(int xp) {
        hp += xp;
        xp = 0;
    }

    // 攻击
    /*public void attack() {
        System.out.println("Hero attack");
    }*/

    // 复活
    public void revive(Hero hero) {
        System.out.println(hero);
        hero = new Hero("复活英雄", 350);
        System.out.println(hero);
    }

    public Hero(String s, String s2) {
        System.out.println("两个参数构造方法");
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
        BattleScore battleScore=garen.new BattleScore();
        battleScore.die=5;
        battleScore.kill=6;
        System.out.println(battleScore.kill);

        // 实例化静态内部类
        Hero.Ec ec=new Hero.Ec();
        ec.hp=300;
        System.out.println(ec.hp);
    }
}
