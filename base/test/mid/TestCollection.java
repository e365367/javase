package test.mid;

import character.basic.ADAPHero;
import character.basic.AdHero;
import character.basic.Hero;
import property.Item;
import property.LifePotion;

import java.util.*;

public class TestCollection {
    public static void main(String[] args) {
        ArrayList heroes = new ArrayList();
        for (int i = 0; i < 5; i++) {
            Hero hero = new Hero(i + "");
            heroes.add(hero);
        }
        Hero specialHero = new Hero("specialHero");
        heroes.add(specialHero);
        System.out.println(heroes);

        Hero[] heroes1 = (Hero[]) heroes.toArray(new Hero[]{});
        System.out.println(heroes1);
        System.out.println(Arrays.toString(heroes1));

        //把另一个容器里所有的元素，都加入到该容器里来
        ArrayList anotherHeroes = new ArrayList();
        anotherHeroes.add(new Hero("hero a"));
        anotherHeroes.add(new Hero("hero b"));
        anotherHeroes.add(new Hero("hero c"));

        heroes.addAll(anotherHeroes);
        System.out.println(heroes);
        specialHero = (Hero) heroes.get(2);
//        heroes.clear();
        System.out.println(heroes);

        ArrayList<Integer> integers = new ArrayList();
        integers.add(2);
        integers.add(21);
        integers.add(21);
        integers.add(25);
        integers.add(24);
        integers.add(12);
        System.out.println(integers);

        // 错误的删除方式！！
        /*for(int i=1;i<3;i++){
            integers.remove(i);
        }
        System.out.println(integers);*/

        // 指定泛型Generic
        // JDK7后，后面尖括号可以不指定类
        List<Item> items = new ArrayList<>();
        items.add(new LifePotion());
        Item item = items.get(0);
        item.effect();

        // 迭代器的while写法
        Iterator<Integer> it = integers.iterator();
        while (it.hasNext()) {
            Integer integer = it.next();
            System.out.println(integer);
        }

        //  迭代器的for写法
        System.out.println(integers);
        for (Iterator<Integer> iterator = integers.iterator(); iterator.hasNext(); ) {
            Integer integer = iterator.next();
            System.out.println(integer);
        }

        // 增强型for循环，【不能用来删除元素！！】
        System.out.println(integers);
        for (Integer i : integers) {
            System.out.println(i);
        }

        List ll = new LinkedList<Hero>();
        Queue<Hero> queue = new LinkedList<>();
        queue.offer(new Hero("ad1"));
        queue.offer(new Hero("ad2"));
        queue.offer(new Hero("ad3"));
        Hero hero = queue.poll();
        System.out.println(hero);
        System.out.println(queue);
        System.out.println(queue.peek());

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "afds");
        hashMap.put("2", "afd大师分s");
        hashMap.put("16", "afdkfds");
        System.out.println(hashMap.get("2"));

        // Set中的元素不重复，且无序
        HashSet hashSet = new HashSet();
        hashSet.add("asd");
        hashSet.add("asd");
        hashSet.add("asadd");
        hashSet.add("asaddll");
        hashSet.add(null);
        System.out.println(hashSet);

        System.out.println(integers);
        Collections.reverse(integers);
        System.out.println(integers);

        Collections.shuffle(integers);
        System.out.println(integers);

        Collections.sort(integers);
        System.out.println(integers);

        Collections.swap(integers, 0, integers.size() - 1);
        System.out.println(integers);

        // rotate 把List中的数据，向右滚动指定单位的长度
        Collections.rotate(integers, 2);
        System.out.println(integers);
        int a = 0;
        int count = 0;
        //定义一个list数组
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);

        //将数组打乱1000000次
        /*while (a<10000000){
            Collections.shuffle(list);
            a++;
            if (list.get(0).equals(3)&&list.get(1).equals(1)&&list.get(2).equals(4)) {
                count++;
            }
        }
        System.out.printf("1000000次打乱中找到%d个[3,1,4],概率是：%f %%",count,(float)count/(float) 10000);*/

        // 测试ArrayList和LinkedList的插入性能
        /*List<Integer> l;
        l = new ArrayList<>();
        insertMiddle(l, "ArraList");

        l = new LinkedList<>();
        insertMiddle(l, "LinkedList");*/

        // HashMap不是线程安全的类
        // Hashtable是线程安全的类
        Hashtable hashtable = new Hashtable<>();
        // Hashtable不能用null作key，不能用null作value
//        hashtable.put("123", null);
        // HashMap可以用null作key,作value
        hashMap.put("123", null);

        double pi = Math.PI;
        System.out.println(pi);
        String p = String.valueOf(pi);
        char[] cs = p.toCharArray();
        LinkedHashSet<Character> lhs = new LinkedHashSet<>();
        for (char c : cs) {
            lhs.add(c);
        }
        for (char c : lhs) {
            System.out.print(c);
        }
        System.out.print(String.format("%n"));
        String str = new String("hsdjashdjkahs");
        System.out.println(hashCode(str));

        for (int i = 0; i < 100; i++) {
            int code = hashCode(getRandomString());
            System.out.printf("%-8d", code);
            if (i % 10 == 9) {
                System.out.println();
            }
        }

        List<Hero> heroes2 = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Hero hero1 = new Hero(getRandomString());
            hero1.setHp(random.nextInt(100));
            heroes2.add(hero1);
            heroes2.add(hero1);
        }
        System.out.println(heroes2);

        // 使用Comparator实例，或者让类实现Comparable接口，类集合才能使用Collections.sort排序
        /*Comparator<Hero> comparator = new Comparator<Hero>() {
            @Override
            public int compare(Hero o1, Hero o2) {
                if (o1.getHp() >= o2.getHp()) {
                    return 1;
                }
                return -1;
            }
        };
        Collections.sort(heroes2, comparator);*/

        Collections.sort(heroes2);

        System.out.println(heroes2);
        System.out.println("————————————————————————");
        // 练习-自定义顺序的TreeSet

        // 使用匿名类
        /*Comparator<Integer> c=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        };*/

        // Lambda表达式
        /*Comparator<Integer> c = (o1, o2) -> o2 - o1;*/

        // 引用静态方法
        Comparator<Integer> c=(o1,o2)->cmp(o1,o2);

        // 引用构造器方法

        Set<Integer> set = new TreeSet<>(c);
        for (int i = 0; i < 10; i++) {
            set.add(random.nextInt(100));
        }
        set.add(5);
        System.out.println(set);

        List<AdHero> adHeroes = new ArrayList<>();
        adHeroes.add(new AdHero("ad"));
        List<ADAPHero> adapHeroes = new ArrayList<>();
        adHeroes.add(new AdHero("adap"));

        List<Hero> heroes3 = new ArrayList<>();
        adHeroes.add(new AdHero("hero"));

        iterate(adHeroes);
        iterate(adapHeroes);
        iterate(heroes3);

        ArrayList<Hero> hs = new ArrayList<>();
        ArrayList<AdHero> adhs = new ArrayList<>();

        // 子类泛型转父类泛型，不行
//        hs = adhs;
        // 父类泛型转子类泛型，能否成功？为什么？ 不行
//        adhs = hs;

    }

    // Lambda表达式引用静态方法
    public static Integer cmp(Integer i1,Integer i2){
        return i1-i2;
    }

    // 使用泛型通配符 "?"
    public static void iterate(List<? extends Hero> list) {
        for (Hero hero : list) {
            System.out.println(hero);
        }
    }
    // 获取2-10位的随机字符串
    public static String getRandomString() {
        int length = (int) (Math.random() * 9 + 2);
        int start = '0', end = 'z' + 1;
        char[] chars = new char[length];
        for (int i = 0; i < length; ) {
            char c = (char) ((int) (Math.random() * (end - start) + start));
            if (Character.isLetterOrDigit(c)) {
                chars[i++] = c;
            }
        }
        return new String(chars);
    }

    public static int hashCode(String string) {
        if (string.length() == 0) return 0;
        char[] chars = string.toCharArray();
        int code = 0;
        for (int i = 0; i < chars.length; i++) {
            code += 23 * chars[i];
            code %= 2000;
//            System.out.println(code);
        }
        return code;
    }


    //练习在后面插入数据
    private static void insertLast(List<Integer> l, String type) {
        int t = 100000;
        final int number = 5;
        long start = System.currentTimeMillis();
        for (int i = 0; i < t; i++) {
            l.add(i, number);
        }
        long end = System.currentTimeMillis();

        System.out.printf("在%s最后面插入%d条数据，总共耗时%d毫秒%n",

                type, t, end - start);
    }


    // 练习在中间插入数据
    private static void insertMiddle(List<Integer> l, String type) {
        int t = 100000;
        final int number = 5;
        long start = System.currentTimeMillis();
        for (int i = 0; i < t; i++) {
            int index = i / 2;
            l.add(index, number);
        }
        long end = System.currentTimeMillis();

        System.out.printf("在%s中间插入%d条数据，总共耗时%d毫秒%n", type, t, end - start);
    }
}
