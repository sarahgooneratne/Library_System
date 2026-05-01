/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;


import java.sql.*;

/**
 *
 * @author sgoon
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:Library.db";
    
    public static void createDatabase() {
        String booksTable = "CREATE TABLE IF NOT EXISTS books (itemNumber INTEGER PRIMARY KEY, title TEXT, year INTEGER, subject TEXT, author TEXT, genre TEXT)";
        String magazinesTable = "CREATE TABLE IF NOT EXISTS magazines (itemNumber INTEGER PRIMARY KEY, title TEXT, year INTEGER, subject TEXT, volume INTEGER, issue INTEGER, publisher TEXT)";
        String dvdsTable = "CREATE TABLE IF NOT EXISTS dvds (itemNumber INTEGER PRIMARY KEY, title TEXT, year INTEGER, subject TEXT, productionCompany TEXT, director TEXT)";
        String checkoutsTable = "CREATE TABLE IF NOT EXISTS checkouts (id INTEGER PRIMARY KEY AUTOINCREMENT, patronID INTEGER, itemNumber INTEGER, checkoutDate TEXT)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(booksTable);
            stmt.execute(magazinesTable);
            stmt.execute(dvdsTable);
            stmt.execute(checkoutsTable);
            System.out.println("Database created!");
        } catch (SQLException e) {
            System.out.println("Error creating database: " + e.getMessage());
        }
    }
    
    public static void insertBook(Book book) {
        String insertSql = "INSERT INTO books (itemNumber, title, year, subject, author, genre) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setInt(1, book.getItemNumber());
            pstmt.setString(2, book.getTitle());
            pstmt.setInt(3, book.getYear());
            pstmt.setString(4, book.getSubject());
            pstmt.setString(5, book.getAuthor());
            pstmt.setString(6, book.getGenre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting book: " + e.getMessage());
        }
    }
    
    public static void insertMagazine(Magazine magazine) {
        String insertSql = "INSERT INTO magazines (itemNumber, title, year, subject, volume, issue, publisher) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setInt(1, magazine.getItemNumber());
            pstmt.setString(2, magazine.getTitle());
            pstmt.setInt(3, magazine.getYear());
            pstmt.setString(4, magazine.getSubject());
            pstmt.setInt(5, magazine.getVolume());
            pstmt.setInt(6, magazine.getIssue());
            pstmt.setString(7, magazine.getPublisher());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting magazine: " + e.getMessage());
        }
    }
    
    public static void insertDVD(DVD dvd) {
        String insertSql = "INSERT INTO dvds (itemNumber, title, year, subject, productionCompany, director) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setInt(1, dvd.getItemNumber());
            pstmt.setString(2, dvd.getTitle());
            pstmt.setInt(3, dvd.getYear());
            pstmt.setString(4, dvd.getSubject());
            pstmt.setString(5, dvd.getProductionCompany());
            pstmt.setString(6, dvd.getDirector());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting DVD: " + e.getMessage());
        }
    }
}
