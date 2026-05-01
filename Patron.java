/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

/**
 *
 * @author sgoon
 */
public class Patron extends Person {
    private int libraryCardNumber; 

    public Patron(int libraryCardNumber, String name, String username, String password) {
        super(name, username, password);
        this.libraryCardNumber = libraryCardNumber;
    }

    public int getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public void setLibraryCardNumber(int libraryCardNumber) {
        this.libraryCardNumber = libraryCardNumber;
    }

}