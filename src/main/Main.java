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
            System.out.println("\n===== HỆ THỐNG QUẢN LÝ HỌC SINH =====");
            System.out.println("1. Quản lý Lớp");
            System.out.println("2. Quản lý Học Sinh");
            System.out.println("3. Quản lý Điểm");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
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
                    System.out.println("Thoát chương trình!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    // ============================
    // MENU QUẢN LÝ LỚP
    // ============================
    public static void menuLop() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ LỚP ---");
            System.out.println("1. Thêm lớp");
            System.out.println("2. Xem danh sách lớp");
            System.out.println("3. Sửa lớp");
            System.out.println("4. Xóa lớp");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int chon = sc.nextInt();

            if (chon == 0) return;

            switch (chon) {
                case 1:
                    sc.nextLine();
                    System.out.print("Tên lớp: ");
                    String tenLop = sc.nextLine();
                    System.out.print("Khối: ");
                    int khoi = sc.nextInt();
                    if (lopService.addLop(tenLop, khoi))
                        System.out.println("Thêm lớp thành công!");
                    else System.out.println("Thêm thất bại!");
                    break;

                case 2:
                    List<lop> lops = lopService.getAllLop();
                    for (lop l : lops) {
                        System.out.println("Mã: " + l.getMaLop() + " | Tên lớp: " + l.getTenLop() + " | Khối: " + l.getKhoi());
                    }
                    break;

                case 3:
                    System.out.print("Nhập mã lớp cần sửa: ");
                    int maLS = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tên lớp mới: ");
                    String tenLopM = sc.nextLine();
                    System.out.print("Khối: ");
                    int khoiM = sc.nextInt();
                    if (lopService.updateLop(maLS, tenLopM, khoiM))
                        System.out.println("Sửa thành công!");
                    else System.out.println("Sửa thất bại!");
                    break;

                case 4:
                    System.out.print("Nhập mã lớp cần xóa: ");
                    int maLX = sc.nextInt();
                    if (lopService.deleteLop(maLX))
                        System.out.println("Xóa thành công!");
                    else System.out.println("Xóa thất bại!");
                    break;

                default:
                    System.out.println("Sai lựa chọn.");
            }
        }
    }

    // ============================
    // MENU QUẢN LÝ HỌC SINH
    // ============================
    public static void menuHocSinh() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ HỌC SINH ---");
            System.out.println("1. Thêm học sinh");
            System.out.println("2. Xem danh sách học sinh");
            System.out.println("3. Sửa học sinh");
            System.out.println("4. Xóa học sinh");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int chon = sc.nextInt();

            if (chon == 0) return;

            switch (chon) {
                case 1:
                    sc.nextLine();
                    System.out.print("Tên HS: ");
                    String ten = sc.nextLine();
                    System.out.print("Giới tính: ");
                    String gt = sc.nextLine();
                    System.out.print("Ngày sinh (yyyy-mm-dd): ");
                    Date ns = Date.valueOf(sc.nextLine());
                    System.out.print("Mã lớp: ");
                    int ml = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Địa chỉ: ");
                    String dc = sc.nextLine();
                    System.out.print("SĐT bố mẹ: ");
                    String sdt = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    if (hocSinhService.addHocSinh(ten, gt, ns, ml, dc, sdt, email))
                        System.out.println("Thêm thành công!");
                    else System.out.println("Thêm thất bại!");
                    break;

                case 2:
                    List<HocSinh> hss = hocSinhService.getAllHocSinh();
                    for (HocSinh h : hss) {
                        System.out.println(h.getMaHS() + " | " + h.getHoTen() + " | Lớp: " + h.getMaLop());
                    }
                    break;

                case 3:
                    System.out.print("Nhập mã HS cần sửa: ");
                    int maS = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tên mới: ");
                    String tNew = sc.nextLine();
                    System.out.print("Giới tính mới: ");
                    String gtNew = sc.nextLine();
                    System.out.print("Ngày sinh mới (yyyy-mm-dd): ");
                    Date nsNew = Date.valueOf(sc.nextLine());
                    System.out.print("Mã lớp mới: ");
                    int mlNew = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Địa chỉ mới: ");
                    String dcNew = sc.nextLine();
                    System.out.print("SĐT mới: ");
                    String sdtNew = sc.nextLine();
                    System.out.print("Email mới: ");
                    String emailNew = sc.nextLine();

                    if (hocSinhService.updateHocSinh(maS, tNew, gtNew, nsNew, mlNew, dcNew, sdtNew, emailNew))
                        System.out.println("Cập nhật thành công!");
                    else System.out.println("Cập nhật thất bại!");
                    break;

                case 4:
                    System.out.print("Nhập mã HS cần xóa: ");
                    int maX = sc.nextInt();
                    if (hocSinhService.deleteHocSinh(maX))
                        System.out.println("Xóa thành công!");
                    else System.out.println("Xóa thất bại!");
                    break;

                default:
                    System.out.println("Sai lựa chọn.");
            }
        }
    }

    // ============================
    // MENU QUẢN LÝ ĐIỂM
    // ============================
    public static void menuDiem() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ ĐIỂM ---");
            System.out.println("1. Nhập điểm cho học sinh");
            System.out.println("2. Xem điểm theo mã học sinh");
            System.out.println("3. Sửa điểm");
            System.out.println("4. Xóa điểm");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int chon = sc.nextInt();

            if (chon == 0) return;

            switch (chon) {
                case 1:
                    System.out.print("Mã học sinh: ");
                    int maHS = sc.nextInt();
                    System.out.println("Nhập lần lượt điểm 8 môn:");
                    float toan = sc.nextFloat();
                    float van = sc.nextFloat();
                    float nn = sc.nextFloat();
                    float gdcd = sc.nextFloat();
                    float ls = sc.nextFloat();
                    float tin = sc.nextFloat();
                    float td = sc.nextFloat();
                    float kh = sc.nextFloat();

                    if (diemHSService.addDiem(maHS, toan, van, nn, gdcd, ls, tin, td, kh))
                        System.out.println("Nhập điểm thành công!");
                    else System.out.println("Thất bại!");
                    break;

                case 2:
                    System.out.print("Nhập mã HS: ");
                    int ma = sc.nextInt();
                    DiemHs d = diemHSService.getDiemByMaHS(ma);
                    if (d == null) System.out.println("Không có dữ liệu!");
                    else {
                        System.out.println("Điểm Toán: " + d.getDiemToan());
                        System.out.println("Điểm Văn: " + d.getDiemNguVan());
                        System.out.println("Điểm Ngoại Ngữ: "+d.getDiemGDCD());
                        System.out.println("Điểm GDCD: " + d.getDiemLichSu());
                        System.out.println("Điểm Tin Học: " + d.getDiemTinHoc());
                        System.out.println("Điểm Thể Dục: " +d.getDiemTheDuc());
                        System.out.println("Điểm Khoa Học: "+d.getDiemKhoaHoc());
                        System.out.println("Điểm TB: " + diemHSService.tinhDiemTB(d));
                        if(diemHSService.tinhDiemTB(d) < 3){
                            System.out.println("Học Lại");
                        }
                        else if(diemHSService.tinhDiemTB(d) < 5){
                            System.out.println("Học Lực : Yếu");
                        }
                        else if(diemHSService.tinhDiemTB(d)< 8){
                            System.out.println("Học lực: Trung bình");

                        }
                        else if(diemHSService.tinhDiemTB(d)< 9){
                            System.out.println("Học lực: Khá");
                        }
                        else if(diemHSService.tinhDiemTB(d) < 10){
                            System.out.println("Học lực: Giỏi");
                        }
                        else if(diemHSService.tinhDiemTB(d) == 10){
                            System.out.println("Học lực: Xuât sắc");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Mã học sinh: ");
                    int maHS2 = sc.nextInt();
                    System.out.println("Nhập lại 8 môn:");
                    float t = sc.nextFloat();
                    float v = sc.nextFloat();
                    float n = sc.nextFloat();
                    float g = sc.nextFloat();
                    float l = sc.nextFloat();
                    float ti = sc.nextFloat();
                    float tduc = sc.nextFloat();
                    float k = sc.nextFloat();

                    if (diemHSService.updateDiem(maHS2, t, v, n, g, l, ti, tduc, k))
                        System.out.println("Cập nhật thành công!");
                    else System.out.println("Cập nhật thất bại!");
                    break;

                case 4:
                    System.out.print("Mã HS cần xóa điểm: ");
                    int maDel = sc.nextInt();
                    if (diemHSService.deleteDiem(maDel))
                        System.out.println("Xóa thành công!");
                    else System.out.println("Xóa thất bại!");
                    break;

                default:
                    System.out.println("Sai lựa chọn.");
            }
        }
    }
}
