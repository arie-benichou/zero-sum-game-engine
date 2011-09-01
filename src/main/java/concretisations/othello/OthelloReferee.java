
package concretisations.othello;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import abstractions.cell.ManagedCellInterface;
import abstractions.context.ContextInterface;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.NullMutation;
import abstractions.referee.RefereeInterface;
import abstractions.side.SideInterface;

// TODO !! à optimiser...
public class OthelloReferee implements RefereeInterface {

    //TODO !? mettre en cache les coups légaux du joueur suivant
    @Override
    public boolean isGameOver(final ContextInterface context) {
        // 1) le board est plein
        if (context.getCellManager().isFull())
            return true;
        // 2) les deux joueurs sont bloqués
        final List<MutationInterface> legalMovesForNextSide = this.getLegalMoves(context, context.getCurrentSide().getNextSide());
        if (legalMovesForNextSide.size() == 1 && legalMovesForNextSide.get(0).isNull()) {
            final List<MutationInterface> legalMovesForCurrentSide = this.getLegalMoves(context, context.getCurrentSide());
            if (legalMovesForCurrentSide.size() == 1 && legalMovesForCurrentSide.get(0).isNull())
                return true;
        }
        // 3) les deux joueurs ont succesivement passé leur tour
        return context.getLastMove(context.getCurrentSide().getNextSide()).isNull() && context.getLastMove(context.getCurrentSide()).isNull();
    }

    private int computeDelta(final ContextInterface context, final SideInterface side) {
        int delta = 0;
        final Iterator<ManagedCellInterface> it = context.getCellManager().iterator();
        it.next();
        while (it.hasNext()) {
            final ManagedCellInterface cell = it.next();
            if (cell.getPiece().getSide().equals(side))
                ++delta;
            else if (cell.getPiece().getSide().equals(side.getNextSide()))
                --delta;
        }
        return delta;
    }

    private int computeLeft(final ContextInterface context, final SideInterface side) {
        int left = 0;
        final Iterator<ManagedCellInterface> it = context.getCellManager().iterator();
        it.next();
        while (it.hasNext()) {
            final ManagedCellInterface cell = it.next();
            if (cell.getPiece().getSide().equals(side))
                ++left;
        }
        return left;
    }

    @Override
    public List<MutationInterface> getLegalMoves(final ContextInterface context, final SideInterface side) {

        //TODO à améliorer
        final List<MutationInterface> legalMoves = new ArrayList<MutationInterface>();
        final Collection<Set<MutationInterface>> mutations = context.getCellManager().getPotentialMutations(side).values();

        for (final Set<MutationInterface> e : mutations)
            legalMoves.addAll(e);

        // TODO ?? doit être géré par le contextManager -> rétablir isNullMoveAllowed
        //if (legalMoves.isEmpty())        
        legalMoves.add(NullMutation.getInstance());

        return legalMoves;
    }

    @Override
    public int getHeuristicEvaluation(final ContextInterface context, final SideInterface side) {
        return this.computeDelta(context, side);
    }

    @Override
    public int getTerminalEvaluation(final ContextInterface context, final SideInterface side) {
        final int delta = this.computeDelta(context, side);
        if (delta < 0)
            return -Integer.MAX_VALUE + this.computeLeft(context, side);
        if (delta > 0)
            return Integer.MAX_VALUE - this.computeLeft(context, side.getNextSide());
        return 0;
    }

}