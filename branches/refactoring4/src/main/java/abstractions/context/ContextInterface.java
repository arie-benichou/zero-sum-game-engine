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