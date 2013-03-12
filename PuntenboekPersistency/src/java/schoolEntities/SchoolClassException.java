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
public class SchoolClassException extends Exception implements Serializable{
    
    public SchoolClassException(){
        super();
    }
    
    public SchoolClassException(String message){
        super(message);
    }
}
