package character.mid;

public class MyException extends Exception{
    public MyException(){

    }
    public MyException(String message){
        super(message);
    }

    private static void test (int hp) throws MyException{
        if(hp<=0){
            throw new MyException("血量小于0");
        }
    }

    public static void main(String[] args) {
        try {
            test(-2);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
