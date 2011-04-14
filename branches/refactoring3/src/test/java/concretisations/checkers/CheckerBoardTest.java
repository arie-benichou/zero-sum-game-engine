
package concretisations.checkers;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import abstractions.board.BoardBuilder;
import abstractions.board.BoardInterface;
import abstractions.dimension.DimensionFactory.DimensionFactory;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;
import concretisations.checkers.mutations.JumpMutation;
import concretisations.checkers.mutations.WalkMutation;
import concretisations.checkers.pieces.CheckersPieceSet;

public class CheckerBoardTest {

    /*
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   | o |   |   |
    ---------------------
    | x |   |   |(x)|   |
    ---------------------
    |   |   |   |   |   |
    ---------------------    
    */
    @Test
    public void testJumpMutationPriority() {

        final BoardInterface board = new BoardBuilder(CheckersPieceSet.class, DimensionFactory.Dimension(5, 5)).build();
        final SideInterface side = Sides.FIRST;

        board.getCell(4, 1).setPiece(side, CheckersPieceSet.MAN);
        board.getCell(4, 4).setPiece(side, CheckersPieceSet.MAN);
        board.getCell(3, 3).setPiece(side.getNextSide(), CheckersPieceSet.MAN);

        final Set<MutationInterface> legalMutations = board.getLegalMutations(side);

        final Set<MutationInterface> expectedLegalMutations = new HashSet<MutationInterface>();
        expectedLegalMutations.add(new JumpMutation(board.getCell(4, 4), Sides.FIRST, RelativePositions.TOP_LEFT));

        Assert.assertTrue(legalMutations.equals(expectedLegalMutations));

    }

    /*
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |(x)|   |   |(x)|   |
    ---------------------
    |   |   |   |   |   |
    ---------------------    
    */
    @Test
    public void testManWalkMutation() {

        final BoardInterface board = new BoardBuilder(CheckersPieceSet.class, DimensionFactory.Dimension(5, 5)).build();
        final SideInterface side = Sides.FIRST;

        board.getCell(4, 1).setPiece(side, CheckersPieceSet.MAN);
        board.getCell(4, 4).setPiece(side, CheckersPieceSet.MAN);

        final Set<MutationInterface> legalMutations = board.getLegalMutations(side);

        final Set<MutationInterface> expectedLegalMutations = new HashSet<MutationInterface>();
        expectedLegalMutations.add(new WalkMutation(board.getCell(4, 1), Sides.FIRST, RelativePositions.TOP_RIGHT));
        expectedLegalMutations.add(new WalkMutation(board.getCell(4, 4), Sides.FIRST, RelativePositions.TOP_LEFT));
        expectedLegalMutations.add(new WalkMutation(board.getCell(4, 4), Sides.FIRST, RelativePositions.TOP_RIGHT));

        Assert.assertTrue(legalMutations.equals(expectedLegalMutations));

    }

    /*
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   | o |   |   |
    ---------------------
    |   |(x)|   |(x)|   |
    ---------------------
    |   |   |   |   |   |
    ---------------------    
    */
    @Test
    public void testOneOpponentPieceTwoJumpMutation() {

        final BoardInterface board = new BoardBuilder(CheckersPieceSet.class, DimensionFactory.Dimension(5, 5)).build();
        final SideInterface side = Sides.FIRST;

        board.getCell(4, 2).setPiece(side, CheckersPieceSet.MAN);
        board.getCell(4, 4).setPiece(side, CheckersPieceSet.MAN);
        board.getCell(3, 3).setPiece(side.getNextSide(), CheckersPieceSet.MAN);

        final Set<MutationInterface> legalMutations = board.getLegalMutations(side);

        final Set<MutationInterface> expectedLegalMutations = new HashSet<MutationInterface>();

        expectedLegalMutations.add(new JumpMutation(board.getCell(4, 2), Sides.FIRST, RelativePositions.TOP_RIGHT));
        expectedLegalMutations.add(new JumpMutation(board.getCell(4, 4), Sides.FIRST, RelativePositions.TOP_LEFT));

        Assert.assertTrue(legalMutations.equals(expectedLegalMutations));

    }

    /*
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   |   | o |   |
    ---------------------
    |   |   | o |   |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------
    */
    @Test
    public void testNoMutations() {

        final BoardInterface board = new BoardBuilder(CheckersPieceSet.class, DimensionFactory.Dimension(5, 5)).build();
        final SideInterface side = Sides.FIRST;

        board.getCell(2, 4).setPiece(side.getNextSide(), CheckersPieceSet.MAN);
        board.getCell(3, 3).setPiece(side.getNextSide(), CheckersPieceSet.MAN);

        final Set<MutationInterface> legalMutations = board.getLegalMutations(side);

        final Set<MutationInterface> expectedLegalMutations = new HashSet<MutationInterface>();

        Assert.assertTrue(legalMutations.equals(expectedLegalMutations));

    }

}