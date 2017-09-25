package actions.wator;

import actions.Action;
import util.Cell;
import util.Grid;
import util.Vector2D;

import java.util.Comparator;
import java.util.Random;

public class Actor extends Action {

    class RandomQueueComparator implements Comparator<Vector2D> {
        @Override
        public int compare(Vector2D o1, Vector2D o2) {
            return 0;
        }
    }

    private static final Random random = new Random();

    @Override
    public void execute(Cell cell, Grid grid) {
    }

    static Vector2D randomNeighbour(Cell cell, Grid grid) {
        return new Vector2D(1 - random.nextInt(2), 1 - random.nextInt(2));
    }
}
