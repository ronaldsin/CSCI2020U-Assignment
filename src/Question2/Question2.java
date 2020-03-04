package Question2;

// Ronald Sin 100700891

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class Question2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // grid pane to hold all elements
        GridPane main = new GridPane();
        main.setPadding(new Insets(11, 300, 75, 11 ));
        main.setHgap(10);
        main.setVgap(5);

        // setting up all the labels and text fields
        Label invLabel = new Label("Investment Amount");
        main.setConstraints(invLabel, 0, 0);
        TextField invTextField = new TextField ();
        invTextField.setPromptText("Please enter your investment amount:");
        main.setConstraints(invTextField, 1, 0);

        Label yearsLabel = new Label("Years");
        main.setConstraints(yearsLabel, 0, 1);
        TextField yearsTextField = new TextField ();
        yearsTextField.setPromptText("Please enter number of years:");
        main.setConstraints(yearsTextField, 1, 1);

        Label annualLabel = new Label("Annual Interest Rate:");
        main.setConstraints(annualLabel, 0, 2);
        TextField annualTextField = new TextField ();
        annualTextField.setPromptText("Please enter Annual Interest Rate:");
        main.setConstraints(annualTextField, 1, 2);

        Label futureLabel = new Label("Future value:");
        main.setConstraints(futureLabel, 0, 3);
        TextField futureTextField = new TextField ();
        futureTextField.setDisable(true);
        main.setConstraints(futureTextField, 1, 3);

        // setting up the button that will calculate based on input
        Button btReg = new Button("Calculate");
        main.setConstraints(btReg, 1, 5);
        btReg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DecimalFormat df = new DecimalFormat("0.00"); // set format to 2 decimals

                // get value of user input
                double invAmt = Double.valueOf(invTextField.getText());
                double years =  Double.valueOf(yearsTextField.getText());
                double annual =  Double.valueOf(annualTextField.getText());

                double future = (invAmt * Math.pow((1 + (annual/100)/12), years * 12)); // future value formula

                futureTextField.setPromptText(df.format(future)); // set textfield text to future value
            }
        });

        // add elements to grid pane
        main.getChildren().addAll(invLabel, invTextField);
        main.getChildren().addAll(yearsLabel, yearsTextField);
        main.getChildren().addAll(annualLabel, annualTextField);
        main.getChildren().addAll(futureLabel, futureTextField);
        main.getChildren().add(btReg);

        Scene scene = new Scene(main);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Question 2");
        primaryStage.show();
    }
}
