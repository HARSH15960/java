// Main.java
import java.util.Scanner;
import services.AuthService;
import services.LibraryService;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthService authService = new AuthService();
        LibraryService libraryService = new LibraryService();

        System.out.println("📚 Welcome to BookNest 📚");
        
        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    authService.register();
                    break;
                case 2:
                    int userId = authService.login();
                    if (userId != -1) {
                        libraryService.menu(userId);
                    }
                    break;
                case 3:
                    System.out.println("Thank you for visiting BookNest. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
