package actions;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.Cell;
import util.State;
import util.Tuple;

import java.util.ArrayList;

abstract public class IAction {
    /**
     * Calculates action of a given Cell, provided neighbouring Cells
     *
     * @param t: tuple of current state and ArrayList of neighbours
     */
    public void process(Tuple<State, ArrayList<Cell>> t) {
        throw new NotImplementedException();
    }
}