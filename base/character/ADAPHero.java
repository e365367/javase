package character;

public class ADAPHero extends Hero implements AD, AP {
    @Override
    public void physicAttack() {
        System.out.println("APADHero魔法攻击");
    }

    // 当所实现的接口中有相同的默认方法时，需要重写（Override)该默认方法
    @Override
    public void attack() {
        System.out.println("ADAPHero attack");
    }

    @Override
    public void magicAttack() {
        System.out.println("APADHero物理攻击");
    }

    public static void main(String[] args) {
        ADAPHero adapHero = new ADAPHero();
        adapHero.attack();
    }
}
