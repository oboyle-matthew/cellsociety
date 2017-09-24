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
        public int priority;
        double x;
        double y;
        int status;
    }

    private class UpdatePriorityComparator implements Comparator<Update> {
        @Override
        public int compare(Cell.Update o1, Cell.Update o2) {
            return o2.priority - o1.priority;
        }
    }

    private Grid grid;
    private PriorityQueue<Update> updates;
    private int status;

    private Action action;

    /**
     * Creates an instance of a cell
     */
    public Cell(Action action) {
        this.action = action;
        updates = new PriorityQueue<>(new UpdatePriorityComparator());
    }

    public void setAction(Action action, Grid grid) {
        this.action = action;
        this.grid = grid;
    }

    public int getStatus() {
        return status;
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
            setX(update.x);
            setY(update.y);
            status = update.status;
            grid.move(this);
        }
        updates.clear();
    }
}
