
package concretisations.othello;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.immutable.context.board.cell.piece.PieceManager;
import abstractions.immutable.context.board.cell.piece.PieceManagerInterface;
import abstractions.immutable.context.board.cell.piece.side.Side;
import abstractions.old.cell.CellManager;
import abstractions.old.cell.CellManagerInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.mutation.MutationInterface;
import abstractions.old.position.PositionManager;
import abstractions.old.position.PositionManagerInterface;
import concretisations.othello.pieces.OthelloPieceSet;

// TODO à compléter
public final class OthelloPotentialMutationsTest {

    private CellManagerInterface cellManager;

    @Before
    public void setUp() throws Exception {

        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(8, 8)));
        final PieceManagerInterface pieceManager = new PieceManager(OthelloPieceSet.class);
        this.cellManager = new CellManager(positionManager, pieceManager);

    }

    //@Test
    public void testGetPotentialMutations1() { // NOPMD

        this.cellManager.getCell(4, 4).setPiece(Side.FIRST, OthelloPieceSet.PAWN);
        this.cellManager.getCell(4, 5).setPiece(Side.SECOND, OthelloPieceSet.PAWN);

        this.cellManager.getCell(5, 4).setPiece(Side.SECOND, OthelloPieceSet.PAWN);
        this.cellManager.getCell(5, 5).setPiece(Side.FIRST, OthelloPieceSet.PAWN);

        //System.out.println(this.cellManager);

        final Map<ManagedCellInterface, Set<MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Side.FIRST);

        for (final Entry<ManagedCellInterface, Set<MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.process();
                //System.out.println(this.cellManager);
                mutation.cancel();
                //System.out.println(this.cellManager);
            }
        }

        // TODO à tester unitairement
        //Assert.assertTrue(legalMutations.equals(expectedLegalMutations));

    }

    @Test
    public void testGetPotentialMutation2() { // NOPMD

        this.cellManager.getCell(4, 4).setPiece(Side.FIRST, OthelloPieceSet.PAWN);
        this.cellManager.getCell(4, 5).setPiece(Side.SECOND, OthelloPieceSet.PAWN);
        this.cellManager.getCell(4, 6).setPiece(Side.SECOND, OthelloPieceSet.PAWN);
        this.cellManager.getCell(4, 7).setPiece(Side.SECOND, OthelloPieceSet.PAWN);

        this.cellManager.getCell(5, 4).setPiece(Side.SECOND, OthelloPieceSet.PAWN);
        this.cellManager.getCell(5, 5).setPiece(Side.FIRST, OthelloPieceSet.PAWN);

        //System.out.println(this.cellManager);

        final Map<ManagedCellInterface, Set<MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Side.FIRST);

        for (final Entry<ManagedCellInterface, Set<MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.process();
                //System.out.println(this.cellManager);
                mutation.cancel();
                //System.out.println(this.cellManager);
            }
        }

        // TODO à tester unitairement
        //Assert.assertTrue(legalMutations.equals(expectedLegalMutations));

    }

    @After
    public void tearDown() throws Exception {
        this.cellManager = null; // NOPMD
    }

}
