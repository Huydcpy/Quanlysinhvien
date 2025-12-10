package main;
import service.LopService;
import service.HocSinhService;
import service.DiemHSService;

import model.HocSinh;
import model.DiemHs;
import model.lop;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static LopService lopService = new LopService();
    private static HocSinhService hocSinhService = new HocSinhService();
    private static DiemHSService diemHSService = new DiemHSService();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== HE THONG QUAN LY HOC SINH =====");
            System.out.println("1. Quan ly Lop");
            System.out.println("2. Quan ly Hoc Sinh");
            System.out.println("3. Quan ly Diem");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int chon = sc.nextInt();

            switch (chon) {
                case 1:
                    menuLop();
                    break;
                case 2:
                    menuHocSinh();
                    break;
                case 3:
                    menuDiem();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }

    // ============================
    // MENU QUAN LY LOP
    // ============================
    public static void menuLop() {
        while (true) {
            System.out.println("\n--- QUAN LY LOP ---");
            System.out.println("1. Them lop");
            System.out.println("2. Xem danh sach lop");
            System.out.println("3. Sua lop");
            System.out.println("4. Xoa lop");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            int chon = sc.nextInt();

            if (chon == 0) return;

            switch (chon) {
                case 1:
                    sc.nextLine();
                    System.out.print("Ten lop: ");
                    String tenLop = sc.nextLine();
                    System.out.print("Khoi: ");
                    int khoi = sc.nextInt();
                    if (lopService.addLop(tenLop, khoi))
                        System.out.println("Them lop thanh cong!");
                    else System.out.println("Them that bai!");
                    break;

                case 2:
                    List<lop> lops = lopService.getAllLop();
                    for (lop l : lops) {
                        System.out.println("Ma: " + l.getMaLop() + " | Ten lop: " + l.getTenLop() + " | Khoi: " + l.getKhoi());
                    }
                    break;

                case 3:
                    System.out.print("Nhap ma lop can sua: ");
                    int maLS = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ten lop moi: ");
                    String tenLopM = sc.nextLine();
                    System.out.print("Khoi: ");
                    int khoiM = sc.nextInt();
                    if (lopService.updateLop(maLS, tenLopM, khoiM))
                        System.out.println("Sua thanh cong!");
                    else System.out.println("Sua that bai!");
                    break;

                case 4:
                    System.out.print("Nhap ma lop can xoa: ");
                    int maLX = sc.nextInt();
                    if (lopService.deleteLop(maLX))
                        System.out.println("Xoa thanh cong!");
                    else System.out.println("Xoa that bai!");
                    break;

                default:
                    System.out.println("Sai lua chon.");
            }
        }
    }

    // ============================
    // MENU QUAN LY HOC SINH
    // ============================
    public static void menuHocSinh() {
        while (true) {
            System.out.println("\n--- QUAN LY HOC SINH ---");
            System.out.println("1. Them hoc sinh");
            System.out.println("2. Xem danh sach hoc sinh");
            System.out.println("3. Sua hoc sinh");
            System.out.println("4. Xoa hoc sinh");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            int chon = sc.nextInt();

            if (chon == 0) return;

            switch (chon) {
                case 1:
                    sc.nextLine();
                    System.out.print("Ten HS: ");
                    String ten = sc.nextLine();
                    System.out.print("Gioi tinh: ");
                    String gt = sc.nextLine();
                    System.out.print("Ngay sinh (yyyy-mm-dd): ");
                    Date ns = Date.valueOf(sc.nextLine());
                    System.out.print("Ma lop: ");
                    int ml = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Dia chi: ");
                    String dc = sc.nextLine();
                    System.out.print("SDT bo me: ");
                    String sdt = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    if (hocSinhService.addHocSinh(ten, gt, ns, ml, dc, sdt, email))
                        System.out.println("Them thanh cong!");
                    else System.out.println("Them that bai!");
                    break;

                case 2:
                    List<HocSinh> hss = hocSinhService.getAllHocSinh();
                    for (HocSinh h : hss) {
                        System.out.println(h.getMaHS() + " | " + h.getHoTen() + " | Lop: " + h.getMaLop());
                    }
                    break;

                case 3:
                    System.out.print("Nhap ma HS can sua: ");
                    int maS = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ten moi: ");
                    String tNew = sc.nextLine();
                    System.out.print("Gioi tinh moi: ");
                    String gtNew = sc.nextLine();
                    System.out.print("Ngay sinh moi (yyyy-mm-dd): ");
                    Date nsNew = Date.valueOf(sc.nextLine());
                    System.out.print("Ma lop moi: ");
                    int mlNew = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Dia chi moi: ");
                    String dcNew = sc.nextLine();
                    System.out.print("SDT moi: ");
                    String sdtNew = sc.nextLine();
                    System.out.print("Email moi: ");
                    String emailNew = sc.nextLine();

                    if (hocSinhService.updateHocSinh(maS, tNew, gtNew, nsNew, mlNew, dcNew, sdtNew, emailNew))
                        System.out.println("Cap nhat thanh cong!");
                    else System.out.println("Cap nhat that bai!");
                    break;

                case 4:
                    System.out.print("Nhap ma HS can xoa: ");
                    int maX = sc.nextInt();
                    if (hocSinhService.deleteHocSinh(maX))
                        System.out.println("Xoa thanh cong!");
                    else System.out.println("Xoa that bai!");
                    break;

                default:
                    System.out.println("Sai lua chon.");
            }
        }
    }

    // ============================
    // MENU QUAN LY DIEM
    // ============================
    public static void menuDiem() {
        while (true) {
            System.out.println("\n--- QUAN LY DIEM ---");
            System.out.println("1. Nhap diem cho hoc sinh");
            System.out.println("2. Xem diem theo ma hoc sinh");
            System.out.println("3. Sua diem");
            System.out.println("4. Xoa diem");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            int chon = sc.nextInt();

            if (chon == 0) return;

            switch (chon) {
                case 1:
                    System.out.print("Ma hoc sinh: ");
                    int maHS = sc.nextInt();
                    System.out.println("Nhap lan luot diem 8 mon:");
                    float toan = sc.nextFloat();
                    float van = sc.nextFloat();
                    float nn = sc.nextFloat();
                    float gdcd = sc.nextFloat();
                    float ls = sc.nextFloat();
                    float tin = sc.nextFloat();
                    float td = sc.nextFloat();
                    float kh = sc.nextFloat();

                    if (diemHSService.addDiem(maHS, toan, van, nn, gdcd, ls, tin, td, kh))
                        System.out.println("Nhap diem thanh cong!");
                    else System.out.println("That bai!");
                    break;

                case 2:
                    System.out.print("Nhap ma HS: ");
                    int ma = sc.nextInt();
                    DiemHs d = diemHSService.getDiemByMaHS(ma);
                    if (d == null) System.out.println("Khong co du lieu!");
                    else {
                        System.out.println("Diem Toan: " + d.getDiemToan());
                        System.out.println("Diem Van: " + d.getDiemNguVan());
                        System.out.println("Diem Ngoai Ngu: "+d.getDiemNgoaiNgu()); // Fix from previous step (DiemGDCD was here)
                        System.out.println("Diem GDCD: " + d.getDiemGDCD()); // Fix from previous step (DiemLichSu was here)
                        System.out.println("Diem Lich Su: " + d.getDiemLichSu()); // Fix from previous step (no output for LichSu)
                        System.out.println("Diem Tin Hoc: " + d.getDiemTinHoc());
                        System.out.println("Diem The Duc: " +d.getDiemTheDuc());
                        System.out.println("Diem Khoa Hoc: "+d.getDiemKhoaHoc());
                        System.out.println("Diem TB: " + diemHSService.tinhDiemTB(d));
                        if(diemHSService.tinhDiemTB(d) < 3){
                            System.out.println("Hoc Lai");
                        }
                        else if(diemHSService.tinhDiemTB(d) < 5){
                            System.out.println("Hoc Luc : Yeu");
                        }
                        else if(diemHSService.tinhDiemTB(d)< 8){
                            System.out.println("Hoc luc: Trung binh");

                        }
                        else if(diemHSService.tinhDiemTB(d)< 9){
                            System.out.println("Hoc luc: Kha");
                        }
                        else if(diemHSService.tinhDiemTB(d) < 10){
                            System.out.println("Hoc luc: Gioi");
                        }
                        else if(diemHSService.tinhDiemTB(d) == 10){
                            System.out.println("Hoc luc: Xuat sac");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Ma hoc sinh: ");
                    int maHS2 = sc.nextInt();
                    System.out.println("Nhap lai 8 mon:");
                    float t = sc.nextFloat();
                    float v = sc.nextFloat();
                    float n = sc.nextFloat();
                    float g = sc.nextFloat();
                    float l = sc.nextFloat();
                    float ti = sc.nextFloat();
                    float tduc = sc.nextFloat();
                    float k = sc.nextFloat();

                    if (diemHSService.updateDiem(maHS2, t, v, n, g, l, ti, tduc, k))
                        System.out.println("Cap nhat thanh cong!");
                    else System.out.println("Cap nhat that bai!");
                    break;

                case 4:
                    System.out.print("Ma HS can xoa diem: ");
                    int maDel = sc.nextInt();
                    if (diemHSService.deleteDiem(maDel))
                        System.out.println("Xoa thanh cong!");
                    else System.out.println("Xoa that bai!");
                    break;

                default:
                    System.out.println("Sai lua chon.");
            }
        }
    }
}