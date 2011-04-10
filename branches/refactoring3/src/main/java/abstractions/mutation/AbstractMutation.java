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
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

public abstract class AbstractMutation implements MutationInterface {

    private final ManagedCellInterface concernedCell;
    private final SideInterface side;
    private final PieceTypeInterface pieceType;
    private final PieceInterface savedSate;

    // TODO virer le pieceType
    // TODO coder les mutations de bases dans la cellule
    public AbstractMutation(final ManagedCellInterface cell, final SideInterface side, final PieceTypeInterface pieceType) {
        this.concernedCell = cell;
        this.side = side;
        this.pieceType = pieceType;
        this.savedSate = cell.getPiece();
    }

    @Override
    public final ManagedCellInterface getCell() {
        return this.concernedCell;
    }

    @Override
    public final SideInterface getSide() {
        return this.side;
    }

    @Override
    public final PieceTypeInterface getPieceType() {
        return this.pieceType;
    }

    @Override
    public final PieceInterface getSavedSate() {
        return this.savedSate;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.getSide() + " " + this.getCell().getPosition() + " " + this.getPieceType();
    }

    @Override
    public abstract void process();

    @Override
    public abstract void cancel();

}
