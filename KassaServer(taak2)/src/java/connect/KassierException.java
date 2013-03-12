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
public class KassierException extends RuntimeException{
    
    public KassierException(){}
    
    public KassierException(String message){super(message);}
}
