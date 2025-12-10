package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() throws SQLException {
        // Thong tin ket noi
        String url = "jdbc:mysql://localhost:3306/quanlyhocsinh";
        String user = "root"; // tai khoan MySQL
        String password = "Huyngu2006@"; // doi thanh mat khau that

        // Tao ket noi
        return DriverManager.getConnection(url, user, password);
    }
}