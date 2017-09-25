package actions.wator;

import util.Cell;
import util.Grid;
import util.Vector2D;

import java.util.ArrayList;

public class Prey extends Actor {
    public static final int priority = 0;
    public static final double breeding = 4.0;

    @Override
    public void execute(Cell cell, Grid grid) {
        local = 0;
        Vector2D pos = cell.getPosition();
        Vector2D offset = getOffset(cell, grid);
        grid.setAvailability(pos, true);
        grid.setAvailability(pos.add(offset), false);

        if (breeding >= cell.getStatus()) {
            ArrayList<Cell> cells = new ArrayList<>();
            Vector2D childPos = pos.add(getOffset(cell, grid));
            grid.setAvailability(childPos, false);
            cells.add(new Cell(childPos.x, childPos.y, grid, cell.getType()));
            grid.add(new Grid.Update(cells));
        }
    }

    private Vector2D getOffset(Cell cell, Grid grid) {
        Vector2D pos = cell.getPosition();
        Vector2D offset;
        do {
            offset = randomNeighbour(cell, grid);
            local = local | (1 << ((1 + offset.y) * 3 + 1 + offset.x));
            if (local == 0x1ff)
                break;
        } while (!grid.getAvailability(pos.add(offset)));
        return offset;
    }
}
