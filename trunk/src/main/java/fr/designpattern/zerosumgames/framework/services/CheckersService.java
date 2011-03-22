package fr.designpattern.zerosumgames.framework.services;

import fr.designpattern.zerosumgames.framework.game.builder.Builder;
import fr.designpattern.zerosumgames.framework.game.builder.BuilderInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.Player;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.PlayerNature;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.strategies.BestLegalMoveStrategy;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.strategies.evaluators.MiniMaxAlphaBeta;
import fr.designpattern.zerosumgames.samples.checkers.Checkers;

public class CheckersService {

	public static void main(String[] args) {
			
		BuilderInterface gameBuilder = new Builder(Checkers.class);
		
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
		
	}
	
}