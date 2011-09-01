
package concretisations.connect4;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.cell.CellManager;
import abstractions.cell.CellManagerInterface;
import abstractions.cell.ManagedCellInterface;
import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceManager;
import abstractions.piece.PieceManagerInterface;
import abstractions.position.PositionManager;
import abstractions.position.PositionManagerInterface;
import abstractions.side.Sides;
import concretisations.connect4.pieces.Connect4PieceSet;

// TODO à compléter
public final class Connect4PotentialMutationsTest {

    private CellManagerInterface cellManager;

    @Before
    public void setUp() throws Exception {

        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(5, 6)));
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.connect4.pieces.Connect4PieceSet.class);
        this.cellManager = new CellManager(positionManager, pieceManager);

    }

    @Test
    public void testGetPotentialMutations() { // NOPMD 

        this.cellManager.getCell(5, 2).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);

        this.cellManager.getCell(4, 3).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(5, 3).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);

        this.cellManager.getCell(3, 4).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(4, 4).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(5, 4).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);

        this.cellManager.getCell(2, 5).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(3, 5).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(4, 5).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(5, 5).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);

        this.cellManager.getCell(1, 6).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(2, 6).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(3, 6).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(4, 6).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(5, 6).setPiece(Sides.FIRST, Connect4PieceSet.PAWN);

        //System.out.println(this.cellManager);

        final Map<ManagedCellInterface, Set<MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Sides.FIRST);

        for (final Entry<ManagedCellInterface, Set<MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.process();
                //System.out.println(this.cellManager);
                mutation.cancel();
                //System.out.println(this.cellManager);
            }
        }

        // TODO à tester unitairement
    }

    @After
    public void tearDown() throws Exception {
        this.cellManager = null; // NOPMD 
    }

}
