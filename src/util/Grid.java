package util;

import java.util.ArrayList;

public class Grid {
    private int[][] grid;
    private ArrayList<Cell> cells;

    Grid(ArrayList<Cell> cells) {

    }

    Grid(String data) {
        cells = new ArrayList<>();
        String[] lines = data.split("\n");
        for (String line: lines) {
            for (char c: line.toCharArray()) {
            }
        }
    }


}
