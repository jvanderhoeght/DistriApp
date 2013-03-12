/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.io.Serializable;

/**
 *
 * @author Jan
 */
public class Product  implements Serializable{
    
    private String naam;
    private double prijs;
    
    public Product(String naam, double prijs){
        this.setNaam(naam);
        this.setPrijs(prijs);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if(naam!=null && !naam.equals("")){
            this.naam = naam;
        }else{
            throw new ProductException("Enter a valid name");
        }
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        if(prijs>0 && prijs <250){
            this.prijs = prijs;
        }else{
            this.prijs = 10;
        }
    }

    @Override
    public String toString() {
        return this.getNaam()+" "+this.getPrijs();
    }
    
}
