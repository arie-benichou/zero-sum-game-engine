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

// TODO ? MutationManager(CellManager)
// TODO mettre les mutations atomiques au niveau de la cellule
public class AtomicMutationFactory {

    public static MutationInterface newBirth(final ManagedCellInterface cell, final SideInterface side, final PieceTypeInterface pieceType) {
        return new Birth(cell, AtomicMutationTypes.BIRTH, pieceType, side);
    }

    public static MutationInterface newDeath(final ManagedCellInterface cell) {
        return new Death(cell, AtomicMutationTypes.DEATH);
    }

    public static MutationInterface newAlteration(final ManagedCellInterface cell, final SideInterface side, final PieceTypeInterface pieceType) {
        return new Alteration(cell, AtomicMutationTypes.ALTERATION, pieceType, side);
    }
}
