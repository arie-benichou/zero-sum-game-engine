
package abstractions.position.absolute;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NullAbsolutePositionTest {

    private AbsolutePositionInterface position;

    @Before
    public void setUp() {

        this.position = new NullAbsolutePosition();

    }

    @Test
    public void testNew() {

        assertTrue(0 == this.position.getRow());
        assertTrue(0 == this.position.getColumn());
        assertTrue(this.position.isNull());

    }

    @Test
    public void testHashCode() {

        assertTrue(this.position.hashCode() == this.position.hashCode());
        assertFalse(this.position.hashCode() == new AbsolutePosition(1, 1).hashCode());
        assertTrue(this.position.hashCode() == new NullAbsolutePosition().hashCode());

    }

    @Test
    public void testEquals() {

        assertTrue(this.position.equals(this.position));
        assertFalse(this.position.equals(null));
        assertFalse(this.position.equals(new Random()));
        assertTrue(this.position.equals(new NullAbsolutePosition()));
        assertFalse(this.position == new NullAbsolutePosition());

    }

    @Test(expected = NullPointerException.class)
    public void testCompareToNull() {

        this.position.compareTo(null);

    }

    @Test
    public void testCompareTo() {

        assertEquals(0, this.position.compareTo(this.position));
        assertEquals(-1, this.position.compareTo(new AbsolutePosition(1, 1)));
        assertEquals(-1, this.position.compareTo(new AbsolutePosition(1, 3)));
        assertEquals(-1, this.position.compareTo(new AbsolutePosition(2, 2)));
        assertEquals(-1, this.position.compareTo(new AbsolutePosition(2, 3)));

    }

    @Test
    public void testOrder() {

        final List<AbsolutePositionInterface> positions = new ArrayList<AbsolutePositionInterface>(9);

        positions.add(new AbsolutePosition(1, 4));
        positions.add(new AbsolutePosition(2, 2));
        positions.add(new AbsolutePosition(2, 1));
        positions.add(new AbsolutePosition(1, 1));
        positions.add(new AbsolutePosition(2, 3));
        positions.add(new AbsolutePosition(2, 4));
        positions.add(new AbsolutePosition(1, 3));
        positions.add(new AbsolutePosition(1, 2));
        positions.add(new NullAbsolutePosition());

        assertEquals(new NullAbsolutePosition(), Collections.min(positions));
        assertEquals(new AbsolutePosition(2, 4), Collections.max(positions));

    }

    @After
    public void tearDown() {

        this.position = null;

    }

}