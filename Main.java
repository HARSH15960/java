import java.util.Scanner;
import services.AuthService;
import services.LibraryService;
import services.AdminService;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthService authService = new AuthService();
        LibraryService libraryService = new LibraryService();
        AdminService adminService = new AdminService();

        System.out.println("📚 Welcome to BookNest 📚");

        while (true) {
            System.out.println("\n1. Register as User");
            System.out.println("2. Login as User");
            System.out.println("3. Login as Admin");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

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
                    System.out.print("Enter admin username: ");
                    String adminUsername = sc.nextLine();
                    System.out.print("Enter admin password: ");
                    String adminPassword = sc.nextLine();

                    if (adminUsername.equals("admin") && adminPassword.equals("admin123")) {
                        System.out.println("✅ Admin login successful!");
                        adminService.showAdminMenu();
                    } else {
                        System.out.println("❌ Invalid admin credentials.");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for visiting BookNest. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
