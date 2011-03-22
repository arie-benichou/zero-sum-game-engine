package fr.designpattern.zerosumgames.samples.othello;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.board.cells.IGameBoardCell;
import fr.designpattern.zerosumgames.framework.game.components.board.positions.IGameBoardPosition;
import fr.designpattern.zerosumgames.framework.game.components.moves.GameBoardMove;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.GamePlayersEnumeration;

// TODO ? implémenter une interface
public class OthelloMove extends GameBoardMove {
	// ---------------------------------------------------------------------
	private List<IGameBoardCell> cellsToRevert;
	public final void setCellsToRevert(final List<IGameBoardCell> revertedCells) {
		this.cellsToRevert = revertedCells;
	}
	public final List<IGameBoardCell> getCellsToRevert() {
		return this.cellsToRevert;
	}		
	// ---------------------------------------------------------------------	
	public OthelloMove(final GamePlayersEnumeration side, final IGameBoardPosition position) {
		super(side, position);
	}
	// ---------------------------------------------------------------------	
}