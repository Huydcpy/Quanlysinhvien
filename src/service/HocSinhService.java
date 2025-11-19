package service;
import model.HocSinh;
import dao.HocSinhDAO;
import java.sql.Date;
import java.util.List;
public class HocSinhService {
    private HocSinhDAO hocSinhDAO = new HocSinhDAO();
    // Thêm học sinh
    public boolean addHocSinh(String hoTen, String gioiTinh, Date ngaySinh, int maLop, String diaChi, String sdtOfBoMe, String email){
        if(hoTen == null || hoTen.isEmpty() || ngaySinh == null || maLop <= 0){
            System.out.println("Thông tin học sinh không hợp lệ.");
            return false;
        }
        HocSinh hs = new HocSinh(0, hoTen, ngaySinh, maLop, diaChi, gioiTinh, sdtOfBoMe, email);
        return hocSinhDAO.addHocSinh(hs);
    }
    // Lấy danh sách học sinh
    public List<HocSinh> getAllHocSinh(){
        return hocSinhDAO.getAllHocSinh();
    }
    // Sửa học sinh
    public boolean updateHocSinh(int maHS, String hoTen, String gioiTinh, Date ngaySinh, int maLop, String diaChi, String sdtOfBoMe, String email){
        if(maHS <= 0 || hoTen == null || hoTen.isEmpty() || ngaySinh == null || maLop <= 0){
            System.out.println("Thông tin học sinh không hợp lệ.");
            return false;
        }
        HocSinh hs = new HocSinh(maHS, hoTen, ngaySinh, maLop, diaChi, gioiTinh, sdtOfBoMe, email);
        return hocSinhDAO.updateHocSinh(hs);
    }
    // Xóa học sinh
    public boolean deleteHocSinh(int maHS){
        if(maHS <= 0){
            System.out.println("Mã học sinh không hợp lệ.");
            return false;
        }
        return hocSinhDAO.deleteHocSinh(maHS);
    }
    
}
