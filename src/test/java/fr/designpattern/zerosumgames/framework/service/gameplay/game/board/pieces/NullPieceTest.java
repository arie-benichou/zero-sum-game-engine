
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.pieces;

import java.util.Random;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

public class NullPieceTest {

    private NullPiece piece;

    @Before
    public void setUp() {
        this.piece = new NullPiece();
    }

    @Test
    public void testNew() {
        Assert.assertEquals(OpponentsEnumeration.NO_ONE, this.piece.getSide());
        Assert.assertTrue(this.piece.isNull());
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(this.piece.hashCode(), this.piece.hashCode());
        Assert.assertNotSame(this.piece.hashCode(), new Piece(OpponentsEnumeration.FIRST_PLAYER).hashCode());
        Assert.assertNotSame(this.piece.hashCode(), new Piece(OpponentsEnumeration.SECOND_PLAYER).hashCode());
        Assert.assertEquals(this.piece.hashCode(), new NullPiece().hashCode());
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(this.piece, this.piece);
        Assert.assertSame(this.piece, this.piece);
        Assert.assertFalse(this.piece.equals(null));
        Assert.assertFalse(this.piece.equals(new Random()));
        Assert.assertFalse(this.piece.equals(new Piece(OpponentsEnumeration.FIRST_PLAYER)));
        Assert.assertFalse(this.piece.equals(new Piece(OpponentsEnumeration.SECOND_PLAYER)));
        Assert.assertEquals(this.piece, new NullPiece());
        Assert.assertNotSame(this.piece, new NullPiece());
    }

    @After
    public void tearDown() {
        this.piece = null;
    }

}