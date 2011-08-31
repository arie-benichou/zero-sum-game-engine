
package abstractions.context;

import java.util.List;

import abstractions.cell.CellManagerInterface;
import abstractions.mutation.MutationInterface;
import abstractions.player.PlayerInterface;
import abstractions.side.SideInterface;

public interface ContextInterface {

    void setCurrentSide(final SideInterface side);

    SideInterface getCurrentSide();

    PlayerInterface getCurrentPlayer();

    List<MutationInterface> getLegalMovesForCurrentPlayer();

    void applyMoveForCurrentPlayer(MutationInterface move);

    boolean isGameOver();

    MutationInterface getLastPlayedMove();

    void undoLastPlayedMoveForCurrentPLayer();

    MutationInterface getLastMoveFromSide(SideInterface side);

    CellManagerInterface getCellManager();

}