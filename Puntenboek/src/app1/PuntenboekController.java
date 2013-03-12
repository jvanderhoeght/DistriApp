package app1;

import bean.PuntenBoekException;
import bean.PuntenboekBeanRemote;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import schoolEntities.Point;
import schoolEntities.PointException;
import schoolEntities.SchoolClass;
import schoolEntities.SchoolClassException;
import schoolEntities.Student;
import schoolEntities.StudentException;
import schoolEntities.Test;
import schoolEntities.TestException;

/**
 *
 * @author Jan
 */
public class PuntenboekController implements Initializable {

    // <editor-fold defaultstate="collapsed" desc="variables">
    @FXML
    private ComboBox classList;
    @FXML
    private ComboBox studentList;
    @FXML
    private ComboBox testList;
    @FXML
    private ComboBox classListEditStudent;
    @FXML
    private ComboBox pointsList;
    @FXML
    private Button studentButton;
    @FXML
    private Button classButton;
    @FXML
    private Button pointButton;
    @FXML
    private Button saveStudentButton;
    @FXML
    private Button editStudentButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button testButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField maxpointsField;
    @FXML
    private TextField classidentfield;
    @FXML
    private TextField titleField;
    @FXML
    private TextField pointsField;
    @FXML
    private Label errorLabel1;
    @FXML
    private Label oklabel1;
    @FXML
    private TableView classView;
    @FXML
    private TableView studentView;
    @FXML
    private TableView pointView;
    @FXML
    private TableColumn classcol;
    @FXML
    private TableColumn lastnamecol;
    @FXML
    private TableColumn firstnamecol;
    @FXML
    private TableColumn testcol;
    @FXML
    private TableColumn maxcol;
    @FXML
    private TableColumn pointscol;
    private Context c;
    private PuntenboekBeanRemote remote;
    private Student editStudent;
    private SchoolClass editSchoolClass;
    private Test editTest;
    private Point editPoint;
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="navigation methods">
    @FXML
    private void toAddStudentPanel(ActionEvent event){
        Navigator.showAddStudentPanel();
    }
    
    @FXML
    private void toShowStudentsPanel(ActionEvent event){
        Navigator.showStudentsPanel();
    }
    
    @FXML
    private void toShowClassesPanel(ActionEvent event){
        Navigator.showClassesPanel();
    }
    
    @FXML
    private void toShowPointsPanel(ActionEvent event){
        Navigator.showPointsPanel();
    }
    
    @FXML
    private void toShowTestsPanel(ActionEvent event){
        Navigator.showTestsPanel();
    }
    
    @FXML
    private void toAddClassPanel(ActionEvent event){
        Navigator.showAddClassPanel();
    }
    
    @FXML
    private void toEditStudentPanel(ActionEvent event){
        Navigator.showEditStudentPanel();
    }
    
    @FXML
    private void toEditClassPanel(ActionEvent event){
        Navigator.showEditClassPanel();
    }
    
    @FXML
    private void toEditTestPanel(ActionEvent event){
        Navigator.showEditTestPanel();
    }
    
    @FXML
    private void toEditPointPanel(ActionEvent event){
        Navigator.showEditPointPanel();
    }
    
    @FXML
    private void toAddTestPanel(ActionEvent event){
        Navigator.showAddTestPanel();
    }
    
    @FXML
    private void toAddPointPanel(ActionEvent event){
        Navigator.showAddPointPanel();
    }
    // </editor-fold>

    @FXML
    private void showClasses(ActionEvent event) {
        ObservableList<SchoolClass> classes = FXCollections.observableArrayList();
        List<SchoolClass> list=remote.getAllClasses();
        for (SchoolClass c : list) {
            classes.add(c);
        }
        classList.setItems(classes);
        if (classes.size() > 0) {
            classList.setValue(classes.get(0));
        }
        if(classListEditStudent!=null){
            classListEditStudent.setItems(classes);
            if (classes.size() > 0) {
                classListEditStudent.setValue(classes.get(0));
            }
            classListEditStudent.setDisable(false);
        }
        classList.setDisable(false);
        if(studentButton!=null){
            studentButton.setDisable(false);
        }
    }
    
    @FXML
    private void getClasses(ActionEvent event){
        ObservableList<SchoolClass> classes = FXCollections.observableArrayList();
        for (SchoolClass ac : remote.getAllClasses()) {
            classes.add(ac);
        }
        classcol.setCellValueFactory(new PropertyValueFactory<SchoolClass, String>("identification"));
        classView.setItems(null);
        classView.setItems(classes);
        classView.setVisible(true);
    }

    @FXML
    private void getStudents(ActionEvent event) {
        SchoolClass sclass = (SchoolClass) classList.getValue();
        List<Student> list=remote.getStudentsFromSchoolClass(sclass);
        ObservableList<Student> students = FXCollections.observableArrayList();
        for (Student s : list) {
            students.add(s);
        }
        if(studentList!=null){
            studentList.setItems(students);
            if(students.size()>0){
                studentList.setValue(students.get(0));
            }
            studentList.setDisable(false);
            if(pointButton!=null){
                pointButton.setVisible(true);
            }
            if(editStudentButton!=null){
                editStudentButton.setDisable(false);
            }
            if(testButton!=null){
                testButton.setDisable(false);
            }
        }else{
            lastnamecol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastname"));
            firstnamecol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstname"));
            studentView.setItems(null);
            studentView.setItems(students);
            studentView.setVisible(true);
        }
        
    }
    
    @FXML
    private void getPoints(ActionEvent event){
        pointView.getItems().removeAll(pointView.getItems());
        ObservableList<Point> points = FXCollections.observableArrayList();
        Student s=(Student) studentList.getValue();
        List<Point> list=remote.getPointsFromStudent(s);
        for(Point p: list){
            points.add(p);
        }
        testcol.setCellValueFactory(new PropertyValueFactory<Point, String>("test"));
        pointscol.setCellValueFactory(new PropertyValueFactory<Point, String>("points"));
        pointView.setItems(points);
        ((TableColumn)pointView.getColumns().get(1)).setVisible(false);
        ((TableColumn)pointView.getColumns().get(1)).setVisible(true);
        pointView.setVisible(true);
    }
    
    @FXML
    private void showTests(ActionEvent event){
        ObservableList<Test> tests = FXCollections.observableArrayList();
        SchoolClass s=(SchoolClass) classList.getValue();
        List<Test> list=remote.getTestsFromSchoolClass(s);
        for(Test p: list){
            tests.add(p);
        }
        testcol.setCellValueFactory(new PropertyValueFactory<Test, String>("title"));
        pointscol.setCellValueFactory(new PropertyValueFactory<Test, String>("maxpoints"));
        pointView.setItems(null);
        pointView.setItems(tests);
        pointView.setVisible(true);
    }
    
    @FXML
    private void getTests(ActionEvent event){
        SchoolClass sclass = (SchoolClass) classList.getValue();
        List<Test> list=remote.getTestsFromSchoolClass(sclass);
        ObservableList<Test> tests = FXCollections.observableArrayList();
        for(Test t: list){
            tests.add(t);
        }
        testList.setItems(tests);
        if(tests.size()>0){
            testList.setValue(tests.get(0));
        }
        testList.setDisable(false);
        if(editStudentButton!=null && tests.size()>0){
            editStudentButton.setDisable(false);
        }
    }

    @FXML
    private void addStudent(ActionEvent event) {
        try {
            String fname = firstNameField.getText().trim();
            String lname = lastNameField.getText().trim();
            Student s = new Student(fname, lname);
            remote.addStudent(s);
            s=remote.findStudent(s);
            SchoolClass sc = (SchoolClass) classList.getValue();
            remote.addStudentToClass(s, sc);
            oklabel1.setVisible(true);
            errorLabel1.setVisible(false);
            firstNameField.setText("");
            lastNameField.setText("");
            classList.setDisable(true);
        } catch (StudentException ex) {
            oklabel1.setVisible(false);
            errorLabel1.setText("Error: "+ex.getMessage());
            errorLabel1.setVisible(true);
        } catch (NumberFormatException ex) {
            oklabel1.setVisible(false);
            errorLabel1.setText("Error: "+ex.getMessage());
            errorLabel1.setVisible(true);
        }
    }
    
    @FXML
    private void addSchoolClass(ActionEvent event){
        try {
            SchoolClass sc=new SchoolClass(classidentfield.getText().trim());
            SchoolClass scFromDB=remote.findSchoolClass(sc);
            if(scFromDB==null){
                remote.addSchoolClass(sc);
                errorLabel1.setVisible(false);
                oklabel1.setVisible(true);
                classidentfield.setText("");
            }else{
                errorLabel1.setText("Error: Deze klas bestaat al");
                errorLabel1.setVisible(true);
            }
        } catch (SchoolClassException ex) {
            oklabel1.setVisible(false);
            errorLabel1.setText("Error: "+ex.getMessage());
            errorLabel1.setVisible(true);
        }
    }
    
    @FXML
    private void addTest(ActionEvent event){
        try{
            String title=titleField.getText().trim();
            SchoolClass sc=(SchoolClass) classList.getValue();
            String points=maxpointsField.getText();
            int max=Integer.parseInt(points);
            if(max>0){
                Test t=new Test(title, sc, max);
                remote.addTest(t);
                t=remote.findTest(t);
                remote.addTestToSchoolClass(sc, t);
                errorLabel1.setVisible(false);
                oklabel1.setVisible(true);
                titleField.setText("");
                maxpointsField.setText("");
            }else{
                oklabel1.setVisible(false);
                errorLabel1.setText("Error: Geef een natuurlijk geheel getal in");
                errorLabel1.setVisible(true);
            }
        } catch (TestException ex) {
            oklabel1.setVisible(false);
            errorLabel1.setText("Error: "+ex.getMessage());
            errorLabel1.setVisible(true);
        }catch(NumberFormatException ex){
            oklabel1.setVisible(false);
            errorLabel1.setText("Error: Geef een natuurlijk geheel getal in");
            errorLabel1.setVisible(true);
        }
    }
    
    @FXML
    private void addPoint(ActionEvent event){
        Test t=(Test) testList.getValue();
        String p=pointsField.getText();
        Student s=(Student) studentList.getValue();
        try{
            int points=Integer.parseInt(p);
            Point point=new Point(t, points);
            remote.addPoint(point);
            remote.addPointToStudent(s, point);
            pointsField.setText("");
            errorLabel1.setVisible(false);
            oklabel1.setVisible(true);
            classList.setDisable(true);
            studentList.setDisable(true);
            testList.setDisable(true);
            studentButton.setDisable(true);
            testButton.setDisable(true);
        } catch (PointException ex) {
            oklabel1.setVisible(false);
            errorLabel1.setText("Error: "+ex.getMessage());
            errorLabel1.setVisible(true);
        }catch(NumberFormatException ex){
            oklabel1.setVisible(false);
            errorLabel1.setText("Error: Geef een getal in van 0 t.e.m. "+t.getMaxpoints());
            errorLabel1.setVisible(true);
        }catch(PuntenBoekException ex){
            oklabel1.setVisible(false);
            errorLabel1.setText("Error: "+ex.getMessage());
            errorLabel1.setVisible(true);
        }
    }

    @FXML
    private void setEditStudent(ActionEvent event){
        classButton.setDisable(true);
        classList.setDisable(true);
        studentList.setDisable(true);
        studentButton.setDisable(true);
        editStudentButton.setDisable(true);
        firstNameField.setDisable(false);
        lastNameField.setDisable(false);
        classListEditStudent.setDisable(false);
        saveStudentButton.setDisable(false);
        editStudent=(Student) studentList.getValue();
        firstNameField.setText(editStudent.getFirstname());
        lastNameField.setText(editStudent.getLastname());
        deleteButton.setDisable(false);
        oklabel1.setVisible(false);
        errorLabel1.setVisible(false);
    }
    
    @FXML
    private void setEditSchoolClass(ActionEvent event){
        classButton.setDisable(true);
        classList.setDisable(true);
        studentButton.setDisable(true);
        deleteButton.setDisable(false);
        saveStudentButton.setDisable(false);
        editSchoolClass=(SchoolClass)classList.getValue();
        firstNameField.setText(editSchoolClass.getIdentification());
        oklabel1.setVisible(false);
        errorLabel1.setVisible(false);
    }
    
    @FXML
    private void setEditTest(ActionEvent event){
        if(testList.getValue()!=null){
            classButton.setDisable(true);
            classList.setDisable(true);
            testList.setDisable(true);
            studentButton.setDisable(true);
            editStudentButton.setDisable(true);
            deleteButton.setDisable(false);
            saveStudentButton.setDisable(false);
            editTest=(Test) testList.getValue();
            firstNameField.setDisable(false);
            firstNameField.setText(editTest.getTitle());
            lastNameField.setDisable(false);
            lastNameField.setText(editTest.getMaxpoints()+"");
            oklabel1.setVisible(false);
            errorLabel1.setVisible(false);
        }
    }
    
    @FXML
    private void setEditPoint(ActionEvent event){
        Test t=(Test)testList.getValue();
        Student s=(Student)studentList.getValue();
        Point p=null;
        try{
            p=s.getPoint(t);
        }catch(StudentException ex){
        }
        if(p!=null){
            firstNameField.setText("in if");
            try {
                classButton.setDisable(true);
                classList.setDisable(true);
                studentList.setDisable(true);
                studentButton.setDisable(true);
                editStudentButton.setDisable(true);
                deleteButton.setDisable(false);
                firstNameField.setText(t.getTitle());
                lastNameField.setDisable(false);
                saveStudentButton.setDisable(false);
                editPoint=s.getPoint(t);
                editStudent=s;
                firstNameField.setText(editPoint.getTest().toString());
                lastNameField.setText(editPoint.getPoints()+"");
                errorLabel1.setVisible(false);
                oklabel1.setVisible(false);
            } catch (StudentException ex) {
                
            }
        }
    }
    
    @FXML
    private void saveStudent(ActionEvent event){
        classButton.setDisable(false);
        if(!editStudent.getSchoolClass().equals((SchoolClass)classListEditStudent.getValue())){
            try {
                editStudent.getSchoolClass().removeStudent(editStudent);
                remote.editSchoolClass(editStudent.getSchoolClass());
                SchoolClass sc=(SchoolClass)classListEditStudent.getValue();
                Student st=remote.findStudent(editStudent.getId());
                remote.addStudentToClass(st, sc);
            }catch (SchoolClassException ex) {
                oklabel1.setVisible(false);
                errorLabel1.setText("Error: "+ex.getMessage());
                errorLabel1.setVisible(true);
            }        
        }
        try{
            editStudent.setFirstname(firstNameField.getText());
            editStudent.setLastname(lastNameField.getText());
            remote.editStudent(editStudent);
            firstNameField.setText("");
            lastNameField.setText("");
            oklabel1.setVisible(true);
            errorLabel1.setVisible(false);

            classButton.setDisable(false);
            classListEditStudent.setDisable(true);
            saveStudentButton.setDisable(true);
            deleteButton.setDisable(true);
        } catch (StudentException ex) {
            oklabel1.setVisible(false);
            errorLabel1.setText("Error: "+ex.getMessage());
            errorLabel1.setVisible(true);
        }
    }
    
    @FXML
    private void deleteStudent(ActionEvent event){
        remote.removeStudent(editStudent);
        errorLabel1.setText("De student werd verwijderd");
        errorLabel1.setVisible(true);
        classButton.setDisable(false);
        classListEditStudent.setDisable(true);
        saveStudentButton.setDisable(true);
        deleteButton.setDisable(true);
        oklabel1.setVisible(false);
            firstNameField.setText("");
            lastNameField.setText("");
    }
    
    @FXML
    private void saveSchoolClass(ActionEvent event){
        try {
            editSchoolClass.setIdentification(firstNameField.getText());
            remote.editSchoolClass(editSchoolClass);
            classButton.setDisable(false);
            saveStudentButton.setDisable(true);
            deleteButton.setDisable(true);
            oklabel1.setVisible(true);
            firstNameField.setText("");
            oklabel1.setVisible(true);
            errorLabel1.setVisible(false);
        } catch (SchoolClassException ex) {
            oklabel1.setVisible(false);
            errorLabel1.setText("Error: "+ex.getMessage());
            errorLabel1.setVisible(true);
        }
    }
    
    @FXML
    private void deleteSchoolClass(ActionEvent event){
        remote.removeSchoolClass(editSchoolClass);
        firstNameField.setText("");
        classButton.setDisable(false);
        saveStudentButton.setDisable(true);
        deleteButton.setDisable(true);
        oklabel1.setVisible(false);
        errorLabel1.setText("De klas werd verwijderd");
        errorLabel1.setVisible(true);
    }
    
    @FXML
    private void saveTest(ActionEvent event){
        try{
            int i=Integer.parseInt(lastNameField.getText());
            editTest.setMaxpoints(i);
            editTest.setTitle(firstNameField.getText());
            remote.editTest(editTest);
            
            classButton.setDisable(false);
            deleteButton.setDisable(true);
            saveStudentButton.setDisable(true);
            firstNameField.setDisable(true);
            firstNameField.setText("");
            lastNameField.setDisable(true);
            lastNameField.setText("");
            errorLabel1.setVisible(false);
            oklabel1.setVisible(true);
        }catch(NumberFormatException ex){
            errorLabel1.setText("Error: Geef een natturlijk geheel getal in");
            errorLabel1.setVisible(true);
        }catch(TestException ex){
            errorLabel1.setText("Error: Geef een natturlijk geheel getal in");
            errorLabel1.setVisible(true);
        }
    }
    
    @FXML
    private void deleteTest(ActionEvent event){
        remote.removeTest(editTest);
        classButton.setDisable(false);
        deleteButton.setDisable(true);
        saveStudentButton.setDisable(true);
        firstNameField.setDisable(true);
        firstNameField.setText("");
        lastNameField.setDisable(true);
        lastNameField.setText("");
        oklabel1.setVisible(false);
        errorLabel1.setText("De test werd verwijderd");
        errorLabel1.setVisible(true);
    }
    
    @FXML
    private void savePoint(ActionEvent event){
        try{
            int punt=Integer.parseInt(lastNameField.getText());
            editPoint.setPoints(punt);
            remote.editPoint(editPoint);
            oklabel1.setVisible(true);
            errorLabel1.setVisible(false);
            
            classButton.setDisable(false);
            deleteButton.setDisable(true);
            testButton.setDisable(true);
            testList.setDisable(true);
            firstNameField.setText("");
            lastNameField.setText("");
            lastNameField.setDisable(true);
            saveStudentButton.setDisable(true);
        }catch(NumberFormatException ex){
            oklabel1.setVisible(false);
            errorLabel1.setText("Error: Geef een getal in");
            errorLabel1.setVisible(true);
        }catch(Exception ex){
            oklabel1.setVisible(false);
            errorLabel1.setText("Error: "+ex.getMessage());
            errorLabel1.setVisible(true);
        }
    }
    
    @FXML
    private void deletePoint(ActionEvent event){
        editStudent.getPoints().remove(editPoint);
        remote.editStudent(editStudent);
        remote.removePoint(editPoint);
        oklabel1.setVisible(false);
        classButton.setDisable(false);
        deleteButton.setDisable(true);
        testButton.setDisable(true);
        testList.setDisable(true);
        firstNameField.setText("");
        lastNameField.setText("");
        lastNameField.setDisable(true);
        saveStudentButton.setDisable(true);
        errorLabel1.setText("Het punt werd verwijderd");
        errorLabel1.setVisible(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            c=new InitialContext();
            remote=(PuntenboekBeanRemote) c.lookup("java:global/PuntenboekPersistency/PuntenboekBean!bean.PuntenboekBeanRemote");
        } catch (NamingException ex) {
            Logger.getLogger(PuntenboekController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
