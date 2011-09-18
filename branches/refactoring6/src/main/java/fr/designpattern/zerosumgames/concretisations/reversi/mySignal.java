package fr.designpattern.zerosumgames.concretisations.reversi;

import java.util.Map;

import fr.designpattern.zerosumgames.abstractions.immutable.GamePlayManagerInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.GamePlayManagerServiceInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.rendering.board.BoardStringRendering;
import fr.designpattern.zerosumgames.abstractions.immutable.rendering.board.cell.BoardCellStringRendering;
import fr.designpattern.zerosumgames.abstractions.immutable.rendering.board.cell.piece.PieceStringRendering;

public final class mySignal {

	/*-------------------------------------8<-------------------------------------*/

	private final GamePlayManagerServiceInterface gamePlayManagerService;
	public GamePlayManagerServiceInterface gamePlayManagerService() {
		return this.gamePlayManagerService;
	}

	/*-------------------------------------8<-------------------------------------*/

	private final GamePlayManagerInterface gamePlayManager;
	public GamePlayManagerInterface gamePlayManager() {
		return this.gamePlayManager;
	}

	/*-------------------------------------8<-------------------------------------*/

	private final Map<Object, Object> symbols;
	public Map<Object, Object> symbols() {
		return this.symbols;
	}

	/*-------------------------------------8<-------------------------------------*/

	public mySignal(final GamePlayManagerServiceInterface gamePlayManagerService, final GamePlayManagerInterface gmpm, final Map<Object, Object> symbols) {
		this.gamePlayManagerService = gamePlayManagerService;
		this.gamePlayManager = gmpm;
		this.symbols = symbols;
	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public String toString() {
		final BoardStringRendering rendering = new BoardStringRendering(new BoardCellStringRendering(new PieceStringRendering()));
		return rendering.render(this.gamePlayManager().gameplay().game().board(), this.symbols());
	}

}
