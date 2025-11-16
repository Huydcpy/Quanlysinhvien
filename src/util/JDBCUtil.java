package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() throws SQLException {
        // Thông tin kết nối
        String url = "jdbc:mysql://localhost:3306/quanlysinhvien";
        String user = "root"; // tài khoản MySQL
        String password = "Huyngu2006@"; // đổi thành mật khẩu thật

        // Tạo kết nối
        return DriverManager.getConnection(url, user, password);
    }
}
