package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

import model.lop;
import service.LopService;
import util.ReportUtil;

public class LopPanel extends JPanel {

    private LopService lopService = new LopService();
    private JTable lopTable;
    private DefaultTableModel tableModel;

    // Cac truong nhap lieu
    private JTextField txtMaLop;
    private JTextField txtTenLop;
    private JTextField txtKhoi;
    
    // Cac nut chuc nang
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnView;
    private JButton btnExport;

    public LopPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // 1. Tieu de
        JLabel lblTitle = new JLabel("--- QUAN LY LOP HOC (GUI) ---");
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        // 2. Panel Nhap lieu (Form Panel)
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.WEST);

        // 3. Control buttons
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);
        
        // 4. Setup bang (Table)
        String[] columnNames = {"Ma Lop", "Ten Lop", "Khoi"};
        tableModel = new DefaultTableModel(columnNames, 0);
        lopTable = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(lopTable);
        add(scrollPane, BorderLayout.CENTER);
        
        // 5. Them Event Listeners
        btnAdd.addActionListener(e -> addLopHandler());
        btnUpdate.addActionListener(e -> updateLopHandler());
        btnDelete.addActionListener(e -> deleteLopHandler());
        btnView.addActionListener(e -> loadLopData());
        btnExport.addActionListener(e -> exportLopHandler());
        
        // Them Listener de dien du lieu len form khi chon dong tren bang
        lopTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && lopTable.getSelectedRow() != -1) {
                int selectedRow = lopTable.getSelectedRow();
                txtMaLop.setText(tableModel.getValueAt(selectedRow, 0).toString());
                txtTenLop.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtKhoi.setText(tableModel.getValueAt(selectedRow, 2).toString());
                txtMaLop.setEditable(false); // Khong cho sua Ma Lop
            }
        });
        
        // Load du lieu ban dau
        loadLopData();
    }
    
    private void exportLopHandler() {
        ReportUtil.exportTableToCSV(lopTable, "DanhSachLop.csv");
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        txtMaLop = new JTextField(5);
        txtTenLop = new JTextField(15);
        txtKhoi = new JTextField(5);

        txtMaLop.setEditable(false); // Ma lop tu dong tao
        
        panel.add(new JLabel("Ma Lop (Tu dong):"));
        panel.add(txtMaLop);
        panel.add(new JLabel("Ten Lop:"));
        panel.add(txtTenLop);
        panel.add(new JLabel("Khoi (10, 11, 12):"));
        panel.add(txtKhoi);
        
        return panel;
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAdd = new JButton("+ Them Lop");
        btnUpdate = new JButton("\u270e Sua Lop");
        btnDelete = new JButton("\u274c Xoa Lop"); 
        btnView = new JButton("Xem Tat Ca");
        btnExport = new JButton("Xuat File CSV");
        
        controlPanel.add(btnAdd);
        controlPanel.add(btnUpdate);
        controlPanel.add(btnDelete);
        controlPanel.add(btnView);
        controlPanel.add(btnExport);
        
        return controlPanel;
    }
    
    private void loadLopData() {
        try {
            tableModel.setRowCount(0);
            List<lop> lops = lopService.getAllLop();
            for (lop l : lops) {
                Object[] row = {
                    l.getMaLop(),
                    l.getTenLop(),
                    l.getKhoi()
                };
                tableModel.addRow(row);
            }
            clearLopInputs(); 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Loi khi tai du lieu lop: " + ex.getMessage(), "Loi Database", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearLopInputs() {
        txtMaLop.setText("");
        txtTenLop.setText("");
        txtKhoi.setText("");
        txtMaLop.setEditable(false);
    }
    
    // --- Them Lop ---
    private void addLopHandler() {
        String tenLop = txtTenLop.getText().trim();
        String khoiStr = txtKhoi.getText().trim();
        
        if (tenLop.isEmpty() || khoiStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ten Lop va Khoi khong duoc de trong.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int khoi = Integer.parseInt(khoiStr);
            if (lopService.addLop(tenLop, khoi)) { 
                JOptionPane.showMessageDialog(this, "Them lop thanh cong!", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
                loadLopData(); 
            } else {
                JOptionPane.showMessageDialog(this, "Them lop that bai!", "Loi Xu Ly", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Khoi phai la so nguyen.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // --- Sua Lop ---
    private void updateLopHandler() {
        String actionName = "Cap nhat"; 
        String maLopStr = txtMaLop.getText().trim();
        String tenLop = txtTenLop.getText().trim();
        String khoiStr = txtKhoi.getText().trim();
        
        if (maLopStr.isEmpty() || tenLop.isEmpty() || khoiStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long chon Lop va nhap day du thong tin de cap nhat.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int maLop = Integer.parseInt(maLopStr);
            int khoi = Integer.parseInt(khoiStr);
            
            if (lopService.updateLop(maLop, tenLop, khoi)) { 
                JOptionPane.showMessageDialog(this, actionName + " lop thanh cong!", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
                loadLopData(); 
            } else {
                JOptionPane.showMessageDialog(this, actionName + " that bai! Kiem tra Ma Lop ton tai.", "Loi Xu Ly", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ma Lop va Khoi phai la so nguyen.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // --- Xoa Lop ---
    private void deleteLopHandler() {
        String maLopStr = txtMaLop.getText().trim();
        
        if (maLopStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long chon Lop tren bang de xoa.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int maLop = Integer.parseInt(maLopStr);
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Ban co chac chan muon xoa Lop co Ma " + maLop + "?", "Xac Nhan Xoa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (lopService.deleteLop(maLop)) { 
                    JOptionPane.showMessageDialog(this, "Xoa lop thanh cong!", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
                    loadLopData(); 
                } else {
                    JOptionPane.showMessageDialog(this, "Xoa that bai! Co the Lop chua duoc xoa hoc sinh.", "Loi Xu Ly", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ma Lop phai la so nguyen.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
        }
    }
}