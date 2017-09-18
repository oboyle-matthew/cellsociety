package actions;

import util.Cell;
import util.State;
import util.Tuple;

import java.util.ArrayList;

public interface IAction {
    /**
     * Calculates action of a given Cell, provided neighbouring Cells
     *
     * @param t: tuple of current state and ArrayList of neighbours
     * @return new state of the cell
     */
    State calculate(Tuple<State, ArrayList<Cell>> t);
}