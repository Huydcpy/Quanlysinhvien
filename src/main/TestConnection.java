package main;
import util.DBConnection;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection connection = DBConnection.getConnection();
        if (connection != null) {
            System.out.println("ĐÃ kết nói đến cơ sở dữ liệu thành công!");
        } else {
            System.out.println("KHÔNG thể kết nối đến cơ sở dữ liệu.");
        }
    }
    
}
