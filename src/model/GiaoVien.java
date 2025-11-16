package model;

public class GiaoVien {
    private int maGV;
    private String hoTen;
    private String chuyenMon;
    private double luong;
    public GiaoVien(int maGV, String hoTen, String chuyenMon, double luong) {
        this.maGV = maGV;
        this.hoTen = hoTen;
        this.chuyenMon = chuyenMon;
        this.luong = luong;
    }
    public int getMaGV(){
        return maGV;
    
    }
    public void setMaGV(int maGV){
        this.maGV = maGV;
    }
    public String getHoTen(){
        return hoTen;
    }
    public void setHoTen(String hoTen){
        this.hoTen = hoTen;
    }
    public String getChuyenMon(){
        return chuyenMon;
    }
    public void setChuyenMon(String chuyenMon){
        this.chuyenMon = chuyenMon;
    }
    public double getLuong(){
        return luong;
    }
    public void setLuong(double luong){
        this.luong = luong;
    }
    public String toString(){
         return "GiaoVien{" +
                "maGV=" + maGV +
                ", hoTen='" + hoTen + '\'' +
                ", chuyenMon='" + chuyenMon + '\'' +
                ", luong=" + luong +
                '}';
    }
}
