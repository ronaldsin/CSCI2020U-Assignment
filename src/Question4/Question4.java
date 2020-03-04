package Question4;

// Ronald Sin 100700891

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Question4 extends Application {

    public static void main(String[] args){ launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // vbox holds everything
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        // hbox that holds the letters
        HBox letters = new HBox();
        letters.setSpacing(10);

        // hbox holds the bottom input
        HBox bot = new HBox();
        bot.setAlignment(Pos.CENTER);

        // canvas holds the bars
        Canvas canvas = new Canvas(500, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // displays each letter of the alphabet
        for(char i = 'a'; i <= 'z'; i++){
            letters.getChildren().add(new Label(String.valueOf(i)));
        }

        // input text field for the file address
        TextField filenameText = new TextField();

        // button to enter the info
        Button enter = new Button("Enter");
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int[] counter = new int['z' - 'a' + 1]; // an array to count the occurrence of each letter

                String filename = filenameText.getText(); // the file to be read

                File file = new File(filename);

                try {
                    Scanner in = new Scanner(file);
                    in.useDelimiter(""); // set delimiter to "" so it goes through each letter

                    while(in.hasNext()){
                        char ch = in.next().charAt(0); // grabs the character
                        if(Character.isLetter(ch)) { // checks if it is a letter
                            counter[Character.toLowerCase(ch) - 'a']++; // increment the counter for that letter
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                gc.setFill(Color.BLUE); // set color to blue
                for(int i = 0; i < ('z' - 'a' + 1); i++){
                    // draw the bars
                    gc.fillRect( i * 16.4,250 - (counter[i] * 2),7,counter[i] * 2);
                }

            }
        });

        // adds the file input components to bot
        bot.getChildren().add(new Label("Filename: "));
        bot.getChildren().add(filenameText);
        bot.getChildren().add(enter);

        // adds to vbox
        vbox.getChildren().add(canvas);
        vbox.getChildren().add(letters);
        vbox.getChildren().add(bot);

        Scene scene = new Scene(vbox);

        // display stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question 4");
        primaryStage.show();
    }
}
