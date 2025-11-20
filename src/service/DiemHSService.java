package service;

import dao.DiemHSDAO;
import model.DiemHs;

public class DiemHSService {
    private DiemHSDAO diemHSDAO = new DiemHSDAO();

    // Thêm điểm
    public boolean addDiem(int maHS, float toan, float nguVan, float ngoaiNgu, float gdcd,
                            float lichSu, float tinHoc, float theDuc, float khoaHoc) {
        DiemHS d = new DiemHS();
        return diemHSDAO.addDiem(d);
    }

    // Sửa điểm
    public boolean updateDiem(int maHS, float toan, float nguVan, float ngoaiNgu, float gdcd,
                              float lichSu, float tinHoc, float theDuc, float khoaHoc) {
        DiemHS d = new DiemHS();
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
    public float tinhDiemTB(DiemHS d) {
        if (d == null) return 0;
        float sum = d.getDiemToan() + d.getDiemNguVan() + d.getDiemNgoaiNgu() + d.getDiemGDCD() +
                    d.getDiemNgu + d.getDiemGDCD() + d.getDiemTheDuc() + d.getDiemKhoaHoc();
        return sum / 8;
    }
}
