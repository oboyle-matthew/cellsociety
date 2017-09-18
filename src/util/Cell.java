package util;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Class that implements cell functionality
 */
public class Cell extends Rectangle {
    public Consumer<Tuple<State, ArrayList<Cell>>> action;

    private int width;
    private int height;

    /**
     * Creates an instance of a cell
     *
     * @param action: the cell's action
     */
    public Cell(Consumer<Tuple<State, ArrayList<Cell>>> action) { }
}
