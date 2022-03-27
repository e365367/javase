import property.Season;

class he{

}
public class HelloWorld_decoded {
    public static void main(String[] args) {
        Season season = Season.AUTUMN;
        switch (season) {
            case SPRING:
                System.out.println("春天");
                break;
            case SUMMER:
                System.out.println("夏天");
                break;
            case AUTUMN:
                System.out.println("秋天");
                break;
            case WINTER:
                System.out.println("冬天");
                break;
        }

        for (Season s : Season.values()) {
            System.out.println(s);
        }
        int i1 = 5;
        // 基本类型转换成封装类型
        Integer integer1 = new Integer(i1);
        // 封装类型转换成基本类型
        int i2 = integer1.intValue();
        System.out.println(i2);

        // 通过“=”符号，基本类型到类类型的自动转换即为封装
        Integer integer2 = i2;

        // 通过“=”符号，类类型到基本类型的自动转换即为拆箱
        int i3 = integer2;

        System.out.println(Integer.MAX_VALUE);

        byte b=16;
        Byte bb=14;
        int i4=bb;
    }
}