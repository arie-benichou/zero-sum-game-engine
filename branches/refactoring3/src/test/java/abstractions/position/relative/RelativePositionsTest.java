
package abstractions.position.relative;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RelativePositionsTest {

    private List<RelativePositionInterface> relativePositions = new ArrayList<RelativePositionInterface>();

    @Before
    public void setUp() throws Exception {
        
        this.relativePositions = new ArrayList<RelativePositionInterface>();
        this.relativePositions.add(new RelativePosition(0, -1));
        this.relativePositions.add(new RelativePosition(1, 0));
        this.relativePositions.add(new RelativePosition(0, 1));
        this.relativePositions.add(new RelativePosition(-1, 1));
        
    }

    @Test
    public final void testReduceListOfRelativePositionInterface() {
        
        assertTrue(RelativePositions.RIGHT.equals(RelativePositions.reduce(this.relativePositions)));
        assertTrue(RelativePositions.RIGHT == (RelativePositions.reduce(this.relativePositions)));
        
    }

    @Test
    public final void testReduceRelativePositionInterfaceArray() {
        
        RelativePositionInterface relativePosition = RelativePositions.reduce(this.relativePositions.get(0), this.relativePositions.get(1), this.relativePositions.get(2), this.relativePositions.get(3));
        assertTrue(RelativePositions.RIGHT.equals(relativePosition));
        assertTrue(RelativePositions.RIGHT == (relativePosition));
        
    }

    @After
    public void tearDown() throws Exception {
        
        this.relativePositions = null;
        
    }

}
