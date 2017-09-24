package util;

import interfaces.Updatable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Grid implements Updatable<Grid.Update> {
    public class Update {
        int priority;
        ArrayList<Cell> newCells;

        public Update(ArrayList<Cell> cells, int priority) {
            this.priority = priority;
            newCells = cells;
        }

        public Update(ArrayList<Cell> cells) {
            this(cells, -1);
        }
    }

    private class UpdatePriorityComparator implements Comparator<Grid.Update> {
        @Override
        public int compare(Grid.Update o1, Grid.Update o2) {
            return o2.priority - o1.priority;
        }
    }

    private Cell[][] grid;
    private ArrayList<Cell> cells;
    private PriorityQueue<Update> updates;

    private double width;
    private double height;

    private int rows;
    private int cols;

    public Grid(ArrayList<Cell> cells) {
        this.cells = cells;
        updates = new PriorityQueue<>(new UpdatePriorityComparator());
    }

    public Grid(String data) {
        String[] lines = data.split("\n");
        for (String line : lines) {
            for (char c : line.toCharArray()) {
            }
        }
    }

    public void move(Cell cell) {
        Tuple<Integer, Integer> pos = cell.getPosition();
        if (pos.a < 0 || pos.b < 0
                || pos.a >= cols || pos.b >= rows)
            return;
        grid[pos.a][pos.b] = cell;
        cell.setPosition(pos.a, pos.b);
    }

    private void add(Cell cell) {
        cells.add(cell);
        move(cell);
    }

    @Override
    public void add(Update update) {
        updates.add(update);
    }

    @Override
    public void applyUpdates() {
        for (Update update : updates) {
            for (Cell cell : update.newCells)
                add(cell);
        }
        updates.clear();
    }
}
