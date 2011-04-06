
package abstractions.position.relative;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RelativePositionTest {

    private RelativePosition relativePosition;

    @Before
    public void setUp() throws Exception {
        this.relativePosition = new RelativePosition(1, -1);
    }

    @Test
    public final void testGetRowDelta() {
        assertTrue(this.relativePosition.getRowDelta() == 1);
    }

    @Test
    public final void testGetColumnDelta() {
        assertTrue(this.relativePosition.getColumnDelta() == -1);
    }

    @Test
    public final void testComputeRow() {
        assertTrue(this.relativePosition.computeRow(3) == 4);
    }

    @Test
    public final void testComputeColumn() {
        assertTrue(this.relativePosition.computeColumn(5) == 4);
    }

    @After
    public void tearDown() throws Exception {
        this.relativePosition = null;
    }

}
