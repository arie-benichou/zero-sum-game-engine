
package concretisations.sudoku;

import java.util.Set;

import abstractions.cell.CellManager;
import abstractions.cell.CellManagerInterface;
import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.piece.PieceManager;
import abstractions.piece.PieceManagerInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionManager;
import abstractions.position.PositionManagerInterface;

import com.google.common.collect.ImmutableSet;

public class SudokuChecker {

    private static SudokuChecker INSTANCE = new SudokuChecker();

    private final static Set<? extends PieceTypeInterface> SUDOKU_CANONICAL_SET = ImmutableSet.of(
            SudokuPieceSet.NUMBER1,
            SudokuPieceSet.NUMBER2,
            SudokuPieceSet.NUMBER3,
            SudokuPieceSet.NUMBER4,
            SudokuPieceSet.NUMBER5,
            SudokuPieceSet.NUMBER6,
            SudokuPieceSet.NUMBER7,
            SudokuPieceSet.NUMBER8,
            SudokuPieceSet.NUMBER9
            );

    private final CellManagerInterface cellManager;

    public static SudokuChecker getInstance() {
        return SudokuChecker.INSTANCE;
    }

    private SudokuChecker() {
        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(9, 9)));
        final PieceManagerInterface pieceManager = new PieceManager(SudokuPieceSet.class);
        this.cellManager = new CellManager(positionManager, pieceManager);
    }

    private void build(final int[][] grid) {
        for (final int[] row : grid) {
            for (final int digit : row) {
                System.out.println(digit);
            }
        }
    }

    public static void main(final String[] args) {
        final SudokuChecker sudokuChecker = SudokuChecker.getInstance();
        final int[][] grid = {
                { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
                { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
                { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
                { 8, 5, 9, 7, 6, 1, 4, 2, 3 },
                { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
                { 7, 1, 3, 9, 2, 4, 8, 5, 6 },
                { 9, 6, 1, 5, 3, 7, 2, 8, 4 },
                { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
                { 3, 4, 5, 2, 8, 6, 1, 7, 9 }
              };
        sudokuChecker.build(grid);
    }
}
