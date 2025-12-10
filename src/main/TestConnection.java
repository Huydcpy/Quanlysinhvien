package main;
import util.DBConnection;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection connection = DBConnection.getConnection();
        if (connection != null) {
            System.out.println("DA ket noi den co so du lieu thanh cong!");
        } else {
            System.out.println("KHONG the ket noi den co so du lieu.");
        }
    }
}