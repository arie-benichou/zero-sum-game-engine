package fr.designpattern.zerosumgames.services;

import fr.designpattern.zerosumgames.extensions.checkers.Checkers;
import fr.designpattern.zerosumgames.game.GameBuilder;
import fr.designpattern.zerosumgames.game.IGameBuilder;
import fr.designpattern.zerosumgames.game.components.opponents.GamePlayer;
import fr.designpattern.zerosumgames.game.components.opponents.GamePlayerNature;
import fr.designpattern.zerosumgames.game.components.opponents.strategies.BestMoveStrategy;
import fr.designpattern.zerosumgames.game.components.opponents.strategies.evaluators.MiniMaxAlphaBetaMoveEvaluator;

public class CheckersService {

	public static void main(String[] args) {
			
		IGameBuilder gameBuilder = new GameBuilder(Checkers.class);
		
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