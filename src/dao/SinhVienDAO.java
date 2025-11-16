package dao;

import model.SinhVien;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {

    // Thêm sinh viên
    public boolean addSinhVien(SinhVien sv) {
        String sql = "INSERT INTO SinhVien(hoTen, gioiTinh, ngaySinh, lop, maLop, diemTB) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, sv.getHoTen());
            stmt.setString(2, sv.getGioiTinh());
            stmt.setDate(3, sv.getNgaySinh());
            stmt.setString(4, sv.getLop());
            stmt.setInt(5, sv.getMaLop());
            stmt.setFloat(6, sv.getDiemTB());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Cập nhật sinh viên
    public boolean updateSinhVien(SinhVien sv) {
        String sql = "UPDATE SinhVien SET hoTen=?, gioiTinh=?, ngaySinh=?, lop=?, maLop=?, diemTB=? "
                   + "WHERE maSV=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, sv.getHoTen());
            stmt.setString(2, sv.getGioiTinh());
            stmt.setDate(3, sv.getNgaySinh());
            stmt.setString(4, sv.getLop());
            stmt.setInt(5, sv.getMaLop());
            stmt.setFloat(6, sv.getDiemTB());
            stmt.setInt(7, sv.getMaSV());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Xóa sinh viên
    public boolean deleteSinhVien(int maSV) {
        String sql = "DELETE FROM SinhVien WHERE maSV=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maSV);

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách tất cả sinh viên
    public List<SinhVien> getAllSinhVien() {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                SinhVien sv = new SinhVien(
                    rs.getInt("maSV"),
                    rs.getString("hoTen"),
                    rs.getString("gioiTinh"),
                    rs.getDate("ngaySinh"),
                    rs.getString("lop"),
                    rs.getInt("maLop"),
                    rs.getFloat("diemTB")
                );
                list.add(sv);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    // Lấy sinh viên theo mã
    public SinhVien getById(int maSV) {
        String sql = "SELECT * FROM SinhVien WHERE maSV = ?";
        SinhVien sv = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maSV);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sv = new SinhVien(
                    rs.getInt("maSV"),
                    rs.getString("hoTen"),
                    rs.getString("gioiTinh"),
                    rs.getDate("ngaySinh"),
                    rs.getString("lop"),
                    rs.getInt("maLop"),
                    rs.getFloat("diemTB")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return sv;
    }
}
