package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.HocSinh;
import service.HocSinhService;
import util.ReportUtil;

public class HocSinhPanel extends JPanel {

    private HocSinhService hocSinhService = new HocSinhService();
    private JTable studentTable;
    private DefaultTableModel tableModel;
    
    // Dinh dang ngay thang: DD-MM-YYYY (Tieng Viet khong dau)
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    // Cac truong nhap lieu
    private JTextField txtMaHS; 
    private JTextField txtHoTen;
    private JTextField txtGioiTinh;
    private JTextField txtNgaySinh; // dd-MM-yyyy
    private JTextField txtMaLop;
    private JTextField txtDiaChi;
    private JTextField txtSdtBoMe;
    private JTextField txtEmail;
    private JTextField txtSearch;
    
    // Cac nut chuc nang
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnView;
    private JButton btnSearch;
    private JButton btnExport;

    public HocSinhPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // 1. Tieu de & Search Panel
        JLabel lblTitle = new JLabel("--- QUAN LY HOC SINH (GUI) ---");
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(lblTitle, BorderLayout.NORTH);
        
        JPanel searchPanel = createSearchPanel();
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        
        add(topPanel, BorderLayout.NORTH); 

        // 2. Panel Nhap lieu (Form Panel)
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.WEST); 

        // 3. Control buttons
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);
        
        // 4. Setup bang (Table)
        String[] columnNames = {"Ma HS", "Ho Ten", "Ngay Sinh", "Ma Lop", "Gioi Tinh", "Dia Chi", "SDT Bo Me", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER); 
        
        // 5. Them Event Listeners
        btnAdd.addActionListener(e -> addHocSinhHandler());
        btnUpdate.addActionListener(e -> updateHocSinhHandler());
        btnDelete.addActionListener(e -> deleteHocSinhHandler());
        
        // Cap nhat va them listeners cho chuc nang Tim kiem/Xem tat ca/Export
        btnView.setText("Xem Tat Ca Hoc Sinh");
        btnView.addActionListener(e -> loadHocSinhData(null)); 
        btnSearch.addActionListener(e -> searchHocSinhHandler()); 
        btnExport.addActionListener(e -> exportHocSinhHandler()); 

        // Them Listener de dien du lieu len form khi chon dong tren bang
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && studentTable.getSelectedRow() != -1) {
                int selectedRow = studentTable.getSelectedRow();
                txtMaHS.setText(tableModel.getValueAt(selectedRow, 0).toString());
                txtHoTen.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtNgaySinh.setText(tableModel.getValueAt(selectedRow, 2).toString()); 
                txtMaLop.setText(tableModel.getValueAt(selectedRow, 3).toString());
                txtGioiTinh.setText(tableModel.getValueAt(selectedRow, 4).toString());
                txtDiaChi.setText(tableModel.getValueAt(selectedRow, 5).toString());
                txtSdtBoMe.setText(tableModel.getValueAt(selectedRow, 6).toString());
                txtEmail.setText(tableModel.getValueAt(selectedRow, 7).toString());
                txtMaHS.setEditable(false); 
            }
        });
        
        // Load du lieu ban dau
        loadHocSinhData(null);
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        txtSearch = new JTextField(25);
        btnSearch = new JButton("Tim/Loc HS (Ten/Ma/Lop)");

        panel.add(new JLabel("Tim kiem (Ten/Ma/MaLop):"));
        panel.add(txtSearch);
        panel.add(btnSearch);

        return panel;
    }
    
    private void searchHocSinhHandler() {
        String searchTerm = txtSearch.getText().trim();
        loadHocSinhData(searchTerm);
        clearHocSinhInputs();
    }
    
    private void exportHocSinhHandler() {
        ReportUtil.exportTableToCSV(studentTable, "DanhSachHocSinh.csv");
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(9, 2, 5, 5)); // 8 truong + 1 row MaHS
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        txtMaHS = new JTextField(5);
        txtHoTen = new JTextField(15);
        txtGioiTinh = new JTextField(5);
        txtNgaySinh = new JTextField(10);
        txtMaLop = new JTextField(5);
        txtDiaChi = new JTextField(15);
        txtSdtBoMe = new JTextField(10);
        txtEmail = new JTextField(15);
        
        txtMaHS.setEditable(false); 
        
        panel.add(new JLabel("Ma HS (Tu dong):"));
        panel.add(txtMaHS);
        panel.add(new JLabel("Ho Ten:"));
        panel.add(txtHoTen);
        panel.add(new JLabel("Gioi Tinh:"));
        panel.add(txtGioiTinh);
        panel.add(new JLabel("Ngay Sinh (dd-MM-yyyy):")); 
        panel.add(txtNgaySinh);
        panel.add(new JLabel("Ma Lop:"));
        panel.add(txtMaLop);
        panel.add(new JLabel("Dia Chi:"));
        panel.add(txtDiaChi);
        panel.add(new JLabel("SDT Bo Me:"));
        panel.add(txtSdtBoMe);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        
        return panel;
    }
    
    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        
        btnAdd = new JButton("+ Them Hoc Sinh"); 
        btnUpdate = new JButton("\u270e Sua Hoc Sinh"); 
        btnDelete = new JButton("\u274c Xoa Hoc Sinh"); 
        btnView = new JButton("Xem Danh Sach Hoc Sinh"); 
        btnExport = new JButton("Xuat File CSV");
        
        controlPanel.add(btnAdd);
        controlPanel.add(btnUpdate);
        controlPanel.add(btnDelete);
        controlPanel.add(btnView);
        controlPanel.add(btnExport);
        
        return controlPanel;
    }

    private void loadHocSinhData(String searchTerm) {
        try {
            tableModel.setRowCount(0);
            List<HocSinh> hss; 
            
            if (searchTerm == null || searchTerm.isEmpty()) {
                 hss = hocSinhService.getAllHocSinh(); // Load All
            } else {
                 hss = hocSinhService.searchHocSinh(searchTerm); // Goi service search
            }
            
            for (HocSinh h : hss) { 
                // CHUYEN DOI DINH DANG NGAY KHI HIEN THI
                String ngaySinhFormatted = dateFormat.format(h.getNgaySinh());
                
                Object[] row = {
                    h.getMaHS(),
                    h.getHoTen(),
                    ngaySinhFormatted, 
                    h.getMaLop(),
                    h.getGioiTinh(),
                    h.getDiachi(),
                    h.getSdtOfBoMe(),
                    h.getEmail()
                };
                tableModel.addRow(row);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Loi khi tai du lieu hoc sinh: " + ex.getMessage(), "Loi Database", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void clearHocSinhInputs() {
        txtMaHS.setText("");
        txtHoTen.setText("");
        txtGioiTinh.setText("");
        txtNgaySinh.setText("");
        txtMaLop.setText("");
        txtDiaChi.setText("");
        txtSdtBoMe.setText("");
        txtEmail.setText("");
    }
    
    // --- Them Hoc Sinh ---
    private void addHocSinhHandler() {
        String hoTen = txtHoTen.getText().trim();
        String gioiTinh = txtGioiTinh.getText().trim();
        String ngaySinhStr = txtNgaySinh.getText().trim();
        String maLopStr = txtMaLop.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String sdtBoMe = txtSdtBoMe.getText().trim();
        String email = txtEmail.getText().trim();
        
        if (hoTen.isEmpty() || ngaySinhStr.isEmpty() || maLopStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ho Ten, Ngay Sinh va Ma Lop khong duoc de trong.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int maLop = Integer.parseInt(maLopStr);
            
            java.util.Date parsedDate = dateFormat.parse(ngaySinhStr);
            Date ngaySinh = new Date(parsedDate.getTime());
            
            if (hocSinhService.addHocSinh(hoTen, gioiTinh, ngaySinh, maLop, diaChi, sdtBoMe, email)) { 
                JOptionPane.showMessageDialog(this, "Them hoc sinh thanh cong!", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
                clearHocSinhInputs();
                loadHocSinhData(null); 
            } else {
                JOptionPane.showMessageDialog(this, "Them hoc sinh that bai! Kiem tra Ma Lop co ton tai.", "Loi Xu Ly", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ma Lop phai la so nguyen.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ngay Sinh phai dung dinh dang dd-MM-yyyy.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // --- Sua Hoc Sinh ---
    private void updateHocSinhHandler() {
        String actionName = "Cap nhat"; 
        String maHSStr = txtMaHS.getText().trim();
        String hoTen = txtHoTen.getText().trim();
        String gioiTinh = txtGioiTinh.getText().trim();
        String ngaySinhStr = txtNgaySinh.getText().trim();
        String maLopStr = txtMaLop.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String sdtBoMe = txtSdtBoMe.getText().trim();
        String email = txtEmail.getText().trim();
        
        if (maHSStr.isEmpty() || hoTen.isEmpty() || ngaySinhStr.isEmpty() || maLopStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long chon HS va nhap day du thong tin de cap nhat.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int maHS = Integer.parseInt(maHSStr);
            int maLop = Integer.parseInt(maLopStr);
            
            java.util.Date parsedDate = dateFormat.parse(ngaySinhStr);
            Date ngaySinh = new Date(parsedDate.getTime());
            
            if (hocSinhService.updateHocSinh(maHS, hoTen, gioiTinh, ngaySinh, maLop, diaChi, sdtBoMe, email)) { 
                JOptionPane.showMessageDialog(this, actionName + " hoc sinh thanh cong!", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
                clearHocSinhInputs();
                loadHocSinhData(null); 
            } else {
                JOptionPane.showMessageDialog(this, actionName + " that bai! Kiem tra Ma HS hoac Ma Lop.", "Loi Xu Ly", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ma HS va Ma Lop phai la so nguyen.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ngay Sinh phai dung dinh dang dd-MM-yyyy.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, "Loi he thong khi " + actionName + " hoc sinh: " + ex.getMessage(), "Loi He Thong", JOptionPane.ERROR_MESSAGE);
             ex.printStackTrace();
        }
    }
    
    // --- Xoa Hoc Sinh ---
    private void deleteHocSinhHandler() {
        String maHSStr = txtMaHS.getText().trim();
        
        if (maHSStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long chon HS tren bang de xoa.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int maHS = Integer.parseInt(maHSStr);
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Ban co chac chan muon xoa Hoc Sinh co Ma " + maHS + "?", "Xac Nhan Xoa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (hocSinhService.deleteHocSinh(maHS)) { 
                    JOptionPane.showMessageDialog(this, "Xoa hoc sinh thanh cong!", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
                    clearHocSinhInputs();
                    loadHocSinhData(null); 
                } else {
                    JOptionPane.showMessageDialog(this, "Xoa that bai! Kiem tra Ma HS hoac rang buoc khoa ngoai.", "Loi Xu Ly", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ma HS phai la so nguyen.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
        }
    }
}