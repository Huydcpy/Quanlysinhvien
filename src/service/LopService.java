package service;

import dao.LopDAO;
import model.Lop;
import java.util.List;

public class LopService {
    private LopDAO lopDao = new LopDAO();

    // Thêm lớp
    public boolean themLop(Lop lop) {
        if(lop.getTenLop() == null || lop.getTenLop().isEmpty()) {
            System.out.println("Tên lớp không được để trống!");
            return false;
        }
        return lopDao.insert(lop);
    }

    // Cập nhật lớp
    public boolean capNhatLop(Lop lop) {
        return lopDao.update(lop);
    }

    // Xóa lớp
    public boolean xoaLop(int maLop) {
        return lopDao.delete(maLop);
    }

    // Lấy danh sách tất cả lớp
    public List<Lop> getTatCaLop() {
        return lopDao.getAll();
    }

    // Lấy lớp theo mã
    public Lop getLopTheoMa(int maLop) {
        return lopDao.getAll().stream()
                     .filter(l -> l.getMaLop() == maLop)
                     .findFirst().orElse(null);
    }
}
