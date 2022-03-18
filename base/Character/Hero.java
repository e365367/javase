package Character;

import property.Weapon;

public class Hero {
    protected int id;
    public String name = "some hero";
    float hp;
    float maxHP;
    float armor;
    int moveSpeed;

    //物品栏的容量
    public static int itemCapacity=8; //声明的时候 初始化

    {
        itemCapacity=6;
    }

    {
        maxHP = 999;
        armor = 300;
    }

    public static void battleWin() {
        System.out.println("battle win");
    }

    public Hero() {
    }

    public Hero(String name) {
        this.name = name;
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
    public void attack(Hero hero, float hp) {
        hero.hp -= hp;
    }

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

        teemo.attack(garen, 100);
        System.out.println(garen.hp);

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
        GiantDragon giantDragon=GiantDragon.getInstance();
        GiantDragon giantDragon2=GiantDragon.getInstance();
        GiantDragon giantDragon3=GiantDragon.getInstance();

        System.out.println(giantDragon);
        System.out.println(giantDragon2);
        System.out.println(giantDragon3);
    }
}
