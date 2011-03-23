package fr.designpattern.zerosumgames.tmp.game.components.opponents.strategies.selectors;
import fr.designpattern.zerosumgames.framework.gameplay.GamePlay;
import fr.designpattern.zerosumgames.framework.gameplay.GamePlayInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.AbstractGame;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.Board;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.BoardInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.Dimension;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.Cells;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.CellsInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.positions.Positions;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.positions.PositionsInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.Opponents;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.OpponentsInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.Opponent;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.OpponentInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.player.Player;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.player.PlayerInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.Strategy;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.StrategyInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.evaluator.EvaluatorInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.evaluator.NullEvaluator;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.selector.HumanMoveSelector;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.selector.SelectorInterface;
import fr.designpattern.zerosumgames.samples.tictactoe.Tictactoe;
import fr.designpattern.zerosumgames.services.GameService;


public class Tests {
	
	public static void main(String[] args) {
		
		PlayerInterface player1 = new Player("Arié");
		EvaluatorInterface evaluator1 = new NullEvaluator();
		SelectorInterface selector1 = new HumanMoveSelector();
		StrategyInterface strategy1 = new Strategy(evaluator1, selector1);
		OpponentInterface opponent1 = new Opponent(player1, strategy1);
		
		
		PlayerInterface player2 = new Player("Anna");
		EvaluatorInterface evaluator2 = new NullEvaluator();
		SelectorInterface selector2 = new HumanMoveSelector();
		StrategyInterface strategy2 = new Strategy(evaluator2, selector2);
		OpponentInterface opponent2 = new Opponent(player2, strategy2);
		
		OpponentsInterface opponents = new Opponents(opponent1, opponent2);
		
		Dimension dimension = new Dimension(1,3,1,3);
		PositionsInterface positionFactory = new Positions(dimension);
		CellsInterface cellFactory = new Cells(positionFactory);
		BoardInterface board = new Board(cellFactory);
		
		AbstractGame game = new Tictactoe(board);
		
		GamePlayInterface gamePlay = new GamePlay(game, opponents);
		
		GameService service = new GameService(gamePlay);
		
		service.start();
		
	}

}
