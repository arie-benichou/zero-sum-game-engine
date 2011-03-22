package fr.designpattern.zerosumgames.services;

import fr.designpattern.zerosumgames.core.GameBuilder;
import fr.designpattern.zerosumgames.core.GamePlayer;
import fr.designpattern.zerosumgames.core.GameService;
import fr.designpattern.zerosumgames.core.interfaces.IGameBuilder;
import fr.designpattern.zerosumgames.core.strategies.BestMoveStrategy;
import fr.designpattern.zerosumgames.core.strategies.moveSelectors.MiniMaxWithAlphaBetaPruning;
import fr.designpattern.zerosumgames.core.types.GamePlayerNature;
import fr.designpattern.zerosumgames.extensions.tictactoe.Tictactoe;

// TODO pouvoire construire un tictactoe de dimension differente et avec un nombre de connexions different de 3
public class TictactoeService {

	public static void main(String[] args) {
			
		IGameBuilder gameBuilder = new GameBuilder(Tictactoe.class);
		
		gameBuilder.player1(
			new GamePlayer(
				"p1",
				GamePlayerNature.COMPUTER,
				new BestMoveStrategy(new MiniMaxWithAlphaBetaPruning(9))
			)
		);
		
		gameBuilder.player2(
			new GamePlayer(
				"p2",
				GamePlayerNature.COMPUTER,
				new BestMoveStrategy(new MiniMaxWithAlphaBetaPruning(8))
			)
		);
		
		new GameService(gameBuilder.build()).start();
		
	}
	
}