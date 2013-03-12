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
public class Kassier implements Serializable{
    
    private String naam;
    private double loon;
    
    public Kassier(String naam, double loon){
        this.setNaam(naam);
        this.setLoon(loon);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getLoon() {
        return loon;
    }

    public void setLoon(double loon) {
        this.loon = loon;
    }

    @Override
    public boolean equals(Object o) {
        boolean res=false;
        if(o!=null && o instanceof Kassier){
            Kassier k=(Kassier)o;
            if(this.getNaam()!=null){
                res=this.getNaam().equals(k.getNaam());
            }
        }
        return res;
    }
}
