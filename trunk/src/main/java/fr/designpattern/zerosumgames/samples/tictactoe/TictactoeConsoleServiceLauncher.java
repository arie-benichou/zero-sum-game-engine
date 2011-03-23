package fr.designpattern.zerosumgames.samples.tictactoe;

import fr.designpattern.zerosumgames.framework.service.GamePlayConsoleService;
import fr.designpattern.zerosumgames.framework.service.gameplay.GamePlay;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameBuilder;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameBuilderInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentBuilder;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsBuilder;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsBuilderInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.OpponentBuilderInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.OpponentInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.player.Player;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.player.PlayerInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator.EvaluatorInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator.NullEvaluator;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.selector.HumanMoveSelector;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.selector.SelectorInterface;

//TODO faire un builder global (builder de builders ?)
//TODO new OneSingleMoveHeuristic(new HumanMoveSelector())
//TODO new OneSingleMoveHeuristic(new BestLegalMoveSelector())

public final class TictactoeConsoleServiceLauncher {
	
	private TictactoeConsoleServiceLauncher() {}

	public static void main(final String[] args) {
		
		
		final OpponentBuilderInterface opponentBuilder = new OpponentBuilder();
		
			final PlayerInterface player1 = new Player("Arié");
			opponentBuilder.player(player1);
			
			final EvaluatorInterface evaluator1 = new NullEvaluator();
			opponentBuilder.evaluator(evaluator1);
			
			final SelectorInterface selector1 = new HumanMoveSelector();
			opponentBuilder.selector(selector1);
			
		OpponentInterface opponent1 = opponentBuilder.build();
			
			final PlayerInterface player2 = new Player("Anatole");
			opponentBuilder.player(player2);
			
		final OpponentInterface opponent2 = opponentBuilder.build();
		
		
			
		final OpponentsBuilderInterface opponentsBuilder = new OpponentsBuilder();
		
			final OpponentsInterface opponents = opponentsBuilder.player1(opponent1).player2(opponent2).build();
		
			
			
		final GameBuilderInterface gameBuilder = new GameBuilder(Tictactoe.class);
		
			final GameInterface game = gameBuilder.build();
		

			
		new GamePlayConsoleService(new GamePlay(game, opponents)).start();
		
		
	}
	
}