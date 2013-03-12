/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import components.Kassier;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Jan
 */
@Singleton
@Startup
public class KassierBean implements KassierCheckLocal, KassierCheckRemote {
    private ArrayList<Kassier> kassiers;
    
    @Schedule(second="*/5", minute="*", hour="*", info="loonincrease")
    public void verhoogLoon(javax.ejb.Timer t){
        for(Kassier k:kassiers){
            k.setLoon(k.getLoon()*1.05);
            System.out.println(k.getNaam()+" "+k.getLoon());
        }
    }
    
    @PostConstruct
    public void init(){
        kassiers=new ArrayList<Kassier>();
        kassiers.add(new Kassier("Chuck Norris", 1617.17));
        kassiers.add(new Kassier("kassier2", 1617.17));
    } 
   
    public boolean checkKassier(Kassier k) {
        if(k!=null){
            return kassiers.contains(k);
        }else{
            throw new KassierException("Enter a valid Kassier-object");
        }
    }

}
