package actions.wator;

import actions.Action;
import util.Cell;
import util.Grid;

import java.util.Random;
import util.Vector2D;

public class Actor extends Action {
    private static final Random random = new Random();
    @Override
    public void execute(Cell cell, Grid grid) { }

    static Vector2D randomNeighbour(Cell cell, Grid grid) {
        return new Vector2D(1 - random.nextInt(2), 1 - random.nextInt(2));
    }
}
