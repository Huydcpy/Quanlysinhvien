package service;

import dao.DiemHsDAO;
import model.DiemHs;

public class DiemHSService {
    private DiemHsDAO diemHSDAO = new DiemHsDAO();

    // Thêm điểm
    public boolean addDiem(int maHS, float toan, float nguVan, float ngoaiNgu, float gdcd,
                            float lichSu, float tinHoc, float theDuc, float khoaHoc) {
        DiemHs d = new DiemHs( maHS, toan, nguVan,ngoaiNgu, gdcd,
                            lichSu, tinHoc, theDuc,  khoaHoc);
        return diemHSDAO.addDiem(d);
    }

    // Sửa điểm
    public boolean updateDiem(int maHS, float toan, float nguVan, float ngoaiNgu, float gdcd,
                              float lichSu, float tinHoc, float theDuc, float khoaHoc) {
        DiemHs d = new DiemHs();
        return diemHSDAO.updateDiem(d);
    }

    // Xóa điểm
    public boolean deleteDiem(int maHS) {
        return diemHSDAO.deleteDiem(maHS);
    }

    // Lấy điểm theo học sinh
    public DiemHs getDiemByMaHS(int maHS) {
        return diemHSDAO.getDiemByMaHS(maHS);
    }

    // Tính điểm trung bình
    public float tinhDiemTB(DiemHs d) {
        if (d == null) return 0;
        float sum = d.getDiemToan() + d.getDiemNguVan() + d.getDiemNgoaiNgu() + d.getDiemGDCD() +
                    d.getDiemLichSu() + d.getDiemTinHoc() + d.getDiemTheDuc() + d.getDiemKhoaHoc();
        return sum / 8;
    }
}
