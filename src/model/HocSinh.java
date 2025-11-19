package model;
import java.util.Date;
public class HocSinh {
    private int maHS;
    private String hoTen;
    private Date ngaySinh;
    private int maLop;
    private String diaChi;
    private String gioiTinh;
    private String sdtOfBoMe;
    private String email;
    public HocSinh(int maHS, String hoTen, Date ngaySinh, int maLop, String diaChi, String gioiTinh, String sdtOfBoMe, String email) {
        this.maHS = maHS;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.maLop = maLop;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.sdtOfBoMe = sdtOfBoMe;
        this.email = email;
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
    public Date getNgaySinh() {
        return ngaySinh;
    }
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public int getMaLop() {
        return maLop;
    }
    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }
    public String getDiachi() {
        return diaChi;
    }
    public void setDiachi(String diachi) {
        this.diaChi = diachi;
    }
    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    public String getSdtOfBoMe() {
        return sdtOfBoMe;
    }
    public void setSdtOfBoMe(String sdtOfBoMe) {
        this.sdtOfBoMe = sdtOfBoMe;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}
