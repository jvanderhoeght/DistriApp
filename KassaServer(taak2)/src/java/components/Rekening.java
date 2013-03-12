/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jan
 */
public class Rekening  implements Serializable{
    
    private ArrayList<Product> producten=new ArrayList<Product>();
    private Kassier kassier;
    private String outprint;
    private double totaal=0;
    
    public Rekening(Kassier k){
        this.setKassier(k);
    }

    public ArrayList<Product> getProducten() {
        return producten;
    }

    public void setProducten(ArrayList<Product> producten) {
        if(producten!=null){
            this.producten = producten;
        }else{
            this.producten = new ArrayList<Product>();
        }
    }
    
    public void addProduct(Product p){
        this.getProducten().add(p);
        this.setTotaal(this.getTotaal()+p.getPrijs());
    }

    public Kassier getKassier() {
        return kassier;
    }

    public void setKassier(Kassier kassier) {
        if(kassier!=null){
            this.kassier = kassier;
        }else{
            this.kassier = new Kassier("Chuck Norris", 1617.17);
        }
    }

    public String getOutprint() {
        return outprint;
    }

    public void setOutprint(String outprint) {
        if(outprint!=null && !outprint.equals("")){
            this.outprint = outprint;
        }else{
            this.outprint = "Er is iets misgelopen, probeer opnieuw";
        }
    }

    public double getTotaal() {
        return totaal;
    }

    public void setTotaal(double totaal) {
        if(totaal>0){
            this.totaal = totaal;
        }
    }
    
    public void generateOutprint(){
        StringBuilder st=new StringBuilder();
        st.append("\t\tDistributed Applications\t\t\n");
        st.append("Product\t\t\t\tPrijs\n");
        st.append("--------------------------------------------------------\n");
        for(Product p:this.getProducten()){
            st.append(p.getNaam()+"\t\t\t\t"+p.getPrijs()+"\n");
        }
        st.append("--------------------------------------------------------\n");
        st.append("Totaal: \t\t\t"+this.getTotaal());
        st.append("\n\tU werd geholpen door: ");
        st.append(this.getKassier().getNaam());
        this.setOutprint(st.toString());
    }
}
