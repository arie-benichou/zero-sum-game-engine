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

package abstractions.piece;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.immutable.context.board.cell.piece.IllegalPieceException;
import abstractions.immutable.context.board.cell.piece.IllegalPieceSetException;
import abstractions.immutable.context.board.cell.piece.PieceManager;
import abstractions.immutable.context.board.cell.piece.side.Side;
import abstractions.piece.mocks.Pawn;
import abstractions.piece.mocks.PieceSet1;
import abstractions.piece.mocks.PieceSet2;
import abstractions.piece.mocks.PieceSetWithAtLeastOneNotFoundPieceClass;
import abstractions.piece.mocks.PieceSetWithAtLeastOnePieceClassNotImplementingPieceInterface;
import abstractions.piece.mocks.PieceSetWithOnlyNullType;
import abstractions.piece.mocks.PieceSetWithoutAnyType;
import abstractions.piece.mocks.PieceSetWithoutNullType;

public final class PieceManagerTest {

    private PieceManager pieceManager;

    @Before
    public void setUp() throws Exception {
        this.pieceManager = new PieceManager(PieceSet1.class);
    }

    @Test(expected = IllegalPieceSetException.class)
    public void testEmptyPieceSet() {
        new PieceManager(PieceSetWithoutAnyType.class);
    }

    @Test(expected = IllegalPieceSetException.class)
    public void testPieceSetWithAtLeastOneNotFoundPiece() {
        new PieceManager(PieceSetWithAtLeastOneNotFoundPieceClass.class);
    }

    @Test(expected = IllegalPieceSetException.class)
    public void testPieceSetWithAtLeastOnePieceClassNotImplementingPieceInterface() {
        new PieceManager(PieceSetWithAtLeastOnePieceClassNotImplementingPieceInterface.class);
    }

    @Test(expected = IllegalPieceSetException.class)
    public void testPieceSetWithOnlyNullPiece() {
        new PieceManager(PieceSetWithOnlyNullType.class);
    }

    @Test(expected = IllegalPieceSetException.class)
    public void testPieceSetWithoutNullPiece() {
        new PieceManager(PieceSetWithoutNullType.class);
    }

    @Test(expected = IllegalPieceException.class)
    public void testGetIllegalPiece() {
        this.pieceManager.getPiece(Side.FIRST, PieceSet2.PAWN);
    }

    @Test
    public void testGetNullPiece() {
        Assert.assertTrue(this.pieceManager.getPiece(Side.NULL, PieceSet1.NULL) == this.pieceManager.getNullPiece());
    }

    @Test
    public void testGetPiece() {
        Assert.assertTrue(this.pieceManager.getPiece(Side.FIRST, PieceSet1.PAWN).equals(new Pawn(Side.FIRST, PieceSet1.PAWN)));
        Assert.assertTrue(this.pieceManager.getPiece(Side.SECOND, PieceSet1.PAWN).equals(new Pawn(Side.SECOND, PieceSet1.PAWN)));
    }

    @After
    public void tearDown() throws Exception {
        this.pieceManager = null; // NOPMD 
    }

}