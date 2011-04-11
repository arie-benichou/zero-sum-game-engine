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

package abstractions.mutation;

import abstractions.cell.ManagedCellInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

class Birth extends AbstractAtomicMutation {

    private final SideInterface side;
    private final PieceTypeInterface pieceType;

    public Birth(final ManagedCellInterface cell, final MutationTypeInterface mutationType, final PieceTypeInterface pieceType, final SideInterface side) {
        super(cell, mutationType);
        this.side = side;
        this.pieceType = pieceType;
    }

    public SideInterface getSide() {
        return this.side;
    }

    public PieceTypeInterface getPieceType() {
        return this.pieceType;
    }

    @Override
    public void process() {
        this.getCell().setPiece(this.getSide(), this.getPieceType());
    }

}
