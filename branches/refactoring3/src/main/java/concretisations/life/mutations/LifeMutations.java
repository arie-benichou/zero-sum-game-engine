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

package concretisations.life.mutations;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.BasicMutationFactory;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.MutationTypeInterface;
import abstractions.piece.PieceInterface;

// TODO ? do the same for pieces
// TODO ! typer les mutations (getType())
public enum LifeMutations implements MutationTypeInterface {

    BIRTH {

        public MutationInterface operation() {
            return BasicMutationFactory.newBirth(this.getCell(), this.getPiece());
        }

    },

    DEATH {

        public MutationInterface operation() {
            return BasicMutationFactory.newDeath(this.getCell());
        }
    };

    private final ManagedCellInterface cell;
    private final PieceInterface piece;

    private LifeMutations(final ManagedCellInterface cell) {
        this.cell = cell;
    }

    private LifeMutations(final ManagedCellInterface cell, final PieceInterface piece) {
        this.cell = cell;
        this.piece = piece;
    }

    public ManagedCellInterface getCell() {
        return this.cell;
    }

    public PieceInterface getPiece() {
        return this.piece;
    }

}
