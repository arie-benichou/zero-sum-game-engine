package fr.designpattern.zerosumgames.services;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.moves.Move;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.opponents.OpponentsEnumeration;

public class GameService implements IGameService {
	// ------------------------------------------------------------
	private transient OpponentsEnumeration currentPlayer = OpponentsEnumeration.FIRST_PLAYER;
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
	protected void displayLegalMoveList(final List<MoveInterface> legalMoveList) {
		int n = 0;		
		final int numberOfDigits = (int) Math.log10(Math.abs(legalMoveList.size())) + 1;
		//System.out.println("\n" + currentPlayerOrdinal + " legal moves :");
		System.out.println("\nlegal moves :");
		for (MoveInterface legalMove : legalMoveList) {
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
		List<MoveInterface> legalMoves;
		MoveInterface legalMoveToPlay;
		// TODO mieux gérer le coup nul
		MoveInterface lastPlayedMove = new Move(OpponentsEnumeration.NO_ONE, this.getGame().cell(null).getPosition());
		// ---------------------------------------------------------------------		
		System.out.println(this.game);
		// ---------------------------------------------------------------------
		do {
			// ---------------------------------------------------------------------
			legalMoves = this.game.getLegalMoves(this.currentPlayer);
			this.displayLegalMoveList(legalMoves);
			// ---------------------------------------------------------------------			
			legalMoveToPlay = this.game.getPlayerStrategy(this.currentPlayer).select(this.game, legalMoves);
			// ---------------------------------------------------------------------
			this.currentPlayer = this.game.whoShallPlay(legalMoveToPlay, this.game.doMove(legalMoveToPlay));
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