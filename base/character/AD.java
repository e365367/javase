package character;

public interface AD {
    void physicAttack();

    // 接口的默认方法，无需实现，可以有实现体
    default void attack() {
        System.out.println("ad attack");
    }
}
