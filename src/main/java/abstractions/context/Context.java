
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

    @Override
    public final List<MutationInterface> getLegalMovesForCurrentPlayer() {
        return this.getReferee().getLegalMoves(this, this.getCurrentSide());
    }

    @Override
    public final boolean isGameOver() {
        return this.getReferee().isGameOver(this);
    }

    @Override
    public final void applyMoveForCurrentPlayer(MutationInterface move) {
        this.moves.get(this.getCurrentSide()).add(move.process());
    }
    
    @Override
    public final MutationInterface getLastMoveFromSide(SideInterface side) {
        return this.moves.get(side).lastElement();
    }    

    @Override
    public final MutationInterface getLastPlayedMove() {
        return this.getLastMoveFromSide(this.getCurrentSide().getNextSide());
    }

    @Override
    public final void undoLastPlayedMoveForCurrentPLayer() {
        this.getLastPlayedMove().cancel();
    }

    //-----------------------------------------------------------------    

    @Override
    public final String toString() {
        //return this.adversity.toString() + "\n" + this.cellManager.toString() + "\n" + "side to play : " + this.sideToPlay;
        //return this.cellManager + "\n" + "side to play : " + this.currentSide;
        return this.cellManager.toString();
    }

    //-----------------------------------------------------------------    

}
