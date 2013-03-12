/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taak3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Jan
 */
public class Taak3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        final Button btn = new Button();
        final FlowPane root = new FlowPane();
        final ToggleGroup group = new ToggleGroup();
        final RadioButton rbplus = new RadioButton("add");
        rbplus.setSelected(true);
        final RadioButton rbmin = new RadioButton("subtract");
        final RadioButton rbmult = new RadioButton("multiply");
        final RadioButton rbdiv = new RadioButton("divide");
        rbplus.setToggleGroup(group);
        rbmin.setToggleGroup(group);
        rbmult.setToggleGroup(group);
        rbdiv.setToggleGroup(group);

        Label l1 = new Label("Number 1:");
        Label l2 = new Label("Number 2:");
        Label l3 = new Label("Result:");
        final TextField tf1 = new TextField();
        final TextField tf2 = new TextField();
        final TextField tf3 = new TextField();
        tf3.setEditable(false);

        btn.setText("Calculate");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double i1 = Double.MAX_VALUE, i2 = Double.MAX_VALUE;
                try {
                    i1 = Double.parseDouble(tf1.getText());
                    i2 = Double.parseDouble(tf2.getText());
                } catch (NumberFormatException e) {
                    if (i1 == Double.MAX_VALUE) {
                        i1 = 0;
                    }
                    if (i2 == Double.MAX_VALUE) {
                        if (rbdiv.isSelected()) {
                            i2 = 1;
                        } else {
                            i2 = 0;
                        }
                    }
                }
                double r = 0;
                if (rbplus.isSelected()) {
                    r = i1 + i2;
                } else if (rbmin.isSelected()) {
                    r = i1 - i2;
                } else if (rbmult.isSelected()) {
                    r = i1 * i2;
                } else if (rbdiv.isSelected()) {
                    r = i1 / i2;
                }
                tf3.setText(r + "");
            }
        });

        HBox hb1 = new HBox();
        hb1.getChildren().add(l1);
        hb1.getChildren().add(tf1);
        hb1.setSpacing(8);
        hb1.setPrefWidth(400);
        HBox hb2 = new HBox();
        hb2.getChildren().add(l2);
        hb2.getChildren().add(tf2);
        hb2.setSpacing(8);
        hb2.setPrefWidth(250);
        HBox hb3 = new HBox();
        hb3.getChildren().add(l3);
        hb3.getChildren().add(tf3);
        hb3.setSpacing(8);
        hb3.setPrefWidth(250);
        VBox vb1 = new VBox();
        vb1.getChildren().add(rbplus);
        vb1.getChildren().add(rbmin);
        vb1.getChildren().add(rbmult);
        vb1.getChildren().add(rbdiv);
        vb1.setSpacing(4);
        vb1.setPrefWidth(250);
        vb1.setLayoutX(30);
        vb1.setLayoutY(50);
        root.getChildren().add(hb1);
        root.getChildren().add(hb2);
        root.getChildren().add(vb1);
        root.getChildren().add(hb3);
        root.getChildren().add(btn);
        Button b=new Button("Exit");
        b.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
        root.getChildren().add(b);
        Scene scene = new Scene(root, 220, 170);
        primaryStage.setTitle("Simple Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
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
