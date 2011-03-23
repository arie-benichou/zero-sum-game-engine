package fr.designpattern.zerosumgames.framework.service;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.GamePlayInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.OpponentInterface;

public abstract class AbstractGamePlayService implements GamePlayServiceInterface {
	// ------------------------------------------------------------
	protected final GamePlayInterface gamePlay;
	public final GamePlayInterface getGamePlay() {
		return gamePlay;
	}
	// ------------------------------------------------------------
	public AbstractGamePlayService(final GamePlayInterface gamePlay) {
		this.gamePlay = gamePlay;
	}
	// ------------------------------------------------------------
	public abstract void start();
	public abstract void reset();
	public abstract void pause();
	public abstract void resume();
	public abstract void stop();
	// ---------------------------------------------------------------------
	protected final OpponentsEnumeration getSideToPlay() {
		return this.getGamePlay().getSideToPlay();
	}
	// ---------------------------------------------------------------------	
	protected final List<LegalMoveInterface>  getLegalMoves(final OpponentsEnumeration sideToPlay) {
		return this.getGamePlay().getLegalMoves(sideToPlay);
	}
	// ---------------------------------------------------------------------	
	protected final OpponentInterface getOpponentByOrder(final OpponentsEnumeration sideToPlay) {
		return this.getGamePlay().getOpponentByOrder(sideToPlay);
	}
	// ---------------------------------------------------------------------	
	protected final void play(LegalMoveInterface legalMove) {
		this.getGamePlay().play(legalMove);
	}
	// ---------------------------------------------------------------------	
	protected final boolean isGamePlayOver() {
		return this.gamePlay.isGamePlayOver();
	}
	// ---------------------------------------------------------------------	
	
}