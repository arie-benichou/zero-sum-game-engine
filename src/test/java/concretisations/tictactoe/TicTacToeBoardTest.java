
package concretisations.tictactoe;

import static abstractions.dimension.API.DimensionFactory.Dimension;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import abstractions.board.BoardBuilder;
import abstractions.board.BoardInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.Sides;
import concretisations.tictactoe.mutations.NewPawnMutation;
import concretisations.tictactoe.pieces.Pieces;

public class TicTacToeBoardTest {

    @Test
    public void testLegalMutations() {

        BoardInterface board = new BoardBuilder(Pieces.class, Dimension(2, 2)).build();
        board.getCell(1, 1).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(1, 2).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(2, 1).setPiece(Sides.FIRST, Pieces.PAWN);

        Set<MutationInterface> expectedLegalMutations = new HashSet<MutationInterface>();
        expectedLegalMutations.add(new NewPawnMutation(board.getCell(2, 2), Sides.FIRST));
        
        Set<MutationInterface> legalMutations = board.getLegalMutations(Sides.FIRST);
        
        Assert.assertTrue(legalMutations.equals(expectedLegalMutations));
        
    }

}
