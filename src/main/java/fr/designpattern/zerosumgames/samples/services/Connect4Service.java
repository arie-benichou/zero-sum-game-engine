package fr.designpattern.zerosumgames.samples.services;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameBuilder;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameBuilderInterface;
import fr.designpattern.zerosumgames.samples.connect4.Connect4;

public class Connect4Service {
	
	public static void main(String[] args) {
		
		GameBuilderInterface gameBuilder = new GameBuilder(Connect4.class);
		/*
		gameBuilder.player1(
			new Player(
				"p1",
				PlayerNature.COMPUTER,
				//new BestMoveStrategy(new MiniMaxAlphaBetaMoveEvaluator(3))
				new HumanLegalMoveStrategy()
			)
		);
		
		gameBuilder.player2(
			new Player(
				"p2",
				PlayerNature.COMPUTER,
				new BestLegalMoveStrategy(new MiniMaxAlphaBeta(10))
				//new HumanStrategy()
			)
		);
		new GameService(gameBuilder.build()).start();
		*/
	}
	
}