import model.User;
public class App {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        User user = new User("dkhuy", "2005");
        System.out.println(user);
        Time t1 = new Time(13, 5, 9);
        System.out.println(t1);
    }
}
