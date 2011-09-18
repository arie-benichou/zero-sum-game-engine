package fr.designpattern.zerosumgames.concretisations.reversi;

import java.util.Map;

import fr.designpattern.zerosumgames.abstractions.immutable.GamePlayManagerInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.GamePlayManagerServiceInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.rendering.board.BoardStringRendering;
import fr.designpattern.zerosumgames.abstractions.immutable.rendering.board.cell.BoardCellStringRendering;
import fr.designpattern.zerosumgames.abstractions.immutable.rendering.board.cell.piece.PieceStringRendering;

public final class mySignal implements SignalInterface{

	/*-------------------------------------8<-------------------------------------*/

	private final GamePlayManagerServiceInterface gamePlayManagerService;
	private final GamePlayManagerInterface gamePlayManager;
	private final Map<Object, Object> symbols;

	/*-------------------------------------8<-------------------------------------*/

	public mySignal(final GamePlayManagerServiceInterface gamePlayManagerService, final GamePlayManagerInterface gmpm, final Map<Object, Object> symbols) {
		this.gamePlayManagerService = gamePlayManagerService;
		this.gamePlayManager = gmpm;
		this.symbols = symbols;
	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public void process() {
		this.gamePlayManagerService.start(this.gamePlayManager, this.symbols);
	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public String toString() {
		final BoardStringRendering rendering = new BoardStringRendering(new BoardCellStringRendering(new PieceStringRendering()));
		return rendering.render(this.gamePlayManager.gameplay().game().board(), this.symbols);
	}

	/*-------------------------------------8<-------------------------------------*/

}