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

public class LopPanel extends JPanel {

    private LopService lopService = new LopService();
    private JTable classTable;
    private DefaultTableModel tableModel;

    // Cac truong nhap lieu
    private JTextField txtSearch;
    private JTextField txtMaLop;
    private JTextField txtTenLop;
    private JTextField txtKhoi;
    
    // Cac nut chuc nang
    private JButton btnSearch;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnView; 

    public LopPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // 1. Tieu de & Search Panel
        JLabel lblTitle = new JLabel("--- QUAN LY LOP (GUI) ---");
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel topPanel = new JPanel(new BorderLayout()); 
        topPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel searchPanel = createSearchPanel();
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        
        add(topPanel, BorderLayout.NORTH);

        // 2. Panel Nhap lieu (Form Panel)
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(new EmptyBorder(10, 0, 10, 0)); 

        txtMaLop = new JTextField(5);
        txtMaLop.setEditable(false); // Ma Lop tu dong, chi editable khi lay tu bang de sua
        txtTenLop = new JTextField(15);
        txtKhoi = new JTextField(15);
        
        inputPanel.add(new JLabel("Ma Lop (Tu dong):")); 
        inputPanel.add(txtMaLop);
        inputPanel.add(new JLabel("Ten Lop:")); 
        inputPanel.add(txtTenLop);
        inputPanel.add(new JLabel("Khoi:")); 
        inputPanel.add(txtKhoi);

        add(inputPanel, BorderLayout.WEST); 

        // 3. Control buttons
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAdd = new JButton("Them Lop"); 
        btnUpdate = new JButton("Sua Lop"); 
        btnDelete = new JButton("Xoa Lop"); 
        btnView = new JButton("Xem Tat Ca Lop"); // Cap nhat ten nut
        
        controlPanel.add(btnAdd);
        controlPanel.add(btnUpdate);
        controlPanel.add(btnDelete);
        controlPanel.add(btnView);
        
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(controlPanel, BorderLayout.WEST);
        add(southPanel, BorderLayout.SOUTH);
        

        // 4. Setup bang (Table)
        String[] columnNames = {"Ma Lop", "Ten Lop", "Khoi"};
        tableModel = new DefaultTableModel(columnNames, 0);
        classTable = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(classTable);
        add(scrollPane, BorderLayout.CENTER);
        
        // 5. Them Event Listeners
        btnAdd.addActionListener(e -> addLopHandler());
        btnUpdate.addActionListener(e -> updateLopHandler()); 
        btnDelete.addActionListener(e -> deleteLopHandler()); 
        
        // Cap nhat listeners cho chuc nang Tim kiem/Xem tat ca
        btnView.addActionListener(e -> loadLopData(null)); // Load All
        btnSearch.addActionListener(e -> searchLopHandler()); // Search Listener

        // Them Listener de dien du lieu len form khi chon dong tren bang
        classTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && classTable.getSelectedRow() != -1) {
                int selectedRow = classTable.getSelectedRow();
                txtMaLop.setText(tableModel.getValueAt(selectedRow, 0).toString());
                txtTenLop.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtKhoi.setText(tableModel.getValueAt(selectedRow, 2).toString());
                txtMaLop.setEditable(false); // Khoa Ma Lop
            }
        });
        
        // Load du lieu ban dau
        loadLopData(null);
    }
    
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Tim/Loc Lop (Ten/Khoi)");

        panel.add(new JLabel("Tim kiem (Ten/Khoi):"));
        panel.add(txtSearch);
        panel.add(btnSearch);

        return panel;
    }

    private void searchLopHandler() {
        String searchTerm = txtSearch.getText().trim();
        loadLopData(searchTerm);
        clearInputs();
    }


    private void loadLopData(String searchTerm) { // Cap nhat signature
        try {
            tableModel.setRowCount(0);
            List<lop> lops;
            
            if (searchTerm == null || searchTerm.isEmpty()) {
                lops = lopService.getAllLop(); 
            } else {
                lops = lopService.searchLop(searchTerm); // Goi LopService.searchLop
            }
            
            for (lop l : lops) {
                Object[] row = {l.getMaLop(), l.getTenLop(), l.getKhoi()}; 
                tableModel.addRow(row);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Loi khi tai du lieu lop: " + ex.getMessage(), "Loi Database", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void addLopHandler() {
        String tenLop = txtTenLop.getText().trim();
        String khoiStr = txtKhoi.getText().trim();
        
        if (tenLop.isEmpty() || khoiStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long nhap day du Ten Lop va Khoi.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int khoi;
        try {
            khoi = Integer.parseInt(khoiStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Khoi phai la mot so nguyen duong.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (lopService.addLop(tenLop, khoi)) { // Goi service them lop
            JOptionPane.showMessageDialog(this, "Them lop thanh cong!", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
            clearInputs();
            loadLopData(null); // Cap nhat: Load All
        } else {
            JOptionPane.showMessageDialog(this, "Them lop that bai! Kiem tra log hoac du lieu dau vao.", "Loi Xu Ly", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // --- Sua Lop ---
    private void updateLopHandler() {
        String maLopStr = txtMaLop.getText().trim();
        String tenLop = txtTenLop.getText().trim();
        String khoiStr = txtKhoi.getText().trim();
        
        if (maLopStr.isEmpty() || tenLop.isEmpty() || khoiStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long chon lop tren bang de sua.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int maLop = Integer.parseInt(maLopStr);
            int khoi = Integer.parseInt(khoiStr);
            
            if (lopService.updateLop(maLop, tenLop, khoi)) { // Goi service sua lop
                JOptionPane.showMessageDialog(this, "Sua lop thanh cong!", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
                loadLopData(null); // Cap nhat: Load All
                clearInputs();
            } else {
                JOptionPane.showMessageDialog(this, "Sua lop that bai! Kiem tra Ma Lop hoac du lieu.", "Loi Xu Ly", JOptionPane.ERROR_MESSAGE);
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
                if (lopService.deleteLop(maLop)) { // Goi service xoa lop
                    JOptionPane.showMessageDialog(this, "Xoa lop thanh cong!", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
                    loadLopData(null); // Cap nhat: Load All
                    clearInputs();
                } else {
                    JOptionPane.showMessageDialog(this, "Xoa that bai! Kiem tra rang buoc khoa ngoai (co HS trong lop nay).", "Loi Xu Ly", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ma Lop phai la so nguyen.", "Loi Nhap Lieu", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void clearInputs() {
        txtMaLop.setText("");
        txtTenLop.setText("");
        txtKhoi.setText("");
    }
}