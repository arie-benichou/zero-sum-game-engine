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

package abstractions.piece;

import abstractions.side.SideInterface;

/**
 * Class for illegal pieces.
 */
public final class IllegalPieceException extends RuntimeException {

    private static final String MESSAGE = "Piece(side=%s, type=%s) is not a legal piece.";

    private static final long serialVersionUID = 1L;

    private final SideInterface side;
    private final PieceTypeInterface pieceType;

    public IllegalPieceException(final SideInterface side, final PieceTypeInterface pieceType) {
        super();
        this.side = side;
        this.pieceType = pieceType;
    }

    @Override
    public String getMessage() {
        return String.format(IllegalPieceException.MESSAGE, this.side, this.pieceType);
    }

}