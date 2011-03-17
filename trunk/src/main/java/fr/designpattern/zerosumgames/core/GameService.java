package fr.designpattern.zerosumgames.core;

import java.util.List;

import fr.designpattern.zerosumgames.core.interfaces.IGame;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;
import fr.designpattern.zerosumgames.core.interfaces.IGamePlayerStrategy;
import fr.designpattern.zerosumgames.core.interfaces.IGameService;
import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;

public class GameService implements IGameService {
	// ------------------------------------------------------------
	private transient GamePlayersEnumeration currentPlayer;	
	// ------------------------------------------------------------
	private IGame game;
	public final IGame getGame() {
		return game;
	}
	private final void setGame(final IGame game) {
		this.game = game;
	}
	// ------------------------------------------------------------
	public GameService(final IGame game) {
		this.setGame(game);
	}
	// ------------------------------------------------------------
	// TODO créer IGameLegalMoveList
	protected void displayLegalMoveList(final GamePlayersEnumeration currentPlayer, final List<IGameBoardMove> legalMoveList) {
		int n = 0;		
		final int numberOfDigits = (int) Math.log10(Math.abs(legalMoveList.size())) + 1;
		//System.out.println("\n" + currentPlayerOrdinal + " legal moves :");
		System.out.println("\nlegal moves :");
		for (IGameBoardMove legalMove : legalMoveList) {
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
		IGamePlayerStrategy playerStrategy;
		List<IGameBoardMove> legalMoves;
		IGameBoardMove legalMoveToPlay;
		// TODO mieux gérer le coup nul
		IGameBoardMove lastPlayedMove = new GameBoardMove(GamePlayersEnumeration.NO_ONE, this.getGame().getCell(null).getPosition());
		// ---------------------------------------------------------------------		
		this.reset();
		// ---------------------------------------------------------------------
		System.out.println(this.game);
		// ---------------------------------------------------------------------
		do {
			// ---------------------------------------------------------------------			
			legalMoves = this.game.getLegalMoves(this.currentPlayer);
			this.displayLegalMoveList(this.currentPlayer, legalMoves);
			// ---------------------------------------------------------------------			
			playerStrategy = this.game.getPlayer(this.currentPlayer).getStrategy();
			legalMoveToPlay = playerStrategy.chooseMoveAmong(this.game, legalMoves);
			// ---------------------------------------------------------------------
			this.currentPlayer = this.game.whoShallPlay(legalMoveToPlay, this.game.playMove(legalMoveToPlay));
			// ---------------------------------------------------------------------
			// TODO à gérer dans le game over d'Othello (et plus généralement dans un jeu acceptant le coup nul)
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
		this.currentPlayer = GamePlayersEnumeration.FIRST_PLAYER;
		this.game.reset();
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