
package game.board.positions;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.After;

import org.junit.Before;
import org.junit.Test;

public class PositionTest {

    private Positions.Interface position;

    @Before
    public void setUp() {
        
        this.position = new Position(1, 2);
        
    }

    @Test
    public void testNew() {

        assertEquals(1, this.position.getRow());
        assertEquals(2, this.position.getColumn());
        assertFalse(this.position.isNull());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew1() {
        
        new Position(0, 0);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew2() {
        
        new Position(-1, 0);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew3() {
        
        new Position(0, -1);
        
    }

    @Test
    public void testHashCode() {

        assertEquals(this.position.hashCode(), this.position.hashCode());
        assertNotSame(this.position.hashCode(), new Position(1, 1).hashCode());
        assertEquals(this.position.hashCode(), new Position(1, 2).hashCode());

    }

    @Test
    public void testEquals() {

        assertTrue(this.position.equals(this.position));
        assertSame(this.position, this.position);

        assertFalse(this.position.equals(null));
        assertFalse(this.position.equals(new Random()));

        assertFalse(this.position.equals(new Position(1, 3)));
        assertFalse(this.position.equals(new Position(2, 2)));

        assertTrue(this.position.equals(new Position(1, 2)));
        assertNotSame(this.position, new Position(1, 2));

    }

    @Test
    public void testCompareTo() {

        assertEquals(0, this.position.compareTo(this.position));
        assertEquals(1, this.position.compareTo(new Position(1, 1)));
        assertEquals(-1, this.position.compareTo(new Position(1, 3)));
        assertEquals(-1, this.position.compareTo(new Position(2, 2)));
        assertEquals(-1, this.position.compareTo(new Position(2, 3)));

    }

    @Test
    public void testOrder() {

        final List<Positions.Interface> positions = new ArrayList<Positions.Interface>(8);

        positions.add(new Position(1, 4));
        positions.add(new Position(2, 2));
        positions.add(new Position(2, 1));
        positions.add(new Position(1, 1));
        positions.add(new Position(2, 3));
        positions.add(new Position(2, 4));
        positions.add(new Position(1, 3));
        positions.add(new Position(1, 2));

        assertEquals(new Position(1, 1), Collections.min(positions));
        assertEquals(new Position(2, 4), Collections.max(positions));

    }

    @After
    public void tearDown() {
        
        this.position = null;
        
    }

}