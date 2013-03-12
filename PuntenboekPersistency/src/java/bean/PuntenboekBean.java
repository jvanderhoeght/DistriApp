/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import schoolEntities.Point;
import schoolEntities.PointException;
import schoolEntities.SchoolClass;
import schoolEntities.SchoolClassException;
import schoolEntities.Student;
import schoolEntities.StudentException;
import schoolEntities.Test;

/**
 *
 * @author Jan
 */
@Stateless
public class PuntenboekBean implements PuntenboekBeanRemote{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addStudent(Student student) {
        if(this.findStudent(student)==null){
            em.persist(student);
        }else{
            throw new PuntenBoekException("Deze student zit al in de databank");
        }
    }

    @Override
    public void editStudent(Student student) {
        em.merge(student);
    }

    @Override
    public void removeStudent(Student student) {
        Student s=this.findStudent(student.getId());
        try {
            this.findSchoolClass(s.getSchoolClass()).removeStudent(s);
        } catch (SchoolClassException ex) {
            throw new PuntenBoekException(ex.getMessage(), ex);
        }
        this.editSchoolClass(this.findSchoolClass(s.getSchoolClass()));
        List<Point> l=s.getPoints();
        for(Point p:l){
            this.removePoint(p);
        }
        this.editStudent(s);
        em.remove(s);
    }

    @Override
    public void removeTest(Test test) {
        SchoolClass sc=this.findSchoolClass(test.getSchoolClass());
        for(int i=0; i<sc.getStudents().size(); i++){
            Student s=sc.getStudents().get(i);
            Point p=null;
            try{
                p=s.getPoint(test);
                if(p!=null){
                    p.setTest(null);
                    this.editPoint(p);
                    s.getPoints().remove(p);
                    this.editStudent(s);
                    this.removePoint(p);
                }
            }catch(StudentException ex){
                
            }
        }
        sc.getTests().remove(test);
        this.editSchoolClass(sc);
        List<Point> list=this.findPoints(test);
        for(int i=list.size()-1; i>=0; i--){
            list.get(i).setTest(null);
            this.editPoint(list.get(i));
            this.removePoint(list.get(i));
        }
        em.remove(em.merge(test));
    }
    
    private List<Point> findPoints(Test test){
        Query q=em.createQuery("SELECT p FROM Point p WHERE p.test=:ident");
        q.setParameter("ident", test);
        List<Point> list=q.getResultList();
        return list;
    }

    @Override
    public void removePoint(Point p) {
        em.remove(em.merge(p));
    }

    @Override
    public void editSchoolClass(SchoolClass schoolClass) {
        em.merge(schoolClass);
    }

    @Override
    public void addStudentToClass(Student stud, SchoolClass schoolClass){
        try {
            if(!schoolClass.getStudents().contains(stud)){
                Student s=this.findStudent(stud.getId());
                SchoolClass sc=this.findSchoolClass(schoolClass.getId());
                int i=sc.firstFreeNumber();
                s.setNumber(i);
                s.setSchoolClass(sc);
                this.editStudent(s);
                sc.addStudent(s);
                this.editSchoolClass(sc);
            }
        } catch (Exception ex) {
            throw new PuntenBoekException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void addSchoolClass(SchoolClass schoolClass) {
        if(this.findSchoolClass(schoolClass)==null){
            em.persist(schoolClass);
        }else{
            throw new PuntenBoekException("Deze klas bestaat al");
        }
    }

    @Override
    public void removeSchoolClass(SchoolClass schoolClass) {
        for(Student s:schoolClass.getStudents()){
            s.setSchoolClass(null);
            this.editStudent(s);
        }
        for(Test t:schoolClass.getTests()){
            t.setSchoolClass(null);
            this.editTest(t);
        }
        schoolClass.getStudents().clear();
        schoolClass.getTests().clear();
        em.remove(em.merge(schoolClass));
    }

    @Override
    public Student findStudent(long id) {
        Student s=em.find(Student.class, id);
        return s;
    }
    
    @Override
    public SchoolClass findSchoolClass(long id) {
        return this.em.find(SchoolClass.class, id);
    }

    @Override
    public List<Student> getAllStudents() {
        return em.createQuery("select object(o) from Student as o").getResultList();
    }

    @Override
    public List<SchoolClass> getAllClasses() {
        return em.createQuery("select sc from SchoolClass as sc").getResultList();
    }

    @Override
    public ArrayList<Student> findStudent(String name) {
        List<Student> all=this.getAllStudents();
        ArrayList<Student> matched=new ArrayList<Student>();
        for(Object o:all){
            Student stud=(Student)o;
            if(stud.getFirstname().toLowerCase().contains(name.toLowerCase())){
                matched.add(stud);
            }else{
                if(stud.getLastname().toLowerCase().contains(name.toLowerCase())){
                    matched.add(stud);
                }
            }
        }
        return matched;
    }

    @Override
    public void addTestToSchoolClass(SchoolClass schoolclass, Test test) {
        SchoolClass sc=this.findSchoolClass(schoolclass.getId());
        Test t=this.findTest(test);
        try {
            sc.addTest(t);
            this.editSchoolClass(sc);
            t.setSchoolClass(sc);
            this.editTest(t);
        } catch (SchoolClassException ex) {
            throw new PuntenBoekException(ex.getMessage(), ex);
        }
    }

    @Override
    public void removeStudentFromClass(Student student, SchoolClass schoolclass) {
        try {
            SchoolClass sc=this.findSchoolClass(schoolclass.getId());
            Student st=this.findStudent(student.getId());
            st.setSchoolClass(null);
            this.editStudent(st);
            sc.removeStudent(st);
            this.editSchoolClass(sc);
            this.editStudent(st);
        } catch (SchoolClassException ex) {
            throw new PuntenBoekException(ex.getMessage(), ex);
        }
    }

    @Override
    public void removeTestFromSchoolClass(Test test, SchoolClass schoolClass) {
        try {
            SchoolClass sc=this.findSchoolClass(schoolClass.getId());
            Test t=this.findTest(test);
            sc.removeTest(t);
            this.editSchoolClass(sc);
            t.setSchoolClass(null);
            this.editTest(t);
        } catch (SchoolClassException ex) {
            throw new PuntenBoekException(ex.getMessage(), ex);
        }
    }

    @Override
    public Test findTest(long id) {
        return em.find(Test.class, id);
    }

    @Override
    public void editTest(Test test) {
        em.merge(test);
    }

    @Override
    public void addTest(Test test) {
        em.persist(test);
    }

    @Override
    public List<Test> getTestsFromSchoolClass(SchoolClass schoolclass) {
        SchoolClass sc=this.findSchoolClass(schoolclass.getId());
        return sc.getTests();
    }

    @Override
    public List<Student> getStudentsFromSchoolClass(SchoolClass schoolclass) {
        SchoolClass sc=this.findSchoolClass(schoolclass.getId());
        return sc.getStudents();
    }

    @Override
    public Student findStudent(Student student) {
        Query q=em.createQuery("SELECT st FROM Student as st WHERE st.firstname=:firstname AND st.lastname=:lastname");
        q.setParameter("firstname", student.getFirstname());
        q.setParameter("lastname", student.getLastname());
        List<Student> list=q.getResultList();
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Point> getPointsFromStudent(Student student) {
        Student s=this.findStudent(student);
        List<Point> list=s.getPoints();
        return list;
    }

    @Override
    public SchoolClass findSchoolClass(SchoolClass schoolClass) {
        Query q=em.createQuery("SELECT sc FROM SchoolClass sc WHERE sc.identification=:ident");
        q.setParameter("ident", schoolClass.getIdentification());
        List<SchoolClass> list=q.getResultList();
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
    
    @Override
    public Test findTest(Test test){
        Query q=em.createQuery("SELECT t FROM Test t WHERE t.title=:title");
        q.setParameter("title", test.getTitle());
        List<Test> list=q.getResultList();
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public void addPointToStudent(Student student, Point point) {
        try {
            Student s=this.findStudent(student);
            s.addPoint(point);
            this.editStudent(s);
        } catch (StudentException ex) {
            throw new PuntenBoekException(ex.getMessage(), ex);
        }
    }

    @Override
    public void addPoint(Point point) {
        em.persist(point);
    }

    @Override
    public void editPoint(Point p) {
        em.merge(p);
    }

    @Override
    public Point findPoint(long id) {
        return em.find(Point.class, id);
    }
}
