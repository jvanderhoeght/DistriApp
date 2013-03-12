/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Jan
 */
public class Navigator {
    static Scene scene;
    static Parent addStudentPanel;//X
    static Parent addClassPanel;//X
    static Parent addTestPanel;//X
    static Parent showStudentsPanel;//X
    static Parent showClassesPanel;//X
    static Parent showPointsPanel;//X
    static Parent showTestsPanel;//X
    static Parent addPointPanel;//X
    static Parent editStudentPanel;//X
    static Parent editClassPanel;//X
    static Parent editPointPanel;//X
    static Parent editTestPanel;
    static Parent welcomePanel;//X
    
    
    
    static private Navigator nav=new Navigator();

    
    private Navigator()    { init();    }

    private void init(){
        try {
            System.out.println("++++++++++ Loading FXML pages ++++++++++");
            welcomePanel=FXMLLoader.load(getClass().getResource("FXML_welcome.fxml"));
            System.out.println("\tFXML_welcome.fxml loaded");
            showStudentsPanel=FXMLLoader.load(getClass().getResource("FXML_showStudentsPane.fxml"));
            System.out.println("\tFXML_showStudentsPane.fxml loaded");
            showClassesPanel=FXMLLoader.load(getClass().getResource("FXML_showClassesPane.fxml"));
            System.out.println("\tFXML_showClassesPane.fxml loaded");
            showPointsPanel=FXMLLoader.load(getClass().getResource("FXML_showPointsPane.fxml"));
            System.out.println("\tFXML_showPointsPane.fxml loaded");
            addStudentPanel=FXMLLoader.load(getClass().getResource("FXML_addStudentPane.fxml"));
            System.out.println("\tFXML_addStudentPane.fxml loaded");
            addClassPanel=FXMLLoader.load(getClass().getResource("FXML_addSchoolClassPane.fxml"));
            System.out.println("\tFXML_addSchoolClassPane.fxml loaded");
            addTestPanel=FXMLLoader.load(getClass().getResource("FXML_addTestPane.fxml"));
            System.out.println("\tFXML_addTestPane.fxml loaded");
            addPointPanel=FXMLLoader.load(getClass().getResource("FXML_addPointPane.fxml"));
            System.out.println("\tFXML_addPointPane.fxml loaded");
            editStudentPanel=FXMLLoader.load(getClass().getResource("FXML_editStudentPane.fxml"));
            System.out.println("\tFXML_editStudentPane.fxml loaded");
            showTestsPanel=FXMLLoader.load(getClass().getResource("FXML_showTestsPane.fxml"));
            System.out.println("\tFXML_showTestsPane.fxml loaded");
            editClassPanel=FXMLLoader.load(getClass().getResource("FXML_editSchoolClassPane.fxml"));
            System.out.println("\tFXML_editSchoolClassPane.fxml loaded");
            editPointPanel=FXMLLoader.load(getClass().getResource("FXML_editPointPane.fxml"));
            System.out.println("\tFXML_editPointPane.fxml loaded");
            editTestPanel=FXMLLoader.load(getClass().getResource("FXML_editTestPane.fxml"));
            System.out.println("\tFXML_editTestPane.fxml loaded");
            System.out.println("++++++++++ All FXML pages loaded ++++++++++");
        } catch (IOException ex) {
            System.out.append(ex.getMessage());
        }
        scene=new Scene(welcomePanel);
    }
    
    public static void showAddStudentPanel(){
        scene.setRoot(addStudentPanel);
    }
    
    public static void showStudentsPanel(){
        scene.setRoot(showStudentsPanel);
    }
    
    public static void showClassesPanel(){
        scene.setRoot(showClassesPanel);
    }
    
    public static void showPointsPanel(){
        scene.setRoot(showPointsPanel);
    }
    
    public static void showAddClassPanel() {
        scene.setRoot(addClassPanel);
    }
    
    public static void showAddTestPanel(){
        scene.setRoot(addTestPanel);
    }
    
    public static void showAddPointPanel(){
        scene.setRoot(addPointPanel);
    }
    
    public static void showEditStudentPanel(){
        scene.setRoot(editStudentPanel);
    }
    
    public static void showEditClassPanel(){
        scene.setRoot(editClassPanel);
    }
    
    public static void showEditTestPanel(){
        scene.setRoot(editTestPanel);
    }
    
    public static void showEditPointPanel(){
        scene.setRoot(editPointPanel);
    }
    
    public static void showTestsPanel() {
        scene.setRoot(showTestsPanel);
    }
}
