package dao;

import model.Lop;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LopDAO {

    // Lấy danh sách
    public List<Lop> getAll() {
        List<Lop> list = new ArrayList<>();
        String sql = "SELECT * FROM Lop";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Lop(
                        rs.getInt("maLop"),
                        rs.getString("tenLop"),
                        rs.getInt("maGV")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Thêm
    public boolean insert(Lop lop) {
        String sql = "INSERT INTO Lop(maLop, tenLop, maGV) VALUES(?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, lop.getMaLop());
            ps.setString(2, lop.getTenLop());
            ps.setInt(3, lop.getMaGV());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật
    public boolean update(Lop lop) {
        String sql = "UPDATE Lop SET tenLop=?, maGV=? WHERE maLop=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, lop.getTenLop());
            ps.setInt(2, lop.getMaGV());
            ps.setInt(3, lop.getMaLop());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xoá
    public boolean delete(int maLop) {
        String sql = "DELETE FROM Lop WHERE maLop=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maLop);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
