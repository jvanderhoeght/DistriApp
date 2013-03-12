/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolEntities;

import java.io.Serializable;

/**
 *
 * @author Jan
 */
public class StudentException extends Exception implements Serializable{
    
    public StudentException(){
        super();
    }
    
    public StudentException(String message){
        super(message);
    }
}
