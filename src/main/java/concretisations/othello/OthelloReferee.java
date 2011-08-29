
package concretisations.othello;

import java.util.List;

import abstractions.mutation.MutationInterface;
import abstractions.referee.RefereeInterface;
import abstractions.side.SideInterface;

public class OthelloReferee implements RefereeInterface {

    @Override
    public boolean isGameOver(MutationInterface lastMutation) {
        return false;
    }

    @Override
    public boolean isNullMoveAllowed() {
        return true;
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
