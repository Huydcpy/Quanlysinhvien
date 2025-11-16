package service;

import dao.GiaoVienDAO;
import model.GiaoVien;
import java.util.List;

public class GiaoVienService {
    private GiaoVienDAO gvDao = new GiaoVienDAO();

    // Thêm giáo viên
    public boolean themGiaoVien(GiaoVien gv) {
        if(gv.getHoTen() == null || gv.getHoTen().isEmpty()) {
            System.out.println("Tên giáo viên không được để trống!");
            return false;
        }
        return gvDao.insert(gv);
    }

    // Cập nhật giáo viên
    public boolean capNhatGiaoVien(GiaoVien gv) {
        return gvDao.update(gv);
    }

    // Xóa giáo viên
    public boolean xoaGiaoVien(int maGV) {
        return gvDao.delete(maGV);
    }

    // Lấy danh sách tất cả giáo viên
    public List<GiaoVien> getTatCaGiaoVien() {
        return gvDao.getAll();
    }

    // Lấy giáo viên theo mã
    public GiaoVien getGiaoVienTheoMa(int maGV) {
        return gvDao.getAll().stream()
                    .filter(gv -> gv.getMaGV() == maGV)
                    .findFirst().orElse(null);
    }
}
