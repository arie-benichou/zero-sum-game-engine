package fr.designpattern.zerosumgames.framework.services;

import fr.designpattern.zerosumgames.framework.game.builder.Builder;
import fr.designpattern.zerosumgames.framework.game.builder.BuilderInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.Player;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.PlayerNature;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.strategies.concretes.BestMove;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.strategies.selectors.evaluators.MiniMaxAlphaBetaMoveEvaluator;
import fr.designpattern.zerosumgames.samples.tictactoe.Tictactoe;

// TODO pouvoire construire un tictactoe de dimension differente et avec un nombre de connexions different de 3
public final class TictactoeService {
	
	private TictactoeService() {}

	public static void main(final String[] args) {
			
		final BuilderInterface gameBuilder = new Builder(Tictactoe.class);
		
		gameBuilder.player1(
			new Player(
				"p1",
				PlayerNature.COMPUTER,
				new BestMove(new MiniMaxAlphaBetaMoveEvaluator(9))
			)
		);
		
		gameBuilder.player2(
			new Player(
				"p2",
				PlayerNature.COMPUTER,
				new BestMove(new MiniMaxAlphaBetaMoveEvaluator(8))
			)
		);
		
		new GameService(gameBuilder.build()).start();
		
	}
	
}