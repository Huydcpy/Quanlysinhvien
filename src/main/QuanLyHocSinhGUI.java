// Trong file src/main/QuanLyHocSinhGUI.java
package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class QuanLyHocSinhGUI extends JFrame {

    private JPanel mainContentPanel;

    public QuanLyHocSinhGUI() {
        setTitle("HE THONG QUAN LY HOC SINH");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Can giua cua so

        // 1. Setup the Menu Bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuQuanLy = new JMenu("Quan ly"); 
        menuBar.add(menuQuanLy);

        // Menu Items
        JMenuItem miLop = new JMenuItem("1. Quan ly Lop"); 
        JMenuItem miHocSinh = new JMenuItem("2. Quan ly Hoc Sinh"); 
        JMenuItem miDiem = new JMenuItem("3. Quan ly Diem"); 
        JMenuItem miThongKe = new JMenuItem("4. Thong ke Hoc Luc"); // THEM MENU MOI
        JMenuItem miThoat = new JMenuItem("0. Thoat"); 

        menuQuanLy.add(miLop);
        menuQuanLy.add(miHocSinh);
        menuQuanLy.add(miDiem);
        menuQuanLy.add(miThongKe); // Them vao menu
        menuQuanLy.addSeparator();
        menuQuanLy.add(miThoat);

        // 2. Setup the Main Content Panel
        mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BorderLayout());
        add(mainContentPanel, BorderLayout.CENTER);

        // Set initial screen
        showPanel(new JPanel()); 

        // 3. Add Event Listeners for menu actions
        miLop.addActionListener(this::handleLopAction);
        miHocSinh.addActionListener(this::handleHocSinhAction); 
        miDiem.addActionListener(this::handleDiemAction); 
        miThongKe.addActionListener(this::handleThongKeAction); // LIEN KET THONG KE
        miThoat.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    /**
     * Thay the panel hien tai trong khung chinh bang mot panel moi.
     */
    private void showPanel(JPanel panel) {
        mainContentPanel.removeAll();
        mainContentPanel.add(panel, BorderLayout.CENTER);
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }

    private void handleLopAction(ActionEvent e) {
        showPanel(new LopPanel()); 
    }
    
    private void handleHocSinhAction(ActionEvent e) {
        showPanel(new HocSinhPanel()); 
    }

    private void handleDiemAction(ActionEvent e) {
        showPanel(new DiemPanel()); 
    }
    
    private void handleThongKeAction(ActionEvent e) {
        showPanel(new ThongKePanel()); 
    }

    public static void main(String[] args) {
        // Chay GUI tren Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuanLyHocSinhGUI();
            }
        });
    }
}