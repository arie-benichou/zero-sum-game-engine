package fr.designpattern.zerosumgames.framework.gameplay;

import java.util.List;

import fr.designpattern.zerosumgames.framework.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.OpponentsInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.OpponentInterface;

public class GamePlay implements GamePlayInterface {
	// ---------------------------------------------------------------------
	private final OpponentsInterface opponents;
	private final OpponentsInterface getOpponents() {
		return this.opponents;
	}	
	// ---------------------------------------------------------------------
	private final GameInterface game;
	private final GameInterface getGame() {
		return this.game;
	}	
	// ---------------------------------------------------------------------
	private transient OpponentsEnumeration sideToPlay = OpponentsEnumeration.FIRST_PLAYER;
	private final void setSideToPlay(OpponentsEnumeration sideToPlay) {
		this.sideToPlay = sideToPlay;
	}
	public final OpponentsEnumeration getSideToPlay() {
		return this.sideToPlay;
	}	
	// ---------------------------------------------------------------------
	public GamePlay(GameInterface game, OpponentsInterface opponents) {
		this.game = game;
		opponents.injectContext(game);
		this.opponents = opponents;
	}
	// ---------------------------------------------------------------------
	public List<LegalMoveInterface> getLegalMoves(final OpponentsEnumeration side) {
		return this.getGame().getLegalMoves(side);
	}
	public OpponentInterface getOpponentByOrder(final OpponentsEnumeration side) {
		return this.getOpponents().getOpponentByOrder(side);
	}
	// ---------------------------------------------------------------------	
	public void play(LegalMoveInterface move) {
		this.setSideToPlay(this.getGame().play(move));
	}
	// ---------------------------------------------------------------------
	public final boolean isGamePlayOver() {
		return !this.getSideToPlay().isAPlayer();
	}
	// ---------------------------------------------------------------------	
	@Override
	public String toString() {
		return  this.getGame().toString() + "\n" + this.getOpponents().toString(); 
	}
	// ---------------------------------------------------------------------	
}