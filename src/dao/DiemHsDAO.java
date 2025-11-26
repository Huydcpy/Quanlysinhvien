package dao;
import model.DiemHs;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class DiemHsDAO {
    //Them diem
    public boolean addDiem(DiemHs d){
        String sql = "INSERT INTO diemHS(maHS, diemToan, diemNguVan, diemNgoaiNgu, diemGDCD, diemLichSu, diemTinHoc, diemTheDuc, diemKhoaHoc) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, d.getMaHS());
            ps.setFloat(2, d.getDiemToan());
            ps.setFloat(3, d.getDiemNguVan());
            ps.setFloat(4, d.getDiemNgoaiNgu());
            ps.setFloat(5, d.getDiemGDCD());
            ps.setFloat(6, d.getDiemLichSu());
            ps.setFloat(7, d.getDiemTinHoc());
            ps.setFloat(8, d.getDiemTheDuc());
            ps.setFloat(9, d.getDiemKhoaHoc());

            return ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        
    }return false;
    }
    //Lay diem theo maHS
    public DiemHs getDiemByMaHS(int maHS){
        String sql = "SELECT * FROM diemHS WHERE  maHS = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setInt(1,maHS);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    DiemHs d = new DiemHs();
                    d.setMaHS(rs.getInt("maHS"));
                    d.setDiemToan(rs.getFloat("diemToan"));
                    d.setDiemNguVan(rs.getFloat("diemNguVan"));
                    d.setDiemNgoaiNgu(rs.getFloat("diemNgoaiNgu"));
                    d.setDiemGDCD(rs.getFloat("diemGDCD"));
                    d.setDiemLichSu(rs.getFloat("diemLichSu"));
                    d.setDiemTinHoc(rs.getFloat("diemTinHoc"));
                    d.setDiemTheDuc(rs.getFloat("diemTheDuc"));
                    d.setDiemKhoaHoc(rs.getFloat("diemKhoaHoc"));
                    return d;

                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
            }
        // Sua diem
        public boolean updateDiem(DiemHs d){
            String sql = "UPDATE diemHS SET diemToan=?, diemNguVan=?, diemNgoaiNgu=?, diemGDCD=?, diemLichSu=?, diemTinHoc=?, diemTheDuc=?, diemKhoaHoc=? WHERE maHS=?";
            try(Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){

                    ps.setFloat(1, d.getDiemToan());
                    ps.setFloat(2, d.getDiemNguVan());
                    ps.setFloat(3, d.getDiemNgoaiNgu());
                    ps.setFloat(4, d.getDiemGDCD());
                    ps.setFloat(5, d.getDiemLichSu());
                    ps.setFloat(6, d.getDiemTinHoc());
                    ps.setFloat(7, d.getDiemTheDuc());
                    ps.setFloat(8, d.getDiemKhoaHoc());
                    ps.setInt(9, d.getMaHS());

                    return ps.executeUpdate() > 0;

                }catch(Exception e){
                    e.printStackTrace();
                }
                return false;
            }
            //xoadiem
            public boolean deleteDiem(int maHS) {
        String sql = "DELETE FROM diemHS WHERE maHS = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maHS);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

            
    }
