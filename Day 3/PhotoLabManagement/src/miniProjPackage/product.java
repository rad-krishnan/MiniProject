package miniProjPackage;

import java.sql.*;
import java.util.Scanner;

public class product {
    private static final String url = "jdbc:mysql://localhost:3306/miniproj";
    private static final String user = "root";
    private static final String password = "root";

    public static void main(String[] args) {
        product product = new product();
        product.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt;

            while(true) {
                System.out.println("Please select an operation:");
                System.out.println("1. Create a new product");
                System.out.println("2. View all products");
                System.out.println("3. Update a product's cost");
                System.out.println("4. Delete a product");
                System.out.println("5. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1: // CREATE
                        System.out.println("Enter new product ID:");
                        int prodId = scanner.nextInt();
                        System.out.println("Enter new product name:");
                        scanner.nextLine(); // Consume newline
                        String prodName = scanner.nextLine();
                        System.out.println("Enter new product cost:");
                        while (!scanner.hasNextDouble()) {
                            System.out.println("That's not a valid number! Try again:");
                            scanner.next(); // this is important!
                        }
                        double prodCost = scanner.nextDouble();

                        String insertProduct = "INSERT INTO product (prodId, prodName, cost) VALUES (?, ?, ?)";
                        pstmt = conn.prepareStatement(insertProduct);
                        pstmt.setInt(1, prodId);
                        pstmt.setString(2, prodName);
                        pstmt.setDouble(3, prodCost);
                        pstmt.executeUpdate();
                        System.out.println("Product has been added successfully.");
                        break;

                    case 2: // READ
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM product");
                        while (rs.next()) {
                            System.out.println("Product ID: " + rs.getInt("prodId") + ", Product Name: " + rs.getString("prodName") + ", Cost: " + rs.getDouble("cost"));
                        }
                        break;

                    case 3: // UPDATE
                        System.out.println("Enter product ID to update:");
                        prodId = scanner.nextInt();
                        System.out.println("Enter new product cost:");
                        prodCost = scanner.nextDouble();

                        String updateProduct = "UPDATE product SET cost = ? WHERE prodId = ?";
                        pstmt = conn.prepareStatement(updateProduct);
                        pstmt.setDouble(1, prodCost);
                        pstmt.setInt(2, prodId);
                        pstmt.executeUpdate();
                        System.out.println("Product price has been updated successfully.");
                        break;

                    case 4: // DELETE
                        System.out.println("Enter product ID to delete:");
                        prodId = scanner.nextInt();

                        String deleteProduct = "DELETE FROM product WHERE prodId = ?";
                        pstmt = conn.prepareStatement(deleteProduct);
                        pstmt.setInt(1, prodId);
                        pstmt.executeUpdate();
                        System.out.println("Product has been deleted successfully.");
                        break;

                    case 5: // EXIT
                        System.out.println("Exiting...");
                        conn.close();
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice! Please choose a number between 1 and 5.");
                        break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

