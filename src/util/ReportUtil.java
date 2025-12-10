package util;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ReportUtil {

    // Xuat du lieu tu JTable hien tai ra file CSV
    public static boolean exportTableToCSV(JTable table, String defaultFileName) {
        TableModel model = table.getModel();
        
        // 1. CHON NOI LUU FILE SU DUNG JFILECHOOSER
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chon vi tri luu file CSV");
        fileChooser.setSelectedFile(new File(defaultFileName));
        // Thiet lap bo loc file chi hien thi .csv
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files (*.csv)", "csv"));

        // Hien thi dialog Save File
        int userSelection = fileChooser.showSaveDialog(table); 

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File saveFile = fileChooser.getSelectedFile();
            
            // Dam bao duoi file la .csv
            String filePath = saveFile.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".csv")) {
                saveFile = new File(filePath + ".csv");
            }
            
            // Kiem tra neu file da ton tai, hoi nguoi dung co muon ghi de khong
            if (saveFile.exists()) {
                int confirm = JOptionPane.showConfirmDialog(null, 
                    "File da ton tai. Ban co muon ghi de?", "Xac nhan ghi de", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    return false;
                }
            }
            
            try (FileWriter csv = new FileWriter(saveFile)) {
                // 2. Ghi Header (Ten cot)
                for (int i = 0; i < model.getColumnCount(); i++) {
                    csv.write(model.getColumnName(i) + (i < model.getColumnCount() - 1 ? "," : ""));
                }
                csv.write("\n");
                
                // 3. Ghi du lieu
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object value = model.getValueAt(i, j);
                        // Xu ly gia tri null va thay the dau phay thanh cham phay de khong lam hong dinh dang CSV
                        String cellData = (value == null ? "" : value.toString().replace(",", ";"));
                        csv.write(cellData + (j < model.getColumnCount() - 1 ? "," : ""));
                    }
                    csv.write("\n");
                }
                
                // Thong bao thanh cong
                JOptionPane.showMessageDialog(null, "Xuat file thanh cong tai: " + saveFile.getAbsolutePath(), "Thong bao", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Loi khi ghi file: " + e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                return false;
            }
        }
        return false; // Neu nguoi dung huy bo viec luu file
    }
}