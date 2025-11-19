package model;

public class HocSinh {
    private int maHS;
    private String hoTen;
    private String ngaySinh;
    private int maLop;
    private String diachi;
    private String gioiTinh;
    private int sdtOfBoMe;
    private String email;
    public HocSinh(int maHS, String hoTen, String ngaySinh, int maLop, String diachi, String gioiTinh, int sdtOfBoMe, String email) {
        this.maHS = maHS;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.maLop = maLop;
        this.diachi = diachi;
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
    public String getNgaySinh() {
        return ngaySinh;
    }
    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public int getMaLop() {
        return maLop;
    }
    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }
    public String getDiachi() {
        return diachi;
    }
    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    public int getSdtOfBoMe() {
        return sdtOfBoMe;
    }
    public void setSdtOfBoMe(int sdtOfBoMe) {
        this.sdtOfBoMe = sdtOfBoMe;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    

}
