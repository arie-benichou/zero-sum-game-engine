
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PositionTest {

    private BoardPositionInterface position;

    @Before
    public void setUp() {
        this.position = new BoardPosition(1, 2);
    }

    @Test
    public void testNew() {

        Assert.assertEquals(1, this.position.getRow());
        Assert.assertEquals(2, this.position.getColumn());
        Assert.assertFalse(this.position.isNull());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew1() {
        new BoardPosition(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew2() {
        new BoardPosition(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew3() {
        new BoardPosition(0, -1);
    }

    @Test
    public void testHashCode() {

        Assert.assertEquals(this.position.hashCode(), this.position.hashCode());
        Assert.assertNotSame(this.position.hashCode(), new BoardPosition(1, 1).hashCode());
        Assert.assertEquals(this.position.hashCode(), new BoardPosition(1, 2).hashCode());

    }

    @Test
    public void testEquals() {

        Assert.assertTrue(this.position.equals(this.position));
        Assert.assertSame(this.position, this.position);

        Assert.assertFalse(this.position.equals(null));
        Assert.assertFalse(this.position.equals(new Random()));

        Assert.assertFalse(this.position.equals(new BoardPosition(1, 3)));
        Assert.assertFalse(this.position.equals(new BoardPosition(2, 2)));

        Assert.assertTrue(this.position.equals(new BoardPosition(1, 2)));
        Assert.assertNotSame(this.position, new BoardPosition(1, 2));

    }

    @Test
    public void testCompareTo() {

        Assert.assertEquals(0, this.position.compareTo(this.position));
        Assert.assertEquals(1, this.position.compareTo(new BoardPosition(1, 1)));
        Assert.assertEquals(-1, this.position.compareTo(new BoardPosition(1, 3)));
        Assert.assertEquals(-1, this.position.compareTo(new BoardPosition(2, 2)));
        Assert.assertEquals(-1, this.position.compareTo(new BoardPosition(2, 3)));

    }

    @Test
    public void testOrder() {

        final List<BoardPositionInterface> positions = new ArrayList<BoardPositionInterface>(8);

        positions.add(new BoardPosition(1, 4));
        positions.add(new BoardPosition(2, 2));
        positions.add(new BoardPosition(2, 1));
        positions.add(new BoardPosition(1, 1));
        positions.add(new BoardPosition(2, 3));
        positions.add(new BoardPosition(2, 4));
        positions.add(new BoardPosition(1, 3));
        positions.add(new BoardPosition(1, 2));

        Assert.assertEquals(new BoardPosition(1, 1), Collections.min(positions));
        Assert.assertEquals(new BoardPosition(2, 4), Collections.max(positions));

    }

    @After
    public void tearDown() {
        this.position = null;
    }

}