
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

public class OthelloReferee implements RefereeInterface {

    @Override
    public boolean isGameOver(ContextInterface context) {

        // 1) le board est plein

        if (context.getCellManager().isFull()) {
            System.out.println("le board est plein");
            return true;
        }

        // 2) les deux joueurs sont bloqués

        //TODO !? mettre en cache les coups légaux du joueur suivant
        List<MutationInterface> legalMovesForNextSide = this.getLegalMoves(context, context.getCurrentSide().getNextSide());
        if (legalMovesForNextSide.size() == 1 && legalMovesForNextSide.get(0).isNull()) {
            List<MutationInterface> legalMovesForCurrentSide = this.getLegalMoves(context, context.getCurrentSide());
            if (legalMovesForCurrentSide.size() == 1 && legalMovesForCurrentSide.get(0).isNull()) {
                System.out.println("les deux joueurs sont bloqués");
                return true;
            }
        }

        // 3) les deux joueurs ont succesivement passé leur tour

        //System.out.println(context.getLastMoveFromSide(context.getCurrentSide().getNextSide()));
        //System.out.println(context.getLastMoveFromSide(context.getCurrentSide()));

        if (context.getLastMoveFromSide(context.getCurrentSide().getNextSide()).isNull() && context.getLastMoveFromSide(context.getCurrentSide()).isNull()) {
            System.out.println("les deux joueurs ont succesivement passé leur tour");
            return true;
        }

        return false;

    }

    private int computeDelta(final ContextInterface context, SideInterface currentSide) {
        int delta = 0;
        Iterator<ManagedCellInterface> it = context.getCellManager().iterator();
        it.next();
        while (it.hasNext()) {
            final ManagedCellInterface cell = it.next();
            if (cell.getPiece().getSide().equals(currentSide)) {
                ++delta;
            }
            else if (cell.getPiece().getSide().equals(currentSide.getNextSide())) {
                --delta;
            }
        }
        return delta;
    }

    /*
    @Override
    public SideInterface getWinner(final ContextInterface context) {
        int firstSide = 0;
        int secondSide = 0;
        Iterator<ManagedCellInterface> it = context.getCellManager().iterator();
        it.next();
        while (it.hasNext()) {
            final ManagedCellInterface cell = it.next();
            if (cell.getPiece().getSide().equals(Sides.FIRST)) {
                ++firstSide;
            }
            else if (cell.getPiece().getSide().equals(Sides.SECOND)) {
                ++secondSide;
            }
        }
        if (firstSide > secondSide)
            return Sides.FIRST;
        if (firstSide < secondSide)
            return Sides.SECOND;
        return Sides.NULL;
    }
    */

    @Override
    public int evaluate(final ContextInterface context, final SideInterface currentSide) {
        return this.computeDelta(context, currentSide);
    }

    @Override
    public List<MutationInterface> getLegalMoves(final ContextInterface context, final SideInterface side) {
        //TODO à améliorer
        List<MutationInterface> legalMoves = new ArrayList<MutationInterface>();
        Collection<Set<MutationInterface>> mutations = context.getCellManager().getPotentialMutations(side).values();
        for (Set<MutationInterface> e : mutations)
            legalMoves.addAll(e);
        legalMoves.add(NullMutation.getInstance());
        return legalMoves;
    }

}
