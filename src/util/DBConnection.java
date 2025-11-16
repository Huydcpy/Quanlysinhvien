package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/quanlysinhvien";
    private static final String USER = "root";
    private static final String PASSWORD = "Huyngu2006@"; // thay bằng mật khẩu thật

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // nạp driver
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Kết nối MySQL thành công!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Không tìm thấy JDBC Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Lỗi kết nối MySQL!");
            e.printStackTrace();
        }
        return conn;
    }
}
