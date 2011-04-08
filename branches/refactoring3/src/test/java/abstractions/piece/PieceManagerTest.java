
package abstractions.piece;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.piece.mocks.PieceSet;
//import abstractions.piece.mocks.PieceSetWithoutNullType;
import abstractions.side.Sides;

public class PieceManagerTest {

    private PieceManager pieceManager;

    @Before
    public void setUp() throws Exception {

        this.pieceManager = new PieceManager(PieceSet.class);

    }

    //@Test(expected = IllegalPieceException.class)
    public final void testGetIllegalPiece() {

        //this.pieceManager.getPiece(Sides.FIRST, PieceSetWithoutNullType.PAWN);

    }

    @Test
    public final void testGetNullPiece() {

        this.pieceManager.getPiece(Sides.NULL, PieceSet.PAWN);

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
