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

package abstractions.cell;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.immutable.context.board.cell.piece.PieceInterface;
import abstractions.immutable.context.board.cell.piece.PieceManager;
import abstractions.immutable.context.board.cell.piece.PieceManagerInterface;
import abstractions.immutable.context.board.cell.piece.side.Side;
import abstractions.old.cell.CellManager;
import abstractions.old.cell.CellManagerInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.position.PositionInterface;
import abstractions.old.position.PositionManager;
import abstractions.old.position.PositionManagerInterface;
import abstractions.piece.mocks.PieceSet1;

public final class CellManagerTest {

    private CellManagerInterface cellManager;

    @Before
    public void setUp() throws Exception {
        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(3, 3)));
        final PieceManagerInterface pieceManager = new PieceManager(PieceSet1.class);
        this.cellManager = new CellManager(positionManager, pieceManager);
    }

    @Test
    public void testGetNullCell() {
        final ManagedCellInterface nullCell = this.cellManager.getNullCell();
        Assert.assertTrue(nullCell.isNull());
        Assert.assertTrue(nullCell == this.cellManager.getCell(0, 0));
        Assert.assertTrue(nullCell == this.cellManager.getCell(3, 4));
    }

    @Test
    public void testGetNullPiece() {
        Assert.assertTrue(this.cellManager.getNullPiece().side().isNull());
        Assert.assertTrue(this.cellManager.getNullPiece().value().equals(PieceSet1.NULL));
    }

    @Test
    public void testGetCellFromPrimitives() {
        final int rowIndex = 1, columnIndex = 1; // NOPMD 
        final ManagedCellInterface cell = this.cellManager.getCell(rowIndex, columnIndex);

        Assert.assertTrue(cell.getPosition() == this.cellManager.position(rowIndex, columnIndex));
        Assert.assertTrue(cell.getPiece() == this.cellManager.piece(Side.NULL, PieceSet1.NULL));
    }

    @Test
    public void testGetCellFromPositionObject() {
        final PositionInterface position = this.cellManager.position(1, 1);
        final ManagedCellInterface cell = this.cellManager.getCell(position);

        Assert.assertTrue(cell.getPosition() == this.cellManager.position(1, 1));
        Assert.assertTrue(cell.getPiece() == this.cellManager.piece(Side.NULL, PieceSet1.NULL));
    }

    @Test
    public void testPieceManagerFacade() {
        PieceInterface piece;

        piece = this.cellManager.piece(Side.NULL, PieceSet1.NULL);
        Assert.assertTrue(piece.side().equals(Side.NULL));
        Assert.assertTrue(piece.type().equals(PieceSet1.NULL));

        piece = this.cellManager.piece(Side.FIRST, PieceSet1.PAWN);
        Assert.assertTrue(piece.side().equals(Side.FIRST));
        Assert.assertTrue(piece.type().equals(PieceSet1.PAWN));

        piece = this.cellManager.piece(Side.SECOND, PieceSet1.PAWN);
        Assert.assertTrue(piece.side().equals(Side.SECOND));
        Assert.assertTrue(piece.type().equals(PieceSet1.PAWN));
    }

    @Test
    public void testPositionManagerFacade1() {
        PositionInterface position;

        position = this.cellManager.position(0, 0);
        Assert.assertTrue(position.getRow() == 0);
        Assert.assertTrue(position.getColumn() == 0);

        position = this.cellManager.position(2, 2);
        Assert.assertTrue(position.getRow() == 2);
        Assert.assertTrue(position.getColumn() == 2);

        position = this.cellManager.position(4, 4);
        Assert.assertTrue(position.getRow() == 0);
        Assert.assertTrue(position.getColumn() == 0);
    }

    @Test
    public void testPositionManagerFacade2() {
        PositionInterface initialPosition, position, expectedPosition;

        initialPosition = this.cellManager.position(1, 1);

        position = this.cellManager.position(initialPosition, NamedDirection.TOP);
        expectedPosition = this.cellManager.position(0, 0);
        Assert.assertTrue(position.equals(expectedPosition));

        position = this.cellManager.position(initialPosition, NamedDirection.RIGHT);
        expectedPosition = this.cellManager.position(1, 2);
        Assert.assertTrue(position.equals(expectedPosition));

        initialPosition = this.cellManager.position(2, 2);

        position = this.cellManager.position(initialPosition, NamedDirection.TOP);
        expectedPosition = this.cellManager.position(1, 2);
        Assert.assertTrue(position.equals(expectedPosition));

        position = this.cellManager.position(initialPosition, NamedDirection.RIGHT);
        expectedPosition = this.cellManager.position(2, 3);
        Assert.assertTrue(position.equals(expectedPosition));
    }

    @Test
    public void testIterableInterface() {
        final List<ManagedCellInterface> expectedCells = new ArrayList<ManagedCellInterface>(1 + 3 * 3);

        expectedCells.add(this.cellManager.getNullCell());

        expectedCells.add(this.cellManager.getCell(1, 1));
        expectedCells.add(this.cellManager.getCell(1, 2));
        expectedCells.add(this.cellManager.getCell(1, 3));

        expectedCells.add(this.cellManager.getCell(2, 1));
        expectedCells.add(this.cellManager.getCell(2, 2));
        expectedCells.add(this.cellManager.getCell(2, 3));

        expectedCells.add(this.cellManager.getCell(3, 1));
        expectedCells.add(this.cellManager.getCell(3, 2));
        expectedCells.add(this.cellManager.getCell(3, 3));

        final List<ManagedCellInterface> cells = new ArrayList<ManagedCellInterface>();
        for (final ManagedCellInterface cell : this.cellManager) {
            cells.add(cell);
        }

        Assert.assertTrue(expectedCells.equals(cells));
    }

    @Test
    public void testGetRow() {

        // TODO ! revoir l'équivalence d'une cellule: this cellule doit être égale à that cellule, si et seulement si, that cellule et this cellule ont la même position (et la même pièce))
        this.cellManager.getCell(1, 1).setPiece(Side.FIRST, PieceSet1.PAWN);
        this.cellManager.getCell(1, 2).setPiece(Side.FIRST, PieceSet1.PAWN);
        this.cellManager.getCell(1, 3).setPiece(Side.FIRST, PieceSet1.PAWN);
        //

        final List<ManagedCellInterface> expectedCells = new ArrayList<ManagedCellInterface>(3);
        expectedCells.add(this.cellManager.getCell(1, 1));
        expectedCells.add(this.cellManager.getCell(1, 2));
        expectedCells.add(this.cellManager.getCell(1, 3));
        Assert.assertTrue(expectedCells.equals(this.cellManager.getRow(1)));

        for (final ManagedCellInterface cell : this.cellManager.getRow(0)) {
            Assert.assertTrue(cell.isNull());
        }
        for (final ManagedCellInterface cell : this.cellManager.getRow(4)) {
            Assert.assertTrue(cell.isNull());
        }

    }

    @Test
    public void testGetColumn() {

        // TODO ! revoir l'équivalence d'une cellule: this cellule doit être égale à that cellule, si et seulement si, that cellule et this cellule ont la même position (et la même pièce))
        this.cellManager.getCell(1, 1).setPiece(Side.FIRST, PieceSet1.PAWN);
        this.cellManager.getCell(2, 1).setPiece(Side.FIRST, PieceSet1.PAWN);
        this.cellManager.getCell(3, 1).setPiece(Side.FIRST, PieceSet1.PAWN);
        //

        final List<ManagedCellInterface> expectedCells = new ArrayList<ManagedCellInterface>(3);
        expectedCells.add(this.cellManager.getCell(1, 1));
        expectedCells.add(this.cellManager.getCell(2, 1));
        expectedCells.add(this.cellManager.getCell(3, 1));
        Assert.assertTrue(expectedCells.equals(this.cellManager.getColumn(1)));

        for (final ManagedCellInterface cell : this.cellManager.getColumn(0)) {
            Assert.assertTrue(cell.isNull());
        }

        for (final ManagedCellInterface cell : this.cellManager.getColumn(4)) {
            Assert.assertTrue(cell.isNull());
        }

    }

    @Test
    public void testGetRegion() {

        // TODO ! revoir l'équivalence d'une cellule: this cellule doit être égale à that cellule, si et seulement si, that cellule et this cellule ont la même position (et la même pièce))
        this.cellManager.getCell(1, 1).setPiece(Side.FIRST, PieceSet1.PAWN);
        this.cellManager.getCell(1, 2).setPiece(Side.FIRST, PieceSet1.PAWN);
        this.cellManager.getCell(2, 1).setPiece(Side.FIRST, PieceSet1.PAWN);
        this.cellManager.getCell(2, 2).setPiece(Side.FIRST, PieceSet1.PAWN);

        final List<ManagedCellInterface> expectedCells = new ArrayList<ManagedCellInterface>(4);
        expectedCells.add(this.cellManager.getCell(1, 1));
        expectedCells.add(this.cellManager.getCell(1, 2));
        expectedCells.add(this.cellManager.getCell(2, 1));
        expectedCells.add(this.cellManager.getCell(2, 2));
        Assert.assertTrue(expectedCells.equals(this.cellManager.getRegion(this.cellManager.position(1, 1), this.cellManager.position(2, 2))));

    }

    /*
    @Test
    public void testGetPiecesByExistingRow() {

        // TODO ? revoir l'équivalence d'une cellule: this cellule doit être égale à that cellule, si et seulement si, that cellule et this cellule ont la même position (et la même pièce))
        this.cellManager.getCell(1, 1).setPiece(Sides.FIRST, PieceSet1.PAWN);
        this.cellManager.getCell(1, 2).setPiece(Sides.FIRST, PieceSet1.PAWN);
        this.cellManager.getCell(1, 3).setPiece(Sides.FIRST, PieceSet1.PAWN);
        //

        final List<PieceInterface> expectedPieces = new ArrayList<PieceInterface>(3);

        expectedPieces.add(this.cellManager.getCell(1, 1).getPiece());
        expectedPieces.add(this.cellManager.getCell(1, 2).getPiece());
        expectedPieces.add(this.cellManager.getCell(1, 3).getPiece());

        Assert.assertTrue(expectedPieces.equals(this.cellManager.getPiecesByRow(1)));

    }

    @Test
    public void testGetPiecesByUnexistingRow() {
        for (final PieceInterface piece : this.cellManager.getPiecesByRow(0)) {
            Assert.assertTrue(piece.getType() == PieceSet1.NULL); // TODO ! ajouter la méthode isNull() à l'interface d'une pièce
        }
        for (final PieceInterface piece : this.cellManager.getPiecesByRow(4)) {
            Assert.assertTrue(piece.getType() == PieceSet1.NULL); // TODO ! ajouter la méthode isNull() à l'interface d'une pièce
        }
    }

    @Test
    public void testGetPiecesByExistingColumn() {

        // TODO ? revoir l'équivalence d'une cellule: this cellule doit être égale à that cellule, si et seulement si, that cellule et this cellule ont la même position (et la même pièce))
        this.cellManager.getCell(1, 1).setPiece(Sides.FIRST, PieceSet1.PAWN);
        this.cellManager.getCell(2, 1).setPiece(Sides.FIRST, PieceSet1.PAWN);
        this.cellManager.getCell(3, 1).setPiece(Sides.FIRST, PieceSet1.PAWN);
        //

        final List<PieceInterface> expectedPieces = new ArrayList<PieceInterface>(3);

        expectedPieces.add(this.cellManager.getCell(1, 1).getPiece());
        expectedPieces.add(this.cellManager.getCell(2, 1).getPiece());
        expectedPieces.add(this.cellManager.getCell(3, 1).getPiece());

        Assert.assertTrue(expectedPieces.equals(this.cellManager.getPiecesByColumn(1)));

    }

    @Test
    public void testGetPiecesByUnexistingColumn() {
        for (final PieceInterface piece : this.cellManager.getPiecesByColumn(0)) {
            Assert.assertTrue(piece.getType() == PieceSet1.NULL); // TODO ! ajouter la méthode isNull() à l'interface d'une pièce
        }
        for (final PieceInterface piece : this.cellManager.getPiecesByColumn(4)) {
            Assert.assertTrue(piece.getType() == PieceSet1.NULL); // TODO ! ajouter la méthode isNull() à l'interface d'une pièce
        }
    }
    */

    @After
    public void tearDown() throws Exception {
        this.cellManager = null; // NOPMD
    }

}
