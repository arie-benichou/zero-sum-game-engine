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

public class NullMutation implements MutationInterface {

    private static MutationInterface INSTANCE = new NullMutation();

    public static MutationInterface getInstance() {
        return INSTANCE;
    }

    private NullMutation() {}

    @Override
    public void process() {}

    @Override
    public ManagedCellInterface getCell() {
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
    public void cancel() {}

}
