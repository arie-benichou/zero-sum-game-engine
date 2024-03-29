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

import abstractions.dimension.DimensionFactory;
import abstractions.direction.DirectionInterface;
import abstractions.direction.DirectionManager;
import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.piece.IllegalPieceException;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceManager;
import abstractions.piece.PieceManagerInterface;
import abstractions.piece.mocks.PieceSet;
import abstractions.position.PositionManager;
import abstractions.position.PositionManagerInterface;
import abstractions.side.Sides;

public final class ManagedCellTest { // NOPMD

    private ManagedCellInterface cell;

    @Before
    public void setUp() throws Exception {
        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(DimensionFactory.dimension(3, 3)));
        final PieceManagerInterface pieceManager = new PieceManager(PieceSet.class);
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
        Assert.assertTrue(this.cell.getNeihgbour(NamedDirection.TOP).isNull());
    }

    @Test
    public void testGetPiece() {
        Assert.assertTrue(this.cell.getPiece().getSide().isNull());
    }

    @Test
    public void testSetPieceSideInterfacePieceTypeInterface() {
        Assert.assertTrue(this.cell.setPiece(Sides.FIRST, PieceSet.PAWN).getPiece().getSide().isFirstSide());
    }

    @Test(expected = NullPointerException.class)
    public void testSetPieceOnNullCell0() {
        final ManagedCellInterface nullCell = this.cell.getNeihgbour(NamedDirection.TOP);
        nullCell.setPiece(Sides.NULL, PieceSet.NULL);
    }

    @Test(expected = NullPointerException.class)
    public void testSetPieceOnNullCell1() {
        final ManagedCellInterface nullCell = this.cell.getNeihgbour(NamedDirection.TOP);
        nullCell.setPiece(Sides.FIRST, PieceSet.PAWN);
    }

    @Test(expected = NullPointerException.class)
    public void testSetPieceOnNullCell2() {
        final ManagedCellInterface nullCell = this.cell.getNeihgbour(NamedDirection.TOP);
        nullCell.setPiece(Sides.SECOND, PieceSet.PAWN);
    }

    @Test(expected = IllegalPieceException.class)
    public void testSetIllegalPieceSideInterfacePieceTypeInterface0() {
        Assert.assertTrue(this.cell.setPiece(Sides.NULL, PieceSet.PAWN).getPiece().getSide().isFirstSide());
    }

    @Test(expected = IllegalPieceException.class)
    public void testSetIllegalPieceSideInterfacePieceTypeInterface1() {
        Assert.assertTrue(this.cell.setPiece(Sides.FIRST, PieceSet.NULL).getPiece().getSide().isFirstSide());
    }

    @Test(expected = IllegalPieceException.class)
    public void testSetIllegalPieceSideInterfacePieceTypeInterface2() {
        Assert.assertTrue(this.cell.setPiece(Sides.SECOND, PieceSet.NULL).getPiece().getSide().isFirstSide());
    }

    @Test
    public void testSetPiecePieceInterface() {
        final PieceInterface piece = this.cell.setPiece(Sides.SECOND, PieceSet.PAWN).getPiece();
        this.cell.setPiece(Sides.FIRST, PieceSet.PAWN).getPiece();
        Assert.assertTrue(this.cell.setPiece(Sides.FIRST, PieceSet.PAWN).getPiece().getSide().isFirstSide());
        this.cell.setPiece(piece);
        Assert.assertTrue(this.cell.getPiece().getSide().isSecondSide());
    }

    @Test
    public void testGetNeihgbour() {
        Assert.assertTrue(this.cell.getNeihgbour(NamedDirection.TOP).getPosition().getRow() == 0);
        Assert.assertTrue(this.cell.getNeihgbour(NamedDirection.TOP).getPosition().getColumn() == 0);

        Assert.assertTrue(this.cell.getNeihgbour(NamedDirection.BOTTOM).getPosition().getRow() == 2);
        Assert.assertTrue(this.cell.getNeihgbour(NamedDirection.BOTTOM).getPosition().getColumn() == 2);

        final ManagedCellInterface nullCell = this.cell.getNeihgbour(NamedDirection.TOP);

        Assert.assertTrue(nullCell.getNeihgbour(NamedDirection.BOTTOM_RIGHT) == nullCell);
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(this.cell.isEmpty());
        Assert.assertFalse(this.cell.getNeihgbour(NamedDirection.TOP).isEmpty());
        Assert.assertFalse(this.cell.setPiece(Sides.FIRST, PieceSet.PAWN).isEmpty());

    }

    @Test(expected = NullPointerException.class)
    public void testCompareToNull() {
        this.cell.compareTo(null);
    }

    @Test
    public void testCompareTo() {
        Assert.assertTrue(this.cell.compareTo(this.cell.getNeihgbour(NamedDirection.TOP)) == 1);

        Assert.assertTrue(this.cell.compareTo(this.cell.getNeihgbour(NamedDirection.BOTTOM)) == -1);
        Assert.assertTrue(this.cell.compareTo(this.cell) == 0);
        Assert.assertTrue(this.cell.compareTo(this.cell.getNeihgbour(NamedDirection.LEFT)) == 1);

        Assert.assertTrue(this.cell.compareTo(this.cell.getNeihgbour(NamedDirection.RIGHT).getNeihgbour(NamedDirection.BOTTOM_RIGHT)) == 1);
    }

    @Test
    public void testEqualsObject() {
        Assert.assertFalse(this.cell.equals(null)); // NOPMD
        Assert.assertFalse(this.cell.equals(new Random()));
        Assert.assertFalse(this.cell.equals(this.cell.getNeihgbour(NamedDirection.TOP)));

        Assert.assertTrue(this.cell.equals(this.cell));
        Assert.assertTrue(this.cell.equals(this.cell.getNeihgbour(NamedDirection.RIGHT)));

        this.cell.getNeihgbour(NamedDirection.RIGHT).setPiece(Sides.FIRST, PieceSet.PAWN);
        Assert.assertFalse(this.cell.equals(this.cell.getNeihgbour(NamedDirection.RIGHT)));

        this.cell.getNeihgbour(NamedDirection.RIGHT).setPiece(Sides.SECOND, PieceSet.PAWN);
        Assert.assertFalse(this.cell.equals(this.cell.getNeihgbour(NamedDirection.RIGHT)));

        this.cell.getNeihgbour(NamedDirection.RIGHT).setPiece(Sides.NULL, PieceSet.NULL);
        Assert.assertTrue(this.cell.equals(this.cell.getNeihgbour(NamedDirection.RIGHT)));
    }

    @Test
    public void testGetNeihgbourhood() {
        boolean isEqual;
        final ManagedCellInterface cell; // NOPMD
        Map<DirectionInterface, ManagedCellInterface> expected;

        cell = this.cell.getNeihgbour(NamedDirection.BOTTOM);
        expected = new HashMap<DirectionInterface, ManagedCellInterface>(8);
        expected.put(NamedDirection.TOP, cell.getNeihgbour(NamedDirection.TOP));
        expected.put(NamedDirection.TOP_RIGHT, cell.getNeihgbour(NamedDirection.TOP_RIGHT));
        expected.put(NamedDirection.RIGHT, cell.getNeihgbour(NamedDirection.RIGHT));
        expected.put(NamedDirection.BOTTOM_RIGHT, cell.getNeihgbour(NamedDirection.BOTTOM_RIGHT));
        expected.put(NamedDirection.BOTTOM, cell.getNeihgbour(NamedDirection.BOTTOM));
        expected.put(NamedDirection.BOTTOM_LEFT, cell.getNeihgbour(NamedDirection.BOTTOM_LEFT));
        expected.put(NamedDirection.LEFT, cell.getNeihgbour(NamedDirection.LEFT));
        expected.put(NamedDirection.TOP_LEFT, cell.getNeihgbour(NamedDirection.TOP_RIGHT)); // should have been TOP_LEFT

        isEqual = true;
        final Map<DirectionInterface, ManagedCellInterface> result = cell.getNeighbourhood();
        for (final Entry<DirectionInterface, ManagedCellInterface> entry : expected.entrySet()) {
            // TODO ! revoir l'override de la méthode equals de la classe ManagedCell
            isEqual = isEqual && result.get(entry.getKey()).getPosition().equals(entry.getValue().getPosition());
        }
        Assert.assertFalse(isEqual);

        expected.remove(NamedDirection.TOP_LEFT);
        expected.put(NamedDirection.TOP_LEFT, cell.getNeihgbour(NamedDirection.TOP_LEFT));

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
