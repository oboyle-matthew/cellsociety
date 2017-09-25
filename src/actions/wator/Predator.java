package actions.wator;

import util.Cell;
import util.Grid;
import util.Vector2D;

import java.util.Comparator;

public class Predator extends Actor {
    @Override
    public void execute(Cell cell, Grid grid) {
        Vector2D pos = cell.getPosition();
        Vector2D offset = getOffset(cell, grid);
        grid.setAvailability(pos, true);
        grid.setAvailability(pos.add(offset), false);

        if (grid.at(pos).sameType(grid.at(pos.add(offset)))) {
            grid.at(pos).add(new Cell.Update(true));
            cell.add(new Cell.Update(pos.add(offset)));
        }
    }

    private Vector2D getOffset(Cell cell, Grid grid) {
        Vector2D pos = cell.getPosition();
        Vector2D offset;
        do {
            offset = randomNeighbour(cell, grid);
        } while (!grid.getAvailability(pos.add(offset)) || grid.at(pos).sameType(grid.at(pos.add(offset))));
        return offset;
    }
}
