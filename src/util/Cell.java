package util;

import actions.Action;
import interfaces.Updatable;
import javafx.scene.shape.Rectangle;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Class that implements cell functionality
 */
public class Cell extends Rectangle implements Updatable<Cell.Update> {
    public class Update {
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
            this(x, y,-1);
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

    private double width;
    private double height;

    private int x;
    private int y;

    private Grid grid;
    private Action action;
    private PriorityQueue<Update> updates;

    private Tuple<Integer, Integer> position;
    private int status;


    /**
     * Creates an instance of a cell
     */
    public Cell(int x, int y, Grid grid, Action action) {
        this.action = action;
        this.grid = grid;

        this.width = width;
        this.height = height;
        setPosition(x, y);

        grid.move(this);
        updates = new PriorityQueue<>(new UpdatePriorityComparator());
    }

    public int getStatus() {
        return status;
    }

    public void setAction(Action action, Grid grid) {
        this.action = action;
        this.grid = grid;
    }

    Tuple<Integer, Integer> getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.x = x == -1 ? this.x : x;
        this.y = y == -1 ? this.y : y;
        setX(this.x * width);
        setY(this.y * height);
    }

    public Tuple<Cell.Update, Grid.Update> execute(Cell cell, Grid grid) {
        return action.execute(cell, grid);
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
}
