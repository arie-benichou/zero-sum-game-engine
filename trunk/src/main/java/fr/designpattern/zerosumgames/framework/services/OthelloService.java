package fr.designpattern.zerosumgames.framework.services;

import fr.designpattern.zerosumgames.extensions.othello.Othello;
import fr.designpattern.zerosumgames.framework.game.builder.Builder;
import fr.designpattern.zerosumgames.framework.game.builder.BuilderInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.GamePlayer;
import fr.designpattern.zerosumgames.framework.game.components.opponents.GamePlayerNature;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.BestMoveStrategy;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.evaluators.MiniMaxAlphaBetaMoveEvaluator;

public class OthelloService {

	public static void main(String[] args) {
			
		BuilderInterface gameBuilder = new Builder(Othello.class);
		
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