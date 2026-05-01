/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author sgoon
 */
public class FileReader {
    public static void loadData(String file) {
        int numLines = 0;
        
        try(java.io.FileReader fr = new java.io.FileReader(file)) {
            BufferedReader br = new BufferedReader(fr);
            JOptionPane.showMessageDialog(null, "The file has been found! Books are being imported...");
            
            String currLine;
            while((currLine = br.readLine()) != null) {
                currLine = currLine.trim();
                
                if(!currLine.isEmpty() && !currLine.startsWith("#")) {
                    Scanner scan = new Scanner(currLine);
                    scan.useDelimiter(";");
                    
                    String materialType = scan.next().trim().toUpperCase();
                    
                    if(materialType.equals("BOOK")) {
                        int itemNumber = Integer.parseInt(scan.next().trim());
                        String title = scan.next().trim();
                        int year = Integer.parseInt(scan.next().trim());
                        String subject = scan.next().trim();
                        String author = scan.next().trim();
                        String genre = scan.next().trim();
                        
                        Book book = new Book(author, genre, itemNumber, title, year, subject);
                        DatabaseManager.insertBook(book);
                        
                    } else if (materialType.equals("MAGAZINE")) {
                        int itemNumber = Integer.parseInt(scan.next().trim());
                        String title = scan.next().trim();
                        int year = Integer.parseInt(scan.next().trim());
                        String subject = scan.next().trim();
                        int volume = Integer.parseInt(scan.next().trim());
                        int issue = Integer.parseInt(scan.next().trim()); 
                        String publisher = scan.next().trim();
                        
                        Magazine magazine = new Magazine(volume, issue, publisher, itemNumber, title, year, subject);
                        DatabaseManager.insertMagazine(magazine);
                        
                    } else if (materialType.equals("DVD")) {
                        int itemNumber = Integer.parseInt(scan.next().trim());
                        String title = scan.next().trim();
                        int year = Integer.parseInt(scan.next().trim());
                        String subject = scan.next().trim();
                        String productionCompany = scan.next().trim();
                        String director = scan.next().trim();
                        
                        DVD dvd = new DVD(productionCompany, director, itemNumber, title, year, subject);
                        DatabaseManager.insertDVD(dvd);
                        
                    }
                    
                    numLines++;
                }
            }
        } catch(FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "The file was not found" + ex);

        } catch (IOException ex1) {
            JOptionPane.showMessageDialog(null, "Error: File could not be read.");
        }
        
        LibraryApp.libraryOpen = true; 
        JOptionPane.showMessageDialog(null, "Materials have finished importing!");
    }
}
