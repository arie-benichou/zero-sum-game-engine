
package concretisations.othello;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.cell.CellManager;
import abstractions.cell.CellManagerInterface;
import abstractions.cell.ManagedCellInterface;
import abstractions.dimension.DimensionFactory;
import abstractions.direction.DirectionManager;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceManager;
import abstractions.piece.PieceManagerInterface;
import abstractions.position.PositionManager;
import abstractions.position.PositionManagerInterface;
import abstractions.side.Sides;
import concretisations.othello.pieces.OthelloPieceSet;

public class OthelloPotentialMutationsTest {

    private CellManagerInterface cellManager;

    @Before
    public void setUp() throws Exception {

        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(DimensionFactory.Dimension(8, 8)));
        final PieceManagerInterface pieceManager = new PieceManager(OthelloPieceSet.class);
        this.cellManager = new CellManager(positionManager, pieceManager);

    }

    //@Test
    public void testGetPotentialMutations1() {

        this.cellManager.getCell(4, 4).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        this.cellManager.getCell(4, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);

        this.cellManager.getCell(5, 4).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        this.cellManager.getCell(5, 5).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);

        /*
        final Set<MutationInterface> expectedLegalMutations = new HashSet<MutationInterface>();

        expectedLegalMutations.add(new NewPawnMutation(this.cellManager.getCell(4, 6), Sides.FIRST));
        expectedLegalMutations.add(new NewPawnMutation(this.cellManager.getCell(5, 3), Sides.FIRST));
        expectedLegalMutations.add(new NewPawnMutation(this.cellManager.getCell(6, 4), Sides.FIRST));
        expectedLegalMutations.add(new NewPawnMutation(this.cellManager.getCell(3, 5), Sides.FIRST));
        */

        System.out.println(this.cellManager);

        final Map<ManagedCellInterface, Set<? extends MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Sides.FIRST);

        for (final Entry<ManagedCellInterface, Set<? extends MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.process();
                System.out.println(this.cellManager);
                mutation.cancel();
                System.out.println(this.cellManager);
            }
        }

        //Assert.assertTrue(legalMutations.equals(expectedLegalMutations));

    }

    @Test
    public void testGetPotentialMutation2() {

        this.cellManager.getCell(4, 4).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        this.cellManager.getCell(4, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        this.cellManager.getCell(4, 6).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        this.cellManager.getCell(4, 7).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);

        this.cellManager.getCell(5, 4).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        this.cellManager.getCell(5, 5).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);

        /*
        final Set<MutationInterface> expectedLegalMutations = new HashSet<MutationInterface>();

        expectedLegalMutations.add(new NewPawnMutation(this.cellManager.getCell(4, 6), Sides.FIRST));
        expectedLegalMutations.add(new NewPawnMutation(this.cellManager.getCell(5, 3), Sides.FIRST));
        expectedLegalMutations.add(new NewPawnMutation(this.cellManager.getCell(6, 4), Sides.FIRST));
        expectedLegalMutations.add(new NewPawnMutation(this.cellManager.getCell(3, 5), Sides.FIRST));
        */

        System.out.println(this.cellManager);

        final Map<ManagedCellInterface, Set<? extends MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Sides.FIRST);

        for (final Entry<ManagedCellInterface, Set<? extends MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.process();
                System.out.println(this.cellManager);
                mutation.cancel();
                System.out.println(this.cellManager);
            }
        }

        //Assert.assertTrue(legalMutations.equals(expectedLegalMutations));

    }

    @After
    public void tearDown() throws Exception {
        this.cellManager = null;
    }

}
