package util;

import interfaces.Updatable;
import javafx.scene.Group;

import java.util.*;

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

    private Random random = new Random();

    private Cell[][] grid;
    private ArrayList<Cell> cells;
    private PriorityQueue<Update> updates;

    private double width;
    private double height;

    private int rows;
    private int cols;

    public Grid(Config config) {
        rows = config.rows;
        cols = config.cols;

        width = config.width;
        height = config.height;

        if (config.type == Config.Type.RANDOM)
            addFromDistribution(config);
        else
            addFromGrid(config);

        updates = new PriorityQueue<>(new UpdatePriorityComparator());
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

    public void next() {
        for (Cell cell : cells)
            cell.execute(cell, this);

        for (Cell cell : cells)
            cell.applyUpdates();

        applyUpdates();
    }

    public Group getGroup() {
        return null;
    }

    int getRows() {
        return rows;
    }

    int getCols() {
        return cols;
    }

    double getWidth() {
        return width;
    }

    double getHeight() {
        return height;
    }

    private void add(Cell cell) {
        if (move(cell))
            cells.add(cell);
    }

    private void addRandom(Config.CellType cellType) {
        int x, y;
        do {
            x = random.nextInt(cols);
            y = random.nextInt(rows);
        } while (grid[x][y] != null);
        add(new Cell(x, y, this, cellType.getAction()));
    }

    private void addFromDistribution(Config config) {
        int total = cols * rows;

        for (Config.CellType type : config.getCellTypes().values()) {
            int n = (int) Math.floor(type.ratio * total);
            for (int i = 0; i < n; ++i)
                addRandom(type);
        }
    }

    private void addFromGrid(Config config) {
        Config.CellType[][] cellTypes = config.getGrid();
        for (int i = 0; i < cellTypes.length; ++i)
            for (int j = 0; j < cellTypes[0].length; ++j)
                add(new Cell(i, j, this, cellTypes[i][j].getAction()));
    }

    boolean move(Cell cell) {
        Tuple<Integer, Integer> pos = cell.getPosition();
        if (pos.a < 0 || pos.b < 0 || pos.a >= cols || pos.b >= rows || grid[pos.a][pos.b] != null)
            return false;
        grid[pos.a][pos.b] = cell;
        cell.setPosition(pos.a, pos.b);
        return true;
    }
}
