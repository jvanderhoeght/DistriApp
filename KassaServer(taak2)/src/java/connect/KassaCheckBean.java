/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import components.Kassier;
import components.Rekening;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Jan
 */
@Remote
public interface KassaCheckBean {

    boolean voegRekeningToe(Rekening Rekening);

    void gedaan();

    void generateFullOutprint();
    
    String getOutput();
    
    List<Rekening> getContents();
    
}
