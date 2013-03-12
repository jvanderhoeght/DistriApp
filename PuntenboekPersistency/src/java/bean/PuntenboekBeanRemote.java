/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import schoolEntities.Point;
import schoolEntities.SchoolClass;
import schoolEntities.Student;
import schoolEntities.Test;

/**
 *
 * @author Jan
 */
@Remote
public interface PuntenboekBeanRemote {
    
    void addStudent(Student student);
    
    void addSchoolClass(SchoolClass schoolClass);
    
    void editStudent(Student student);

    void editSchoolClass(SchoolClass schoolClass);
    
    void addStudentToClass(Student stud, SchoolClass schoolClass);
    
    Student findStudent(long id);
    
    ArrayList<Student> findStudent(String name);
    
    List<Student> getAllStudents();

    void removeStudent(Student student);

    void removeSchoolClass(SchoolClass schoolClass);
    
    void removeTest(Test test);
    
    void removePoint(Point p);

    List<SchoolClass> getAllClasses();

    void addTestToSchoolClass(SchoolClass schoolclass, Test test);

    SchoolClass findSchoolClass(long id);

    void removeStudentFromClass(Student student, SchoolClass schoolclass);

    void removeTestFromSchoolClass(Test test, SchoolClass schoolClass);

    Test findTest(long id);

    void editTest(Test test);

    void addTest(Test test);

    List<Test> getTestsFromSchoolClass(SchoolClass schoolclass);

    List<Student> getStudentsFromSchoolClass(SchoolClass schoolclass);

    Student findStudent(Student student);

    List<Point> getPointsFromStudent(Student student);

    SchoolClass findSchoolClass(SchoolClass schoolClass);
    
    Test findTest(Test test);

    void addPointToStudent(Student student, Point point);

    void addPoint(Point point);

    void editPoint(Point p);

    Point findPoint(long id);

}
