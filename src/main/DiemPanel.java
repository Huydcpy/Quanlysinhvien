package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.DiemHs;
import service.DiemHSService;

public class DiemPanel extends JPanel {

    private DiemHSService diemHSService = new DiemHSService();
    
    // Cac truong nhap lieu
    private JTextField txtMaHS;
    private JTextField txtToan, txtVan, txtNgoaiNgu, txtGDCD, txtLichSu, txtTinHoc, txtTheDuc, txtKhoaHoc;
    
    // Cac truong hien thi ket qua
    private JLabel lblDiemTBResult;
    private JLabel lblHocLucResult;

    public DiemPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // 1. Tieu de
        JLabel lblTitle = new JLabel("--- QUAN LY DIEM (GUI) ---");
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        // 2. Panel Nhap lieu Diem
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.CENTER);

        // 3. Panel Ket qua & Chuc nang
        JPanel resultAndControlPanel = createResultAndControlPanel();
        add(resultAndControlPanel, BorderLayout.EAST);
        
        // Them Event Listeners cho nut "Xem Diem"
        JButton btnView = (JButton) ((JPanel) resultAndControlPanel.getComponent(1)).getComponent(1);
        btnView.addActionListener(e -> viewDiemHandler());
        
        // Them Event Listeners cho nut "Them Diem"
        JButton btnAdd = (JButton) ((JPanel) resultAndControlPanel.getComponent(1)).getComponent(0);
        btnAdd.addActionListener(e -> addOrUpdateDiemHandler(true)); 
        
        // Them Event Listeners cho nut "Sua Diem"
        JButton btnUpdate = (JButton) ((JPanel) resultAndControlPanel.getComponent(1)).getComponent(2);
        btnUpdate.addActionListener(e -> addOrUpdateDiemHandler(false)); 
        
        // Them Event Listeners cho nut "Xoa Diem"
        JButton btnDelete = (JButton) ((JPanel) resultAndControlPanel.getComponent(1)).getComponent(3);
        btnDelete.addActionListener(e -> deleteDiemHandler());
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(9, 2, 5, 5)); // MaHS + 8 mon
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Ma HS
        txtMaHS = new JTextField(5);
        panel.add(new JLabel("Ma Hoc Sinh (HS):"));
        panel.add(txtMaHS);

        // Cac mon hoc
        txtToan = new JTextField(5);
        txtVan = new JTextField(5);
        txtNgoaiNgu = new JTextField(5);
        txtGDCD = new JTextField(5);
        txtLichSu = new JTextField(5);
        txtTinHoc = new JTextField(5);
        txtTheDuc = new JTextField(5);
        txtKhoaHoc = new JTextField(5);

        panel.add(new JLabel("Diem Toan:"));
        panel.add(txtToan);
        panel.add(new JLabel("Diem Ngu Van:"));
        panel.add(txtVan);
        panel.add(new JLabel("Diem Ngoai Ngu:"));
        panel.add(txtNgoaiNgu);
        panel.add(new JLabel("Diem GDCD:"));
        panel.add(txtGDCD);
        panel.add(new JLabel("Diem Lich Su:"));
        panel.add(txtLichSu);
        panel.add(new JLabel("Diem Tin Hoc:"));
        panel.add(txtTinHoc);
        panel.add(new JLabel("Diem The Duc:"));
        panel.add(txtTheDuc);
        panel.add(new JLabel("Diem Khoa Hoc:"));
        panel.add(txtKhoaHoc);
        
        return panel;
    }

    private JPanel createResultAndControlPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // Ket qua + Nut + Khoang cach
        
        // 3.1. Panel Ket qua
        JPanel resultPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        lblDiemTBResult = new JLabel("0.0");
        lblHocLucResult = new JLabel("Chua co");

        resultPanel.add(new JLabel("Diem Trung Binh (TB):"));
        resultPanel.add(lblDiemTBResult);
        resultPanel.add(new JLabel("Hoc Luc:"));
        resultPanel.add(lblHocLucResult);

        mainPanel.add(resultPanel);
        
        // 3.2. Panel Chuc nang
        JPanel controlPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        controlPanel.add(new JButton("Them Diem"));
        controlPanel.add(new JButton("Xem Diem"));
        controlPanel.add(new JButton("Sua Diem"));
        controlPanel.add(new JButton("Xoa Diem"));

        mainPanel.add(controlPanel);
        
        return mainPanel;
    }

    private void viewDiemHandler() {
        try {
            int maHS = Integer.parseInt(txtMaHS.getText().trim());
            DiemHs d = diemHSService.getDiemByMaHS(maHS); // Goi service

            if (d == null) {
                JOptionPane.showMessageDialog(this, "Khong tim thay diem cho Ma HS: " + maHS, "Ket Qua", JOptionPane.INFORMATION_MESSAGE);
                clearScoreInputs();
                lblDiemTBResult.setText("0.0");
                lblHocLucResult.setText("Khong co du lieu");
                return;
            }

            // Hien thi diem vao cac o nhap
            txtToan.setText(String.valueOf(d.getDiemToan()));
            txtVan.setText(String.valueOf(d.getDiemNguVan()));
            txtNgoaiNgu.setText(String.valueOf(d.getDiemNgoaiNgu()));
            txtGDCD.setText(String.valueOf(d.getDiemGDCD()));
            txtLichSu.setText(String.valueOf(d.getDiemLichSu()));
            txtTinHoc.setText(String.valueOf(d.getDiemTinHoc()));
            txtTheDuc.setText(String.valueOf(d.getDiemTheDuc()));
            txtKhoaHoc.setText(String.valueOf(d.getDiemKhoaHoc()));

            // Tinh va hien thi diem trung binh va hoc luc
            float diemTB = diemHSService.tinhDiemTB(d); // Goi service
            lblDiemTBResult.setText(String.format("%.2f", diemTB));
            
            // SU DUNG LOGIC MOI TU SERVICE
            lblHocLucResult.setText(diemHSService.getHocLuc(diemTB)); 

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ma HS phai la so nguyen.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, "Loi khi xem diem: " + ex.getMessage(), "Loi He Thong", JOptionPane.ERROR_MESSAGE);
             ex.printStackTrace();
        }
    }
    
    private void addOrUpdateDiemHandler(boolean isAdd) {
        // KHAI BAO BIEN O NGOAI KHOI TRY
        String actionName = isAdd ? "Them" : "Cap nhat"; 
        
        try {
            int maHS = Integer.parseInt(txtMaHS.getText().trim());
            float toan = getScore(txtToan);
            float van = getScore(txtVan);
            float nn = getScore(txtNgoaiNgu);
            float gdcd = getScore(txtGDCD);
            float ls = getScore(txtLichSu);
            float tin = getScore(txtTinHoc);
            float td = getScore(txtTheDuc);
            float kh = getScore(txtKhoaHoc);
            
            boolean success;
            
            if (isAdd) {
                // Them diem
                success = diemHSService.addDiem(maHS, toan, van, nn, gdcd, ls, tin, td, kh);
            } else {
                // Cap nhat diem
                success = diemHSService.updateDiem(maHS, toan, van, nn, gdcd, ls, tin, td, kh);
            }
            
            if (success) {
                // Su dung actionName o day
                JOptionPane.showMessageDialog(this, actionName + " diem thanh cong!", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
                viewDiemHandler(); 
            } else {
                // Su dung actionName o day
                JOptionPane.showMessageDialog(this, actionName + " diem that bai! Kiem tra Ma HS hoac du lieu dau vao.", "Loi Xu Ly", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ma HS va Diem phai la so.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
             // Su dung actionName o day
             JOptionPane.showMessageDialog(this, "Loi he thong khi " + actionName + " diem: " + ex.getMessage(), "Loi He Thong", JOptionPane.ERROR_MESSAGE);
             ex.printStackTrace();
        }
    }
    
    private void deleteDiemHandler() {
        try {
            String maHSStr = txtMaHS.getText().trim();
            if (maHSStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui long nhap Ma HS can xoa.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int maHS = Integer.parseInt(maHSStr);
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Ban co chac chan muon xoa diem cua HS " + maHS + "?", "Xac Nhan Xoa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (diemHSService.deleteDiem(maHS)) { // Goi service
                    JOptionPane.showMessageDialog(this, "Xoa diem thanh cong!", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
                    clearScoreInputs();
                    txtMaHS.setText("");
                    lblDiemTBResult.setText("0.0");
                    lblHocLucResult.setText("Khong co du lieu");
                } else {
                    JOptionPane.showMessageDialog(this, "Xoa that bai! Co the Ma HS khong ton tai.", "Loi Xu Ly", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ma HS phai la so nguyen.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private float getScore(JTextField field) throws NumberFormatException {
        String text = field.getText().trim();
        if (text.isEmpty()) {
            return 0.0f; // Mac dinh la 0 neu de trong
        }
        // Kiem tra gia tri hop le (0-10)
        float score = Float.parseFloat(text);
        if (score < 0 || score > 10) {
            throw new NumberFormatException("Diem phai nam trong khoang 0 den 10.");
        }
        return score;
    }
    
    private void clearScoreInputs() {
        txtToan.setText("");
        txtVan.setText("");
        txtNgoaiNgu.setText("");
        txtGDCD.setText("");
        txtLichSu.setText("");
        txtTinHoc.setText("");
        txtTheDuc.setText("");
        txtKhoaHoc.setText("");
    }
    
}