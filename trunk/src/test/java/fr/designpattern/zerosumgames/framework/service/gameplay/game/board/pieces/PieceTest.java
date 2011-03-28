
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.pieces;

import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

public class PieceTest {

    private PieceInterface piece;

    @Before
    public void setUp() {
        this.piece = new Piece(OpponentsEnumeration.FIRST_PLAYER);
    }

    @Test
    public void testNew() {
        Assert.assertEquals(OpponentsEnumeration.FIRST_PLAYER, this.piece.getSide());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew1() {
        new Piece(OpponentsEnumeration.NO_ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew2() {
        new Piece(OpponentsEnumeration.NOT_FIRST_PLAYER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew3() {
        new Piece(OpponentsEnumeration.NOT_SECOND_PLAYER);
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(this.piece.hashCode(), this.piece.hashCode());
        Assert.assertNotSame(this.piece.hashCode(), new Piece(OpponentsEnumeration.SECOND_PLAYER).hashCode());
        Assert.assertEquals(this.piece.hashCode(), new Piece(OpponentsEnumeration.FIRST_PLAYER).hashCode());
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(this.piece.equals(this.piece));
        Assert.assertSame(this.piece, this.piece);
        Assert.assertFalse(this.piece.equals(null));
        Assert.assertFalse(this.piece.equals(new Random()));
        Assert.assertFalse(this.piece.equals(new Piece(OpponentsEnumeration.SECOND_PLAYER)));
        Assert.assertTrue(this.piece.equals(new Piece(OpponentsEnumeration.FIRST_PLAYER)));
        Assert.assertNotSame(this.piece, new Piece(OpponentsEnumeration.FIRST_PLAYER));
    }

    @After
    public void tearDown() {
        this.piece = null;
    }

}