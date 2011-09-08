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

import java.util.Set;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;

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
     * Returns the type related to this piece.
     * 
     * @return the type related to this piece
     */
    PieceTypeInterface getType();

    /**
     * Returns the potential cell mutations induced by the piece (see a piece as
     * a kind of cell kernel).
     * 
     * @param cell
     *            the cell concerned by these mutations.
     * 
     * @param side
     *            the side to play
     * 
     * @return the potential cell mutations induced by the piece (see a piece as
     *         a kind of cell kernel)
     */
    Set<MutationInterface> computePotentialMutations(final ManagedCellInterface cell, SideInterface side);

    // TODO ? add method isNull() to a piece interface    

}