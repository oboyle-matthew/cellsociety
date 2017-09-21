package actions;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.Cell;
import util.Tuple;

import java.util.ArrayList;

class AbstractAction {
    /**
     * Calculates action of a given Cell, provided neighbouring Cells
     *
     * @param t: tuple of current state and ArrayList of neighbours
     */
    public static void process(Tuple<Cell, ArrayList<Cell>> t) {
        throw new NotImplementedException();
    }
}