package service;

import java.util.List;
import dao.DiemHsDAO;
import model.DiemHs;

public class DiemHSService {
    private DiemHsDAO diemHSDAO = new DiemHsDAO();

    // Them diem
    public boolean addDiem(int maHS, float toan, float nguVan, float ngoaiNgu, float gdcd,
                            float lichSu, float tinHoc, float theDuc, float khoaHoc) {
        DiemHs d = new DiemHs( maHS, toan, nguVan,ngoaiNgu, gdcd,
                            lichSu, tinHoc, theDuc,  khoaHoc);
        return diemHSDAO.addDiem(d);
    }

    // Sua diem (DA SUA)
    public boolean updateDiem(int maHS, float toan, float nguVan, float ngoaiNgu, float gdcd,
                              float lichSu, float tinHoc, float theDuc, float khoaHoc) {
        // Can tao doi tuong DiemHs voi du lieu moi de DAO co the cap nhat
        DiemHs d = new DiemHs(maHS, toan, nguVan, ngoaiNgu, gdcd, lichSu, tinHoc, theDuc, khoaHoc);
        return diemHSDAO.updateDiem(d);
    }

    // Xoa diem
    public boolean deleteDiem(int maHS) {
        return diemHSDAO.deleteDiem(maHS);
    }

    // Lay diem theo hoc sinh
    public DiemHs getDiemByMaHS(int maHS) {
        return diemHSDAO.getDiemByMaHS(maHS);
    }

    // Tinh diem trung binh
    public float tinhDiemTB(DiemHs d) {
        if (d == null) return 0;
        float sum = d.getDiemToan() + d.getDiemNguVan() + d.getDiemNgoaiNgu() + d.getDiemGDCD() +
                    d.getDiemLichSu() + d.getDiemTinHoc() + d.getDiemTheDuc() + d.getDiemKhoaHoc();
        return sum / 8;
    }

    public List<DiemHs> getAllDiemHs() {
        return diemHSDAO.getAllDiemHs();
    }
}