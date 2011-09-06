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
import abstractions.context.ContextInterface;
import abstractions.piece.PieceInterface;
import abstractions.position.PositionInterface;

// TODO equals et hashcode ?
public abstract class AbstractMutation implements MutationInterface {

    private final PositionInterface position;
    private final PieceInterface savedSate;
    private final MutationTypeInterface type;

    public AbstractMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType) {
        this.type = mutationType;
        this.position = cell.getPosition();
        this.savedSate = cell.getPiece();
    }

    @Override
    public final PositionInterface getPosition() {
        return this.position;
    }

    @Override
    public final MutationTypeInterface getType() {
        return this.type;
    }

    @Override
    public final PieceInterface getSavedSate() {
        return this.savedSate;
    }

    @Override
    public final String toString() {
        return this.getType() + " " + this.getPosition();
    }

    @Override
    public void computeSequence(final ContextInterface context) {} // TODO refactoring avec AbstractCompositeMutation

    @Override
    public abstract MutationInterface process(final ContextInterface context);

    @Override
    public abstract MutationInterface cancel(final ContextInterface context);

    @Override
    public abstract int compareTo(MutationInterface o);

    @Override
    public boolean isNull() {
        return false;
    }

}
