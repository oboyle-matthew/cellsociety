package util;

import interfaces.Updatable;
import javafx.scene.Group;

import java.util.*;

public class Grid implements Updatable<Grid.Update> {
    public static class Update {
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
    private boolean[][] availability;
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
        for (Cell cell : cells) cell.execute(cell, this);

        Cell[] _cells = cells.toArray(new Cell[cells.size()]);
        for (Cell cell : _cells) cell.applyUpdates();

        applyUpdates();

        for (int i = 0; i < grid[0].length; ++i)
            for (int j = 0; j < grid.length; ++j)
                availability[i][j] = grid[i][j] == null;
    }

    public Group getGroup() {
        return group;
    }

    public Cell at(int i, int j) {
        if (inBounds(i, j))
            return grid[i][j];
        return null;
    }

    public Cell at(Vector2D pos) {
        return at(pos.x, pos.y);
    }

    public ArrayList<Cell> getNeighbours(Cell cell) {
        ArrayList<Cell> list = new ArrayList<>();
        Vector2D pos = cell.getPosition();
        list.add(at(pos.x - 1, pos.y - 1));
        list.add(at(pos.x, pos.y - 1));
        list.add(at(pos.x + 1, pos.y - 1));
        list.add(at(pos.x + 1, pos.y));
        list.add(at(pos.x + 1, pos.y + 1));
        list.add(at(pos.x, pos.y + 1));
        list.add(at(pos.x - 1, pos.y + 1));
        list.add(at(pos.x - 1, pos.y));
        return list;
    }

    public boolean getAvailability(int i, int j) {
        return inBounds(i, j) && availability[i][j];
    }

    public void setAvailability(int i, int j, boolean available) {
        if (inBounds(i, j))
            availability[i][j] = available;
    }

    public boolean getAvailability(Vector2D pos) {
        return inBounds(pos.x, pos.y) && availability[pos.x][pos.y];
    }

    public void setAvailability(Vector2D pos, boolean available) {
        if (inBounds(pos.x, pos.y))
            availability[pos.x][pos.y] = available;
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
        availability = new boolean[rows][cols];
        int total = cols * rows;

        for (Config.CellType type : config.getCellTypes().values()) {
            int n = (int) Math.floor(type.ratio * total);
            for (int i = 0; i < n; ++i)
                addRandom(type);
        }
    }

    private void addFromGrid(Config config) {
        grid = new Cell[rows][cols];
        availability = new boolean[rows][cols];
        Config.CellType[][] cellTypes = config.getGrid();
        for (int i = 0; i < cellTypes.length; ++i)
            for (int j = 0; j < cellTypes[0].length; ++j)
                add(new Cell(i, j, this, cellTypes[i][j]));
    }

    boolean move(Cell cell) {
        Vector2D pos = cell.getPosition();
        if (!inBounds(pos.x, pos.y) || grid[pos.x][pos.y] != null)
            return false;
        grid[pos.x][pos.y] = cell;
        availability[pos.x][pos.y] = false;
        return true;
    }

    public void clear(int i, int j) {
        if (inBounds(i, j)) {
            grid[i][j] = null;
            availability[i][j] = true;
        }
    }

    void remove(Cell cell) {
        Vector2D pos = cell.getPosition();
        if (inBounds(pos.x, pos.y)) {
            grid[pos.x][pos.y] = null;
            availability[pos.x][pos.y] = false;
        }
        cells.remove(cell);
        group.getChildren().remove(cell);
    }

    void prettyPrint() {
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j)
                System.out.printf("%c ", grid[i][j] == null ? '_' : grid[i][j].getType().symbol);
            System.out.println();
        }
        System.out.println("----------------------------");
    }

    private boolean inBounds(int i, int j) {
        return i >= 0 && j >= 0 && i < cols && j < rows;
    }
}
