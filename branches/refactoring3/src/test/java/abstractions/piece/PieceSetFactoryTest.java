
package abstractions.piece;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
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

public class PieceSetFactoryTest {

    private PieceSetFactory pieceSetFactory;

    @Before
    public void setUp() throws Exception {

        this.pieceSetFactory = new PieceSetFactory();

    }

    @Test(expected = IllegalPieceSetException.class)
    public final void testEmptyPieceSet() {

        this.pieceSetFactory.newPieceSet(PieceSetWithoutAnyType.class);

    }

    @Test(expected = IllegalPieceSetException.class)
    public final void testPieceSetWithAtLeastOneNotFoundPiece() {

        this.pieceSetFactory.newPieceSet(PieceSetWithAtLeastOneNotFoundPieceClass.class);

    }

    @Test(expected = IllegalPieceSetException.class)
    public final void testPieceSetWithAtLeastOnePieceClassNotImplementingPieceInterface() {

        this.pieceSetFactory.newPieceSet(PieceSetWithAtLeastOnePieceClassNotImplementingPieceInterface.class);

    }

    @Test(expected = IllegalPieceSetException.class)
    public final void testPieceSetWithOnlyNullPiece() {

        this.pieceSetFactory.newPieceSet(PieceSetWithOnlyNullType.class);

    }

    @Test(expected = IllegalPieceSetException.class)
    public final void testPieceSetWithoutNullPiece() {

        this.pieceSetFactory.newPieceSet(PieceSetWithoutNullType.class);

    }

    @Test
    public final void testLegalPieceSet() {

        HashSet<PieceInterface> expectedPieceSet = new HashSet<PieceInterface>();
        expectedPieceSet.add(new Null(Sides.NULL, PieceSet.NULL));
        expectedPieceSet.add(new Pawn(Sides.FIRST, PieceSet.PAWN));
        expectedPieceSet.add(new Pawn(Sides.SECOND, PieceSet.PAWN));

        Set<PieceInterface> pieceSet = this.pieceSetFactory.newPieceSet(PieceSet.class);
        assertTrue(pieceSet.equals(expectedPieceSet));

    }

    @After
    public void tearDown() throws Exception {
        this.pieceSetFactory = null;
    }

}
