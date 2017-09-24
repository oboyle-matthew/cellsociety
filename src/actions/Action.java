package actions;

import util.Cell;
import util.Grid;
import util.Tuple;

public abstract class Action {
    /**
     * Calculates action of a given Cell, provided neighbouring Cells
     */
    abstract public Tuple<Cell.Update, Grid.Update> execute(Cell cell, Grid grid);
}