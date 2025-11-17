package model;

import java.sql.Date;

public class HocSinh {
    private int maHS;        // PRIMARY KEY
    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;   // kiểu DATE trong DB
    private String diaChi;
    private int maLop;       // FOREIGN KEY → Lop.maLop
    private float diemTB;    // có trong DB

    public HocSinh() {}

    public HocSinh(int maHS, String hoTen, String gioiTinh, Date ngaySinh, 
                   String diaChi, int maLop, float diemTB) {
        this.maHS = maHS;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.maLop = maLop;
        this.diemTB = diemTB;
    }

    public int getMaHS() {
        return maHS;
    }

    public void setMaHS(int maHS) {
        this.maHS = maHS;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public float getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(float diemTB) {
        this.diemTB = diemTB;
    }
}
