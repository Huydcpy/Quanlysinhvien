package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/quanlyhocsinh";
    private static final String USER = "root";
    private static final String PASSWORD = "thinh2000"; // thay bang mat khau that

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // nap driver
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("[THANH CONG] Ket noi MySQL thanh cong!");
        } catch (ClassNotFoundException e) {
            System.out.println("[LOI] Khong tim thay JDBC Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("[LOI] Loi ket noi MySQL!");
            e.printStackTrace();
        }
        return conn;
    }
}