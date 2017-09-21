package util;

import java.util.ArrayList;

public class Grid {
    private Grid[][] grid;
    private ArrayList<Cell> cells;

    public Grid(ArrayList<Cell> cells) {

    }

    public Grid(String data) {
        cells = new ArrayList<>();
        String[] lines = data.split("\n");
        for (String line: lines) {
            for (char c: line.toCharArray()) {
            }
        }
    }


}
