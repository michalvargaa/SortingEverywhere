package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    public Label name;
    public Button start;
    public Button reset;
    public Pane canvasPane;
    public ComboBox<String> comboBox;
    public ColorPicker colorPicker;
    public ProgressIndicator progressBar;



    public int[] values;
    public static int HEIGHT, WIDTH;
    public AnimationTimer timer;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //get height, width
        HEIGHT = (int)canvasPane.getMinHeight();
        WIDTH = (int)canvasPane.getMinWidth();
        values = new int[WIDTH];

        //sorting algorithms
        comboBox.getItems().addAll("Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort");

        //progressIndicator
        progressBar.setProgress(0);
        progressBar.setStyle(String.format("-fx-progress-color: #%s;", colorPicker.getValue().toString().substring(2))); //color of the progressIndicator according to colorPicker

        start.setDisable(true);
        reset.setDisable(true);


        // add random integers to the array
        shuffle();
    }

    public void whichAlgo() {

        if(comboBox.getSelectionModel().isEmpty()) return;


        start.setDisable(false);
        reset.setDisable(false);

        timer = new Utils().animationTimer(values, canvasPane, comboBox.getValue().toLowerCase().split(" ")[0],colorPicker, progressBar);
        name.setText(comboBox.getValue());


    }

    public void changeColor() {
        progressBar.setStyle(String.format("-fx-progress-color: #%s;", colorPicker.getValue().toString().substring(2))); //color of the progressIndicator according to colorPicker
    }


    public void startSorting() {
        progressBar.setProgress(-1);
        start.setDisable(true);
        comboBox.setDisable(true);
        timer.start();

    }

    public void resetSorting() {
        timer.stop();
        progressBar.setProgress(0);

        start.setDisable(false);
        comboBox.setDisable(false);
        canvasPane.getChildren().clear();
        shuffle();


        whichAlgo();

    }

    private void shuffle() {
        for (int i = 0; i < WIDTH; i++) {
            values[i] = (int) (Math.random() * HEIGHT);
            Line line = new Line(i,HEIGHT,i,values[i]);
            line.setStroke(colorPicker.getValue());
            canvasPane.getChildren().add(line);
        }
    }








}
