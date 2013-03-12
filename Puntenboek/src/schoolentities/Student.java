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
public class Student implements Comparable<Student> {
    
    private String firstname;
    private String lastname;
    private int number;
    private ArrayList<Point> points=new ArrayList<>();

    public Student(String firstname, String lastname, int number) throws StudentException {
        this.setFirstname(firstname);
        this.setLastname(lastname);
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) throws StudentException {
        if(firstname!=null && !firstname.equals("")){
            this.firstname = firstname;
        }else{
            throw new StudentException("Geef een voornaam in");
        }
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) throws StudentException {
        if(lastname!=null && !lastname.equals("")){
            this.lastname = lastname;
        }else{
            throw new StudentException("Geef een achternaam in");
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) throws StudentException {
        if(number>0 && number<=30){
            this.number = number;
        }else{
            throw new StudentException("Geef een nummer van 1 t.e.m. 30");
        }
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
    
    public int getPoint(Test t) throws StudentException{
        int p=-1;
        int tel=0;
        while(p==-1 && tel<this.getPoints().size()){
            if(this.getPoints().get(tel).getTest().equals(t)){
                p=this.getPoints().get(tel).getPoints();
            }
            tel++;
        }
        if(p==-1){
            throw new StudentException("Deze student heeft geen punten voor deze test");
        }
        return p;
    }

    @Override
    public boolean equals(Object o) {
        boolean res=false;
        if(o!=null && o instanceof Student){
            Student s=(Student)o;
            res=this.getFirstname().equals(s.getFirstname()) && this.getLastname().equals(s.getLastname()) && this.getNumber()== s.getNumber();
        }
        return res;
    }

    @Override
    public String toString() {
        return this.getLastname()+" "+this.getFirstname();
    }

    @Override
    public int compareTo(Student t) {
        if(this.getNumber()<t.getNumber()){
            return -1;
        }else if(this.getNumber()>t.getNumber()){
            return 1;
        }else{
            return 0;
        }
    }
    
    
}
