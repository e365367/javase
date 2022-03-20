package property;

public class MagicPotion extends Item{
    @Override
    public void effect() {
        System.out.println("使用蓝瓶可以回蓝");
    }

    public static void main(String[] args) {
        Item i1=new LifePotion();
        i1.effect();
        Item i2=new MagicPotion();
        i2.effect();
    }
}
