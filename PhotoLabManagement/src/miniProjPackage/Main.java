package miniProjPackage;

import java.util.Scanner;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Select the system to run:");
            System.out.println("1. Product Management");
            System.out.println("2. Photo Lab Management");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            try {
                switch (choice) {
                    case 1: // Product Management
                        product product = new product();
                        product.run(); // Polymorphism: we can call run() on a Product because it's a DatabaseOperation
                        break;

                    case 2: // Photo Lab Management
                        PhotoLabManagement photoLabManagement = new PhotoLabManagement();
                        photoLabManagement.run(); // Polymorphism: we can call run() on a PhotoLabManagement because it's a DatabaseOperation
                        break;

                    case 3: // Exit
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice! Please choose a number between 1 and 3.");
                        break;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}



