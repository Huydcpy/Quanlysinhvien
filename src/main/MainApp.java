package main;

import model.HocSinh;
import model.lop;
import model.DiemHs;
import service.HocSinhService;
import service.LopService;
import service.DiemHSService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    static Scanner sc = new Scanner(System.in);

    static LopService lopService = new LopService();
    static HocSinhService hsService = new HocSinhService();
    static DiemHSService diemService = new DiemHSService();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== QUẢN LÝ HỌC SINH =====");
            System.out.println("1. Thêm lớp");
            System.out.println("2. Thêm học sinh");
            System.out.println("3. Thêm điểm học sinh");
            System.out.println("4. Xem danh sách học sinh");
            System.out.println("5. Xem điểm 1 học sinh");
            System.out.println("6. Xóa học sinh");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    addLop();
                    break;
                case 2:
                    addHocSinh();
                    break;
                case 3:
                    addDiem();
                    break;
                case 4:
                    listHocSinh();
                    break;
                case 5:
                    viewDiem();
                    break;
                case 6:
                    deleteHocSinh();
                    break;
                case 0:
                    System.out.println("Thoát chương trình...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 0);
    }

    // ====================== HÀM CHỨC NĂNG ======================

    // 1. Thêm lớp
    public static void addLop() {
        System.out.print("Tên lớp: ");
        String ten = sc.nextLine();
        System.out.print("Khối: ");
        int khoi = Integer.parseInt(sc.nextLine());

        lop lop = new lop(0, ten, khoi);

        if (LopService.addLop(lop)) {
            System.out.println("✔ Thêm lớp thành công!");
        } else {
            System.out.println("✖ Lỗi thêm lớp!");
        }
    }

    // 2. Thêm học sinh
    public static void addHocSinh() {
        System.out.print("Họ tên: ");
        String hoten = sc.nextLine();
        System.out.print("Giới tính: ");
        String gt = sc.nextLine();
        System.out.print("Ngày sinh (yyyy-mm-dd): ");
        Date ns = Date.valueOf(sc.nextLine());
        System.out.print("Mã lớp: ");
        int maLop = Integer.parseInt(sc.nextLine());
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("SĐT bố/mẹ: ");
        String sdt = sc.nextLine();
        System.out.print("Địa chỉ: ");
        String dc = sc.nextLine();

        HocSinh hs = new HocSinh(maHS, hoten, gt, ns, maLop, email, sdt, dc);

        if (hsService.addHocSinh(hs)) {
            System.out.println("✔ Thêm học sinh thành công!");
        } else {
            System.out.println("✖ Lỗi thêm học sinh!");
        }
    }

    // 3. Thêm điểm học sinh
    public static void addDiem() {
        System.out.print("Mã học sinh: ");
        int maHS = Integer.parseInt(sc.nextLine());

        System.out.print("Điểm Toán: ");
        float toan = Float.parseFloat(sc.nextLine());
        System.out.print("Điểm Văn: ");
        float van = Float.parseFloat(sc.nextLine());
        System.out.print("Điểm Ngoại ngữ: ");
        float nn = Float.parseFloat(sc.nextLine());
        System.out.print("Điểm GDCD: ");
        float gdcd = Float.parseFloat(sc.nextLine());
        System.out.print("Điểm Lịch sử: ");
        float ls = Float.parseFloat(sc.nextLine());
        System.out.print("Điểm Tin học: ");
        float tin = Float.parseFloat(sc.nextLine());
        System.out.print("Điểm Thể dục: ");
        float td = Float.parseFloat(sc.nextLine());
        System.out.print("Điểm Khoa học: ");
        float kh = Float.parseFloat(sc.nextLine());

        DiemHs d = new DiemHs(maHS, toan, van, nn, gdcd, ls, tin, td, kh);

        if (DiemHSService.addDiem(d)) {
            System.out.println("✔ Thêm điểm thành công!");
        } else {
            System.out.println("✖ Lỗi thêm điểm!");
        }
    }

    // 4. Xem danh sách học sinh
    public static void listHocSinh() {
        System.out.println("\n===== DANH SÁCH HỌC SINH =====");
        List<HocSinh> list = hsService.getAllHocSinh();

        for (HocSinh hs : list) {
            System.out.println(hs.getMaHS() + " - " + hs.getHoTen() + " - Lớp: " + hs.getMaLop());
        }
    }

    // 5. Xem điểm của 1 học sinh
    public static void viewDiem() {
        System.out.print("Nhập mã HS: ");
        int ma = Integer.parseInt(sc.nextLine());

        DiemHs d = diemService.getDiemByMaHS(ma);

        if (d == null) {
            System.out.println("❌ Không có điểm!");
            return;
        }

        System.out.println("===== ĐIỂM HỌC SINH " + ma + " =====");
        System.out.println("Toán: " + d.getDiemToan());
        System.out.println("Văn: " + d.getDiemNguVan());
        System.out.println("Ngoại ngữ: " + d.getDiemNgoaiNgu());
        System.out.println("GDCD: " + d.getDiemGDCD());
        System.out.println("Lịch sử: " + d.getDiemLichSu());
        System.out.println("Tin học: " + d.getDiemTinHoc());
        System.out.println("Thể dục: " + d.getDiemTheDuc());
        System.out.println("Khoa học: " + d.getDiemKhoaHoc());
    }

    // 6. Xóa học sinh
    public static void deleteHocSinh() {
        System.out.print("Nhập mã HS muốn xóa: ");
        int ma = Integer.parseInt(sc.nextLine());

        if (hsService.deleteHocSinh(ma)) {
            System.out.println("✔ Xóa thành công!");
        } else {
            System.out.println("✖ Lỗi xóa học sinh!");
        }
    }
}
