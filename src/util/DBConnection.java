package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
    public static void initializeDatabase() {
        System.out.println("[DB INIT] Dang khoi tao cau truc CSDL...");
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Dam bao ket noi thanh con
            if (conn != null) { 
                // 1. Tao bang lop
                String createLop = "CREATE TABLE IF NOT EXISTS lop ("
                        + "maLop INT PRIMARY KEY AUTO_INCREMENT,"
                        + "tenLop VARCHAR(100) NOT NULL,"
                        + "khoi INT NOT NULL"
                        + ")";
                stmt.execute(createLop);
                
                // 2. Tao bang hocSinh
                String createHocSinh = "CREATE TABLE IF NOT EXISTS hocSinh ("
                        + "maHS INT PRIMARY KEY AUTO_INCREMENT,"
                        + "hoTen VARCHAR(100) NOT NULL,"
                        + "ngaySinh DATE,"
                        + "maLop INT,"
                        + "diaChi VARCHAR(255),"
                        + "gioiTinh VARCHAR(10),"
                        + "sdtOfBoMe VARCHAR(15),"
                        + "email VARCHAR(100),"
                        + "FOREIGN KEY (maLop) REFERENCES lop(maLop) ON DELETE CASCADE"
                        + ")";
                stmt.execute(createHocSinh);
                
                // 3. Tao bang diemHS
                String createDiemHS = "CREATE TABLE IF NOT EXISTS diemHS ("
                        + "maHS INT PRIMARY KEY,"
                        + "diemToan FLOAT NOT NULL,"
                        + "diemNguVan FLOAT NOT NULL,"
                        + "diemNgoaiNgu FLOAT NOT NULL,"
                        + "diemGDCD FLOAT NOT NULL,"
                        + "diemLichSu FLOAT NOT NULL,"
                        + "diemTinHoc FLOAT NOT NULL,"
                        + "diemTheDuc FLOAT NOT NULL,"
                        + "diemKhoaHoc FLOAT NOT NULL,"
                        + "FOREIGN KEY (maHS) REFERENCES hocSinh(maHS) ON DELETE CASCADE"
                        + ")";
                stmt.execute(createDiemHS);

                System.out.println("[DB INIT] Khoi tao CSDL thanh cong (cac bang da san sang).");
            }

        } catch (Exception e) {
            System.err.println("[DB INIT] LOI khi khoi tao cau truc CSDL.");
            e.printStackTrace();
        }
    }
}