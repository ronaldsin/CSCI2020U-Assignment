package Question3;

// Ronald Sin 100700891

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Question3 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // pane holds everything
        Pane pane = new Pane();
        pane.setMaxWidth(200);
        pane.setMaxHeight(200);

        // big circle that the dots will revolve around
        Circle circle = new Circle();

        // set to center of pane
        circle.setCenterX(pane.getMaxWidth() / 2);
        circle.setCenterY(pane.getMaxHeight() / 2);

        circle.setRadius(50); // set radius to 50
        circle.setStroke(Color.BLACK);
        circle.setFill(null); // no fill for clarity

        pane.getChildren().add(circle); // add the circle to the pane

        Circle[] dots = new Circle[3]; // the dots that will move around the circle

        // the line that connect the circle
        Line[] lines = new Line[3];
        Group lineGroup = new Group();

        // text that displays the angle
        Text[] angle = new Text[3];
        Group textGroup = new Group();

        // sets up the 3 dots lines and text
        for(int i = 0; i < 3; i++){
            // creates a small red dot
            dots[i] = new Circle();
            dots[i].setRadius(5);
            dots[i].setFill(Color.RED);

            // creates and adds line to pane
            lines[i] = new Line();
            lineGroup.getChildren().add(lines[i]);

            // creates and adds angle text to pane
            angle[i] = new Text();
            textGroup.getChildren().add(angle[i]);

            int finalI = i;
            dots[finalI].setOnMouseDragged(e -> {
                // the vector components between the mouse and center of the large circle
                double dx = e.getX() - circle.getCenterX();
                double dy = e.getY() - circle.getCenterY();

                double hyp = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)); // calculates the hypotenuse

                // normalize the vector
                double x = dx / hyp;
                double y = dy / hyp;

                // sets the new location of the dot relative to the circle
                dots[finalI].setCenterX(x * circle.getRadius() + circle.getCenterX());
                dots[finalI].setCenterY(y * circle.getRadius() + circle.getCenterY());

                // update lines and text
                updateLine(lines, dots);
                updateText(angle, dots);
            });

            // randomize the initial starting position of the dots
            int randX = (int)(Math.random() * ((100))) - 50;
            System.out.println(randX);

            dots[i].setCenterX(circle.getCenterX() + randX);
            dots[i].setCenterY(circle.getCenterY() - Math.sqrt(Math.pow(circle.getRadius(), 2) - Math.pow(randX, 2)));

            pane.getChildren().add(dots[i]); // add the dots to the pane
        }

        // update the lines and text for thr first time
        updateLine(lines, dots);
        updateText(angle, dots);

        // add line group and text group to pane
        pane.getChildren().add(lineGroup);
        pane.getChildren().add(textGroup);

        Scene scene = new Scene(pane, 200, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Question 3");
        primaryStage.show();
    }

    public void updateLine(Line[] lines, Circle[] dots){ // updates the position of the lines
        connectDots(lines[0], dots[0], dots[1]);
        connectDots(lines[1], dots[1], dots[2]);
        connectDots(lines[2], dots[2], dots[0]);
    }

    public void connectDots(Line line, Circle start, Circle end){ // draws the lines
        line.setStartX(start.getCenterX());
        line.setStartY(start.getCenterY());

        line.setEndX(end.getCenterX());
        line.setEndY(end.getCenterY());
    }

    public void updateText(Text[] text, Circle[] dots){ // updates the labels that display the angle
        // gets the side lengths of the triangle
        double a = Math.sqrt(Math.pow(dots[2].getCenterX() - dots[1].getCenterX(), 2) + Math.pow(dots[2].getCenterY() - dots[1].getCenterY(), 2));
        double b = Math.sqrt(Math.pow(dots[2].getCenterX() - dots[0].getCenterX(), 2) + Math.pow(dots[2].getCenterY() - dots[0].getCenterY(), 2));
        double c = Math.sqrt(Math.pow(dots[0].getCenterX() - dots[1].getCenterX(), 2) + Math.pow(dots[0].getCenterY() - dots[1].getCenterY(), 2));

        // gets the angles converted to degrees
        text[0].setText(Integer.toString((int)(((Math.acos((a * a - b * b - c * c) / (-2 * b * c)))) * 180/Math.PI)));
        text[1].setText(Integer.toString((int)(((Math.acos((b * b - a * a - c * c) / (-2 * a * c)))) * 180/Math.PI)));
        text[2].setText(Integer.toString((int)(((Math.acos((c * c - b * b - a * a) / (-2 * a * b)))) * 180/Math.PI)));

        // sets the test to be above the dot
        for(int i = 0; i < 3; i ++) {
            text[i].setX(dots[i].getCenterX());
            text[i].setY(dots[i].getCenterY() - 10);
        }
    }

}
