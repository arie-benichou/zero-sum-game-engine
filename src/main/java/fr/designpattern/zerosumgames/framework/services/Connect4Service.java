package fr.designpattern.zerosumgames.framework.services;

import fr.designpattern.zerosumgames.framework.game.builder.Builder;
import fr.designpattern.zerosumgames.framework.game.builder.BuilderInterface;
import fr.designpattern.zerosumgames.framework.game.components.move.evaluators.MiniMaxAlphaBetaMoveEvaluator;
import fr.designpattern.zerosumgames.framework.game.components.opponents.player.GamePlayer;
import fr.designpattern.zerosumgames.framework.game.components.opponents.player.GamePlayerNature;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategy.BestMoveStrategy;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategy.HumanStrategy;
import fr.designpattern.zerosumgames.samples.connect4.Connect4;

public class Connect4Service {
	
	public static void main(String[] args) {
		
		BuilderInterface gameBuilder = new Builder(Connect4.class);
		
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