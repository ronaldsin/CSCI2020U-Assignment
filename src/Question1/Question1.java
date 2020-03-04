package Question1;

// Ronald Sin 100700891

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Question1 extends Application {
    public static void main(String[] args){ launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        int[] cardNum = new int[3]; // card number
        Image[] image = new Image[3]; // the images for the card
        ImageView[] card = new ImageView[3]; // the imageveiw that displays the card image

        HBox hbox = new HBox();

        for(int i = 0; i < 3; i++){ // loop for the 3 cards
            cardNum[i] =  (int)(Math.random() * ((52 - 1)) + 1); // a random number between 1 and 52

            // grabs the image and puts it in the imageview
            image[i] = new Image(getClass().getResource("image/card/" + cardNum[i] + ".png").toExternalForm());
            card[i] = new ImageView(image[i]);

            hbox.getChildren().add(card[i]); // add imageview to hbox
        }

        Scene scene = new Scene(hbox);

        // show primary stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question 1");
        primaryStage.show();
    }
}
