package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;

import sample.sorting.SortingAlgorithms;


public class Utils {

    private final int timeToTick = 10; //milliseconds between ticks
    private SortingAlgorithms sortingAlgorithms;
    private boolean running = true;

    public AnimationTimer animationTimer(int[] values, Pane pane, String sort, ColorPicker colorPicker, ProgressIndicator progressIndicator) {

        sortingAlgorithms = new SortingAlgorithms(sort, values, pane, colorPicker);

        return new AnimationTimer() {
            long curr = System.currentTimeMillis();
            long lastUpdate = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                if (curr - lastUpdate > 10) {
                    if(running) {
                        sortingAlgorithms.sort();
                        sortingAlgorithms.display();
                        running = !sortingAlgorithms.sorted;

                    }
                    progressIndicator.setProgress(running ? -1 : 1);
                    lastUpdate = curr;
                }
                curr = System.currentTimeMillis();

            }

        };
    }

}
