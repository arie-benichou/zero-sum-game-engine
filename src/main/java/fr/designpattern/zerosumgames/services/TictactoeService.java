package fr.designpattern.zerosumgames.services;

import fr.designpattern.zerosumgames.extensions.tictactoe.Tictactoe;
import fr.designpattern.zerosumgames.framework.build.GameBuilder;
import fr.designpattern.zerosumgames.framework.build.IGameBuilder;
import fr.designpattern.zerosumgames.game.components.opponents.GamePlayer;
import fr.designpattern.zerosumgames.game.components.opponents.GamePlayerNature;
import fr.designpattern.zerosumgames.game.components.opponents.strategies.BestMoveStrategy;
import fr.designpattern.zerosumgames.game.components.opponents.strategies.evaluators.MiniMaxAlphaBetaMoveEvaluator;

// TODO pouvoire construire un tictactoe de dimension differente et avec un nombre de connexions different de 3
public final class TictactoeService {
	
	private TictactoeService() {}

	public static void main(final String[] args) {
			
		final IGameBuilder gameBuilder = new GameBuilder(Tictactoe.class);
		
		gameBuilder.player1(
			new GamePlayer(
				"p1",
				GamePlayerNature.COMPUTER,
				new BestMoveStrategy(new MiniMaxAlphaBetaMoveEvaluator(9))
			)
		);
		
		gameBuilder.player2(
			new GamePlayer(
				"p2",
				GamePlayerNature.COMPUTER,
				new BestMoveStrategy(new MiniMaxAlphaBetaMoveEvaluator(8))
			)
		);
		
		new GameService(gameBuilder.build()).start();
		
	}
	
}