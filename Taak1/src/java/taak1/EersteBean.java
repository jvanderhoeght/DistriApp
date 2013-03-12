/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taak1;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.naming.*;

/**
 *
 * @author Jan
 */
@Stateful
public class EersteBean implements EersteCheckBean {

    TweedeCheckBean bean;
    
    @PostConstruct
    public void init(){
        try{
            Context con=new InitialContext();
            bean=(TweedeCheckBean)con.lookup("java:global/Taak1/TweedeBean!taak1.TweedeCheckBean");
        } catch(NamingException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public String change(String word) {
        return bean.addSalt(word);
    }
    
    @Remove
    public void gedaan(){
        
    }
}
