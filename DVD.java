/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

/**
 *
 * @author sgoon
 */
public class DVD extends Material {
    private String productionCompany;
    private String director;

    public DVD(String productionCompany, String director, int itemNumber, String title, int year, String subject) {
        super(itemNumber, title, year, subject);
        this.productionCompany = productionCompany;
        this.director = director;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }  
}
