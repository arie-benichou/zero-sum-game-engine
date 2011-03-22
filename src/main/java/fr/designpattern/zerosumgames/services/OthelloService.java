package fr.designpattern.zerosumgames.services;

import fr.designpattern.zerosumgames.extensions.othello.Othello;
import fr.designpattern.zerosumgames.framework.build.GameBuilder;
import fr.designpattern.zerosumgames.framework.build.IGameBuilder;
import fr.designpattern.zerosumgames.game.components.opponents.GamePlayer;
import fr.designpattern.zerosumgames.game.components.opponents.GamePlayerNature;
import fr.designpattern.zerosumgames.game.components.opponents.strategies.BestMoveStrategy;
import fr.designpattern.zerosumgames.game.components.opponents.strategies.evaluators.MiniMaxAlphaBetaMoveEvaluator;

public class OthelloService {

	public static void main(String[] args) {
			
		IGameBuilder gameBuilder = new GameBuilder(Othello.class);
		
		gameBuilder.player1(
			new GamePlayer(
				"p1",
				GamePlayerNature.COMPUTER,
				new BestMoveStrategy(new MiniMaxAlphaBetaMoveEvaluator(4))
			)
		);
		
		gameBuilder.player2(
			new GamePlayer(
				"p2",
				GamePlayerNature.COMPUTER,
				new BestMoveStrategy(new MiniMaxAlphaBetaMoveEvaluator(4))
			)
		);
		
		new GameService(gameBuilder.build()).start();
	}
	
}