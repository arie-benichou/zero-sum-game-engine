package fr.designpattern.zerosumgames.samples.othello;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.game.components.moves.Move;
import fr.designpattern.zerosumgames.framework.game.components.opponents.OpponentsEnumeration;

// TODO ? implémenter une interface
public class OthelloMove extends Move {
	// ---------------------------------------------------------------------
	private List<CellInterface> cellsToRevert;
	public final void setCellsToRevert(final List<CellInterface> revertedCells) {
		this.cellsToRevert = revertedCells;
	}
	public final List<CellInterface> getCellsToRevert() {
		return this.cellsToRevert;
	}		
	// ---------------------------------------------------------------------	
	public OthelloMove(final OpponentsEnumeration side, final PositionInterface position) {
		super(side, position);
	}
	// ---------------------------------------------------------------------	
}