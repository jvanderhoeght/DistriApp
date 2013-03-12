/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolentities;

import java.util.ArrayList;

/**
 *
 * @author Jan
 */
public class Puntenboek {
    
    private ArrayList<Course> courses=new ArrayList<>();
    
    public Puntenboek(){
        
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    public void addCourse(Course course) throws PuntenboekException{
        if(!this.getCourses().contains(course)){
            this.getCourses().add(course);
        }else{
            throw new PuntenboekException("Dit vak bestaat al in het puntenboek");
        }
    }
    
    public void removeCourse(Course course) throws PuntenboekException{
        if(this.getCourses().contains(course)){
            this.getCourses().remove(course);
        }else{
            throw new PuntenboekException("Dit vak bestaat niet in het puntenboek");
        }
    }
    
}
