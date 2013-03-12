/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taak1;

import javax.ejb.Remote;

/**
 *
 * @author Jan
 */
@Remote
public interface TweedeCheckBean {

    String addSalt(String word);
    
}
