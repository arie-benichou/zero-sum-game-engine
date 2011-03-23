package fr.designpattern.zerosumgames.samples.services;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameBuilder;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameBuilderInterface;
import fr.designpattern.zerosumgames.samples.checkers.Checkers;

public class CheckersService {

	public static void main(String[] args) {
			
		GameBuilderInterface gameBuilder = new GameBuilder(Checkers.class);
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