package abstractions.strategy;

import java.util.List;

import abstractions.game.GameInterface;
import abstractions.move.API.MoveInterface;

public interface StrategyInterface {

	EvaluatorInterface getEvaluator();

	SelectorInterface getSelector();

	List<MoveInterface> applyEvaluator(final List<MoveInterface> legalMoves);

	MoveInterface applySelector(final List<MoveInterface> legalMoves);

	MoveInterface computeStrategicMoveFrom(final List<MoveInterface> legalMoves);

	void injectContext(final GameInterface context);

}