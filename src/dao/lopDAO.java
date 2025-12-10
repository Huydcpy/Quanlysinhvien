package dao;
import model.lop;
import util.DBConnection;
import java.sql.*;
import java.util.List;
import java.util.ArrayList; // Them import cho ArrayList

public class lopDAO {
    
    // Them lop 
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
    // Tim lop
    public List<lop> searchLop(String searchTerm) {
    List<lop> list = new ArrayList<>();
    // Tim theo tenLop hoac Khoi (chuyen Khoi sang chuoi de tim kiem)
    String sql = "SELECT * FROM lop WHERE tenLop LIKE ? OR CAST(khoi AS CHAR) LIKE ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        String searchPattern = "%" + searchTerm + "%";
        ps.setString(1, searchPattern);
        ps.setString(2, searchPattern);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lop lop = new lop(
                rs.getInt("maLop"),
                rs.getString("tenLop"),
                rs.getInt("khoi")
            );
            list.add(lop);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
    
    // Lay danh sach lop
    public List<lop> getAllLop(){
        // Thay the "new java.util.ArrayList<>()" thanh "new ArrayList<>()" 
        List<lop> list = new ArrayList<>(); 
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
    
    // Sua lop
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
    
    // Xoa lop
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