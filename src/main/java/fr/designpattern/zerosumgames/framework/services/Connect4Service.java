package fr.designpattern.zerosumgames.framework.services;

import fr.designpattern.zerosumgames.framework.game.builder.Builder;
import fr.designpattern.zerosumgames.framework.game.builder.BuilderInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.Player;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.PlayerNature;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.strategies.concretes.BestMove;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.strategies.concretes.HumanMove;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.strategies.selectors.evaluators.MiniMaxAlphaBetaMoveEvaluator;
import fr.designpattern.zerosumgames.samples.connect4.Connect4;

public class Connect4Service {
	
	public static void main(String[] args) {
		
		BuilderInterface gameBuilder = new Builder(Connect4.class);
		
		gameBuilder.player1(
			new Player(
				"p1",
				PlayerNature.COMPUTER,
				//new BestMoveStrategy(new MiniMaxAlphaBetaMoveEvaluator(3))
				new HumanMove()
			)
		);
		
		gameBuilder.player2(
			new Player(
				"p2",
				PlayerNature.COMPUTER,
				new BestMove(new MiniMaxAlphaBetaMoveEvaluator(10))
				//new HumanStrategy()
			)
		);
		
		new GameService(gameBuilder.build()).start();
	}
	
}