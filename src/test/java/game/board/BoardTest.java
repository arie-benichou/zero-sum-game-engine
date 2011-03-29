package game.board;

import static org.junit.Assert.fail;
import game.board.cells.Cells;
import game.board.dimensions.Dimensions;
import game.board.positions.Positions;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private Board board;

	@Before
	public void setUp() {
		List<Positions.Interface> positions = Positions.Factory.Positions(Dimensions.Factory.Dimension(3, 3));
		List<Cells.Interface> cells = Cells.Factory.Cells(positions);
		this.board = new Board(cells);
	}

	@After
	public void tearDown() {
		this.board = null;
	}

	@Test
	public final void testBoard() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCell() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIterator() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testToString() {
		fail("Not yet implemented"); // TODO
	}

}
