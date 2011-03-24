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

package fr.designpattern.zerosumgames.samples.checkers;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMove;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

// TODO ? implémenter une interface
public class CheckersMove extends LegalMove {

    // ---------------------------------------------------------------------
    private BoardCardinalPosition direction;

    private final void setDirection(final BoardCardinalPosition direction) {
        this.direction = direction;
    }

    public final BoardCardinalPosition getDirection() {
        return this.direction;
    }

    // ---------------------------------------------------------------------
    private PieceInterface capturedPiece;

    public final void setCapturedPiece(final PieceInterface capturedPiece) {
        this.capturedPiece = capturedPiece;
    }

    public final PieceInterface getCapturedPiece() {
        return this.capturedPiece;
    }

    // ---------------------------------------------------------------------
    private boolean isDone;

    public boolean isDone() {
        return this.isDone;
    }

    public void isDone(final boolean isDone) {
        this.isDone = isDone;
    }

    // ---------------------------------------------------------------------
    private boolean hasBeenCrowned;

    public boolean hasBeenCrowned() {
        return this.hasBeenCrowned;
    }

    public void hasBeenCrowned(final boolean hasBeenCrowned) {
        this.hasBeenCrowned = hasBeenCrowned;
    }

    // ---------------------------------------------------------------------
    public CheckersMove(final OpponentsEnumeration side,
            final PositionInterface position,
            final BoardCardinalPosition direction) {
        super(side, position);
        this.setDirection(direction);
    }

    // ---------------------------------------------------------------------
    @Override
    public final String toString() {
        return super.toString() + " " + this.getDirection();
    }
    // ---------------------------------------------------------------------
}