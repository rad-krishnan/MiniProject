package miniProjPackage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please select an operation:");
        System.out.println("1. Run Photo Lab Management");
        System.out.println("2. Run Product CRUD");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                PhotoLabManagement photoLabManagement = new PhotoLabManagement();
                photoLabManagement.run();
                break;

            case 2:
                product productCrud = new product();
                productCrud.run();
                break;

            default:
                System.out.println("Invalid choice! Please choose either 1 or 2.");
                break;
        }

        scanner.close();
    }
}


