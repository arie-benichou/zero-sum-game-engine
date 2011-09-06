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

import abstractions.context.ContextInterface;
import abstractions.piece.PieceInterface;
import abstractions.position.PositionInterface;
import annotations.Immutable;

@Immutable
public class NullMutation implements MutationInterface {

    private static MutationInterface INSTANCE = new NullMutation();

    public static MutationInterface getInstance() {
        return NullMutation.INSTANCE;
    }

    private NullMutation() {}

    @Override
    public MutationInterface process(final ContextInterface context) {
        return this;
    }

    @Override
    public PositionInterface getPosition() {
        return null; // TODO Null Cell
    }

    @Override
    public MutationTypeInterface getType() {
        return null; // TODO Mutation Type
    }

    @Override
    public PieceInterface getSavedSate() {
        return null; // TODO Null Piece
    }

    @Override
    public MutationInterface cancel(final ContextInterface context) {
        return this;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int compareTo(final MutationInterface o) {
        return 0;
    }

    @Override
    public void computeSequence(final ContextInterface context) {}

}
