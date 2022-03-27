package test.mid;

import character.basic.Hero;
import character.mid.HeroChecker;
import property.Item;
import property.LifePotion;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TestLambda {
    public static void main(String[] args) {
        Random random = new Random();
        List<Hero> heroes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Hero hero = new Hero("hero" + i);
            hero.setHp(random.nextInt((200)));
            hero.setArmor(random.nextInt(100));
            heroes.add(hero);
            if (i == 5) {
                heroes.add(hero);
            }
        }
        System.out.println(heroes);

        // 匿名类
        /*HeroChecker heroChecker=new HeroChecker() {
            @Override
            public boolean test(Hero hero) {
                return hero.getHp() > 100 && hero.getArmor() > 50;
            }
        };*/

        // 由匿名类简化成Lambda表达式
        HeroChecker heroChecker = hero -> hero.getHp() > 100 && hero.getArmor() > 50;
//        filter(heroes, heroChecker);

        // 直接在方法参数中写Lambda表达式
        filter(heroes, hero -> hero.getHp() > 100 && hero.getArmor() > 50);
        System.out.println("——————————————————————");

        // 引用静态方法
        filter(heroes, h -> testHero(h));
        System.out.println("————————————————————————");
        filter(heroes, TestLambda::testHero);

        // 引用对象方法
        System.out.println("——————————————————————————————");
        TestLambda myTestLambda = new TestLambda();
        filter(heroes, myTestLambda::testHero1);

        // 引用容器中的对象的方法
        System.out.println("——————————————————————————————");
        filter(heroes, h -> h.matched());
        System.out.println("——————————————————————————————");
        filter(heroes, Hero::matched);

        // 匿名类
        String s = getString(new Supplier<String>() {
            @Override
            public String get() {
                return "abc";
            }
        });
        // Lambda表达式
//        String s = getString(() -> "abc");

        // 引用构造器
        String ss = getString(String::new);
        System.out.println(ss);
        System.out.println(s);
        System.out.println("————————————————————————");

        // 聚合操作
        heroes.stream().filter(h -> testHero(h)).forEach(h -> System.out.println(h.name));

        Hero[] heroArray = heroes.toArray(new Hero[heroes.size()]);
        Arrays.stream(heroArray).filter(h -> h.matched()).mapToDouble(Hero::getArmor).forEach(h -> System.out.println(h));
        Stream.of(heroArray).filter(h -> h.matched()).map((h) -> h.getName() + "--" + h.getHp() + "--" + h.getArmor()).forEach(h -> System.out.println(h));

        // 遍历集合中每一个数据
        heroes.stream().skip(5).limit(3).forEach(h -> System.out.println(h.name));
        heroes.stream().limit(8).distinct().filter(h -> testHero(h)).sorted((h1, h2) -> (int) (h1.getHp() - h2.getHp()))
                .map((h) -> h.getName() + "-" + h.getHp() + "-" + h.getArmor() + "!!!").forEach(h -> System.out.println(h));

        // 返回一个数组
        Object[] objects = heroes.stream().limit(8).distinct().filter(h -> testHero(h)).sorted((h1, h2) -> (int) (h1.getHp() - h2.getHp()))
                .map((h) -> h.getName() + "-" + h.getHp() + "-" + h.getArmor() + "!!!").toArray();
        System.out.println(Arrays.toString(objects));

        // 返回流中数据总数
        System.out.println(heroes.stream().limit(8).distinct().filter(h -> testHero(h)).sorted((h1, h2) -> (int) (h1.getHp() - h2.getHp()))
                .map((h) -> h.getName() + "-" + h.getHp() + "-" + h.getArmor() + "!!!").count());

        // 返回第一个数据
        try {
            String first = heroes.stream().limit(8).distinct().filter(h -> testHero(h)).sorted((h1, h2) -> (int) (h1.getHp() - h2.getHp()))
                    .map((h) -> h.getName() + "-" + h.getHp() + "-" + h.getArmor() + "!!!").findFirst().get();
            System.out.println(first);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("流中最后没有数据");
        }
        System.out.println("q");

        // 练习-聚合操作
        heroes.stream().sorted((h1, h2) -> h1.getHp() >= h2.getHp() ? -1 : 1).map((h)->h.name+"-"+h.getHp()).forEach(h-> System.out.println(h));

        Hero third_high_hp_hero = heroes.stream().sorted((h1, h2) -> h1.getHp() >= h2.getHp() ? -1 : 1).skip(2).findFirst().get();
        System.out.println(third_high_hp_hero);
    }

    public static void filter(List<Hero> heroes, HeroChecker heroChecker) {
        for (Hero hero : heroes) {
            if (heroChecker.test(hero)) {
                System.out.println(hero);
            }
        }
    }

    // 静态方法
    public static boolean testHero(Hero hero) {
        return hero.getHp() > 100 && hero.getArmor() > 50;
    }

    // 对象方法
    public boolean testHero1(Hero hero) {
        return hero.getHp() > 100 && hero.getArmor() > 50;
    }

    //
    public static String getString(Supplier<String> s) {
        return s.get();
    }
}
