package fr.designpattern.zerosumgames.services;

import trash.NegaMaxAlphaBetaStrategy;
import fr.designpattern.zerosumgames.core.GameBuilder;
import fr.designpattern.zerosumgames.core.GamePlayer;
import fr.designpattern.zerosumgames.core.GameService;
import fr.designpattern.zerosumgames.core.interfaces.IGameBuilder;
import fr.designpattern.zerosumgames.core.strategies.HumanWithComputerHelpStrategy;
import fr.designpattern.zerosumgames.core.types.GamePlayerNature;
import fr.designpattern.zerosumgames.implementations.checkers.Checkers;

public class CheckersService {

	public static void main(String[] args) {
			
		IGameBuilder gameBuilder = new GameBuilder(Checkers.class);
		
		gameBuilder.player1(
			new GamePlayer(
				"p1",
				GamePlayerNature.COMPUTER,
				new NegaMaxAlphaBetaStrategy(6)
			)
		);
		
		gameBuilder.player2(
			new GamePlayer(
				"p2",
				GamePlayerNature.COMPUTER,
				new HumanWithComputerHelpStrategy(4)
			)
		);
		
		new GameService(gameBuilder.build()).start();
		
	}
	
}