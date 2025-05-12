package data;
import models.User;

public class UserDatabase {
    public static User[] users = new User[100];
    public static int userCount = 0;

    public static void addUser(User user) {
        users[userCount++] = user;
    }

    public static User findUserByEmail(String email) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].email.equals(email)) return users[i];
        }
        return null;
    }

    public static User getUserById(int id) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].id == id) return users[i];
        }
        return null;
    }
}
