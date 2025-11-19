package service;
import model.lop;
import dao.lopDAO;
import java.util.List;
public class LopService {
    private lopDAO lopDAO = new lopDAO();
    // Thêm lớp
    public boolean addLop(String tenLop, int khoi){
        if(tenLop == null || tenLop.isEmpty() || khoi <= 0){
            System.out.println("Thông tin lớp không hợp lệ.");
            return false;
        }
        lop lop = new lop(0, tenLop, khoi);
        return lopDAO.addLop(lop);
    }
    // Lấy danh sách lớp
    public List<lop> getAllLop(){
        return lopDAO.getAllLop();
    }
    // Sửa lớp
    public boolean updateLop(int maLop, String tenLop, int khoi){
        if(maLop <= 0 || tenLop == null || tenLop.isEmpty() || khoi <= 0){
            System.out.println("Thông tin lớp không hợp lệ.");
            return false;
        }
        lop lop = new lop(maLop, tenLop, khoi);
        return lopDAO.updateLop(lop);
    }
    // Xóa lớp
    public boolean deleteLop(int maLop){
        if(maLop <= 0){
            System.out.println("Mã lớp không hợp lệ.");
            return false;
        }
        return lopDAO.deleteLop(maLop);
    }
}
