
package abstractions.position.absolute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.position.absolute.AbsolutePositionInterface;

public class AbsolutePositionTest {

    private AbsolutePositionInterface position;

    @Before
    public void setUp() {

        this.position = new AbsolutePosition(1, 2);

    }

    @Test
    public void testNew() {

        assertTrue(1 == this.position.getRow());
        assertTrue(2 == this.position.getColumn());
        assertFalse(this.position.isNull());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew1() {

        new AbsolutePosition(0, 0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew2() {

        new AbsolutePosition(-1, 0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew3() {

        new AbsolutePosition(0, -1);

    }

    @Test
    public void testHashCode() {

        assertTrue(this.position.hashCode() == this.position.hashCode());
        assertTrue(this.position.hashCode() == new AbsolutePosition(1, 2).hashCode());

        assertFalse(this.position.hashCode() == new AbsolutePosition(1, 3).hashCode());
        assertFalse(this.position.hashCode() == new AbsolutePosition(2, 2).hashCode());

    }

    @Test
    public void testEquals() {

        assertTrue(this.position.equals(this.position));
        assertTrue(this.position.equals(new AbsolutePosition(1, 2)));

        assertFalse(this.position.equals(null));
        assertFalse(this.position.equals(new Random()));

        assertFalse(this.position.equals(new AbsolutePosition(1, 3)));
        assertFalse(this.position.equals(new AbsolutePosition(2, 2)));

        assertFalse(this.position == new AbsolutePosition(1, 2));

    }

    @Test(expected = NullPointerException.class)
    public void testCompareToNull() {

        this.position.compareTo(null);
    }

    @Test
    public void testCompareTo() {

        assertEquals(0, this.position.compareTo(this.position));
        assertEquals(1, this.position.compareTo(new AbsolutePosition(1, 1)));
        assertEquals(-1, this.position.compareTo(new AbsolutePosition(1, 3)));
        assertEquals(-1, this.position.compareTo(new AbsolutePosition(2, 2)));
        assertEquals(-1, this.position.compareTo(new AbsolutePosition(2, 3)));

    }

    @Test
    public void testOrder() {

        final List<AbsolutePositionInterface> positions = new ArrayList<AbsolutePositionInterface>(8);

        positions.add(new AbsolutePosition(1, 4));
        positions.add(new AbsolutePosition(2, 2));
        positions.add(new AbsolutePosition(2, 1));
        positions.add(new AbsolutePosition(1, 1));
        positions.add(new AbsolutePosition(2, 3));
        positions.add(new AbsolutePosition(2, 4));
        positions.add(new AbsolutePosition(1, 3));
        positions.add(new AbsolutePosition(1, 2));

        assertEquals(new AbsolutePosition(1, 1), Collections.min(positions));
        assertEquals(new AbsolutePosition(2, 4), Collections.max(positions));

    }

    @After
    public void tearDown() {

        this.position = null;

    }

}