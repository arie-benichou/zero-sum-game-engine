
package abstractions.side;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SidesTest {

    @Test
    public final void testSides() {
        assertTrue(Sides.FIRST == Side.FIRST_SIDE);
        assertTrue(Sides.SECOND == Side.SECOND_SIDE);
        assertTrue(Sides.NULL == Side.NULL_SIDE);
    }

}
