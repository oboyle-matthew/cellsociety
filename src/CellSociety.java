import javafx.scene.Group;
import util.Config;
import util.Grid;

/**
 * Main game class runner
 */
class CellSociety {
    private Config config;
    private Grid grid;

    /**
     * Creates an instance of the game
     *
     * @param config: configuration
     */
    CellSociety(Config config) {
        update(config);
    }

    void update(Config config) {
        this.config = config;
        grid = new Grid(config);
    }

    Group getGroup() {
        return grid.getGroup();
    }

    /**
     * Calculates the grid  at given generation
     *
     * @param generation: generation number
     * @return grid of the new generation
     */
    Grid calculate(int generation) {
        return null;
    }

    /**
     * Calculate all of the cell's actions
     */
    private void calculate() {

    }
}