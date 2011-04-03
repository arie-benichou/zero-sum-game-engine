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

import static abstractions.side.API.*;

import java.util.Set;

import abstractions.cell.API.CellInterface;
import abstractions.cell.mutation.MutationInterface;
import abstractions.side.API.SideInterface;

/**
 * API related to pieces.
 */
public class API {

    /**
     * The null object for a piece.
     */
    public final static NullPiece NULL_PIECE = new NullPiece();

    /**
     * Class for illegal piece exceptions.
     */
    public static final class IllegalPieceException extends RuntimeException {

        private static final String MESSAGE = "Piece(side=%d) is not a legal piece.";

        private static final long serialVersionUID = 1L;

        private SideInterface side;

        public IllegalPieceException(final SideInterface side) {
            super();
            this.side = side;
        }

        @Override
        public String getMessage() {
            return String.format(MESSAGE, this.side);
        }

    }

    /**
     * This is the interface for a piece.
     */
    public interface PieceInterface {

        /**
         * Returns the side related to this piece.
         * 
         * @return the side related to this piece
         */
        SideInterface getSide();

        /**
         * Returns true if this piece is the null object, false otherwise.
         * 
         * @return true if this piece is the null object, false otherwise
         */
        boolean isNull();
        
        //Set<MutationInterface> computeAvailableMutations(final CellInterface cell);
        Set<MutationInterface> computeAvailableMutations(final CellInterface cell, SideInterface side);

    }

    /**
     * The piece factory.
     */
    //public static final class PieceFactory {

        /**
         * Returns the null piece.
         * 
         * @return the null piece
         */
        /*
        public static PieceInterface NullPiece() {
            return NULL_PIECE;
        }
        */
        /**
         * Returns a new instance of a piece for a given side.
         * 
         * @param side
         *            a given side
         * 
         * @return a new instance of a piece for a given side
         */
        /*
        public static PieceInterface Piece(SideInterface side) {
            try {
                return new AbstractPiece(side);
            }
            catch (NullPointerException e) {
                throw new IllegalPieceException(side);
            }            
            catch (IllegalArgumentException e) {
                throw new IllegalPieceException(side);
            }
        }
        */
    //}

}
