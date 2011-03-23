package fr.designpattern.zerosumgames.services;

import fr.designpattern.zerosumgames.framework.gameplay.game.GameBuilder;
import fr.designpattern.zerosumgames.framework.gameplay.game.GameBuilderInterface;
import fr.designpattern.zerosumgames.samples.othello.Othello;

public class OthelloService {

	public static void main(String[] args) {
			
		GameBuilderInterface gameBuilder = new GameBuilder(Othello.class);
		
		/*
		gameBuilder.player1(
			new Player(
				"p1",
				PlayerNature.COMPUTER,
				new BestLegalMoveStrategy(new MiniMaxAlphaBeta(4))
			)
		);
		
		gameBuilder.player2(
			new Player(
				"p2",
				PlayerNature.COMPUTER,
				new BestLegalMoveStrategy(new MiniMaxAlphaBeta(4))
			)
		);
		new GameService(gameBuilder.build()).start();
		*/
	}
	
}