/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package abstractions.context;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import abstractions.adversity.AdversityInterface;
import abstractions.cell.CellManagerInterface;
import abstractions.gameplay.GamePlayInterface;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.NullMutation;
import abstractions.player.PlayerInterface;
import abstractions.referee.RefereeInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;

import com.google.common.collect.Maps;

// TODO refactoring possible GamePlay = Context
public class Context implements ContextInterface {

    //-----------------------------------------------------------------    

    private final CellManagerInterface cellManager;
    private final RefereeInterface referee;
    private final AdversityInterface adversity;

    private final Map<SideInterface, Stack<MutationInterface>> moves = Maps.newHashMap();

    private transient SideInterface currentSide = Sides.SECOND;

    //-----------------------------------------------------------------    

    public Context(final GamePlayInterface gamePlay) {

        this.cellManager = gamePlay.getGame().getCellManager();
        this.referee = gamePlay.getGame().getReferee();
        this.adversity = gamePlay.getAdversity();

        this.moves.put(Sides.FIRST, new Stack<MutationInterface>());
        this.moves.get(Sides.FIRST).add(NullMutation.getInstance());

        this.moves.put(Sides.SECOND, new Stack<MutationInterface>());
        this.moves.get(Sides.SECOND).add(NullMutation.getInstance());

    }

    //-----------------------------------------------------------------

    @Override
    public final AdversityInterface getAdversity() {
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
        return this.getAdversity().getPlayer(this.getCurrentSide());
    }

    //-----------------------------------------------------------------    

    @Override
    public final boolean isGameOver() {
        return this.getReferee().isGameOver(this);
    }

    //-----------------------------------------------------------------    

    @Override
    public Double getHeuristicEvaluation(final SideInterface side) {
        return this.getReferee().getHeuristicEvaluation(this, side);
    }

    //-----------------------------------------------------------------    

    @Override
    public Double getTerminalEvaluation(final SideInterface side) {
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

    @Override
    public List<MutationInterface> getSortedLegalMoves(final SideInterface side) {
        return this.getReferee().getSortedLegalMoves(this, side);
    }

    //-----------------------------------------------------------------    

    @Override
    public void applyMove(final MutationInterface move, final SideInterface side) {
        this.onApplyMove(this.moves.get(side).push(move.process(this)));
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
        this.onUnapplyMove(this.moves.get(side).pop().cancel(this));
    }

    @Override
    public final void unapplyLastPlayedMove() {
        this.unapplyLastPlayedMove(this.getCurrentSide());
    }

    //-----------------------------------------------------------------    

    @Override
    public void onApplyMove(final MutationInterface move) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUnapplyMove(final MutationInterface move) {
        // TODO Auto-generated method stub

    }

    //-----------------------------------------------------------------

    @Override
    public String toString() {
        return this.cellManager.toString();
    }

    //-----------------------------------------------------------------    

}
