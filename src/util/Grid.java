package util;

import interfaces.Updatable;

import java.util.ArrayList;
import java.util.Comparator;

public class Grid implements Updatable<Grid.Update> {
    public class Update {
        public int priority;
        ArrayList<Cell> newCells;
    }

    private class UpdatePriorityComparator implements Comparator<Cell.Update> {
        @Override
        public int compare(Cell.Update o1, Cell.Update o2) {
            return o2.priority - o1.priority;
        }
    }

    private Grid[][] grid;
    private Grid[][] future_grid;
    private ArrayList<Cell> cells;

    public Grid(ArrayList<Cell> cells) {

    }

    public Grid(String data) {
        String[] lines = data.split("\n");
        for (String line : lines) {
            for (char c : line.toCharArray()) {
            }
        }
    }

    @Override
    public void add(Update update) {

    }

    @Override
    public void applyUpdates() {

    }
}
