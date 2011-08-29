
package concretisations.tictactoe;

import java.util.List;

import abstractions.cell.ManagedCellInterface;
import abstractions.direction.DirectionInterface;
import abstractions.direction.DirectionManager;
import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.mutation.MutationInterface;
import abstractions.referee.RefereeInterface;
import abstractions.side.SideInterface;

public class TicTacToeReferee implements RefereeInterface {

    private final static int CONNECTIONS = 3;

    //TODO à améliorer
    private final static NamedDirection[][] DIRECTIONS = {
            { DirectionManager.NamedDirection.LEFT, DirectionManager.NamedDirection.RIGHT },
            { DirectionManager.NamedDirection.TOP, DirectionManager.NamedDirection.BOTTOM },
            { DirectionManager.NamedDirection.TOP_RIGHT, DirectionManager.NamedDirection.BOTTOM_LEFT },
            { DirectionManager.NamedDirection.BOTTOM_RIGHT, DirectionManager.NamedDirection.TOP_LEFT },
    };

    /*
    private final static RefereeInterface INSTANCE = new Connect4Referee();

    private Connect4Referee() {}
    
    public final static RefereeInterface getInstance() {
        return INSTANCE;
    }
    */

    @Override
    public boolean isGameOver(MutationInterface lastMutation) {
        boolean isGameOver = false;
        for (final NamedDirection[] directions : DIRECTIONS) {
            final int connections = this.computeConnection(lastMutation, directions[0].value()) + 1
                    + this.computeConnection(lastMutation, directions[1].value());
            if (connections >= CONNECTIONS) {
                isGameOver = true;
                break;
            }
        }
        return isGameOver;
    }

    protected int computeConnection(final MutationInterface lastMutation, final DirectionInterface direction) {
        int connected;
        ManagedCellInterface cell = lastMutation.getCell();
        for (connected = 0; connected <= CONNECTIONS; ++connected) {
            cell = cell.getNeighbour(direction);
            if (cell.isNull() || cell.isEmpty() || cell.getPiece().getSide() != lastMutation.getCell().getPiece().getSide())
                break;
        }
        return connected;
    }

    @Override
    public boolean isNullMoveAllowed() {
        return false;
    }
    
    @Override
    public List<MutationInterface> filterMutation(List<MutationInterface> potentialMutations) {
        return potentialMutations;
    }

    @Override
    public SideInterface getSideToPlay(SideInterface currentSide, MutationInterface lastMutation) {
        return currentSide.getNextSide();
    }    

}
