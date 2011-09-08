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

import java.util.List;
import java.util.Map;
import java.util.Set;

import abstractions.direction.DirectionInterface;
import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
import abstractions.side.SideInterface;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public final class ManagedCell implements ManagedCellInterface {

    private final PositionInterface position;
    private final CellManagerInterface cellManager;

    private transient PieceInterface piece;
    private transient Map<DirectionInterface, ManagedCellInterface> neighbourhood;

    public ManagedCell(final CellManagerInterface cellManager, final PositionInterface position) {

        this.cellManager = cellManager;
        this.position = position;

        this.piece = this.cellManager.getNullPiece();
    }

    public PositionInterface getPosition() {
        return this.position;
    }

    public int getRow() {
        return this.position.getRow();
    }

    public int getColumn() {
        return this.position.getColumn();
    }

    public PieceInterface getPiece() {
        return this.piece;
    }

    public boolean isNull() {
        return this.position.isNull();
    }

    public ManagedCellInterface setPiece(final PieceInterface piece) {
        if (this.isNull()) {
            throw new NullPointerException("This cell is null."); // NOPMD
        }
        this.piece = piece;
        return this;
    }

    public ManagedCellInterface setPiece(final SideInterface side, final PieceTypeInterface pieceType) {
        return this.setPiece(this.cellManager.piece(side, pieceType));
    }

    public boolean isEmpty() {
        return this.isNull() ? false : this.piece.getSide().isNull();
    }

    public int compareTo(final ManagedCellInterface that) {
        Preconditions.checkNotNull(that, "That argument is not intended to be null.");
        return this.position.compareTo(that.getPosition());
    }

    @Override
    public int hashCode() { // TODO à revoir
        return this.position.hashCode();
    }

    @Override
    public boolean equals(final Object object) { // TODO à revoir
        final boolean isEqual; // NOPMD 
        if (object == this) {
            isEqual = true;
        }
        else if (object == null) {
            isEqual = false;
        }
        else if (!(object instanceof ManagedCellInterface)) { // NOPMD 
            isEqual = false;
        }
        else {
            final ManagedCellInterface that = (ManagedCellInterface) object;

            //System.out.println(this);
            //System.out.println(that);

            isEqual = that.isNull() == this.isNull() && that.getPiece().equals(this.getPiece());
            System.out.println(isEqual);
        }
        return isEqual;
    }

    // TODO create class CellRenderer(ManagedCellInterface cellToRender)
    public String render() {
        String consoleView = "";
        if (!this.isNull()) {
            consoleView = " " + this.getPiece().toString() + " |";
        }
        return consoleView;
    }

    // use only on trace purpose
    @Override
    public String toString() {
        return this.getPosition().toString() + " " + this.getPiece().getSide() + " " + this.getPiece().getType();
    }

    public Set<MutationInterface> getPotentialMutations(final SideInterface side) {
        return this.getPiece().computePotentialMutations(this, side);
    }

    public ManagedCellInterface die() {
        this.setPiece(this.cellManager.getNullPiece());
        return this;
    }

    public Map<DirectionInterface, ManagedCellInterface> getNeighbourhood() {
        if (this.neighbourhood == null) {
            final Builder<DirectionInterface, ManagedCellInterface> neighbourhoodMapBuilder = ImmutableMap.builder();
            for (final NamedDirection namedDirection : this.getNamedDirections()) {
                final ManagedCellInterface neighbourCell = this.isNull() ?
                        this : this.cellManager.getCell(this.cellManager.position(this.position, namedDirection.value()));
                neighbourhoodMapBuilder.put(namedDirection.value(), neighbourCell);
            }
            this.neighbourhood = neighbourhoodMapBuilder.build();
        }
        return this.neighbourhood;
    }

    //TODO ? parer le risque de NullPointerException en renvoyant une exception si la direction ne correspond pas à une position cardinale
    //TODO permettre le rerverse lorsque possible entre une direction nommée et une direction quelquonque
    public ManagedCellInterface getNeighbour(final DirectionInterface direction) {
        return this.getNeighbourhood().get(direction);
    }

    public ManagedCellInterface getNeighbour(final NamedDirection namedDirection) {
        return this.getNeighbour(namedDirection.value());
    }

    public List<NamedDirection> getNamedDirections() {
        return this.cellManager.getNamedDirections();
    }

}
