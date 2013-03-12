/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import components.Kassier;
import javax.ejb.Remote;

/**
 *
 * @author Jan
 */
@Remote
public interface KassierCheckRemote {
    
    boolean checkKassier(Kassier k);
}
