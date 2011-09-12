/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package concretisations.sudoku;

import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.immutable.context.board.cell.piece.OldPieceTypeInterface;
import abstractions.immutable.context.board.cell.piece.PieceManager;
import abstractions.immutable.context.board.cell.piece.PieceManagerInterface;
import abstractions.immutable.context.board.cell.piece.side.Side;
import abstractions.old.cell.CellManager;
import abstractions.old.cell.CellManagerInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.position.PositionInterface;
import abstractions.old.position.PositionManager;
import abstractions.old.position.PositionManagerInterface;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public final class SudokuTest {

    private final static Set<? extends OldPieceTypeInterface> SUDOKU_CANONICAL_SET = ImmutableSet.of(SudokuPieceSet.NUMBER1, SudokuPieceSet.NUMBER2,
            SudokuPieceSet.NUMBER3, SudokuPieceSet.NUMBER4, SudokuPieceSet.NUMBER5, SudokuPieceSet.NUMBER6, SudokuPieceSet.NUMBER7, SudokuPieceSet.NUMBER8,
            SudokuPieceSet.NUMBER9);

    private CellManagerInterface cellManager;

    @Before
    public void setUp() throws Exception {

        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(9, 9)));
        final PieceManagerInterface pieceManager = new PieceManager(SudokuPieceSet.class);
        this.cellManager = new CellManager(positionManager, pieceManager);

    }

    private void setUpSudokuPuzzle() {

        this.cellManager.getCell(1, 1).setPiece(Side.FIRST, SudokuPieceSet.NUMBER5);
        this.cellManager.getCell(1, 2).setPiece(Side.FIRST, SudokuPieceSet.NUMBER3);
        this.cellManager.getCell(2, 1).setPiece(Side.FIRST, SudokuPieceSet.NUMBER6);
        this.cellManager.getCell(3, 2).setPiece(Side.FIRST, SudokuPieceSet.NUMBER9);
        this.cellManager.getCell(3, 3).setPiece(Side.FIRST, SudokuPieceSet.NUMBER8);

        this.cellManager.getCell(1, 5).setPiece(Side.FIRST, SudokuPieceSet.NUMBER7);
        this.cellManager.getCell(2, 4).setPiece(Side.FIRST, SudokuPieceSet.NUMBER1);
        this.cellManager.getCell(2, 5).setPiece(Side.FIRST, SudokuPieceSet.NUMBER9);
        this.cellManager.getCell(2, 6).setPiece(Side.FIRST, SudokuPieceSet.NUMBER5);

        this.cellManager.getCell(3, 8).setPiece(Side.FIRST, SudokuPieceSet.NUMBER6);

        this.cellManager.getCell(4, 1).setPiece(Side.FIRST, SudokuPieceSet.NUMBER8);
        this.cellManager.getCell(5, 1).setPiece(Side.FIRST, SudokuPieceSet.NUMBER4);
        this.cellManager.getCell(6, 1).setPiece(Side.FIRST, SudokuPieceSet.NUMBER7);

        this.cellManager.getCell(4, 5).setPiece(Side.FIRST, SudokuPieceSet.NUMBER6);
        this.cellManager.getCell(5, 4).setPiece(Side.FIRST, SudokuPieceSet.NUMBER8);
        this.cellManager.getCell(5, 6).setPiece(Side.FIRST, SudokuPieceSet.NUMBER3);
        this.cellManager.getCell(6, 5).setPiece(Side.FIRST, SudokuPieceSet.NUMBER2);

        this.cellManager.getCell(4, 9).setPiece(Side.FIRST, SudokuPieceSet.NUMBER3);
        this.cellManager.getCell(5, 9).setPiece(Side.FIRST, SudokuPieceSet.NUMBER1);
        this.cellManager.getCell(6, 9).setPiece(Side.FIRST, SudokuPieceSet.NUMBER6);

        this.cellManager.getCell(7, 2).setPiece(Side.FIRST, SudokuPieceSet.NUMBER6);

        this.cellManager.getCell(8, 4).setPiece(Side.FIRST, SudokuPieceSet.NUMBER4);
        this.cellManager.getCell(8, 5).setPiece(Side.FIRST, SudokuPieceSet.NUMBER1);
        this.cellManager.getCell(8, 6).setPiece(Side.FIRST, SudokuPieceSet.NUMBER9);
        this.cellManager.getCell(9, 5).setPiece(Side.FIRST, SudokuPieceSet.NUMBER8);

        this.cellManager.getCell(7, 7).setPiece(Side.FIRST, SudokuPieceSet.NUMBER2);
        this.cellManager.getCell(7, 8).setPiece(Side.FIRST, SudokuPieceSet.NUMBER8);
        this.cellManager.getCell(8, 9).setPiece(Side.FIRST, SudokuPieceSet.NUMBER5);
        this.cellManager.getCell(9, 8).setPiece(Side.FIRST, SudokuPieceSet.NUMBER7);
        this.cellManager.getCell(9, 9).setPiece(Side.FIRST, SudokuPieceSet.NUMBER9);
    }

    private void setUpSudokuPotentialSolution() {

        this.cellManager.getCell(1, 3).setPiece(Side.FIRST, SudokuPieceSet.NUMBER4);
        this.cellManager.getCell(2, 2).setPiece(Side.FIRST, SudokuPieceSet.NUMBER7);
        this.cellManager.getCell(2, 3).setPiece(Side.FIRST, SudokuPieceSet.NUMBER2);
        this.cellManager.getCell(3, 1).setPiece(Side.FIRST, SudokuPieceSet.NUMBER1);

        this.cellManager.getCell(1, 4).setPiece(Side.FIRST, SudokuPieceSet.NUMBER6);
        this.cellManager.getCell(1, 6).setPiece(Side.FIRST, SudokuPieceSet.NUMBER8);
        this.cellManager.getCell(3, 4).setPiece(Side.FIRST, SudokuPieceSet.NUMBER3);
        this.cellManager.getCell(3, 5).setPiece(Side.FIRST, SudokuPieceSet.NUMBER4);
        this.cellManager.getCell(3, 6).setPiece(Side.FIRST, SudokuPieceSet.NUMBER2);

        this.cellManager.getCell(1, 7).setPiece(Side.FIRST, SudokuPieceSet.NUMBER9);
        this.cellManager.getCell(1, 8).setPiece(Side.FIRST, SudokuPieceSet.NUMBER1);
        this.cellManager.getCell(1, 9).setPiece(Side.FIRST, SudokuPieceSet.NUMBER2);
        this.cellManager.getCell(2, 7).setPiece(Side.FIRST, SudokuPieceSet.NUMBER3);
        this.cellManager.getCell(2, 8).setPiece(Side.FIRST, SudokuPieceSet.NUMBER4);
        this.cellManager.getCell(2, 9).setPiece(Side.FIRST, SudokuPieceSet.NUMBER8);
        this.cellManager.getCell(3, 7).setPiece(Side.FIRST, SudokuPieceSet.NUMBER5);
        this.cellManager.getCell(3, 9).setPiece(Side.FIRST, SudokuPieceSet.NUMBER7);

        this.cellManager.getCell(4, 2).setPiece(Side.FIRST, SudokuPieceSet.NUMBER5);
        this.cellManager.getCell(4, 3).setPiece(Side.FIRST, SudokuPieceSet.NUMBER9);
        this.cellManager.getCell(5, 2).setPiece(Side.FIRST, SudokuPieceSet.NUMBER2);
        this.cellManager.getCell(5, 3).setPiece(Side.FIRST, SudokuPieceSet.NUMBER6);
        this.cellManager.getCell(6, 2).setPiece(Side.FIRST, SudokuPieceSet.NUMBER1);
        this.cellManager.getCell(6, 3).setPiece(Side.FIRST, SudokuPieceSet.NUMBER3);

        this.cellManager.getCell(4, 4).setPiece(Side.FIRST, SudokuPieceSet.NUMBER7);
        this.cellManager.getCell(4, 6).setPiece(Side.FIRST, SudokuPieceSet.NUMBER1);
        this.cellManager.getCell(5, 5).setPiece(Side.FIRST, SudokuPieceSet.NUMBER5);
        this.cellManager.getCell(6, 4).setPiece(Side.FIRST, SudokuPieceSet.NUMBER9);
        this.cellManager.getCell(6, 6).setPiece(Side.FIRST, SudokuPieceSet.NUMBER4);

        this.cellManager.getCell(4, 7).setPiece(Side.FIRST, SudokuPieceSet.NUMBER4);
        this.cellManager.getCell(4, 8).setPiece(Side.FIRST, SudokuPieceSet.NUMBER2);
        this.cellManager.getCell(5, 7).setPiece(Side.FIRST, SudokuPieceSet.NUMBER7);
        this.cellManager.getCell(5, 8).setPiece(Side.FIRST, SudokuPieceSet.NUMBER9);
        this.cellManager.getCell(6, 7).setPiece(Side.FIRST, SudokuPieceSet.NUMBER8);
        this.cellManager.getCell(6, 8).setPiece(Side.FIRST, SudokuPieceSet.NUMBER5);

        this.cellManager.getCell(7, 1).setPiece(Side.FIRST, SudokuPieceSet.NUMBER9);
        this.cellManager.getCell(7, 3).setPiece(Side.FIRST, SudokuPieceSet.NUMBER1);
        this.cellManager.getCell(8, 1).setPiece(Side.FIRST, SudokuPieceSet.NUMBER2);
        this.cellManager.getCell(8, 2).setPiece(Side.FIRST, SudokuPieceSet.NUMBER8);
        this.cellManager.getCell(8, 3).setPiece(Side.FIRST, SudokuPieceSet.NUMBER7);
        this.cellManager.getCell(9, 1).setPiece(Side.FIRST, SudokuPieceSet.NUMBER3);
        this.cellManager.getCell(9, 2).setPiece(Side.FIRST, SudokuPieceSet.NUMBER4);
        this.cellManager.getCell(9, 3).setPiece(Side.FIRST, SudokuPieceSet.NUMBER5);

        this.cellManager.getCell(7, 4).setPiece(Side.FIRST, SudokuPieceSet.NUMBER5);
        this.cellManager.getCell(7, 5).setPiece(Side.FIRST, SudokuPieceSet.NUMBER3);
        this.cellManager.getCell(7, 6).setPiece(Side.FIRST, SudokuPieceSet.NUMBER7);
        this.cellManager.getCell(9, 4).setPiece(Side.FIRST, SudokuPieceSet.NUMBER2);
        this.cellManager.getCell(9, 6).setPiece(Side.FIRST, SudokuPieceSet.NUMBER6);

        this.cellManager.getCell(7, 9).setPiece(Side.FIRST, SudokuPieceSet.NUMBER4);
        this.cellManager.getCell(8, 7).setPiece(Side.FIRST, SudokuPieceSet.NUMBER6);
        this.cellManager.getCell(8, 8).setPiece(Side.FIRST, SudokuPieceSet.NUMBER3);
        this.cellManager.getCell(9, 7).setPiece(Side.FIRST, SudokuPieceSet.NUMBER1);

    }

    @Test
    public void testSudokuRendering() {
        this.setUpSudokuPuzzle();
        this.setUpSudokuPotentialSolution();
        //TODO tester la chaine de caractère :
        System.out.println(this.cellManager);
    }

    @Test
    public void testCheckLines() {
        this.setUpSudokuPuzzle();
        this.setUpSudokuPotentialSolution();
        final Set<OldPieceTypeInterface> rowSet = Sets.newHashSetWithExpectedSize(9);
        for (int rowIndex = 1; rowIndex <= 9; ++rowIndex) {
            for (final ManagedCellInterface cell : this.cellManager.getRow(rowIndex)) {
                rowSet.add(cell.getPiece().type());
            }
            Assert.assertTrue(rowSet.equals(SudokuTest.SUDOKU_CANONICAL_SET));
            rowSet.clear();
            System.out.println("row " + rowIndex + " is ok.");
        }
        System.out.println("================");
    }

    @Test
    public void testCheckColumns() {
        this.setUpSudokuPuzzle();
        this.setUpSudokuPotentialSolution();
        final Set<OldPieceTypeInterface> columnSet = Sets.newHashSetWithExpectedSize(9);
        for (int columnIndex = 1; columnIndex <= 9; ++columnIndex) {
            for (final ManagedCellInterface cell : this.cellManager.getColumn(columnIndex)) {
                columnSet.add(cell.getPiece().type());
            }
            Assert.assertTrue(columnSet.equals(SudokuTest.SUDOKU_CANONICAL_SET));
            columnSet.clear();
            System.out.println("column " + columnIndex + " is ok.");
        }
        System.out.println("================");
    }

    @Test
    public void testCheckRegions() {
        this.setUpSudokuPuzzle();
        this.setUpSudokuPotentialSolution();
        final Set<OldPieceTypeInterface> regionSet = Sets.newHashSetWithExpectedSize(9);
        for (int regionIndex = 0; regionIndex < 9; ++regionIndex) {
            final int rowIndex = 1 + 3 * (regionIndex / 3);
            final int columnIndex = 1 + 3 * (regionIndex % 3);
            final PositionInterface topLeftPosition = this.cellManager.position(rowIndex, columnIndex);
            final PositionInterface bottomRightPosition = this.cellManager.position(rowIndex + 3 - 1, columnIndex + 3 - 1);
            for (final ManagedCellInterface cell : this.cellManager.getRegion(topLeftPosition, bottomRightPosition)) {
                regionSet.add(cell.getPiece().type());
            }
            Assert.assertTrue(regionSet.equals(SudokuTest.SUDOKU_CANONICAL_SET));
            regionSet.clear();
            System.out.println("region " + (regionIndex + 1) + " is ok.");
        }
        System.out.println("================");
    }
    
    
    

    @After
    public void tearDown() throws Exception {
        this.cellManager = null; // NOPMD
    }

}
