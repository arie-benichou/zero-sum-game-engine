package fr.designpattern.zerosumgames.services;

import fr.designpattern.zerosumgames.core.GameBuilder;
import fr.designpattern.zerosumgames.core.GamePlayer;
import fr.designpattern.zerosumgames.core.GameService;
import fr.designpattern.zerosumgames.core.interfaces.IGameBuilder;
import fr.designpattern.zerosumgames.core.strategies.BestMoveStrategy;
import fr.designpattern.zerosumgames.core.strategies.selectors.MiniMaxAlphaBetaMoveSelector;
import fr.designpattern.zerosumgames.core.types.GamePlayerNature;
import fr.designpattern.zerosumgames.extensions.connect4.Connect4;

public class Connect4Service {
	
	public static void main(String[] args) {
		
		IGameBuilder gameBuilder = new GameBuilder(Connect4.class);
		
		gameBuilder.player1(
			new GamePlayer(
				"p1",
				GamePlayerNature.COMPUTER,
				new BestMoveStrategy(new MiniMaxAlphaBetaMoveSelector(8))
			)
		);
		
		gameBuilder.player2(
			new GamePlayer(
				"p2",
				GamePlayerNature.COMPUTER,
				new BestMoveStrategy(new MiniMaxAlphaBetaMoveSelector(6))
			)
		);
		
		new GameService(gameBuilder.build()).start();
	}
	
}