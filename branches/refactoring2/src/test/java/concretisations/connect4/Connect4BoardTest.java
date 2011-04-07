
package concretisations.connect4;

import static abstractions.dimension.API.DimensionFactory.Dimension;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import abstractions.board.BoardBuilder;
import abstractions.board.BoardInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.Sides;
import concretisations.connect4.mutations.NewPawnMutation;
import concretisations.connect4.pieces.Pieces;

public class Connect4BoardTest {

    @Test
    public void testLegalMutations() {

        BoardInterface board = new BoardBuilder(Pieces.class, Dimension(5, 6)).build();
        
        board.getCell(5, 2).setPiece(Sides.FIRST, Pieces.PAWN);

        board.getCell(4, 3).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(5, 3).setPiece(Sides.FIRST, Pieces.PAWN);

        board.getCell(3, 4).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(4, 4).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(5, 4).setPiece(Sides.FIRST, Pieces.PAWN);

        board.getCell(2, 5).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(3, 5).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(4, 5).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(5, 5).setPiece(Sides.FIRST, Pieces.PAWN);

        board.getCell(1, 6).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(2, 6).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(3, 6).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(4, 6).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(5, 6).setPiece(Sides.FIRST, Pieces.PAWN);
        
        Set<MutationInterface> legalMutations = board.getLegalMutations(Sides.FIRST);
        
        Set<MutationInterface> expectedLegalMutations = new HashSet<MutationInterface>();
        expectedLegalMutations.add(new NewPawnMutation(board.getCell(5, 1), Sides.FIRST));
        expectedLegalMutations.add(new NewPawnMutation(board.getCell(4, 2), Sides.FIRST));
        expectedLegalMutations.add(new NewPawnMutation(board.getCell(3, 3), Sides.FIRST));
        expectedLegalMutations.add(new NewPawnMutation(board.getCell(2, 4), Sides.FIRST));
        expectedLegalMutations.add(new NewPawnMutation(board.getCell(1, 5), Sides.FIRST));        
        
        Assert.assertTrue(legalMutations.equals(expectedLegalMutations));
        
    }

}
