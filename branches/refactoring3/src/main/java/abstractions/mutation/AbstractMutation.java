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

public abstract class AbstractMutation implements MutationInterface {

    private final ManagedCellInterface cell;
    private final PieceInterface savedSate;
    private final MutationTypeInterface type;

    public AbstractMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType) {
        this.type = mutationType;
        this.cell = cell;
        this.savedSate = cell.getPiece();
    }

    public final ManagedCellInterface getCell() {
        return this.cell;
    }

    public final MutationTypeInterface getType() {
        return this.type;
    }

    public final PieceInterface getSavedSate() {
        return this.savedSate;
    }

    @Override
    public final String toString() {
        return this.getType() + " " + this.getCell().getPosition() + " ";
    }

    public abstract void process();

    public abstract void cancel();

}
