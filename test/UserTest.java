import model.User;

public class UserTest {
    public static void main(String[] args) {
        User user1 = new User("dkhuy", "2005");
        System.out.println("User1: " + user1);

        // Kiểm tra getter
        System.out.println("Name: " + user1.getName());
        System.out.println("UserID: " + user1.getUserID());

        user1.setUser("Minh", "2025");
        System.out.println("User1 sau khi cập nhật: " + user1);

        User user2 = new User("Linh", "1234");
        System.out.println("User2: " + user2);
    }
}
