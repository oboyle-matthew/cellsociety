package actions;

import util.Cell;
import util.Grid;
import util.Tuple;

public class Test extends Action {
    @Override
    public void execute(Cell cell, Grid grid) {
        Tuple<Integer, Integer> position = cell.getPosition();
        cell.add(new Cell.Update(position.a, position.b + 1));
    }
}
