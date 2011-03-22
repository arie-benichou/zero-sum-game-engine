package fr.designpattern.zerosumgames.framework.services;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.game.components.move.GameBoardMove;
import fr.designpattern.zerosumgames.framework.game.components.move.IGameMove;
import fr.designpattern.zerosumgames.framework.game.components.opponents.GamePlayersEnumeration;

public class GameService implements IGameService {
	// ------------------------------------------------------------
	private transient GamePlayersEnumeration currentPlayer = GamePlayersEnumeration.FIRST_PLAYER;
	// ------------------------------------------------------------
	private GameInterface game;
	public final GameInterface getGame() {
		return game;
	}
	private final void setGame(final GameInterface game) {
		this.game = game;
	}
	// ------------------------------------------------------------
	public GameService(final GameInterface game) {
		this.setGame(game);
	}
	// ------------------------------------------------------------
	// TODO créer IGameLegalMoveList
	protected void displayLegalMoveList(final List<IGameMove> legalMoveList) {
		int n = 0;		
		final int numberOfDigits = (int) Math.log10(Math.abs(legalMoveList.size())) + 1;
		//System.out.println("\n" + currentPlayerOrdinal + " legal moves :");
		System.out.println("\nlegal moves :");
		for (IGameMove legalMove : legalMoveList) {
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
		List<IGameMove> legalMoves;
		IGameMove legalMoveToPlay;
		// TODO mieux gérer le coup nul
		IGameMove lastPlayedMove = new GameBoardMove(GamePlayersEnumeration.NO_ONE, this.getGame().cell(null).getPosition());
		// ---------------------------------------------------------------------		
		System.out.println(this.game);
		// ---------------------------------------------------------------------
		do {
			// ---------------------------------------------------------------------
			legalMoves = this.game.getLegalMoves(this.currentPlayer, lastPlayedMove);
			this.displayLegalMoveList(legalMoves);
			// ---------------------------------------------------------------------			
			legalMoveToPlay = this.game.getPlayerStrategy(this.currentPlayer).chooseMoveAmong(this.game, legalMoves);
			// ---------------------------------------------------------------------
			this.currentPlayer = this.game.whoShallPlay(legalMoveToPlay, this.game.doMove(legalMoveToPlay));
			// ---------------------------------------------------------------------
			// TODO !! à gérer dans le game over d'Othello (et plus généralement dans un jeu acceptant le coup nul)
			if(legalMoveToPlay.isNull()) {
				if(lastPlayedMove.isNull()) {
					double evaluation = this.game.evaluate(legalMoveToPlay);
					if(evaluation > 0) {
						this.currentPlayer = GamePlayersEnumeration.not(GamePlayersEnumeration.opponent(legalMoveToPlay.getSide()));
					}
					else if(evaluation < 0) {
						this.currentPlayer = GamePlayersEnumeration.not(legalMoveToPlay.getSide());						
					}
					else {
						this.currentPlayer = GamePlayersEnumeration.NO_ONE;	
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
		} while (GamePlayersEnumeration.isAPlayer(this.currentPlayer));
		// ---------------------------------------------------------------------		
		System.out.println("Game over...");
		if(GamePlayersEnumeration.isNoOne(this.currentPlayer)) {
			System.out.println("There is no winner.");
		}
		else {
			System.out.println("And the winner is " + GamePlayersEnumeration.opponent(GamePlayersEnumeration.not(this.currentPlayer)) +  "!");			
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