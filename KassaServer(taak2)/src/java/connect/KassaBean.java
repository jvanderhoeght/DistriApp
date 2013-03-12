/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import components.Kassier;
import components.Rekening;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author Jan
 */
@Stateful
public class KassaBean implements KassaCheckBean {
    
    @EJB
    private KassierCheckLocal kassierCheckBean;
    
    private String output;
    ArrayList<Rekening> rekeningen;

    @PostConstruct
    public void init(){
        rekeningen=new ArrayList<Rekening>();
    }
    @Override
    public boolean voegRekeningToe(Rekening rekening) {
        boolean b;
        try{
            b=kassierCheckBean.checkKassier(rekening.getKassier());
            if(b){
                rekeningen.add(rekening);
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            throw new KassaException(e.getMessage());
        } 
    }

    @Remove
    public void gedaan() {
    }

    @Override
    public void generateFullOutprint() {
        StringBuilder s=new StringBuilder();
        s.append("Overzicht rekeningen\n");
        for(int i=0; i< this.rekeningen.size(); i++){
            s.append("Rekening nr: ");s.append(i);s.append("\n");
            s.append("Totaal: ");s.append(this.rekeningen.get(i).getTotaal());s.append("\n");
            s.append("Kassier: ");s.append(this.rekeningen.get(i).getKassier().getNaam());s.append("\n");
        }
        this.output=s.toString();
    }

    @Override
    public String getOutput() {
        return this.output;
    }

    @Override
    public List<Rekening> getContents() {
        return this.rekeningen;
    }
    
}
