
package abstractions.piece;

import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.cell.CellInterface;
import abstractions.piece.mocks.PieceSet;
import abstractions.side.SideInterface;
import abstractions.side.Sides;

public class AbstractPieceTest {

    private AbstractPiece nullPiece;
    private AbstractPiece firstSidePiece;
    private AbstractPiece secondSidePiece;;

    @Before
    public void setUp() throws Exception {

        this.nullPiece = new AbstractPiece(Sides.NULL, PieceSet.NULL) {

            @Override
            public Set<? extends PieceActionTypeInterface> computePotentialActionTypes(final CellInterface cell, final SideInterface side) {
                return null;
            }
        };

        this.firstSidePiece = new AbstractPiece(Sides.FIRST, PieceSet.PAWN) {

            @Override
            public Set<? extends PieceActionTypeInterface> computePotentialActionTypes(final CellInterface cell, final SideInterface side) {
                return null;
            }
        };

        this.secondSidePiece = new AbstractPiece(Sides.SECOND, PieceSet.PAWN) {

            @Override
            public Set<? extends PieceActionTypeInterface> computePotentialActionTypes(final CellInterface cell, final SideInterface side) {
                return null;
            }
        };

    }

    @Test
    public void testGetSide() {
        Assert.assertTrue(this.firstSidePiece.getSide().equals(Sides.FIRST));
        Assert.assertTrue(this.nullPiece.getSide().equals(Sides.NULL));
        Assert.assertTrue(this.secondSidePiece.getSide().equals(Sides.SECOND));
    }

    @Test
    public void testGetType() {

        Assert.assertTrue(this.firstSidePiece.getType().equals(PieceSet.PAWN));
        Assert.assertTrue(this.nullPiece.getType().equals(PieceSet.NULL));
        Assert.assertTrue(this.secondSidePiece.getType().equals(PieceSet.PAWN));

    }

    @Test
    public void testHashCode() {

        Assert.assertTrue(this.firstSidePiece.hashCode() != this.secondSidePiece.hashCode());
        Assert.assertTrue(this.nullPiece.hashCode() != this.secondSidePiece.hashCode());

    }

    @Test
    public void testEqualsObjectFirstSidePiece() {
        Assert.assertTrue(this.firstSidePiece.equals(this.firstSidePiece));
        Assert.assertFalse(this.firstSidePiece.equals(this.nullPiece));
        Assert.assertFalse(this.firstSidePiece.equals(this.secondSidePiece));
        Assert.assertFalse(this.firstSidePiece.equals(new Random()));
        Assert.assertFalse(this.firstSidePiece.equals(null));
    }

    @Test
    public void testEqualsObjectNullPiece() {
        Assert.assertFalse(this.nullPiece.equals(this.firstSidePiece));
        Assert.assertTrue(this.nullPiece.equals(this.nullPiece));
        Assert.assertFalse(this.nullPiece.equals(this.secondSidePiece));
        Assert.assertFalse(this.nullPiece.equals(new Random()));
        Assert.assertFalse(this.nullPiece.equals(null));
    }

    @Test
    public void testEqualsObjectSecondSidePiece() {
        Assert.assertFalse(this.secondSidePiece.equals(this.firstSidePiece));
        Assert.assertFalse(this.secondSidePiece.equals(this.nullPiece));
        Assert.assertTrue(this.secondSidePiece.equals(this.secondSidePiece));
        Assert.assertFalse(this.secondSidePiece.equals(new Random()));
        Assert.assertFalse(this.secondSidePiece.equals(null));
    }

    @After
    public void tearDown() throws Exception {

        this.nullPiece = null;
        this.firstSidePiece = null;
        this.secondSidePiece = null;

    }

}
