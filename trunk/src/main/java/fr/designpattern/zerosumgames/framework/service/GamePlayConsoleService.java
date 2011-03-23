package fr.designpattern.zerosumgames.framework.service;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.GamePlayInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.OpponentInterface;

public class GamePlayConsoleService extends AbstractGamePlayService {
	// ------------------------------------------------------------
	public GamePlayConsoleService(GamePlayInterface gamePlay) {
		super(gamePlay);
	}
	// ------------------------------------------------------------	
	public void start() {
		
		OpponentsEnumeration sideToPlay;
		OpponentInterface opponent;
		List<LegalMoveInterface> legalMoves;
		LegalMoveInterface legalMove;
		String result;
		
		System.out.println(this);
		
		do {
			
			sideToPlay = this.getSideToPlay();
			
			opponent = this.getOpponentByOrder(sideToPlay);
			
			System.out.println(opponent + " must play...");
			
			legalMoves = this.getLegalMoves(sideToPlay);

			legalMove = opponent.selectMove(legalMoves);
			
			this.play(legalMove);
			
			System.out.println(this);
			
		} while (!this.isGamePlayOver());
		
		result =
			"Gameplay is over." + "\n\n" +
			(this.getSideToPlay().isNoOne() ?
				"There is no winner."
			:
				"And the winner is : " + this.getOpponentByOrder(this.getSideToPlay().getNegation().getOpponent()))
		;
		
		System.out.println(result);
		
	}
	// ---------------------------------------------------------------------
	public void reset() {}
	public void pause() {}
	public void resume() {}
	public void stop() {}
	// ------------------------------------------------------------
	@Override
	public String toString() {
		return
			"\n===================================================\n" +
			this.getGamePlay().toString() +
			"\n===================================================\n"
		;
	}
	
	// ---------------------------------------------------------------------
}