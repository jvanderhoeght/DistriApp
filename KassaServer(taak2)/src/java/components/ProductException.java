/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import javax.ejb.ApplicationException;

/**
 *
 * @author Jan
 */
@ApplicationException
public class ProductException extends RuntimeException{
    
    public ProductException(){}
    
    public ProductException(String message){super(message);}
    
}
