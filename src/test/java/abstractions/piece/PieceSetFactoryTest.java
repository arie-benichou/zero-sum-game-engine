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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
import abstractions.side.SideInterface;
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

        final Map<SideInterface, Set<PieceInterface>> expectedPiecesMap = new HashMap<SideInterface, Set<PieceInterface>>(3);

        final Set<PieceInterface> nullSidePieceSet = new HashSet<PieceInterface>();
        nullSidePieceSet.add(new Null(Sides.NULL, PieceSet.NULL));
        expectedPiecesMap.put(Sides.NULL, nullSidePieceSet);

        final Set<PieceInterface> firstSidePieceSet = new HashSet<PieceInterface>();
        firstSidePieceSet.add(new Pawn(Sides.FIRST, PieceSet.PAWN));
        expectedPiecesMap.put(Sides.FIRST, firstSidePieceSet);

        final Set<PieceInterface> secondSidePieceSet = new HashSet<PieceInterface>();
        secondSidePieceSet.add(new Pawn(Sides.SECOND, PieceSet.PAWN));
        expectedPiecesMap.put(Sides.SECOND, secondSidePieceSet);

        Assert.assertTrue(expectedPiecesMap.equals(this.pieceSetFactory.newPieceSet(PieceSet.class)));

    }

    @After
    public void tearDown() throws Exception {
        this.pieceSetFactory = null; // NOPMD 
    }

}
