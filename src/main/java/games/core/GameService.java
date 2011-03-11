package main.java.games.core;

import java.util.List;

import main.java.games.core.interfaces.IGame;
import main.java.games.core.interfaces.IGameBoardMove;
import main.java.games.core.interfaces.IGamePlayerStrategy;
import main.java.games.core.interfaces.IGameService;
import main.java.games.core.types.GamePlayersEnumeration;

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
	@Override
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
			legalMoveToPlay = playerStrategy.chooseMoveAmong(legalMoves);
			// ---------------------------------------------------------------------			
			this.currentPlayer = this.game.applyGameStateTransition(legalMoveToPlay);
			// ---------------------------------------------------------------------			
			System.out.println(this.game);
			// ---------------------------------------------------------------------			
		} while (this.currentPlayer != null); // TODO ? utiliser GamePlayersEnumeration.NONE
		// ---------------------------------------------------------------------		
		System.out.println("Game Over");
		// ---------------------------------------------------------------------		
	}
	// ---------------------------------------------------------------------
	@Override
	public void reset() {
		this.currentPlayer = GamePlayersEnumeration.FIRST_PLAYER;
		this.game.reset();
	}	
	// ---------------------------------------------------------------------	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}
	// ---------------------------------------------------------------------
	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}
	// ---------------------------------------------------------------------
	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}
	// ---------------------------------------------------------------------
}