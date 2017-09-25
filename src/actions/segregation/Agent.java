package actions.segregation;

import actions.Action;
import util.Cell;
import util.Grid;
import util.Tuple;
import util.Vector2D;

import java.util.ArrayList;
import java.util.Random;

public class Agent extends Action {
    private static final double ratio = .5;
    private final Random random = new Random();
    @Override
    public void execute(Cell cell, Grid grid) {
        ArrayList<Cell> neighbours = grid.getNeighbours(cell);
        int same = 0, different = 0;
        for (Cell neighbour : neighbours) {
            if (neighbour == null)
                continue;
            if (neighbour.getType().equals(cell.getType()))
                same++;
            else
                different++;
        }

        if (different != 0 && ratio < same / different) {
            int x, y;
            do {
                x = random.nextInt(grid.getCols());
                y = random.nextInt(grid.getRows());
            } while (!grid.getAvailability(x, y));
            Vector2D pos = cell.getPosition();
            grid.setAvailability(pos, true);
            grid.setAvailability(x, y, false);
            cell.add(new Cell.Update(x, y));
        }
    }
}
