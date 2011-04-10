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
import abstractions.mutation.MutationInterface;
import abstractions.mutation.MutationTypeInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

// TODO ? do the same for pieces
public enum LifeMutations implements MutationTypeInterface {

    BIRTH_PAWN {

        @Override
        public MutationInterface operation(final ManagedCellInterface cell, final SideInterface side, final PieceTypeInterface pieceType) {
            // TODO ? utiliser la Birth mutation
            return new BirthPawnMutation(cell, side, pieceType);
        }
    },

    DEATH_PAWN {

        @Override
        public MutationInterface operation(final ManagedCellInterface cell, final SideInterface side, final PieceTypeInterface pieceType) {
            // TODO ? utiliser la Death mutation
            return new DeathPawnMutation(cell, side, pieceType);
        }
    };

}
