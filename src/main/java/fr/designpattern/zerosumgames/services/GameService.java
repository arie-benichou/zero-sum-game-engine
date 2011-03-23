package fr.designpattern.zerosumgames.services;

import java.util.List;

import fr.designpattern.zerosumgames.framework.gameplay.GamePlayInterface;
import fr.designpattern.zerosumgames.framework.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.OpponentInterface;

public class GameService implements GameServiceInterface {
	// ------------------------------------------------------------
	private final GamePlayInterface gamePlay;
	public final GamePlayInterface getGamePlay() {
		return gamePlay;
	}
	// ------------------------------------------------------------
	public GameService(final GamePlayInterface gamePlay) {
		this.gamePlay = gamePlay;
	}
	// ------------------------------------------------------------
	public void start() {
		OpponentInterface opponent;
		List<LegalMoveInterface> legalMoves;
		OpponentsEnumeration sideToPlay;
		System.out.println(this.getGamePlay());
		do {
			sideToPlay = this.getGamePlay().getSideToPlay();
			legalMoves = this.getGamePlay().getLegalMoves(sideToPlay);
			opponent = this.getGamePlay().getOpponentByOrder(sideToPlay);
			this.getGamePlay().play(opponent.selectMove(legalMoves));
			System.out.println(this.getGamePlay());
		} while (OpponentsEnumeration.isAPlayer(this.getGamePlay().getSideToPlay()));
		sideToPlay = this.getGamePlay().getSideToPlay();
		System.out.println("Game over...");
		if(OpponentsEnumeration.isNoOne(sideToPlay)) {
			System.out.println("There is no winner.");
		}
		else {
			System.out.println("And the winner is " + OpponentsEnumeration.opponent(OpponentsEnumeration.not(sideToPlay)) +  "!");
		}
	}
	// ---------------------------------------------------------------------
	public void reset() {}
	public void pause() {}
	public void resume() {}
	public void stop() {}
	// ---------------------------------------------------------------------
}