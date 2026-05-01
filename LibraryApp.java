/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package finalproject;

import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author sgoon
 */
public class LibraryApp extends JFrame {
    public static boolean libraryOpen = false; 
    
    public LibraryApp() {
        setTitle("Library System");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField();

        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();

        JLabel lblRole = new JLabel("Role:");
        JComboBox<String> cmbRole = new JComboBox<>(new String[]{"Patron", "Employee"});

        JButton btnLogin = new JButton("Login");
        JButton btnExit = new JButton("Exit");

        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(lblRole);
        panel.add(cmbRole);
        panel.add(btnLogin);
        panel.add(btnExit);

        add(panel);
        setVisible(true);

        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();
            String role = (String) cmbRole.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both username and password.");
                return;
            }

            if (role.equals("Patron")) {
                if (!LibraryApp.libraryOpen) {
                    JOptionPane.showMessageDialog(null, "Sorry, the library is currently under maintenance.");
                    return;
                }
                if (username.equals("sarah_patron") && password.equals("123456")) {
                    dispose();
                    PatronScreen ps = new PatronScreen();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials.");
                }
            } else if (role.equals("Employee")) {
                if(username.equals("sarah_admin") && password.equals("123456")) {
                    dispose();
                    EmployeeScreen es = new EmployeeScreen();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login information");
                }    
            }
        });

        btnExit.addActionListener(e -> {
                System.exit(0);
        });
    }
    
    public static void main(String[] args) {
        DatabaseManager.createDatabase();
        LibraryApp la = new LibraryApp();
    }
}
