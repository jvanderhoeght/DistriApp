/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puntenboekpersistencyapp;

import bean.PuntenboekBean;
import bean.PuntenboekBeanRemote;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import schoolEntities.SchoolClass;
import schoolEntities.Student;
import schoolEntities.Test;
import schoolEntities.TestException;

/**
 *
 * @author Jan
 */
public class PuntenboekPersistencyApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Context c;
        try{
            
            c = new InitialContext();
                    //   java:global/PuntenboekPersistency/PuntenboekBean!bean.PuntenboekBean
            PuntenboekBeanRemote remote =(PuntenboekBeanRemote) c.lookup("java:global/PuntenboekPersistency/PuntenboekBean!bean.PuntenboekBeanRemote");
            System.out.println(remote.toString());
            System.out.println("Toevoegen studenten en klas");
            Student s1=new Student("Jan", "Vanderhoeght");
            Student s2=new Student("Kevin", "Van Houtte");
            Student s3=new Student("Birgit", "Guldentops");
            remote.addStudent(s1);
            remote.addStudent(s2);
            remote.addStudent(s3);
            SchoolClass sc=new SchoolClass("3TXR/3-");
            SchoolClass sc2=new SchoolClass("blabla");
            remote.addSchoolClass(sc);
            remote.addSchoolClass(sc2);
            List<Student> studs=remote.getAllStudents();
            System.out.println("Alle studenten:");
            for(Student s : studs){
                System.out.println("\t"+s.toString());
            }
            s3=remote.findStudent(s3);
            sc=remote.findSchoolClass(sc);
            sc2=remote.findSchoolClass(sc2);
            System.out.println(sc2);
            remote.removeStudentFromClass(s3, sc);
            remote.addStudentToClass(s3, sc2);
            
            System.out.println(remote.findStudent(s3).getSchoolClass());
            System.out.println(sc2.getStudents());
            System.out.println(remote.getStudentsFromSchoolClass(sc));
//            System.out.println(s3);
            /*System.out.println("Verwijderen");
            Student s=remote.findStudent(2);
            remote.removeStudent(s);
            /*System.out.println("Alle studenten na aanpassing naam:");
            for(Student s : studs){
                s.setFirstname(s.getFirstname()+"#");
                s.setNumber(5);
                remote.editStudent(s);
            }*/
            /*studs=remote.getAllStudents();
            System.out.println("Alle studenten:");
            for(Student st : studs){
                System.out.println("\t"+st.toString());
            }
            /*System.out.println("\nStudenten die matchen met 'van':");
            ArrayList<Student> match=remote.findStudent("van");
            for(Student st : match){
                System.out.println("\t"+st.toString());
            }
            
            /*System.out.println("\nKlasnaam aanpassen:");
            sc=remote.findClass(4);
            sc.setIdentification(sc.getIdentification()+"#");
            remote.editSchoolClass(sc);
            sc=remote.findClass(4);
            System.out.println(sc.toString());*/
            
            /*System.out.println("\nStudent toevoegen aan klas");
            s1=remote.findStudent(3);
            SchoolClass sc1=remote.findSchoolClass(4);
            remote.addStudentToClass(s1, sc1);
            
            studs=remote.getAllStudents();
            System.out.println("Alle studenten:");
            for(Student st : studs){
                System.out.println("\t"+st.toString()+" "+st.getSchoolClass());
            }
            SchoolClass sc1bla=remote.findSchoolClass(4);
            remote.removeSchoolClass(sc1bla);
            System.out.println("Klas verwijderen");
            List<SchoolClass> classes=remote.getAllClasses();
            for(SchoolClass c1 : classes){
                System.out.println(c1.toString());
            }
            s1=remote.findStudent(3);
            System.out.println(s1.getSchoolClass());*/
//            Test t=new Test("Test 1", null, 50);
//            Test t2=new Test("Test 2", null, 70);
//            sc=new SchoolClass("sc1");
//            remote.addSchoolClass(sc);
//            sc=remote.findSchoolClass(5);
//            remote.addTest(t);
//            remote.addTest(t2);
//            t=remote.findTest(6);
//            t2=remote.findTest(7);
//            t.setTitle("myNewTitle");
//            t.setMaxpoints(20);
//            remote.editTest(t);
//            s1=remote.findStudent(1);
//            s2=remote.findStudent(2);
//            s3=remote.findStudent(3);
//            remote.addStudentToClass(s1, sc);
//            remote.addStudentToClass(s2, sc);
//            remote.addStudentToClass(s3, sc);
//            remote.addTestToSchoolClass(sc, t);
//            remote.addTestToSchoolClass(sc, t2);
//            for(Test te:remote.getTestsFromSchoolClass(sc)){
//                System.out.println(te.getTitle());
//            }
//            List<Student> l=remote.getStudentsFromSchoolClass(sc);
//            System.out.println(l.size());
//            for(Student st:remote.getStudentsFromSchoolClass(sc)){
//                System.out.println(st);
//            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void testTests(Context c, PuntenboekBeanRemote remote){
        try {
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public static void testPoints(Context c, PuntenboekBeanRemote remote){
        
    }
}
