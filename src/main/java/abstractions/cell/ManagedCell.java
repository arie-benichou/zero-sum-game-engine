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

package abstractions.cell;

import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
import abstractions.position.PositionManager.Direction;
import abstractions.side.SideInterface;

import com.google.common.base.Preconditions;

public class ManagedCell implements ManagedCellInterface {

    private final PositionInterface position;
    private final CellManagerInterface cellManager;

    private transient PieceInterface piece;

    public ManagedCell(final CellManagerInterface cellManager, final PositionInterface position) {

        this.cellManager = cellManager;
        this.position = position;

        this.piece = this.cellManager.getNullPiece();
    }

    @Override
    public PositionInterface getPosition() {
        return this.position;
    }

    @Override
    public int getRow() {
        return this.position.getRow();
    }

    @Override
    public int getColumn() {
        return this.position.getColumn();
    }

    @Override
    public PieceInterface getPiece() {
        return this.piece;
    }

    @Override
    public boolean isNull() {
        return this.position.isNull();
    }

    @Override
    public ManagedCellInterface setPiece(final PieceInterface piece) {
        if (this.isNull()) {
            throw new NullPointerException("This cell is null.");
        }
        this.piece = piece;
        return this;
    }

    @Override
    public ManagedCellInterface setPiece(final SideInterface side, final PieceTypeInterface pieceType) {
        return this.setPiece(this.cellManager.piece(side, pieceType));
    }

    @Override
    public boolean isEmpty() {
        return this.isNull() ? false : this.piece.getSide().isNull();
    }

    @Override
    public ManagedCellInterface getRelative(final Direction direction) {
        return this.isNull() ? this : this.cellManager.getCell(this.cellManager.position(this.position, direction));
    }

    @Override
    public int compareTo(final ManagedCellInterface that) {
        Preconditions.checkNotNull(that, "That argument is not intended to be null.");
        return this.position.compareTo(that.getPosition());
    }

    @Override
    public int hashCode() {
        return this.position.hashCode();
    }

    @Override
    public final boolean equals(final Object object) {
        final boolean isEqual;
        if (object == this) {
            isEqual = true;
        }
        else if (object == null) {
            isEqual = false;
        }
        else if (!(object instanceof ManagedCellInterface)) {
            isEqual = false;
        }
        else {
            final ManagedCellInterface that = (ManagedCellInterface) object;
            isEqual = that.isNull() == this.isNull() && that.getPiece().equals(this.getPiece());
        }
        return isEqual;
    }

    // TODO create class PieceRenderer(PieceInterface pieceToRender)
    private String pieceRenderer(final PieceInterface pieceToRender) {
        String consoleView = "";
        if (pieceToRender.getSide().isFirstSide()) {
            consoleView = "x";
        }
        else if (pieceToRender.getSide().isSecondSide()) {
            consoleView = "o";
        }
        else {
            consoleView = " ";
        }
        return consoleView;
    }

    // TODO create class CellRenderer(ManagedCellInterface cellToRender)
    private String cellRenderer() {
        String consoleView = "";
        if (!this.isNull()) {
            consoleView = " " + this.pieceRenderer(this.getPiece()) + " |";
        }
        return consoleView;
    }

    // TODO use only on trace purpose
    @Override
    public String toString() {
        return this.cellRenderer();
    }

}
