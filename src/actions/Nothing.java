package actions;

import util.Cell;
import util.State;
import util.Tuple;

import java.util.ArrayList;

public class Nothing extends IAction {
    @Override
    public State process(Tuple<State, ArrayList<Cell>> t) {
        return t.a;
    }
}
