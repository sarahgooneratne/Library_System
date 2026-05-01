/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author sgoon
 */
class EmployeeScreen extends JFrame {
    public EmployeeScreen() {
        setTitle("Employee - Library System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblStatus = new JLabel("Library Status: UNDER MAINTENANCE", SwingConstants.CENTER);
        lblStatus.setForeground(Color.RED);

        JLabel lblInstructions = new JLabel("Import list of materials from text file.");
        
        JLabel lblFile = new JLabel("Enter file name:");
        JTextField txtFile = new JTextField();
        
        JButton btnImport = new JButton("Import Materials");
        JButton btnLogout = new JButton("Logout");

        panel.add(lblStatus);
        panel.add(lblInstructions);
        panel.add(lblFile);
        panel.add(txtFile);
        panel.add(btnImport);
        panel.add(btnLogout);

        add(panel);
        setVisible(true);

        btnImport.addActionListener(e -> {
            String filePath = txtFile.getText().trim();
            if (filePath.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a file name");
                return;
            }
            FileReader.loadData(filePath);
            LibraryApp.libraryOpen = true;
            lblStatus.setText("Library Status: OPEN");
            lblStatus.setForeground(Color.GREEN);
        });

        btnLogout.addActionListener(e -> {
            dispose();
            LibraryApp la = new LibraryApp();
        });
    }
}
