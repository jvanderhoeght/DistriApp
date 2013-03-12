/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app1;

import bean.PuntenboekBeanRemote;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import schoolEntities.Point;
import schoolEntities.SchoolClass;
import schoolEntities.SchoolClassException;
import schoolEntities.Student;
import schoolEntities.StudentException;
import schoolEntities.Test;

/**
 *
 * @author Jan
 */
public class Launch extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        try {
            SchoolClass class1 = new SchoolClass("3FOTO");
            SchoolClass class2 = new SchoolClass("4BE");
            Student s1 = new Student("Kevin", "Van Houtte");
            Student s2 = new Student("Jan", "Vanderhoeght");
            Student s3 = new Student("Birgit", "Guldentops");
            Student s4 = new Student("Laurens", "Marcelis");
            Student s5 = new Student("Olivia", "Van Slagmolen");
            Student s6 = new Student("Gilbert", "Becaud");
            Student s7 = new Student("Jens", "Adriaensen");
            Student s8 = new Student("Gabriël", "Van Damme");
            System.out.println("Start connectie");
            Context c=new InitialContext();
            PuntenboekBeanRemote remote=(PuntenboekBeanRemote) c.lookup("java:global/PuntenboekPersistency/PuntenboekBean!bean.PuntenboekBeanRemote");
            System.out.println("Connectie aangemaakt");
            if(remote.getAllStudents().isEmpty()){
                remote.addStudent(s1);//1
                remote.addStudent(s2);//2
                remote.addStudent(s3);//3
                remote.addStudent(s4);//4
                remote.addStudent(s5);//5
                remote.addStudent(s6);//6
                remote.addStudent(s7);//7
                remote.addStudent(s8);//8
                System.out.println("Studenten toegevoegd");
                remote.addSchoolClass(class1);//9
                remote.addSchoolClass(class2);//10
                System.out.println("Klassen toegevoegd");
                s1=remote.findStudent(1);
                s2=remote.findStudent(2);
                s3=remote.findStudent(3);
                s4=remote.findStudent(4);
                s5=remote.findStudent(5);
                s6=remote.findStudent(6);
                s7=remote.findStudent(7);
                s8=remote.findStudent(8);
                class1=remote.findSchoolClass(9);
                class2=remote.findSchoolClass(10);
                remote.addStudentToClass(s1, class1);
                remote.addStudentToClass(s2, class1);
                remote.addStudentToClass(s3, class1);
                remote.addStudentToClass(s4, class1);
                remote.addStudentToClass(s5, class2);
                remote.addStudentToClass(s6, class2);
                remote.addStudentToClass(s7, class2);
                remote.addStudentToClass(s8, class2);
                System.out.println("Studenten toegevoegd aan klas");
                Test t1 = new Test("Irregular Verbs", class1, 10);
                Test t2 = new Test("Vocabulary Unit 2", class1, 20);
                Test t3 = new Test("Listening Test: Fawlty Towers", class1, 15);
                Test t4 = new Test("Revision of the Tenses", class1, 20);
                Test t5 = new Test("Massadichtheid", class1, 20);
                Test t6 = new Test("Bouw van het Oor", class1, 20);
                Test t7 = new Test("Inwendige bouw van het menselijk lichaam", class1, 20);
                Test t8 = new Test("Bouw en Werking van het Oog", class1, 20);
                remote.addTest(t1);//11
                remote.addTest(t2);//12
                remote.addTest(t3);//13
                remote.addTest(t4);//14
                remote.addTest(t5);//15
                remote.addTest(t6);//16
                remote.addTest(t7);//17
                remote.addTest(t8);//18
                t1=remote.findTest(11);
                t2=remote.findTest(12);
                t3=remote.findTest(13);
                t4=remote.findTest(14);
                t5=remote.findTest(15);
                t6=remote.findTest(16);
                t7=remote.findTest(17);
                t8=remote.findTest(18);
                remote.addTestToSchoolClass(class1, t1);
                remote.addTestToSchoolClass(class1, t2);
                remote.addTestToSchoolClass(class1, t3);
                remote.addTestToSchoolClass(class1, t4);
                remote.addTestToSchoolClass(class1, t5);
                remote.addTestToSchoolClass(class1, t6);
                remote.addTestToSchoolClass(class1, t7);
                remote.addTestToSchoolClass(class1, t8);
                Point p=new Point(t1, 8);
                remote.addPoint(p);
                p=remote.findPoint(19);
                remote.addPointToStudent(s1, p);
            }
            System.out.println("Gestart");
        } catch (NamingException ex) {
            System.out.println(ex.getMessage());
        } catch (SchoolClassException ex) {
            System.out.println(ex.getMessage());
        } catch (StudentException ex) {
            System.out.println(ex.getMessage());
        }
        stage.setScene(Navigator.scene);
        stage.setTitle("Puntenboek 1.0");
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
