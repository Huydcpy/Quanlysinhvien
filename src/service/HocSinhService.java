package service;
import model.HocSinh;
import dao.HocSinhDAO;
import java.sql.Date;
import java.util.List;
public class HocSinhService {
    private HocSinhDAO hocSinhDAO = new HocSinhDAO();
    
    // Them hoc sinh
    public boolean addHocSinh(String hoTen, String gioiTinh, Date ngaySinh, int maLop, String diaChi, String sdtOfBoMe, String email){
        if(hoTen == null || hoTen.isEmpty() || ngaySinh == null || maLop <= 0){
            System.out.println("Thong tin hoc sinh khong hop le.");
            return false;
        }
        HocSinh hs = new HocSinh(0, hoTen, ngaySinh, maLop, diaChi, gioiTinh, sdtOfBoMe, email);
        return hocSinhDAO.addHocSinh(hs);
    }
    
    // Lay danh sach hoc sinh
    public List<HocSinh> getAllHocSinh(){
        return hocSinhDAO.getAllHocSinh();
    }
    
    // Sua hoc sinh
    public boolean updateHocSinh(int maHS, String hoTen, String gioiTinh, Date ngaySinh, int maLop, String diaChi, String sdtOfBoMe, String email){
        if(maHS <= 0 || hoTen == null || hoTen.isEmpty() || ngaySinh == null || maLop <= 0){
            System.out.println("Thong tin hoc sinh khong hop le.");
            return false;
        }
        HocSinh hs = new HocSinh(maHS, hoTen, ngaySinh, maLop, diaChi, gioiTinh, sdtOfBoMe, email);
        return hocSinhDAO.updateHocSinh(hs);
    }
    
    // Xoa hoc sinh
    public boolean deleteHocSinh(int maHS){
        if(maHS <= 0){
            System.out.println("Ma hoc sinh khong hop le.");
            return false;
        }
        return hocSinhDAO.deleteHocSinh(maHS);
    }
    
}