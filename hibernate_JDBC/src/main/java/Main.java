import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        Animal animal = new Animal();
//        animal.setColor("black");
//        animal.setId(1);
//        animal.setName("anim");
//        animal.setTail(true);
    Class cl = new ArrayList<Integer>().getClass();
    Class cd = new ArrayList<String>().getClass();
        System.out.println(cl == cd);
    }
}
