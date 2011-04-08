
package abstractions.piece;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.piece.PieceSetFactory.IllegalPiecesAlphabetException;
import abstractions.piece.mocks.EmptyPiecesAlphabet;
import abstractions.piece.mocks.LegalPiecesAlphabet;
import abstractions.piece.mocks.Null;
import abstractions.piece.mocks.Pawn;
import abstractions.piece.mocks.PiecesAlphabetWithAtLeastOneNotFoundPieceClass;
import abstractions.piece.mocks.PiecesAlphabetWithAtLeastOnePieceClassNotImplementingPieceInterface;
import abstractions.piece.mocks.PiecesAlphabetWithOnlyNullPiece;
import abstractions.piece.mocks.PiecesAlphabetWithoutNullPiece;
import abstractions.side.Sides;

public class PieceFactoryTest {

    private PieceSetFactory pieceFactory;

    @Before
    public void setUp() throws Exception {
        this.pieceFactory = new PieceSetFactory(LegalPiecesAlphabet.class);
    }

    @Test
    public final void testNullPiece() {
        assertTrue(this.pieceFactory.NullPiece().equals(new Null(Sides.NULL)));
    }

    //@Test
    public final void testPiece() {
        assertTrue(pieceFactory.Piece(Sides.NULL, LegalPiecesAlphabet.NULL).equals(new Null(Sides.NULL)));
        assertTrue(pieceFactory.Piece(Sides.FIRST, LegalPiecesAlphabet.PAWN).equals(new Pawn(Sides.FIRST)));
        assertTrue(pieceFactory.Piece(Sides.SECOND, LegalPiecesAlphabet.PAWN).equals(new Pawn(Sides.SECOND)));

        //TODO à faire dans AbstractPieceTest
        /*
        assertFalse(pieceFactory.Piece(Sides.FIRST_SIDE, Pieces.PAWN).equals(new Pawn(Sides.SECOND_SIDE)));
        assertFalse(pieceFactory.Piece(Sides.FIRST_SIDE, Pieces.PAWN).equals(new Null()));
        */
    }

    /*
    @Test(expected = IllegalPiecesAlphabetException.class)
    public final void testPiecesAlphabetNotImplementingPieceTypeInterface() {
        new PieceFactory(PiecesAlphabetNotImplementingPieceTypeInterface.class);
    }
    */

    @Test(expected = IllegalPiecesAlphabetException.class)
    public final void testEmptyPiecesAlphabet() {
        new PieceSetFactory(EmptyPiecesAlphabet.class);
    }

    @Test(expected = IllegalPiecesAlphabetException.class)
    public final void testPiecesAlphabetWithAtLeastOneNotFoundPiece() {
        new PieceSetFactory(PiecesAlphabetWithAtLeastOneNotFoundPieceClass.class);
    }

    @Test(expected = IllegalPiecesAlphabetException.class)
    public final void testPiecesAlphabetWithAtLeastOnePieceClassNotImplementingPieceInterface() {
        new PieceSetFactory(PiecesAlphabetWithAtLeastOnePieceClassNotImplementingPieceInterface.class);
    }

    @Test(expected = IllegalPiecesAlphabetException.class)
    public final void testPiecesAlphabetWithOnlyNullPiece() {
        new PieceSetFactory(PiecesAlphabetWithOnlyNullPiece.class);
    }

    @Test(expected = IllegalPiecesAlphabetException.class)
    public final void testPiecesAlphabetWithoutNullPiece() {
        new PieceSetFactory(PiecesAlphabetWithoutNullPiece.class);
    }

    @After
    public void tearDown() throws Exception {
        this.pieceFactory = null;
    }

}
