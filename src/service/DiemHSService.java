package service;
import model.HocSinh;
import model.DiemHS;
import dao.DiemHSDAO;
import dao.HocSinhDAO;

public class DiemHSService {
     private DiemHSDAO diemHSDAO = new DiemHSDAO();
     public boolean addDiem(int maHS, float toan, float nguVan, float ngoaiNgu, float gdcd,
                            float lichSu, float tinHoc, float theDuc, float khoaHoc) {
        DiemHS d = new DiemHS(maHS, toan, nguVan, ngoaiNgu, gdcd, lichSu, tinHoc, theDuc, khoaHoc);
        return diemHSDAO.addDiem(d);
    }

    // Sửa điểm
    public boolean updateDiem(int maHS, float toan, float nguVan, float ngoaiNgu, float gdcd,
                              float lichSu, float tinHoc, float theDuc, float khoaHoc) {
        DiemHS d = new DiemHS(maHS, toan, nguVan, ngoaiNgu, gdcd, lichSu, tinHoc, theDuc, khoaHoc);
        return diemHSDAO.updateDiem(d);
    }

    // Xóa điểm
    public boolean deleteDiem(int maHS) {
        return diemHSDAO.deleteDiem(maHS);
    }

    // Lấy điểm theo học sinh
    public DiemHS getDiemByMaHS(int maHS) {
        return diemHSDAO.getDiemByMaHS(maHS);
    }

    // Tính điểm trung bình
    public float tinhDiemTB(DiemHS d) {
        if (d == null) return 0;
        float sum = d.getDiemToan() + d.getDiemNguVan() + d.getDiemNgoaiNgu() + d.getDiemGDCD() +
                    d.getDiemLichSu() + d.getDiemTinHoc() + d.getDiemTheDuc() + d.getDiemKhoaHoc();
        return sum / 8;
    }
}
