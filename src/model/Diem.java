package model;

public class Diem {
    private int id;        // PRIMARY KEY
    private int maHS;      // FOREIGN KEY → HocSinh.maHS
    private String monHoc;
    private float diem;

    public Diem() {}

    public Diem(int id, int maHS, String monHoc, float diem) {
        this.id = id;
        this.maHS = maHS;
        this.monHoc = monHoc;
        this.diem = diem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaHS() {
        return maHS;
    }

    public void setMaHS(int maHS) {
        this.maHS = maHS;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }
}
