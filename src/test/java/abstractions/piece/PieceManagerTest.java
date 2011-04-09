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
import org.junit.Before;
import org.junit.Test;

import abstractions.piece.mocks.PieceSet;
import abstractions.piece.mocks.PieceSetWithoutNullType;
import abstractions.side.Sides;

public class PieceManagerTest {

    private PieceManager pieceManager;

    @Before
    public void setUp() throws Exception {

        this.pieceManager = new PieceManager(PieceSet.class);

    }

    @Test(expected = IllegalPieceException.class)
    public final void testGetIllegalPiece() {

        this.pieceManager.getPiece(Sides.FIRST, PieceSetWithoutNullType.PAWN);

    }

    @Test
    public final void testGetNullPiece() {

        this.pieceManager.getPiece(Sides.NULL, PieceSet.NULL);

    }

    @Test
    public final void testGetPiece() {

        this.pieceManager.getPiece(Sides.FIRST, PieceSet.PAWN);
        this.pieceManager.getPiece(Sides.SECOND, PieceSet.PAWN);

    }

    @After
    public void tearDown() throws Exception {

        this.pieceManager = null;

    }

}
