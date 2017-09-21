package actions;

import util.Cell;
import util.State;
import util.Tuple;

import java.util.ArrayList;

public class Nothing extends IAction {
    @Override
    public void process(Tuple<State, ArrayList<Cell>> t) {
    }
}
