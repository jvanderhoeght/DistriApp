/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.ejb.ApplicationException;

/**
 *
 * @author Jan
 */
@ApplicationException
public class PuntenBoekException extends RuntimeException {
    
    public PuntenBoekException(String message){
        super(message);
    }
    
    public PuntenBoekException(String message, Throwable throwable){
        super(message, throwable);
    }
}
