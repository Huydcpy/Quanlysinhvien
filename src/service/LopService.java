package service;
import model.lop;
import dao.lopDAO;
import java.util.List;
public class LopService {
    private lopDAO lopDAO = new lopDAO();
    
    // Them lop
    public boolean addLop(String tenLop, int khoi){
        if(tenLop == null || tenLop.isEmpty() || khoi <= 0){
            System.out.println("Thong tin lop khong hop le.");
            return false;
        }
        lop lop = new lop(0, tenLop, khoi);
        return lopDAO.addLop(lop);
    }
    
    // Lay danh sach lop
    public List<lop> getAllLop(){
        return lopDAO.getAllLop();
    }
    
    // Sua lop
    public boolean updateLop(int maLop, String tenLop, int khoi){
        if(maLop <= 0 || tenLop == null || tenLop.isEmpty() || khoi <= 0){
            System.out.println("Thong tin lop khong hop le.");
            return false;
        }
        lop lop = new lop(maLop, tenLop, khoi);
        return lopDAO.updateLop(lop);
    }
    
    // Xoa lop
    public boolean deleteLop(int maLop){
        if(maLop <= 0){
            System.out.println("Ma lop khong hop le.");
            return false;
        }
        return lopDAO.deleteLop(maLop);
    }
}