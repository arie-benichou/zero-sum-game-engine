package fr.designpattern.zerosumgames.framework.services;

import fr.designpattern.zerosumgames.framework.game.builder.Builder;
import fr.designpattern.zerosumgames.framework.game.builder.BuilderInterface;
import fr.designpattern.zerosumgames.framework.game.components.move.evaluators.MiniMaxAlphaBetaMoveEvaluator;
import fr.designpattern.zerosumgames.framework.game.components.opponents.player.GamePlayer;
import fr.designpattern.zerosumgames.framework.game.components.opponents.player.GamePlayerNature;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategy.BestMoveStrategy;
import fr.designpattern.zerosumgames.samples.checkers.Checkers;

public class CheckersService {

	public static void main(String[] args) {
			
		BuilderInterface gameBuilder = new Builder(Checkers.class);
		
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