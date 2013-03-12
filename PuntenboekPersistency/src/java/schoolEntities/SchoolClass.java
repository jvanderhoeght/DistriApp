/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolEntities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Jan
 */
@Entity
public class SchoolClass implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int nr=0;
    
    private String identification;
    @OneToMany(fetch= FetchType.EAGER)
    private List<Student> students=new ArrayList<Student>();
    @OneToMany(fetch= FetchType.EAGER)
    private List<Test> tests=new ArrayList<Test>();

    public SchoolClass(){}
    
    public SchoolClass(String ident) throws SchoolClassException{
        this.setIdentification(ident);
    }
    
    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) throws SchoolClassException {
        if(identification!=null && !identification.equals("")){
            this.identification = identification;
        }else{
            throw new SchoolClassException("Geef een klasidentificatie in");
        }
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
    public void setStudents(List<Student> students) throws SchoolClassException{
        if(students!=null){
            this.students=students;
        }else{
            throw new SchoolClassException("Enter valid studentlist");
        }
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
    
    public void addStudent(Student s) throws SchoolClassException{
        if(!this.getStudents().contains(s)){
            this.getStudents().add(s);
        }else{
            throw new SchoolClassException("Deze student zit al in deze klas");
        }
    }
    
    public void removeStudent(Student s) throws SchoolClassException{
        Student stud=null;
        for(Student st: this.getStudents()){
            if(st.getId()==s.getId()){
                stud=st;
            }
        }
        this.getStudents().remove(stud);
    }
    
    public void addTest(Test t) throws SchoolClassException{
        if(!this.getTests().contains(t)){
            this.getTests().add(t);
        }else{
            throw new SchoolClassException("Deze test zit al in de toetsenlijst van deze klas");
        }
    }
    
    public void removeTest(Test t) throws SchoolClassException{
        if(this.getTests().contains(t)){
            this.getTests().remove(t);
        }else{
            throw new SchoolClassException("Deze test zit niet in de toetsenlijst van deze klas");
        }
    }
    
    public boolean hasTest(Test t){
        if(this.getTests().contains(t)){
            return true;
        }
        return false;
    }
    
    public void ClearStudentList(){
        this.getStudents().clear();
    }

    @Override
    public boolean equals(Object o) {
        boolean res=false;
        if(o!=null && o instanceof SchoolClass){
            SchoolClass c=(SchoolClass)o;
            res=this.getIdentification().equals(c.getIdentification());
        }
        return res;
    }

    @Override
    public String toString() {
        return this.getIdentification();
    }

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
    
    public int firstFreeNumber(){
        nr+=1;
        return nr;
    }
}
