/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taak1applicatie;

import javax.naming.*;
import taak1.EersteCheckBean;

/**
 *
 * @author Jan
 */
public class Taak1Applicatie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Context c;
        Object o;
        try{
            
            c = new InitialContext();
                    //   java:global/Taak1/EersteBean
            o=c.lookup("java:global/Taak1/EersteBean");

            System.out.println("EersteCheckBean class: "+o.getClass().getName());
            EersteCheckBean n=(EersteCheckBean)o;
            System.out.println(n.change("test"));
            
            //java:global/Taak1/EersteBean!taak1.EersteCheckBean
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
