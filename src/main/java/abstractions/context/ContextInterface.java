
package abstractions.context;

import java.util.List;

import abstractions.adversity.AdversityInterface;
import abstractions.cell.CellManagerInterface;
import abstractions.mutation.MutationInterface;
import abstractions.player.PlayerInterface;
import abstractions.side.SideInterface;

public interface ContextInterface {

    void setCurrentSide(final SideInterface side);

    SideInterface getCurrentSide();

    PlayerInterface getCurrentPlayer();

    List<MutationInterface> getLegalMoves(final SideInterface side);

    List<MutationInterface> getLegalMoves();

    void applyMove(final MutationInterface move, final SideInterface side);

    void applyMove(final MutationInterface move);

    boolean isGameOver();

    MutationInterface getLastPlayedMove();

    MutationInterface getLastMove(final SideInterface side);

    void unapplyLastPlayedMove(SideInterface side);

    void unapplyLastPlayedMove();

    CellManagerInterface getCellManager();

    int getHeuristicEvaluation(final SideInterface side);

    int getTerminalEvaluation(final SideInterface side);

    AdversityInterface getAdversity();

}