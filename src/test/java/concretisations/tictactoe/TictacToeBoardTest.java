
package concretisations.tictactoe;

import static abstractions.dimension.API.DimensionFactory.Dimension;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import abstractions.board.BoardBuilder;
import abstractions.board.BoardInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.Sides;
import concretisations.tictactoe.mutations.NewPawnMutation;
import concretisations.tictactoe.pieces.Pieces;

public class TictacToeBoardTest {

    @Test
    public void testLegalMutations() {

        BoardInterface board = new BoardBuilder(Pieces.class, Dimension(2, 2)).build();
        board.getCell(1, 1).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(1, 2).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(2, 1).setPiece(Sides.FIRST, Pieces.PAWN);
        System.out.println(board);

        List<MutationInterface> expectedLegalMutations = new ArrayList<MutationInterface>();
        
        expectedLegalMutations.add(new NewPawnMutation(board.getCell(2, 2), Sides.FIRST));
        System.out.println(expectedLegalMutations);
        
        List<MutationInterface> legalMutations = board.getLegalMutations(Sides.FIRST);
        System.out.println(legalMutations);

        Assert.assertTrue(legalMutations.equals(expectedLegalMutations));
        
    }

}
