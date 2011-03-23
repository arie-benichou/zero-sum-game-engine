package fr.designpattern.zerosumgames.services;

import fr.designpattern.zerosumgames.framework.gameplay.game.builder.Builder;
import fr.designpattern.zerosumgames.framework.gameplay.game.builder.BuilderInterface;
import fr.designpattern.zerosumgames.samples.checkers.Checkers;

public class CheckersService {

	public static void main(String[] args) {
			
		BuilderInterface gameBuilder = new Builder(Checkers.class);
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