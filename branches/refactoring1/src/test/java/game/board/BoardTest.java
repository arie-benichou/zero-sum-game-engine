package game.board;


import static game.board.dimension.API.DimensionFactory.*;
import static game.board.position.API.*;
import static game.board.position.API.PositionFactory.*;
import static game.board.cell.API.*;
import static game.board.cell.API.CellFactory.*;


import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private Board board;

	@Before
	public void setUp() {
		List<PositionInterface> positions = Positions(Dimension(3, 3));
		List<CellInterface> cells =  Cells(positions);
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
