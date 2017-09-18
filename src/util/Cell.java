package util;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Class that implements cell functionality
 */
public class Cell extends Rectangle {
    private State state;
    private int width;
    private int height;
    private Consumer<Tuple<State, ArrayList<Cell>>> action;

    /**
     * Creates an instance of a cell
     *
     * @param t: the cell's action
     */
    public Cell(Consumer<Tuple<State, ArrayList<Cell>>> t) {
        setAction(t);
    }

    /**
     * Get state
     */
    public State getState() {
        return state;
    }

    /**
     * Set state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Sets action
     */
    public void setAction(Consumer<Tuple<State, ArrayList<Cell>>> action) {
        this.action = action;
    }
}
