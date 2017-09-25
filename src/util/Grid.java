package util;

import interfaces.Updatable;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

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
    private Group group;

    private double width;
    private double height;

    private int rows;
    private int cols;

    public Grid(Config config) {
        rows = config.rows;
        cols = config.cols;

        width = config.width;
        height = config.height;

        group = new Group();
        cells = new ArrayList<>();

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
        return group;
    }

    public Cell at(int i, int j) {
        if (inBounds(i, j))
            return grid[i][j];
        return null;
    }

    public ArrayList<Cell> getNeighbours(Cell cell) {
        ArrayList<Cell> list = new ArrayList<>();
        Tuple<Integer, Integer> pos = cell.getPosition();
        list.add(at(pos.a - 1, pos.b - 1));
        list.add(at(pos.a, pos.b - 1));
        list.add(at(pos.a + 1, pos.b - 1));
        list.add(at(pos.a + 1, pos.b));
        list.add(at(pos.a + 1, pos.b + 1));
        list.add(at(pos.a, pos.b + 1));
        list.add(at(pos.a - 1, pos.b + 1));
        list.add(at(pos.a - 1, pos.b));
        return list;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    double getWidth() {
        return width;
    }

    double getHeight() {
        return height;
    }

    private void add(Cell cell) {
        if (move(cell)) {
            cells.add(cell);
            group.getChildren().add(cell);
        }
    }

    private void addRandom(Config.CellType cellType) {
        int x, y;
        do {
            x = random.nextInt(cols);
            y = random.nextInt(rows);
        } while (grid[x][y] != null);
        add(new Cell(x, y, this, cellType));
    }

    private void addFromDistribution(Config config) {
        grid = new Cell[rows][cols];
        int total = cols * rows;

        for (Config.CellType type : config.getCellTypes().values()) {
            int n = (int) Math.floor(type.ratio * total);
            for (int i = 0; i < n; ++i)
                addRandom(type);
        }
    }

    private void addFromGrid(Config config) {
        grid = new Cell[rows][cols];
        Config.CellType[][] cellTypes = config.getGrid();
        for (int i = 0; i < cellTypes.length; ++i)
            for (int j = 0; j < cellTypes[0].length; ++j)
                add(new Cell(i, j, this, cellTypes[i][j]));
    }

    boolean move(Cell cell) {
        Tuple<Integer, Integer> pos = cell.getPosition();
        if (!inBounds(pos.a, pos.b) || grid[pos.a][pos.b] != null)
            return false;
        grid[pos.a][pos.b] = cell;
        return true;
    }

    void clear(int i, int j) {
        if (inBounds(i, j))
            grid[i][j] = null;
    }

    void prettyPrint() {
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j)
                System.out.print(grid[i][j]);
            System.out.println();
        }
    }

    private boolean inBounds(int i, int j) {
        return i >= 0 && j >= 0 && i < cols && j < rows;
    }
}
