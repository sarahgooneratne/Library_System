/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

/**
 *
 * @author sgoon
 */
class PatronScreen extends JFrame {
    public PatronScreen() {
        setTitle("Patron - Library System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tp = new JTabbedPane();
        tp.addTab("Books", BookTable());
        tp.addTab("Magazines", MagazineTable());
        tp.addTab("DVDs", DVDTable());
        tp.addTab("Checkout", CheckoutTab());

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            dispose();
            LibraryApp la = new LibraryApp();
        });

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(btnLogout);

        add(tp, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        setVisible(true);
    }

    private JPanel BookTable() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] cols = {"Item Number", "Title", "Year", "Subject", "Author", "Genre"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Library.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("itemNumber"),
                    rs.getString("title"),
                    rs.getInt("year"),
                    rs.getString("subject"),
                    rs.getString("author"),
                    rs.getString("genre")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! Having trouble loading books: " + e.getMessage());
        }

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel MagazineTable() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] cols = {"Item #", "Title", "Year", "Subject", "Volume", "Issue", "Publisher"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Library.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM magazines")) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("itemNumber"),
                    rs.getString("title"),
                    rs.getInt("year"),
                    rs.getString("subject"),
                    rs.getInt("volume"),
                    rs.getInt("issue"),
                    rs.getString("publisher")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! Having trouble loading magazines: " + e.getMessage());
        }

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel DVDTable() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] cols = {"Item #", "Title", "Year", "Subject", "Production Company", "Director"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Library.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM dvds")) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("itemNumber"),
                    rs.getString("title"),
                    rs.getInt("year"),
                    rs.getString("subject"),
                    rs.getString("productionCompany"),
                    rs.getString("director")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! Having trouble loading DVDs: " + e.getMessage());
        }

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel CheckoutTab() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblPatronID = new JLabel("Your Library Card Number:");
        JTextField txtPatronID = new JTextField();
        JLabel lblItemNumber = new JLabel("Item Number to Checkout:");
        JTextField txtItemNumber = new JTextField();
        JLabel lblCheckoutDate = new JLabel("Checkout Date (YYYY-MM-DD):");
        JTextField txtCheckoutDate = new JTextField();
        JButton btnCheckout = new JButton("Checkout");

        panel.add(lblPatronID);
        panel.add(txtPatronID);
        panel.add(lblItemNumber);
        panel.add(txtItemNumber);
        panel.add(lblCheckoutDate);
        panel.add(txtCheckoutDate);
        panel.add(new JLabel());
        panel.add(btnCheckout);

        btnCheckout.addActionListener(e -> {
            String patronID = txtPatronID.getText().trim();
            String itemNumber = txtItemNumber.getText().trim();
            String checkoutDate = txtCheckoutDate.getText().trim();

            if (patronID.isEmpty() || itemNumber.isEmpty() || checkoutDate.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }

            String insertSql = "INSERT INTO checkouts (patronID, itemNumber, checkoutDate) VALUES (?,?,?)";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Library.db");
                 PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setInt(1, Integer.parseInt(patronID));
                ps.setInt(2, Integer.parseInt(itemNumber));
                ps.setString(3, checkoutDate);
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Successfully checked out item!");
                
                txtPatronID.setText("");
                txtItemNumber.setText("");
                txtCheckoutDate.setText("");
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Checkout error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Patron ID and Item Number must be numbers.");
            }
        });

        return panel;
    }
}
