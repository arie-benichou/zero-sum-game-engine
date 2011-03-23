package fr.designpattern.zerosumgames.services;

import fr.designpattern.zerosumgames.framework.gameplay.game.builder.Builder;
import fr.designpattern.zerosumgames.framework.gameplay.game.builder.BuilderInterface;
import fr.designpattern.zerosumgames.samples.connect4.Connect4;

public class Connect4Service {
	
	public static void main(String[] args) {
		
		BuilderInterface gameBuilder = new Builder(Connect4.class);
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