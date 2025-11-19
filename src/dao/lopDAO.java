package dao;
import model.lop;
import util.DBConnection;
import java.sql.*;
import java.util.List;
public class lopDAO {
    // Thêm lớp 
    public boolean addLop(lop lop){
        String sql = "INSERT INTO lop(tenLop, khoi) values(?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){

                    ps.setString(1, lop.getTenLop());
                    ps.setInt(2, lop.getKhoi());

                    return ps.executeUpdate() > 0;
                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
    }
    // Lấy danh sách lớp
    public List<lop> getAllLop(){
        List<lop> list = new java.util.ArrayList<>();
        String sql = "SELECT * FROM lop";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lop lop = new lop(
                    rs.getInt("maLop"),
                    rs.getString("tenLop"),
                    rs.getInt("khoi")
                );
                list.add(lop);

            }
    }catch(Exception e){
        e.printStackTrace();
    }
    return list;

    }
    // Sửa lớp
    public boolean updateLop(lop lop){
        String sql = "UPDATE lop SET tenLop = ?, khoi = ? WHERE maLop = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, lop.getTenLop());
            ps.setInt(2, lop.getKhoi());
            ps.setInt(3, lop.getMaLop());

            return ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    // Xóa lớp
    public boolean deleteLop(int maLop){
        String sql = "DELETE FROM lop WHERE maLop = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, maLop);

            return ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
    }
        return false;
    }
}