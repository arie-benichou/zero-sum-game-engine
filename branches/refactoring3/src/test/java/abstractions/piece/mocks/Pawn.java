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

package abstractions.piece.mocks;

import java.util.Set;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.piece.AbstractPiece;
import abstractions.piece.PieceActionTypeInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

public final class Pawn extends AbstractPiece {

    public Pawn(SideInterface side, PieceTypeInterface type) {
        super(side, type);
    }
    
    @Override
    public Set<? extends PieceActionTypeInterface> computePotentialActionTypes(CellInterface cell, SideInterface side) {
        // TODO Auto-generated method stub
        return null;
    }    

    @Override
    public String toString() {
        return this.getType() + " " + this.getSide();
    }

}