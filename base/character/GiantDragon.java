package character;

public class GiantDragon {
    // 私有化构造方法，使得该类无法在外部通过new 进行实例化
    private GiantDragon() {
    }

    // 饿汉式单例模式
    /*private static GiantDragon instance = new GiantDragon();

    public static GiantDragon getInstance() {
        return instance;
    }*/

    // 懒汉式单例模式
    private static GiantDragon instance;

    public static GiantDragon getInstance(){
        if (null==instance){
            instance=new GiantDragon();
        }
        return instance;
    }
    public static void main(String[] args) {
    }
}
