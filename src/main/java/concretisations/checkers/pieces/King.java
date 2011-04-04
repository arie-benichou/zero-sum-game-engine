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

package concretisations.checkers.pieces;

import abstractions.piece.PieceFactory;
import abstractions.position.RelativePositions;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;

public class King extends AbstractCheckerPiece {

    public King(SideInterface side) {
        super(side, ImmutableSet.of(RelativePositions.TOP, RelativePositions.BOTTOM));
    }
    
    /*
    public boolean isPromotable(final CellInterface cell) {
        return false;
    }
    */
    
    @Override
    public String toString() {
        return super.toString().toUpperCase();
    }
    
}