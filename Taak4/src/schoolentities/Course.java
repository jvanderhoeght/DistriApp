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
public class Course implements Comparable<Course> {
    
    private ArrayList<Class> classes=new ArrayList<>();
    private ArrayList<Test> tests=new ArrayList<>();
    private CourseName name;

    public Course(CourseName cn){
        this.setName(cn);
    }
    
    public CourseName getName() {
        return name;
    }

    public void setName(CourseName name) {
        this.name = name;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
    
    public void addClass(Class c) throws CourseException{
        if(!this.getClasses().contains(c)){
            this.getClasses().add(c);
        }else{
            throw new CourseException("Deze klas zit al in de lijst");
        }
    }
    
    public void removeClass(Class c) throws CourseException{
        if(this.getClasses().contains(c)){
            this.getClasses().add(c);
        }else{
            throw new CourseException("Deze klas zit niet in de lijst");
        }
    }
    
    public void addTest(Test t) throws CourseException{
        if(!this.getTests().contains(t)){
            this.getTests().add(t);
        }else{
            throw new CourseException("Deze test zit al in de lijst");
        }
    }
    
    public void removeTest(Test t) throws CourseException{
        if(this.getTests().contains(t)){
            this.getTests().add(t);
        }else{
            throw new CourseException("Deze test zit niet in de lijst");
        }
    }

    @Override
    public boolean equals(Object o) {
        boolean res=false;
        if(o!=null && o instanceof Course){
            Course c=(Course)o;
            res=this.getName().equals(c.getName()) && this.getClasses().size()==c.getClasses().size();
            if(res){
                for(int i=0; i<this.getClasses().size(); i++){
                    res&=this.getClasses().get(i).equals(c.getClasses().get(i));
                }
            }
        }
        return res;
    }

    @Override
    public int compareTo(Course t) {
        String n1=this.getName().toString();
        String n2=this.getName().toString();
        return n1.compareToIgnoreCase(n2);
    }
}
