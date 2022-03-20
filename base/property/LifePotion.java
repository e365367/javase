package property;

public class LifePotion extends Item{
    @Override
    public void effect() {
        System.out.println("使用血瓶可以回血");
    }

    public static void main(String[] args) {
        Item item=new LifePotion();
        item.effect();

        LifePotion lifePotion=new LifePotion();
        lifePotion.effect();
    }
}
