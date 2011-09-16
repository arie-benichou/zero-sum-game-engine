
package concretisations.connect4;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.immutable.context.gameplay.game.board.cell.piece.PieceManager;
import abstractions.immutable.context.gameplay.game.board.cell.piece.PieceManagerInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.Side;
import abstractions.old.cell.CellManager;
import abstractions.old.cell.CellManagerInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.mutation.MutationInterface;
import abstractions.old.position.PositionManager;
import abstractions.old.position.PositionManagerInterface;
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

        this.cellManager.getCell(5, 2).setPiece(Side.FIRST, Connect4PieceSet.PAWN);

        this.cellManager.getCell(4, 3).setPiece(Side.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(5, 3).setPiece(Side.FIRST, Connect4PieceSet.PAWN);

        this.cellManager.getCell(3, 4).setPiece(Side.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(4, 4).setPiece(Side.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(5, 4).setPiece(Side.FIRST, Connect4PieceSet.PAWN);

        this.cellManager.getCell(2, 5).setPiece(Side.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(3, 5).setPiece(Side.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(4, 5).setPiece(Side.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(5, 5).setPiece(Side.FIRST, Connect4PieceSet.PAWN);

        this.cellManager.getCell(1, 6).setPiece(Side.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(2, 6).setPiece(Side.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(3, 6).setPiece(Side.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(4, 6).setPiece(Side.FIRST, Connect4PieceSet.PAWN);
        this.cellManager.getCell(5, 6).setPiece(Side.FIRST, Connect4PieceSet.PAWN);

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
    }

    @After
    public void tearDown() throws Exception {
        this.cellManager = null; // NOPMD 
    }

}
