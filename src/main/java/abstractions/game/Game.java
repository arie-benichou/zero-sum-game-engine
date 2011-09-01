
package abstractions.game;

import abstractions.cell.CellManagerInterface;
import abstractions.referee.RefereeInterface;

public class Game implements GameInterface {

    private final RefereeInterface referee;
    private final CellManagerInterface cellManager;

    public Game(final CellManagerInterface cellManager, final RefereeInterface referee) {
        this.cellManager = cellManager;
        this.referee = referee;
    }

    @Override
    public final CellManagerInterface getCellManager() {
        return this.cellManager;
    }

    @Override
    public final RefereeInterface getReferee() {
        return this.referee;
    }

}
