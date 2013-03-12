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
public class Class {
    
    private String identification;
    private ArrayList<Student> students;

    
    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) throws ClassException {
        if(identification!=null && !identification.equals("")){
            this.identification = identification;
        }else{
            throw new ClassException("Geef een klasidentificatie in");
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
    
    public void addStudent(Student s) throws ClassException{
        if(!this.getStudents().contains(s)){
            this.getStudents().add(s);
        }else{
            throw new ClassException("Deze student zit al in deze klas");
        }
    }
    
    public void removeStudent(Student s) throws ClassException{
        if(this.getStudents().contains(s)){
            this.getStudents().remove(s);
        }else{
            throw new ClassException("Deze student zit niet in deze klas");
        }
    }
    
    public void ClearStudentList(){
        this.getStudents().clear();
    }

    @Override
    public boolean equals(Object o) {
        boolean res=false;
        if(o!=null && o instanceof Class){
            Class c=(Class)o;
            res=this.getIdentification().equals(c.getIdentification()) && this.getStudents().size()==this.getStudents().size();
            if(res){
                for(int i=0; i<this.getStudents().size(); i++){
                    res&=this.getStudents().get(i).equals(c.getStudents().get(i));
                }
            }
        }
        return res;
    }
    
    
}
