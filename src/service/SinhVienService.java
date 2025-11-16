package service;

import dao.SinhVienDAO;
import model.SinhVien;
import java.util.List;

public class SinhVienService {
    private SinhVienDAO svDao = new SinhVienDAO();

    // Thêm sinh viên
    public boolean themSinhVien(SinhVien sv) {
        if(sv.getHoTen() == null || sv.getHoTen().isEmpty()) {
            System.out.println("Tên sinh viên không được để trống!");
            return false;
        }
        if(sv.getDiemTB() < 0 || sv.getDiemTB() > 10) {
            System.out.println("Điểm trung bình phải từ 0 đến 10!");
            return false;
        }
        return svDao.addSinhVien(sv);
    }

    // Cập nhật sinh viên
    public boolean capNhatSinhVien(SinhVien sv) {
        return svDao.updateSinhVien(sv);
    }

    // Xóa sinh viên
    public boolean xoaSinhVien(int maSV) {
        return svDao.deleteSinhVien(maSV);
    }

    // Lấy danh sách tất cả sinh viên
    public List<SinhVien> getTatCaSinhVien() {
        return svDao.getAllSinhVien();
    }

    // Lấy sinh viên theo mã
    public SinhVien getSinhVienTheoMa(int maSV) {
        return svDao.getById(maSV);
    }
}
