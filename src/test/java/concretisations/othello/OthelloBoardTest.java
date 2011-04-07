
package concretisations.othello;

import static abstractions.dimension.API.DimensionFactory.Dimension;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.*;

import abstractions.board.BoardBuilder;
import abstractions.board.BoardInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.Sides;
import concretisations.othello.mutations.NewPawnMutation;
import concretisations.othello.pieces.Pieces;

public class OthelloBoardTest {

    @Test
    public void testLegalMutations() {

        BoardInterface board = new BoardBuilder(Pieces.class, Dimension(8, 8)).build();

        board.getCell(4, 4).setPiece(Sides.FIRST, Pieces.PAWN);
        board.getCell(4, 5).setPiece(Sides.SECOND, Pieces.PAWN);

        board.getCell(5, 4).setPiece(Sides.SECOND, Pieces.PAWN);
        board.getCell(5, 5).setPiece(Sides.FIRST, Pieces.PAWN);

        Set<MutationInterface> legalMutations = board.getLegalMutations(Sides.FIRST);

        for (MutationInterface mutation : legalMutations) {
            System.out.println(mutation);
        }
        Set<MutationInterface> expectedLegalMutations = new HashSet<MutationInterface>();

        expectedLegalMutations.add(new NewPawnMutation(board.getCell(4, 6), Sides.FIRST));
        expectedLegalMutations.add(new NewPawnMutation(board.getCell(5, 3), Sides.FIRST));
        expectedLegalMutations.add(new NewPawnMutation(board.getCell(6, 4), Sides.FIRST));
        expectedLegalMutations.add(new NewPawnMutation(board.getCell(3, 5), Sides.FIRST));

        assertTrue(legalMutations.equals(expectedLegalMutations));

    }

}
