
package abstractions.cell;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.API.DimensionFactory;
import abstractions.piece.PieceManager;
import abstractions.piece.PieceManagerInterface;
import abstractions.piece.mocks.PieceSet;
import abstractions.position.PositionManager;
import abstractions.position.PositionManagerInterface;

public class CellManagerTest {

    private CellManager cellManager;

    @Before
    public void setUp() throws Exception {
        final PositionManagerInterface positionManager = new PositionManager(DimensionFactory.Dimension(3, 3));
        final PieceManagerInterface pieceManager = new PieceManager(PieceSet.class);
        this.cellManager = new CellManager(positionManager, pieceManager);
    }

    @After
    public void tearDown() throws Exception {
        this.cellManager = null;
    }

    @Test
    public void testCellManager() {
        System.out.println(this.cellManager);
    }

}
