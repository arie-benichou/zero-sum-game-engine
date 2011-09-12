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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionInterface;
import abstractions.direction.DirectionManager;
import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.immutable.context.board.cell.piece.IllegalPieceException;
import abstractions.immutable.context.board.cell.piece.PieceInterface;
import abstractions.immutable.context.board.cell.piece.PieceManager;
import abstractions.immutable.context.board.cell.piece.PieceManagerInterface;
import abstractions.immutable.context.board.cell.piece.side.Side;
import abstractions.old.cell.CellManager;
import abstractions.old.cell.ManagedCell;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.position.PositionManager;
import abstractions.old.position.PositionManagerInterface;
import abstractions.piece.mocks.PieceSet1;

public final class ManagedCellTest { // NOPMD

    private ManagedCellInterface cell;

    @Before
    public void setUp() throws Exception {
        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(3, 3)));
        final PieceManagerInterface pieceManager = new PieceManager(PieceSet1.class);
        final CellManager cellManager = new CellManager(positionManager, pieceManager);
        this.cell = new ManagedCell(cellManager, positionManager.getPosition(1, 2));
    }

    @Test
    public void testGetPosition() {
        Assert.assertTrue(this.cell.getPosition().getRow() == 1);
        Assert.assertTrue(this.cell.getPosition().getColumn() == 2);
    }

    @Test
    public void testGetRow() {
        Assert.assertTrue(this.cell.getRow() == 1);
    }

    @Test
    public void testGetColumn() {
        Assert.assertTrue(this.cell.getColumn() == 2);
    }

    @Test
    public void testHashCode() {
        Assert.assertTrue(this.cell.hashCode() == this.cell.getPosition().hashCode());
    }

    @Test
    public void testIsNull() {
        Assert.assertFalse(this.cell.isNull());
        Assert.assertTrue(this.cell.getNeighbour(NamedDirection.TOP).isNull());
    }

    @Test
    public void testGetPiece() {
        Assert.assertTrue(this.cell.getPiece().side().isNull());
    }

    @Test
    public void testSetPieceSideInterfacePieceTypeInterface() {
        Assert.assertTrue(this.cell.setPiece(Side.FIRST, PieceSet1.PAWN).getPiece().side().isFirst());
    }

    @Test(expected = NullPointerException.class)
    public void testSetPieceOnNullCell0() {
        final ManagedCellInterface nullCell = this.cell.getNeighbour(NamedDirection.TOP);
        nullCell.setPiece(Side.NULL, PieceSet1.NULL);
    }

    @Test(expected = NullPointerException.class)
    public void testSetPieceOnNullCell1() {
        final ManagedCellInterface nullCell = this.cell.getNeighbour(NamedDirection.TOP);
        nullCell.setPiece(Side.FIRST, PieceSet1.PAWN);
    }

    @Test(expected = NullPointerException.class)
    public void testSetPieceOnNullCell2() {
        final ManagedCellInterface nullCell = this.cell.getNeighbour(NamedDirection.TOP);
        nullCell.setPiece(Side.SECOND, PieceSet1.PAWN);
    }

    @Test(expected = IllegalPieceException.class)
    public void testSetIllegalPieceSideInterfacePieceTypeInterface0() {
        Assert.assertTrue(this.cell.setPiece(Side.NULL, PieceSet1.PAWN).getPiece().side().isFirst());
    }

    @Test(expected = IllegalPieceException.class)
    public void testSetIllegalPieceSideInterfacePieceTypeInterface1() {
        Assert.assertTrue(this.cell.setPiece(Side.FIRST, PieceSet1.NULL).getPiece().side().isFirst());
    }

    @Test(expected = IllegalPieceException.class)
    public void testSetIllegalPieceSideInterfacePieceTypeInterface2() {
        Assert.assertTrue(this.cell.setPiece(Side.SECOND, PieceSet1.NULL).getPiece().side().isFirst());
    }

    @Test
    public void testSetPiecePieceInterface() {
        final PieceInterface piece = this.cell.setPiece(Side.SECOND, PieceSet1.PAWN).getPiece();
        this.cell.setPiece(Side.FIRST, PieceSet1.PAWN).getPiece();
        Assert.assertTrue(this.cell.setPiece(Side.FIRST, PieceSet1.PAWN).getPiece().side().isFirst());
        this.cell.setPiece(piece);
        Assert.assertTrue(this.cell.getPiece().side().isSecondSide());
    }

    @Test
    public void testGetNeighbour() {
        Assert.assertTrue(this.cell.getNeighbour(NamedDirection.TOP).getPosition().getRow() == 0);
        Assert.assertTrue(this.cell.getNeighbour(NamedDirection.TOP).getPosition().getColumn() == 0);

        Assert.assertTrue(this.cell.getNeighbour(NamedDirection.BOTTOM).getPosition().getRow() == 2);
        Assert.assertTrue(this.cell.getNeighbour(NamedDirection.BOTTOM).getPosition().getColumn() == 2);

        final ManagedCellInterface nullCell = this.cell.getNeighbour(NamedDirection.TOP);

        Assert.assertTrue(nullCell.getNeighbour(NamedDirection.BOTTOM_RIGHT) == nullCell);
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(this.cell.isEmpty());
        Assert.assertFalse(this.cell.getNeighbour(NamedDirection.TOP).isEmpty());
        Assert.assertFalse(this.cell.setPiece(Side.FIRST, PieceSet1.PAWN).isEmpty());

    }

    @Test(expected = NullPointerException.class)
    public void testCompareToNull() {
        this.cell.compareTo(null);
    }

    @Test
    public void testCompareTo() {
        Assert.assertTrue(this.cell.compareTo(this.cell.getNeighbour(NamedDirection.TOP)) == 1);

        Assert.assertTrue(this.cell.compareTo(this.cell.getNeighbour(NamedDirection.BOTTOM)) == -1);
        Assert.assertTrue(this.cell.compareTo(this.cell) == 0);
        Assert.assertTrue(this.cell.compareTo(this.cell.getNeighbour(NamedDirection.LEFT)) == 1);

        Assert.assertTrue(this.cell.compareTo(this.cell.getNeighbour(NamedDirection.RIGHT).getNeighbour(NamedDirection.BOTTOM_RIGHT)) == 1);
    }

    @Test
    public void testEqualsObject() {
        Assert.assertFalse(this.cell.equals(null)); // NOPMD
        Assert.assertFalse(this.cell.equals(new Random()));
        Assert.assertFalse(this.cell.equals(this.cell.getNeighbour(NamedDirection.TOP)));

        Assert.assertTrue(this.cell.equals(this.cell));
        Assert.assertTrue(this.cell.equals(this.cell.getNeighbour(NamedDirection.RIGHT)));

        this.cell.getNeighbour(NamedDirection.RIGHT).setPiece(Side.FIRST, PieceSet1.PAWN);
        Assert.assertFalse(this.cell.equals(this.cell.getNeighbour(NamedDirection.RIGHT)));

        this.cell.getNeighbour(NamedDirection.RIGHT).setPiece(Side.SECOND, PieceSet1.PAWN);
        Assert.assertFalse(this.cell.equals(this.cell.getNeighbour(NamedDirection.RIGHT)));

        this.cell.getNeighbour(NamedDirection.RIGHT).setPiece(Side.NULL, PieceSet1.NULL);
        Assert.assertTrue(this.cell.equals(this.cell.getNeighbour(NamedDirection.RIGHT)));
    }

    @Test
    public void testGetNeihgbourhood() {
        boolean isEqual;
        final ManagedCellInterface cell; // NOPMD
        Map<DirectionInterface, ManagedCellInterface> expected;

        cell = this.cell.getNeighbour(NamedDirection.BOTTOM);
        expected = new HashMap<DirectionInterface, ManagedCellInterface>(8);
        expected.put(NamedDirection.TOP.value(), cell.getNeighbour(NamedDirection.TOP));
        expected.put(NamedDirection.TOP_RIGHT.value(), cell.getNeighbour(NamedDirection.TOP_RIGHT));
        expected.put(NamedDirection.RIGHT.value(), cell.getNeighbour(NamedDirection.RIGHT));
        expected.put(NamedDirection.BOTTOM_RIGHT.value(), cell.getNeighbour(NamedDirection.BOTTOM_RIGHT));
        expected.put(NamedDirection.BOTTOM.value(), cell.getNeighbour(NamedDirection.BOTTOM));
        expected.put(NamedDirection.BOTTOM_LEFT.value(), cell.getNeighbour(NamedDirection.BOTTOM_LEFT));
        expected.put(NamedDirection.LEFT.value(), cell.getNeighbour(NamedDirection.LEFT));
        expected.put(NamedDirection.TOP_LEFT.value(), cell.getNeighbour(NamedDirection.TOP_RIGHT)); // should have been TOP_LEFT

        isEqual = true;
        final Map<DirectionInterface, ManagedCellInterface> result = cell.getNeighbourhood();
        for (final Entry<DirectionInterface, ManagedCellInterface> entry : expected.entrySet()) {
            // TODO ! revoir l'override de la méthode equals de la classe ManagedCell
            isEqual = isEqual && result.get(entry.getKey()).getPosition().equals(entry.getValue().getPosition());
        }
        Assert.assertFalse(isEqual);

        expected.remove(NamedDirection.TOP_LEFT.value());
        expected.put(NamedDirection.TOP_LEFT.value(), cell.getNeighbour(NamedDirection.TOP_LEFT));

        isEqual = true;
        for (final Entry<DirectionInterface, ManagedCellInterface> entry : expected.entrySet()) {
            isEqual = isEqual && result.get(entry.getKey()).getPosition().equals(entry.getValue().getPosition());
        }
        Assert.assertTrue(isEqual);
        Assert.assertTrue(result == cell.getNeighbourhood()); // cache test 
    }

    @After
    public void tearDown() throws Exception {
        this.cell = null; // NOPMD
    }

}
