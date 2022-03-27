package character.mid;

public class Dog {
    public String name;
    public Dog() {
    }
    public void setName(String  name){
        this.name = name;
    }
      
    public String  getName(){
        return this.name;
    }

    public static void main(String[] args) {
        Dog dog=new Dog();
        dog.name="df";
        System.out.println(dog.name);
    }
}
