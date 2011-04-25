
package abstractions.context;

import abstractions.adversity.AdversityInterface;
import abstractions.cell.CellManagerInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;

public class Context implements ContextInterface {

    private final AdversityInterface adversity;
    private final CellManagerInterface cellManager;
    private transient SideInterface sideToPlay = Sides.FIRST;

    // TODO gérer la stack de mutation (information parfaite)

    // TODO isTerminalMutation = isGameOver, après le process d'une mutation (MutationManager)
    // TODO ? créer la terminalMutation
    public Context(final AdversityInterface adversity, final CellManagerInterface cellManager) {
        this.adversity = adversity;
        this.cellManager = cellManager;
    }

    public AdversityInterface getAdversity() {
        return this.adversity;
    }

    public CellManagerInterface getCellManager() {
        return this.cellManager;
    }

    public void setSideToPlay(final SideInterface side) {
        this.sideToPlay = side;
    }

    public SideInterface getSideToPlay() {
        return this.sideToPlay;
    }

    @Override
    public String toString() {
        return this.adversity.toString() + "\n" + this.cellManager.toString() + "\n" + "side to play : " + this.sideToPlay;
    }

}
