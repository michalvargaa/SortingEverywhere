package sample.sorting;


import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import sample.Controller;

import java.util.Arrays;

public class SortingAlgorithms {

    public boolean sorted = false;

    public int index;
    public int anotherOne;

    private Pane pane;
    private String algo;
    private int[] values;
    private ColorPicker colorPicker;


    public SortingAlgorithms(String algo, int[] values, Pane pane, ColorPicker colorPicker) {
        this.algo = algo;
        this.values = values;
        this.pane = pane;
        this.colorPicker = colorPicker;

        index = 0;
        anotherOne = 0;



    }

    public void sort() {
        switch (algo) {
            case "bubble":
                bubbleSort(1);
                break;
            case "selection":
                selectionSort(1);
                break;
            case "insertion":
                insertionSort(1);
                break;
            case "merge":
                mergeSort(values,0,values.length-1,1);
                break;
        }
    }

    public void display() {
        pane.getChildren().clear();
        for (int i = 0; i < values.length; i++) {
            Line line = new Line(i, Controller.HEIGHT, i, Controller.HEIGHT - values[i]);
            line.setStroke(colorPicker.getValue());
            pane.getChildren().add(line);
        }
    }

    private void bubbleSort(int steps) {
        for(int i = 0; i < steps; i++) {
            if(!sorted) {
                for (int j = 0; j < values.length - 1; j++) {
                    if (values[j] > values[j + 1]) {
                        int temp = values[j];
                        values[j] = values[j + 1];
                        values[j + 1] = temp;
                    }
                }
                sorted = isSorted();
                if(sorted) System.out.println("Bubble sort finished");
            }


        }
    }

    private void selectionSort(int steps) {

        for(int i = 0; i < steps; i++) {

            if(!sorted) {

                int min = values[index];
                int indexOfNewMin = index;
                //finding min number in the rest of the array
                for (int j = index; j < values.length; j++) {
                    if (values[j] < min) {
                        min = values[j];
                        indexOfNewMin = j;
                    }
                }
                //swapping array
                int temp = values[index];
                values[index] = min;
                values[indexOfNewMin] = temp;

                index++;

                sorted = isSorted();
                if (sorted) System.out.println("SELECTION SORT FINISHED");
            }
        }

    }

    private void insertionSort(int steps) {
        for (int i = 0; i < steps; i++) {
            if (!sorted) {

                int key = values[index];
                int k = index-1;
                while ( (k > -1) && ( values [k] > key ) ) {
                    values [k+1] = values [k];
                    k--;
                }
                values[k+1] = key;

                index++;
                sorted = isSorted();
                if (sorted) System.out.println("INSERTION SORT FINISHED");
            }
        }
    }


    private void mergeSort(int[] values, int start, int end, int steps) {
        if(!sorted){
            int high = values.length - 1;
            int[] temp = Arrays.copyOf(values, values.length);

            if(index == 0){index++;}

            int temper = index+steps;
            int m = index;

            while(m <= temper)
            {
                boolean a = false;
                for (int i = anotherOne; i <= high; i += 2*m)
                {
                    a = false;
                    int from = i;
                    int mid = i + m - 1;
                    int to = Integer.min(i + 2 * m - 1, high);
                    merge(values, temp, from, mid, to);

                    if(i+2*m > high){anotherOne = 0; a = true;}
                    else {anotherOne = i + 2*m;}
                    break;
                }
                if(a) {
                    index = m * 2;
                    m = m * 2;
                }
                else{
                    if(!(m == 1))
                        m = m * 2;
                }
                sorted = isSorted();
                if (sorted) System.out.println("MERGE SORT FINISHED");
            }
        }
    }
    private static void merge(int[] A, int[] temp, int from, int mid, int to) {
        int k = from, i = from, j = mid + 1;

        // loop till there are elements in the left and right runs
        while (i <= mid && j <= to) {
            if (A[i] < A[j]) {
                temp[k++] = A[i++];
            } else {
                temp[k++] = A[j++];
            }
        }

        // Copy remaining elements
        while (i < A.length && i <= mid) {
            temp[k++] = A[i++];
        }

        // Don't need to copy second half

        // copy back to the original array to reflect sorted order
        for (i = from; i <= to; i++) {
            A[i] = temp[i];
        }
    }

    private boolean isSorted() {
        boolean isSorted = true;
        for (int i = 0; i < values.length - 1; i++) {
            if (values[i] > values[i + 1]) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

}
