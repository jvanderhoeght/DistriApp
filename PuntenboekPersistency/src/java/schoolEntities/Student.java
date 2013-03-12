/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolEntities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Jan
 */
@Entity
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String firstname;
    private String lastname;
    private int number=-1;
    @ManyToOne
    private SchoolClass schoolClass;
    @OneToMany(cascade=CascadeType.MERGE, fetch= FetchType.EAGER)
    private List<Point> points=new ArrayList<Point>();

    public Student(){}
    
    public Student(String firstname, String lastname) throws StudentException {
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

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public List<Point> getPoints() {
        return points;
    }
    
    public Point getPoint(Test t) throws StudentException{
        Point p=null;
        int tel=0;
        boolean present=this.getSchoolClass().hasTest(t);
        while(p==null && tel<this.getPoints().size() && present){
            if(this.getPoints().get(tel).getTest().equals(t)){
                p=this.getPoints().get(tel);
            }
            tel++;
        }
        if(!present){
            throw new StudentException("Deze student heeft geen punten voor deze test");
        }
        return p;
    }
    
    public void addPoint(Point p) throws StudentException{
        boolean testpresent=this.getSchoolClass().hasTest(p.getTest());
        boolean pointpresent=false;
        for(Point point:this.getPoints()){
            if(point.getTest().equals(p.getTest())){
                pointpresent=true;
                break;
            }
        }
        if(testpresent && !pointpresent){
            this.getPoints().add(p);
        }else{
            if(testpresent){
                throw new StudentException("Deze student heeft al punten voor deze test");
            }else{
                throw new StudentException("De klas van deze student heeft deze test niet afgelegd");
            }
        }
    }
    
    public void removePoint(Point p) throws StudentException{
        if(this.getPoints().contains(p)){
                this.getPoints().add(p);
            }else{
                throw new StudentException("Deze student heeft dit punt niet");
            }
    }

    @Override
    public boolean equals(Object o) {
        boolean res=false;
        if(o!=null && o instanceof Student){
            Student s=(Student)o;
            res=this.getFirstname().equals(s.getFirstname()) && this.getLastname().equals(s.getLastname());
        }
        return res;
    }

    @Override
    public String toString() {
        return this.getLastname()+" "+this.getFirstname();
    }

    /**
     *
     * @param t
     * @return
     
    @Override
    public int compareTo(Student t) {
        if(this.getNumber()<t.getNumber()){
            return -1;
        }else if(this.getNumber()>t.getNumber()){
            return 1;
        }else{
            return 0;
        }
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
}
