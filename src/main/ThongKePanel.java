package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout; // NEW IMPORT
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.DiemHs;
import model.HocSinh;
import service.DiemHSService;
import service.HocSinhService;
import util.ReportUtil; // NEW IMPORT

public class ThongKePanel extends JPanel {

    private DiemHSService diemHSService = new DiemHSService();
    private HocSinhService hocSinhService = new HocSinhService();
    
    private JTable resultTable;
    private DefaultTableModel tableModel;
    
    // Labels thong ke tong quan
    private JLabel lblTongHS;
    private JLabel lblTongCoDiem;
    private JLabel lblXuatSac;
    private JLabel lblGioi;
    private JLabel lblKha;
    private JLabel lblTrungBinh;
    private JLabel lblYeu;
    private JLabel lblHocLai;
    
    private JButton btnExport; // NEW FIELD

    public ThongKePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // 1. Tieu de
        JLabel lblTitle = new JLabel("--- THONG KE HOC LUC (GUI) ---");
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        // 2. Panel Thong tin chung
        JPanel summaryPanel = createSummaryPanel();
        add(summaryPanel, BorderLayout.WEST);

        // 3. Bang ket qua chi tiet
        String[] columnNames = {"Ma HS", "Ho Ten", "Diem TB", "Hoc Luc"};
        tableModel = new DefaultTableModel(columnNames, 0);
        resultTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        add(scrollPane, BorderLayout.CENTER);

        // 4. Nut tai lai & Export (UPDATED LAYOUT)
        JButton btnRefresh = new JButton("Tai lai Thong ke");
        btnRefresh.addActionListener(e -> loadThongKeData());
        
        btnExport = new JButton("Xuat File CSV"); // NEW BUTTON
        btnExport.addActionListener(e -> exportThongKeHandler()); // NEW LISTENER

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(btnRefresh);
        bottomPanel.add(btnExport); 
        
        add(bottomPanel, BorderLayout.SOUTH);

        // Load du lieu ban dau
        loadThongKeData();
    }

    private JPanel createSummaryPanel() {
        JPanel summaryPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        summaryPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        lblTongHS = new JLabel("0");
        lblTongCoDiem = new JLabel("0");
        lblXuatSac = new JLabel("0");
        lblGioi = new JLabel("0");
        lblKha = new JLabel("0");
        lblTrungBinh = new JLabel("0");
        lblYeu = new JLabel("0");
        lblHocLai = new JLabel("0");

        summaryPanel.add(new JLabel("Tong so Hoc Sinh:"));
        summaryPanel.add(lblTongHS);
        summaryPanel.add(new JLabel("Tong so HS co Diem:"));
        summaryPanel.add(lblTongCoDiem);
        summaryPanel.add(new JLabel("-----------------"));
        summaryPanel.add(new JLabel("-----------------"));
        
        summaryPanel.add(new JLabel("Hoc Luc XUAT SAC:"));
        summaryPanel.add(lblXuatSac);
        summaryPanel.add(new JLabel("Hoc Luc GIOI:"));
        summaryPanel.add(lblGioi);
        summaryPanel.add(new JLabel("Hoc Luc KHA:"));
        summaryPanel.add(lblKha);
        summaryPanel.add(new JLabel("Hoc Luc TRUNG BINH:"));
        summaryPanel.add(lblTrungBinh);
        summaryPanel.add(new JLabel("Hoc Luc YEU:"));
        summaryPanel.add(lblYeu);
        summaryPanel.add(new JLabel("Hoc LAI (Duoi 3):"));
        summaryPanel.add(lblHocLai);

        return summaryPanel;
    }
    
    private void exportThongKeHandler() { // NEW HANDLER
        ReportUtil.exportTableToCSV(resultTable, "ThongKeHocLuc.csv");
    }

    private void loadThongKeData() {
        try {
            tableModel.setRowCount(0);
            
            // Buoc 1: Lay du lieu va map diem
            List<HocSinh> allHocSinh = hocSinhService.getAllHocSinh();
            List<DiemHs> allDiem = diemHSService.getAllDiemHs(); 
            
            Map<Integer, DiemHs> diemMap = new HashMap<>();
            for (DiemHs d : allDiem) {
                diemMap.put(d.getMaHS(), d);
            }
            
            // Reset thong ke
            Map<String, Integer> hocLucCount = new HashMap<>();
            hocLucCount.put("Xuat sac", 0);
            hocLucCount.put("Gioi", 0);
            hocLucCount.put("Kha", 0);
            hocLucCount.put("Trung binh", 0);
            hocLucCount.put("Yeu", 0);
            hocLucCount.put("Hoc Lai", 0);
            
            int tongCoDiem = 0;

            // Buoc 2: Xu ly va hien thi
            for (HocSinh hs : allHocSinh) {
                int maHS = hs.getMaHS();
                DiemHs diem = diemMap.get(maHS); 
                
                String hocLuc = "Chua co Diem";
                float diemTB = 0.0f;
                
                if (diem != null) {
                    diemTB = diemHSService.tinhDiemTB(diem); //
                    hocLuc = getHocLuc(diemTB);
                    tongCoDiem++;
                    
                    if (hocLucCount.containsKey(hocLuc)) {
                        hocLucCount.put(hocLuc, hocLucCount.get(hocLuc) + 1);
                    }
                }
                
                Object[] row = {
                    maHS,
                    hs.getHoTen(),
                    (diem != null ? String.format("%.2f", diemTB) : "N/A"),
                    hocLuc
                };
                tableModel.addRow(row);
            }
            
            // Buoc 3: Cap nhat Panel Tong quan
            lblTongHS.setText(String.valueOf(allHocSinh.size()));
            lblTongCoDiem.setText(String.valueOf(tongCoDiem));
            
            lblXuatSac.setText(String.valueOf(hocLucCount.get("Xuat sac")));
            lblGioi.setText(String.valueOf(hocLucCount.get("Gioi")));
            lblKha.setText(String.valueOf(hocLucCount.get("Kha")));
            lblTrungBinh.setText(String.valueOf(hocLucCount.get("Trung binh")));
            lblYeu.setText(String.valueOf(hocLucCount.get("Yeu")));
            lblHocLai.setText(String.valueOf(hocLucCount.get("Hoc Lai")));


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Loi khi tai thong ke: " + ex.getMessage(), "Loi He Thong", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    // Ham Helper de xac dinh Hoc Luc (Sao chep logic tu Main.java console)
    private String getHocLuc(float diemTB) {
        if(diemTB < 3){ 
            return "Hoc Lai";
        }
        else if(diemTB < 5){ 
            return "Yeu";
        }
        else if(diemTB < 8){ 
            return "Trung binh";
        }
        else if(diemTB < 9){ 
            return "Kha";
        }
        else if(diemTB < 10){ 
            return "Gioi";
        }
        else if(diemTB == 10){ 
            return "Xuat sac";
        }
        return "Khong xac dinh";
    }
}