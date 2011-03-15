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
			System.out.println(this.game);
			// ---------------------------------------------------------------------			
		} while (this.currentPlayer != GamePlayersEnumeration.NONE);
		// ---------------------------------------------------------------------		
		System.out.println("Game over!");
		if(this.game.isWinningMove(legalMoveToPlay)) {
			System.out.println("And the winner is " + legalMoveToPlay.getSide() + ".");	
		}
		else {
			System.out.println("There is no winner.");
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