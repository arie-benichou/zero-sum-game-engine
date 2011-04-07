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

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;

// TODO renommer cette classe en AbstractPiece
public abstract class AbstractPiece implements PieceInterface {

    private volatile int hashCode;
    
    private final SideInterface side;

    public AbstractPiece(final SideInterface side) {
        checkNotNull(side, "Argument 'side' is not intended to be null.");
        this.side = side;
    }

    public final SideInterface getSide() {
        return this.side;
    }

    @Override
    public final int hashCode() {
        int result = this.hashCode;
        if (result == 0) {
            result = 17;
            result *= 31;
            result += this.getClass().hashCode();
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
            else if (!that.getClass().equals(this.getClass())) {
                isEqual = false;
            }
            else {
                isEqual = that.getSide().equals(this.getSide());
            }
        }
        return isEqual;
    }

    public abstract Set<? extends MutationInterface> computeAvailableMutations(CellInterface cell, SideInterface side);
    
    public abstract String toString();    

}