package fr.designpattern.zerosumgames.framework.services;

import fr.designpattern.zerosumgames.extensions.connect4.Connect4;
import fr.designpattern.zerosumgames.framework.build.GameBuilder;
import fr.designpattern.zerosumgames.framework.build.IGameBuilder;
import fr.designpattern.zerosumgames.framework.build.game.components.opponents.GamePlayer;
import fr.designpattern.zerosumgames.framework.build.game.components.opponents.GamePlayerNature;
import fr.designpattern.zerosumgames.framework.build.game.components.opponents.strategies.BestMoveStrategy;
import fr.designpattern.zerosumgames.framework.build.game.components.opponents.strategies.HumanStrategy;
import fr.designpattern.zerosumgames.framework.build.game.components.opponents.strategies.evaluators.MiniMaxAlphaBetaMoveEvaluator;

public class Connect4Service {
	
	public static void main(String[] args) {
		
		IGameBuilder gameBuilder = new GameBuilder(Connect4.class);
		
		gameBuilder.player1(
			new GamePlayer(
				"p1",
				GamePlayerNature.COMPUTER,
				//new BestMoveStrategy(new MiniMaxAlphaBetaMoveEvaluator(3))
				new HumanStrategy()
			)
		);
		
		gameBuilder.player2(
			new GamePlayer(
				"p2",
				GamePlayerNature.COMPUTER,
				new BestMoveStrategy(new MiniMaxAlphaBetaMoveEvaluator(10))
				//new HumanStrategy()
			)
		);
		
		new GameService(gameBuilder.build()).start();
	}
	
}