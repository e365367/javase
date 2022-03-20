package character;

import property.Item;
import property.LifePotion;

public class AdHero extends Hero implements AD {
    int moveSpeed = 400;


    @Override
    public void physicAttack() {
        System.out.println("物理攻击");
    }

    public static void battleWin() {
        System.out.println("ad hero battle win");
    }

    public int getMoveSpeed(){
        return this.moveSpeed;
    }

    public int getMoveSpeed2(){
        return super.moveSpeed;
    }

    public void useItem(Item item){
        System.out.println("Ad hero use itrm");
        super.useItem(item);
    }

    public AdHero() {
        System.out.println("AdHero的无参构造");
    }

    public AdHero(String name) {
        super(name);
        System.out.println("AdHero的有参构造");
    }

    public static void main(String[] args) {
        Hero hero = new Hero("sd", 25);
        System.out.println(hero);
        System.out.println(hero.id);

        AdHero adHero1 = new AdHero();
        hero = adHero1;
        adHero1 = (AdHero) hero;
        AD ad = adHero1;
        Hero adHero2 = (Hero) ad;
        Hero hero1 = new Hero();
        System.out.println((hero1 instanceof AD));
        AdHero adHero = new AdHero("das");

        System.out.println(adHero.getMoveSpeed());
        System.out.println(adHero.getMoveSpeed2());

        Item item=new LifePotion();
        adHero.useItem(item);

        adHero.attack();
    }
}
