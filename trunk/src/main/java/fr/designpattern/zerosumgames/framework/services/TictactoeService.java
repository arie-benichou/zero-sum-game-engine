package fr.designpattern.zerosumgames.framework.services;

import fr.designpattern.zerosumgames.framework.game.builder.Builder;
import fr.designpattern.zerosumgames.framework.game.builder.BuilderInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.Player;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.BestLegalMoveSelector;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.HumanMoveSelector;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.heuristics.OneSingleMoveHeuristic;
import fr.designpattern.zerosumgames.samples.tictactoe.Tictactoe;

// TODO pouvoire construire un tictactoe de dimension differente et avec un nombre de connexions different de 3
public final class TictactoeService {
	
	private TictactoeService() {}

	public static void main(final String[] args) {
			
		final BuilderInterface gameBuilder = new Builder(Tictactoe.class);
		
		gameBuilder.player1(
			new Player(
				"p1",
				//new OneSingleMoveHeuristic(new HumanMoveSelector())
			)
		);
		
		gameBuilder.player2(
			new Player(
				"p2",
				//new OneSingleMoveHeuristic(new HumanMoveSelector())
				new OneSingleMoveHeuristic(new BestLegalMoveSelector())
			)
		);
		
		new GameService(gameBuilder.build()).start();
		
	}
	
}