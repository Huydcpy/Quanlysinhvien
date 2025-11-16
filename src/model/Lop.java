package model;

public class Lop {
    private int maLop;
    private String tenLop;
    private int maGV;
    public Lop(){

    }
    public Lop(int maLop, String tenLop, int maGV) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.maGV = maGV;
    }
    public int getMaLop() {
        return maLop;
    }
    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }
    public String getTenLop() {
        return tenLop;
    }
    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
    public int getMaGV() {
        return maGV;
    }
    public void setMaGV(int maGV) {
        this.maGV = maGV;
    }
    public String toString() {
        return "Lop{" +
                "maLop=" + maLop +
                ", tenLop='" + tenLop + '\'' +
                ", maGV=" + maGV +
                '}';
    }
}
