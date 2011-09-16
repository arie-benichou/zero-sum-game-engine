
package concretisations.checkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.gameplay.game.referee.RefereeInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.mutation.MutationInterface;
import concretisations.checkers.mutations.CheckersMutation;
import concretisations.checkers.mutations.CheckersMutations;

//TODO prendre en compte les régles ici : http://www.ffjd.fr/Web/index.php?page=reglesdujeu#article3
public class CheckersReferee implements RefereeInterface {

    @Override
    public boolean isGameOver(MutationInterface lastMutation) {
        return false;
    }

    @Override
    public boolean isNullMoveAllowed() {
        return false;
    }

    public List<MutationInterface> filterMutation(final List<MutationInterface> potentialMutations) {

        //TODO utiliser un tableau ou une liste indexée
        final List<MutationInterface> walkMutations = new ArrayList<MutationInterface>();

        //TODO utiliser un tableau ou une liste indexée        
        final List<MutationInterface> jumpMutations = new ArrayList<MutationInterface>();

        for (MutationInterface mutation : potentialMutations) {
            if (mutation.getType().equals(CheckersMutations.JUMP))
                jumpMutations.add(mutation);
            else if (mutation.getType().equals(CheckersMutations.WALK)) // TODO else
                walkMutations.add(mutation);
        }

        if (jumpMutations.isEmpty())
            return walkMutations;
        else
            return jumpMutations;

    }

    @Override
    public SideInterface getSideToPlay(final SideInterface currentSide, MutationInterface lastMutation) {

        if (lastMutation.getType().equals(CheckersMutations.JUMP)) {

            CheckersMutation mutation = (CheckersMutation) lastMutation;
            final ManagedCellInterface cell = mutation.getPosition().getNeighbour(mutation.getDirection()).getNeighbour(mutation.getDirection());

            //TODO à améliorer: utiliser une map avec les types de mutations en clé            
            Set<? extends MutationInterface> potentialMutations = cell.getPiece().computePotentialMutations(cell, currentSide);
            for (MutationInterface e : potentialMutations)
                if (e.getType().equals(CheckersMutations.JUMP)) {
                    System.out.println();
                    System.out.println("## Encore une fois! ##");
                    System.out.println();
                    return currentSide;
                }
                /*
                else
                    break;
                */
        }
        return currentSide.getNextSide();
    }
}
