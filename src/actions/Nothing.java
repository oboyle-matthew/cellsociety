package actions;

import util.Cell;
import util.State;
import util.Tuple;

import java.util.ArrayList;

public class Nothing implements IAction {
    @Override
    public State calculate(Tuple<State, ArrayList<Cell>> t) {
        return null;
    }
}
