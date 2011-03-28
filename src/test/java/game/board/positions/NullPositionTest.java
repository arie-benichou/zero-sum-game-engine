
package game.board.positions;

import static junit.framework.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

        assertEquals(0, this.position.getRow());
        assertEquals(0, this.position.getColumn());
        assertTrue(this.position.isNull());

    }

    @Test
    public void testHashCode() {
        
        assertEquals(this.position.hashCode(), this.position.hashCode());
        assertNotSame(this.position.hashCode(), new Position(1, 1).hashCode());
        assertEquals(this.position.hashCode(), new NullPosition().hashCode());
        
    }

    @Test
    public void testEquals() {

        assertEquals(this.position, this.position);
        assertSame(this.position, this.position);

        assertFalse(this.position.equals(null));
        assertFalse(this.position.equals(new Random()));

        assertEquals(this.position, new NullPosition());
        assertNotSame(this.position, new NullPosition());

    }

    @Test
    public void testCompareTo() {

        assertEquals(0, this.position.compareTo(this.position));
        assertEquals(-1, this.position.compareTo(new Position(1, 1)));
        assertEquals(-1, this.position.compareTo(new Position(1, 3)));
        assertEquals(-1, this.position.compareTo(new Position(2, 2)));
        assertEquals(-1, this.position.compareTo(new Position(2, 3)));

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

        assertEquals(new NullPosition(), Collections.min(positions));
        assertEquals(new Position(2, 4), Collections.max(positions));

    }

    @After
    public void tearDown() {
        
        this.position = null;
        
    }

}