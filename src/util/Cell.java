package util;

import actions.Action;
import interfaces.Updatable;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Class that implements cell functionality
 */
public class Cell extends Rectangle implements Updatable<Cell.Update> {
    public static class Update {
        int priority;
        int x;
        int y;
        int status;

        public Update(int x, int y, int status, int priority) {
            this.priority = priority;
            this.x = x;
            this.y = y;
            this.status = status;
        }

        public Update(int x, int y, int priority) {
            this(x, y, -1, priority);
        }

        public Update(int x, int y) {
            this(x, y, -1);
        }

        public Update(int status) {
            this(-1, -1, status, -1);
        }
    }

    private class UpdatePriorityComparator implements Comparator<Update> {
        @Override
        public int compare(Cell.Update o1, Cell.Update o2) {
            return o2.priority - o1.priority;
        }
    }

    private int x;
    private int y;
    private int status;

    private Grid grid;
    private Action action;
    private PriorityQueue<Update> updates;
    private Tuple<Integer, Integer> position;


    /**
     * Creates an instance of a cell
     */
    public Cell(int x, int y, Grid grid, Config.CellType type) {
        super(grid.getWidth() / grid.getCols(), grid.getHeight() / grid.getRows());
        this.action = type.getAction();
        this.setFill(Paint.valueOf("green"));
        this.grid = grid;
        setPosition(x, y);

        updates = new PriorityQueue<>(new UpdatePriorityComparator());
    }

    public void execute(Cell cell, Grid grid) {
        action.execute(cell, grid);
    }

    @Override
    public void add(Update update) {
        updates.add(update);
    }

    @Override
    public void applyUpdates() {
        for (Update update : updates) {
            setPosition(update.x, update.y);
            grid.move(this);
            status = update.status;
        }
        updates.clear();
    }

    public Tuple<Integer, Integer> getPosition() {
        return position;
    }

    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    private void setPosition(int x, int y) {
        position = new Tuple<>(x, y);
        this.x = x == -1 ? this.x : x;
        this.y = y == -1 ? this.y : y;
        setX(this.x * getWidth());
        setY(this.y * getHeight());
    }
}
