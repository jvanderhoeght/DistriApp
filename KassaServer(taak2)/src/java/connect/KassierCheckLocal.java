/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import components.Kassier;
import javax.ejb.Local;

/**
 *
 * @author Jan
 */
@Local
public interface KassierCheckLocal {
    
    boolean checkKassier(Kassier k);
}
