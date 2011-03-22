package fr.designpattern.zerosumgames.extensions.othello;

import java.util.List;

import fr.designpattern.zerosumgames.framework.build.game.components.board.cell.IGameBoardCell;
import fr.designpattern.zerosumgames.framework.build.game.components.board.position.IGameBoardPosition;
import fr.designpattern.zerosumgames.framework.build.game.components.move.GameBoardMove;
import fr.designpattern.zerosumgames.framework.build.game.components.opponents.GamePlayersEnumeration;

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