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

package abstractions.immutable.context;


public interface ContextInterface<T> {

    ///PlayerInterface getCurrentPlayer();

    //List<MutationInterface> getLegalMoves(final SideInterface side);

    //List<MutationInterface> getLegalMoves();

    //MutationInterface applyMove(final MutationInterface move);

    //MutationInterface getLastPlayedMove();

    //MutationInterface unapplyLastPlayedMove(SideInterface side);

    //MutationInterface unapplyLastPlayedMove();

    //CellManagerInterface getCellManager();

    ///AdversityInterface getAdversity();

    //MutationInterface onApplyMove(final MutationInterface move);

    //MutationInterface onUnapplyMove(final MutationInterface move);

    //ContextInterface duplicate();

    //RefereeInterface getReferee();

    //T getLastOption(final SideInterface side);    

    //Double getHeuristicEvaluation(final SideInterface side);

    //Double getTerminalEvaluation(final SideInterface side);

    //Map<SideInterface, Stack<T>> getOptionsHistory();

    //SideInterface setCurrentSide(final SideInterface side);

    //boolean isOver();

    //SideInterface getSide();

    ContextInterface<T> apply(T option);

    //List<T> getOptions();

}