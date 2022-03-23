package test.basic;

public class TestChar {
    public static void main(String[] args) {
        char c1='a';
        char c2='张';
        Character character=c2;
        char c3=character;
        System.out.println(c3);
        System.out.println(c2);

        System.out.println(Character.toUpperCase('c'));
        String s=Character.toString(c1);
        System.out.println(s);

        System.out.println("abc def");
        System.out.println("ab def");
        System.out.println("a def");

        System.out.println("abc\tdef");
        System.out.println("ab\tdef");
        System.out.println("a\tdef");
        System.out.println("一个\\t制表符长度是8");
        System.out.println("12345678def");
        System.out.println("1234def");

        System.out.println("换行符 \\n");
        System.out.println("abc\ndef");

        System.out.println("单引号 \\'");
        System.out.println("abc\'def");
        System.out.println("双引号 \\\"");
        System.out.println("abc\"def");
        System.out.println("反斜杠本身 \\\\");
        System.out.println("abc\\def");
    }
}
