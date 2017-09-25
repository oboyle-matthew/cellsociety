package actions.wator;

import util.Cell;
import util.Grid;
import util.Vector2D;

public class Predator extends Actor {
    @Override
    public void execute(Cell cell, Grid grid) {
        local = 0;
        Vector2D pos1 = cell.getPosition();
        Vector2D offset = getOffset(cell, grid);
        Vector2D pos2 = pos1.add(offset);
        grid.setAvailability(pos1, true);
        grid.setAvailability(pos1.add(offset), false);

        if (cell.sameType(grid.at(pos2))) {
            cell.add(new Cell.Update(true));
            cell.add(new Cell.Update(pos2));
        }
    }

    private Vector2D getOffset(Cell cell, Grid grid) {
        Vector2D pos = cell.getPosition();
        Vector2D offset;
        while (true) {
            offset = randomNeighbour(cell, grid);
            local = local | (1 << ((1 + offset.y) * 3 + 1 + offset.x));
            if (local == 0x1ff || (!grid.getAvailability(pos.add(offset))
                    || (grid.at(pos) != null && grid.at(pos).sameType(grid.at(pos.add(offset))))))
                break;
        }
        return offset;
    }
}
