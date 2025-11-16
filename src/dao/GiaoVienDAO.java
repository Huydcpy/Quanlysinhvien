package dao;
import model.GiaoVien;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class GiaoVienDAO {
    public List<GiaoVien> getAll(){
        List<GiaoVien> list = new ArrayList<>();
        String sql = "SELECT * FROM giaovien";
        
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

                while (rs.next()){
                    list.add(new GiaoVien(
                            rs.getInt("maGV"),
                            rs.getString("hoTen"),
                            rs.getString("chuyenMon"),
                            rs.getDouble("luong")

                    ));

                }
            } catch(Exception e){
                e.printStackTrace();
            }
            return list;
            
                
    }
    //Thêm giáo viên
    public boolean insert(GiaoVien gv){
        String sql = "INSERT INTO giaovien (maGV, hoTen, chuyenMon, luong) values (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps =  conn.prepareStatement(sql)){
                ps.setString(1,gv.getHoTen());
                ps.setString(2,gv.getChuyenMon());
                ps.setDouble(3,gv.getLuong());
                
                return ps.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
            }
        return false;
    }
    

    //Cập nhật giáo viên
    public boolean update(GiaoVien gv){
    String sql = "UPDATE giaovien SET hoTen = ?, chuyenMon = ?, luong = ? WHERE maGV = ?";

    try(Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, gv.getHoTen());
            ps.setString(2, gv.getChuyenMon());
            ps.setDouble(3, gv.getLuong());
            ps.setInt(4, gv.getMaGV());

            return ps.executeUpdate() > 0;
    } catch (SQLException e){
        e.printStackTrace();
    }
    return false;
    }
    // Xóa giáo viên
    public boolean delete(int maGV){
    String sql = "DELETE FROM giaovien WHERE maGV = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, maGV);
            return ps.executeUpdate() > 0;
    } catch (SQLException e){
        e.printStackTrace();
    }
    return false;
    }
}
