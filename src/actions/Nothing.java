package actions;

import util.Cell;
import util.Grid;
import util.Tuple;

public class Nothing extends Action {
    @Override
    public Tuple<Cell.Update, Grid.Update> execute(Cell cell, Grid grid) {
        return null;
    }
}
