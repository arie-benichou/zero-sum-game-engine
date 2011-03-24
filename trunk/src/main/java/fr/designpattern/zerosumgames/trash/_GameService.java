package fr.designpattern.zerosumgames.trash;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.GamePlayServiceInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.GamePlay;
import fr.designpattern.zerosumgames.framework.service.gameplay.GamePlayInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMove;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

public class _GameService implements GamePlayServiceInterface {
	// ------------------------------------------------------------
	private transient OpponentsEnumeration currentPlayer = OpponentsEnumeration.FIRST_PLAYER;
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
	// TODO à mettre dans IGameLegalMoveList
	protected void displayLegalMoveList(final List<LegalMoveInterface> legalMoveList) {
		int n = 0;		
		final int numberOfDigits = (int) Math.log10(Math.abs(legalMoveList.size())) + 1;
		//System.out.println("\n" + currentPlayerOrdinal + " legal moves :");
		System.out.println("\nlegal moves :");
		for (LegalMoveInterface legalMove : legalMoveList) {
			System.out.format("#%0" + numberOfDigits + "d: %s\n", ++n, legalMove);
		}
	}
	// ---------------------------------------------------------------------
	public void start() {
		// ---------------------------------------------------------------------		
		// TODO utiliser un thread pour le client lourd
		// TODO pas de boucle pour la version client léger	
		// TODO GameWebService		
		// ---------------------------------------------------------------------
		List<LegalMoveInterface> legalMoves;
		LegalMoveInterface legalMoveToPlay;
		// TODO mieux gérer le coup nul
		LegalMoveInterface lastPlayedMove = new LegalMove(OpponentsEnumeration.NO_ONE, this.getGamePlay().cell(null).getPosition());
		// ---------------------------------------------------------------------		
		System.out.println(this.gamePlay);
		// ---------------------------------------------------------------------
		do {
			legalMoves = this.getGamePlay().getLegalMoves(this.currentPlayer);
			this.displayLegalMoveList(legalMoves);			
			legalMoveToPlay = this.getGamePlay().getOpponentByOrder(this.currentPlayer).selectMove(legalMoves);
			// ---------------------------------------------------------------------
			//this.currentPlayer = this.getGamePlay().whoShallPlay(legalMoveToPlay, this.game.doMove(legalMoveToPlay));
			this.currentPlayer = this.getGamePlay().whoShallPlay(legalMoveToPlay, this.game.doMove(legalMoveToPlay));
			// ---------------------------------------------------------------------
			// TODO !! à gérer dans le game over d'Othello (et plus généralement dans un jeu acceptant le coup nul)
			if(legalMoveToPlay.isNull()) {
				if(lastPlayedMove.isNull()) {
					double evaluation = this.game.evaluate(legalMoveToPlay);
					if(evaluation > 0) {
						this.currentPlayer = OpponentsEnumeration.not(OpponentsEnumeration.opponent(legalMoveToPlay.getSide()));
					}
					else if(evaluation < 0) {
						this.currentPlayer = OpponentsEnumeration.not(legalMoveToPlay.getSide());						
					}
					else {
						this.currentPlayer = OpponentsEnumeration.NO_ONE;	
					}
				}
			}
			// ---------------------------------------------------------------------
			lastPlayedMove = legalMoveToPlay;
			// ---------------------------------------------------------------------						
			System.out.println(this.game);
			// ---------------------------------------------------------------------
			//System.out.println("next player = " + this.currentPlayer);
			
			//System.exit(0);
			
			// ---------------------------------------------------------------------
		} while (OpponentsEnumeration.isAPlayer(this.currentPlayer));
		// ---------------------------------------------------------------------		
		System.out.println("Game over...");
		if(OpponentsEnumeration.isNoOne(this.currentPlayer)) {
			System.out.println("There is no winner.");
		}
		else {
			System.out.println("And the winner is " + OpponentsEnumeration.opponent(OpponentsEnumeration.not(this.currentPlayer)) +  "!");			
		}
		
		// ---------------------------------------------------------------------		
	}
	// ---------------------------------------------------------------------
	public void reset() {
		// TODO Auto-generated method stub
	}	
	// ---------------------------------------------------------------------	
	public void pause() {
		// TODO Auto-generated method stub
	}
	// ---------------------------------------------------------------------
	public void resume() {
		// TODO Auto-generated method stub
	}
	// ---------------------------------------------------------------------
	public void stop() {
		// TODO Auto-generated method stub
	}
	// ---------------------------------------------------------------------
}