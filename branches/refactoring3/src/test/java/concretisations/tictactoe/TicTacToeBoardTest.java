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

package concretisations.tictactoe;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import abstractions.board.BoardBuilder;
import abstractions.board.BoardInterface;
import abstractions.dimension.API.DimensionFactory;
import abstractions.mutation.MutationInterface;
import abstractions.side.Sides;
import concretisations.tictactoe.mutations.NewPawnMutation;
import concretisations.tictactoe.pieces.Pieces;

public class TicTacToeBoardTest {

    @Test
    public void testLegalMutations() {

        final BoardInterface board = new BoardBuilder(Pieces.class, DimensionFactory.Dimension(2, 2)).build();
        board.getCell(1, 1).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(1, 2).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(2, 1).setPiece(Sides.FIRST, Pieces.PAWN);

        final Set<MutationInterface> expectedLegalMutations = new HashSet<MutationInterface>();
        expectedLegalMutations.add(new NewPawnMutation(board.getCell(2, 2), Sides.FIRST));

        final Set<MutationInterface> legalMutations = board.getLegalMutations(Sides.FIRST);

        Assert.assertTrue(legalMutations.equals(expectedLegalMutations));

    }

}
