package model;

public class Lop {
    private int maLop;     // PRIMARY KEY
    private String tenLop;

    public Lop() {}

    public Lop(int maLop, String tenLop) {
        this.maLop = maLop;
        this.tenLop = tenLop;
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
}
