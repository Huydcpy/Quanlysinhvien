package dao;
import model.HocSinh;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HocSinhDAO {
    // Them Hocsinh; (DA SUA)
    public boolean addHocSinh(HocSinh hs){
        // Loai bo maHS khoi danh sach cot, gia su no la AUTO_INCREMENT
        String sql = "INSERT INTO hocSinh(hoTen, ngaySinh, maLop, diaChi, gioiTinh, sdtOfBoMe, email) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                // ps.setInt(1, hs.getMaHS()); // Removed to fix auto-increment issue
                ps.setString(1, hs.getHoTen());
                ps.setDate(2, hs.getNgaySinh());
                ps.setInt(3, hs.getMaLop());
                ps.setString(4, hs.getDiachi());
                ps.setString(5, hs.getGioiTinh());
                ps.setString(6, hs.getSdtOfBoMe());
                ps.setString(7, hs.getEmail());
                return ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
            }
            return false;
    }
    
    // Lay danh sach hoc sinh
    public List<HocSinh> getAllHocSinh(){
        List<HocSinh> list = new ArrayList<>();
        String sql = "SELECT * FROM hocSinh";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HocSinh hs = new HocSinh(
                    rs.getInt("maHS"),
                    rs.getString("hoTen"),
                    rs.getDate("ngaySinh"),
                    rs.getInt("maLop"),
                    rs.getString("diaChi"),
                    rs.getString("gioiTinh"),
                    rs.getString("sdtOfBoMe"),
                    rs.getString("email")
                );
                list.add(hs);
            }
            }catch (Exception e){
                e.printStackTrace();
            }
            return list;
        }
        
    // Sua hoc sinh
    public boolean updateHocSinh(HocSinh hs){
        String sql = "UPDATE hocSinh SET hoTen = ?, ngaySinh = ?, maLop = ?, diaChi = ?, gioiTinh = ?, sdtOfBoMe = ?, email = ? WHERE maHS = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, hs.getHoTen());
                ps.setDate(2, hs.getNgaySinh());
                ps.setInt(3, hs.getMaLop());
                ps.setString(4, hs.getDiachi());
                ps.setString(5, hs.getGioiTinh());
                ps.setString(6, hs.getSdtOfBoMe());
                ps.setString(7, hs.getEmail());
                ps.setInt(8, hs.getMaHS());

                return ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    // Xoa hoc sinh
    public boolean deleteHocSinh(int maHS){
        String sql = "DELETE FROM hocSinh WHERE maHS = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setInt(1, maHS);

                return ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    // Tim kiem hoc sinh (NEW)
    public List<HocSinh> searchHocSinh(String searchTerm){
        List<HocSinh> list = new ArrayList<>();
        // Tim theo hoTen, maHS, hoac maLop (chuyen sang chuoi de tim kiem)
        String sql = "SELECT * FROM hocSinh WHERE hoTen LIKE ? OR CAST(maHS AS CHAR) LIKE ? OR CAST(maLop AS CHAR) LIKE ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            String searchPattern = "%" + searchTerm + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HocSinh hs = new HocSinh(
                    rs.getInt("maHS"),
                    rs.getString("hoTen"),
                    rs.getDate("ngaySinh"),
                    rs.getInt("maLop"),
                    rs.getString("diaChi"),
                    rs.getString("gioiTinh"),
                    rs.getString("sdtOfBoMe"),
                    rs.getString("email")
                );
                list.add(hs);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}