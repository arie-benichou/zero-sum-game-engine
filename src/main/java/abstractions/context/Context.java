
package abstractions.context;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import abstractions.adversity.AdversityInterface;
import abstractions.cell.CellManagerInterface;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.NullMutation;
import abstractions.player.PlayerInterface;
import abstractions.referee.RefereeInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;

import com.google.common.collect.Maps;

public class Context implements ContextInterface {

    //-----------------------------------------------------------------    

    private final CellManagerInterface cellManager;
    private final RefereeInterface referee;
    private final AdversityInterface adversity;

    private final Map<SideInterface, Stack<MutationInterface>> moves = Maps.newHashMap();

    private transient SideInterface currentSide = Sides.SECOND;

    //-----------------------------------------------------------------    

    public Context(final AdversityInterface adversity, final CellManagerInterface cellManager, final RefereeInterface referee) {

        this.adversity = adversity;
        this.cellManager = cellManager;
        this.referee = referee;

        this.moves.put(Sides.FIRST, new Stack<MutationInterface>());
        this.moves.get(Sides.FIRST).add(NullMutation.getInstance());

        this.moves.put(Sides.SECOND, new Stack<MutationInterface>());
        this.moves.get(Sides.SECOND).add(NullMutation.getInstance());

    }

    //-----------------------------------------------------------------

    private final AdversityInterface getAdversity() {
        return this.adversity;
    }

    private final RefereeInterface getReferee() {
        return this.referee;
    }

    //-----------------------------------------------------------------

    @Override
    public final CellManagerInterface getCellManager() {
        return this.cellManager;
    }

    //-----------------------------------------------------------------

    @Override
    public final void setCurrentSide(final SideInterface side) {
        this.currentSide = side;
    }

    @Override
    public final SideInterface getCurrentSide() {
        return this.currentSide;
    }

    //-----------------------------------------------------------------    

    @Override
    public final PlayerInterface getCurrentPlayer() {
        return this.getAdversity().getOpponent(this.getCurrentSide());
    }

    //-----------------------------------------------------------------    

    @Override
    public final boolean isGameOver() {
        return this.getReferee().isGameOver(this);
    }

    //-----------------------------------------------------------------    

    @Override
    public int getHeuristicEvaluation(final SideInterface side) {
        return this.getReferee().getHeuristicEvaluation(this, side);
    }

    //-----------------------------------------------------------------    

    @Override
    public int getTerminalEvaluation(final SideInterface side) {
        return this.getReferee().getTerminalEvaluation(this, side);
    }

    //-----------------------------------------------------------------    

    @Override
    public List<MutationInterface> getLegalMoves(final SideInterface side) {
        return this.getReferee().getLegalMoves(this, side);
    }

    @Override
    public final List<MutationInterface> getLegalMoves() {
        return this.getLegalMoves(this.getCurrentSide());
    }

    //-----------------------------------------------------------------    

    @Override
    public void applyMove(final MutationInterface move, final SideInterface side) {
        this.moves.get(side).add(move.process());
    }

    @Override
    public final void applyMove(final MutationInterface move) {
        this.applyMove(move, this.getCurrentSide());
    }

    //-----------------------------------------------------------------    

    @Override
    public final MutationInterface getLastMove(final SideInterface side) {
        return this.moves.get(side).lastElement();
    }

    @Override
    public final MutationInterface getLastPlayedMove() {
        return this.getLastMove(this.getCurrentSide().getNextSide());
    }

    //-----------------------------------------------------------------    

    @Override
    public void unapplyLastPlayedMove(final SideInterface side) {
        this.moves.get(side).pop().cancel();
    }

    @Override
    public final void unapplyLastPlayedMove() {
        this.unapplyLastPlayedMove(this.getCurrentSide());
    }

    //-----------------------------------------------------------------    

    @Override
    public final String toString() {
        return this.cellManager.toString();
    }

    //-----------------------------------------------------------------    

}
