
package game.board.positions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NullPositionTest {

    private Positions.Interface position;

    @Before
    public void setUp() {
        this.position = new NullPosition();
    }

    @Test
    public void testNew() {

        Assert.assertEquals(0, this.position.getRow());
        Assert.assertEquals(0, this.position.getColumn());
        Assert.assertTrue(this.position.isNull());

    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(this.position.hashCode(), this.position.hashCode());
        Assert.assertNotSame(this.position.hashCode(), new Position(1, 1).hashCode());
        Assert.assertEquals(this.position.hashCode(), new NullPosition().hashCode());
    }

    @Test
    public void testEquals() {

        Assert.assertEquals(this.position, this.position);
        Assert.assertSame(this.position, this.position);

        Assert.assertFalse(this.position.equals(null));
        Assert.assertFalse(this.position.equals(new Random()));

        Assert.assertEquals(this.position, new NullPosition());
        Assert.assertNotSame(this.position, new NullPosition());

    }

    @Test
    public void testCompareTo() {

        Assert.assertEquals(0, this.position.compareTo(this.position));
        Assert.assertEquals(-1, this.position.compareTo(new Position(1, 1)));
        Assert.assertEquals(-1, this.position.compareTo(new Position(1, 3)));
        Assert.assertEquals(-1, this.position.compareTo(new Position(2, 2)));
        Assert.assertEquals(-1, this.position.compareTo(new Position(2, 3)));

    }

    @Test
    public void testOrder() {

        final List<Positions.Interface> positions = new ArrayList<Positions.Interface>(9);

        positions.add(new Position(1, 4));
        positions.add(new Position(2, 2));
        positions.add(new Position(2, 1));
        positions.add(new Position(1, 1));
        positions.add(new Position(2, 3));
        positions.add(new Position(2, 4));
        positions.add(new Position(1, 3));
        positions.add(new Position(1, 2));
        positions.add(new NullPosition());

        Assert.assertEquals(new NullPosition(), Collections.min(positions));
        Assert.assertEquals(new Position(2, 4), Collections.max(positions));

    }

    @After
    public void tearDown() {
        this.position = null;
    }

}