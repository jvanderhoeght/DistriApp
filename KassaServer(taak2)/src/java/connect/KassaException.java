/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import javax.ejb.ApplicationException;

/**
 *
 * @author Jan
 */
@ApplicationException
public class KassaException extends RuntimeException{
    
    public KassaException(){}
    public KassaException(String message){super(message);}
}
