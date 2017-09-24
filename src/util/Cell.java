package util;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Class that implements cell functionality
 */
public class Cell extends Rectangle {
    private class State {
        double x;
        double y;
        int state;

    }

    private State state;
    private int width;
    private int height;

    /**
     * Creates an instance of a cell
     */
    public Cell() {

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
}
