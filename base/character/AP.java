package character;

public interface AP {
    void magicAttack();

    // 接口的默认方法，无需实现，可以有实现体
    default void attack() {
        System.out.println("ap attack");
    }
}
