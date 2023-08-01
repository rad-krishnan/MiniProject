package miniProjPackage;

import java.sql.*;

public abstract class DatabaseOperation {
    protected static final String url = "jdbc:mysql://localhost:3306/miniproj";
    protected static final String user = "root";
    protected static final String password = "root";
    protected Connection conn;

    public DatabaseOperation() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
    }

    public abstract void run(); // Abstraction
}
