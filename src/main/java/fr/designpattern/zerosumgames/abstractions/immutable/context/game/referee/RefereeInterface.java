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

package fr.designpattern.zerosumgames.abstractions.immutable.context.game.referee;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

/**
 * This is the interface for a game referee.
 * 
 * @todo commentaires
 */
public interface RefereeInterface extends ImmutableInterface<RefereeInterface> {

    //Double getHeuristicEvaluation(final ContextInterface context, final SideInterface side);

    //Double getTerminalEvaluation(final ContextInterface context, final SideInterface side);

    //List<MutationInterface> getSortedLegalMoves(ContextInterface context, SideInterface side);

    // TODO l'arbitre devrait prendre en argument uniquement un objet Context
    List<MoveTypeInterface> computePlayableMoves(final BoardInterface board, final SideInterface side);

    // TODO l'arbitre devrait prendre en argument uniquement un objet Context    
    boolean isPlayable(final BoardInterface board, final SideInterface side);

    //boolean isGamePlayOver(final BoardInterface board, final SideInterface side);
    boolean isGamePlayOver(ContextInterface context);

    /*-------------------------------------8<-------------------------------------*/
    // TODO ?? responsabilité du referee
    /*-------------------------------------8<-------------------------------------*/

    Double getTerminalEvaluation(ContextInterface context);

    Double getHeuristicEvaluation(ContextInterface context);

}