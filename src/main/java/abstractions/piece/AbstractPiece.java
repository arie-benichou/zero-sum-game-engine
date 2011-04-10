/*
 * Copyright (C) 2011 Arié Bénichou
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
import abstractions.mutation.MutationTypeInterface;
import abstractions.side.SideInterface;

import com.google.common.base.Preconditions;

// TODO renommer cette classe en AbstractPiece
public abstract class AbstractPiece implements PieceInterface {

    private volatile int hashCode;

    private final SideInterface side;
    private final PieceTypeInterface type;

    public AbstractPiece(final SideInterface side, final PieceTypeInterface type) {
        Preconditions.checkNotNull(side, "Argument 'side' is not intended to be null.");
        this.side = side;
        this.type = type;
    }

    @Override
    public final SideInterface getSide() {
        return this.side;
    }

    @Override
    public final PieceTypeInterface getType() {
        return this.type;
    }

    @Override
    public final int hashCode() {
        int result = this.hashCode;
        if (result == 0) {
            result = 17;
            result *= 31;
            result += this.getType().hashCode();
            result *= 31;
            result += this.getSide().hashCode();
            this.hashCode = result;
        }
        return result;
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
        else if (!(object instanceof PieceInterface)) {
            isEqual = false;
        }
        else {
            final PieceInterface that = (PieceInterface) object;
            if (that.hashCode() != this.hashCode()) {
                isEqual = false;
            }
            else {
                isEqual = that.getType().equals(this.getType()) && that.getSide().equals(this.getSide());
            }
        }
        return isEqual;
    }

    @Override
    public String toString() {
        return this.getType() + " " + this.getSide();
    }

    @Override
    public abstract Set<? extends MutationTypeInterface> computePotentialMutationTypes(ManagedCellInterface cell, SideInterface side);

}