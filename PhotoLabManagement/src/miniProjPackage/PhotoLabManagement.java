package miniProjPackage;

import java.sql.*;
import java.util.*;

public class PhotoLabManagement {
    private static final String url = "jdbc:mysql://localhost:3306/miniproj";
    private static final String user = "root";
    private static final String password = "root";

    public static void main(String[] args) {
        PhotoLabManagement photoLabManagement = new PhotoLabManagement();
        photoLabManagement.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Customer ID:");
        int cId = scanner.nextInt();

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product");

            Map<Integer, String> products = new HashMap<>();
            Map<Integer, Double> productPrices = new HashMap<>();

            int i = 1;
            while (rs.next()) {
                products.put(i, rs.getString("prodName"));
                productPrices.put(i, rs.getDouble("cost"));
                i++;
            }

            for (Map.Entry<Integer, String> entry : products.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue());
            }

            List<Integer> selectedProducts = new ArrayList<>();
            System.out.println("Select product(s) by entering the corresponding number (enter 0 when done):");

            while (true) {
                int prodNumber = scanner.nextInt();
                if (prodNumber == 0) {
                    break;
                }
                selectedProducts.add(prodNumber);
            }

            double totalPrice = 0;
            for (int prodNumber : selectedProducts) {
                totalPrice += productPrices.get(prodNumber);
            }

            System.out.println("Total Price: " + totalPrice);

            String insertOrder = "INSERT INTO orders (ordName, cost, prodId, cId) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertOrder);

            for (int prodNumber : selectedProducts) {
                pstmt.setString(1, products.get(prodNumber));
                pstmt.setDouble(2, productPrices.get(prodNumber));
                pstmt.setInt(3, prodNumber);
                pstmt.setInt(4, cId);
                pstmt.executeUpdate();
            }

            System.out.println("Order has been saved successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
