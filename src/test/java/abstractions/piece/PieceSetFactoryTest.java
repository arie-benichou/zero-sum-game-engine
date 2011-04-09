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

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.piece.mocks.Null;
import abstractions.piece.mocks.Pawn;
import abstractions.piece.mocks.PieceSet;
import abstractions.piece.mocks.PieceSetWithAtLeastOneNotFoundPieceClass;
import abstractions.piece.mocks.PieceSetWithAtLeastOnePieceClassNotImplementingPieceInterface;
import abstractions.piece.mocks.PieceSetWithOnlyNullType;
import abstractions.piece.mocks.PieceSetWithoutAnyType;
import abstractions.piece.mocks.PieceSetWithoutNullType;
import abstractions.side.Sides;

public final class PieceSetFactoryTest {

    private PieceSetFactory pieceSetFactory;

    @Before
    public void setUp() throws Exception {

        this.pieceSetFactory = new PieceSetFactory();

    }

    @Test(expected = IllegalPieceSetException.class)
    public void testEmptyPieceSet() {

        this.pieceSetFactory.newPieceSet(PieceSetWithoutAnyType.class);

    }

    @Test(expected = IllegalPieceSetException.class)
    public void testPieceSetWithAtLeastOneNotFoundPiece() {

        this.pieceSetFactory.newPieceSet(PieceSetWithAtLeastOneNotFoundPieceClass.class);

    }

    @Test(expected = IllegalPieceSetException.class)
    public void testPieceSetWithAtLeastOnePieceClassNotImplementingPieceInterface() {

        this.pieceSetFactory.newPieceSet(PieceSetWithAtLeastOnePieceClassNotImplementingPieceInterface.class);

    }

    @Test(expected = IllegalPieceSetException.class)
    public void testPieceSetWithOnlyNullPiece() {

        this.pieceSetFactory.newPieceSet(PieceSetWithOnlyNullType.class);

    }

    @Test(expected = IllegalPieceSetException.class)
    public void testPieceSetWithoutNullPiece() {

        this.pieceSetFactory.newPieceSet(PieceSetWithoutNullType.class);

    }

    @Test
    public void testLegalPieceSet() {

        final HashSet<PieceInterface> expectedPieceSet = new HashSet<PieceInterface>();
        expectedPieceSet.add(new Null(Sides.NULL, PieceSet.NULL));
        expectedPieceSet.add(new Pawn(Sides.FIRST, PieceSet.PAWN));
        expectedPieceSet.add(new Pawn(Sides.SECOND, PieceSet.PAWN));

        final Set<PieceInterface> pieceSet = this.pieceSetFactory.newPieceSet(PieceSet.class);

        Assert.assertTrue(pieceSet.equals(expectedPieceSet));

    }

    @After
    public void tearDown() throws Exception {
        this.pieceSetFactory = null;
    }

}
