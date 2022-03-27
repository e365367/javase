package property;

import character.basic.Hero;
public class Weapon extends Item{
    int damage; // 攻击力

    /*public void attack(){
        System.out.println("no parameter");
    }

    public void attack(String s1){
        System.out.println(s1);
    }

    public void attack(String s1,String s2){
        System.out.print(s1);
        System.out.print(" "+s2+"\n");
    }*/

    /**
     * 可变参数方法
     * @param strings
     */
    public void attack(String... strings){
        for (int i = 0; i < strings.length; i++) {
            System.out.print(strings[i]);
            if(i!=strings.length-1){
                System.out.print(" ");
            }else{
                System.out.println();
            }
        }
    }



    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "property.Weapon{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", damage=" + damage +
                '}';
    }

    @Override
    public void effect() {
        System.out.println("装备剑");
    }

    public static void main(String[] args) {
        Weapon w1=new Weapon();
        w1.damage=20;
        w1.name="A剑";
        w1.price=500;
        System.out.println(w1);
        w1.attack("sad");
        w1.attack("asdd","515","dsfsdf","21+151");

        Hero hero=new Hero("garen",500);
        Hero.battleWin();
    }
}
