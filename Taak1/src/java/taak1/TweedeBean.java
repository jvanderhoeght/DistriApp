/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taak1;

import javax.ejb.Stateless;

/**
 *
 * @author Jan
 */
@Stateless
public class TweedeBean implements TweedeCheckBean{

    @Override
    public String addSalt(String word) {
        return "###"+word+"###";
    }
}
