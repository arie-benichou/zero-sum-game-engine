package strategy;

import java.util.List;

public interface StrategyInterface {

	EvaluatorInterface getEvaluator();

	SelectorInterface getSelector();

	List<MoveInterface> applyEvaluator(final List<MoveInterface> legalMoves);

	MoveInterface applySelector(final List<MoveInterface> legalMoves);

	MoveInterface computeStrategicMoveFrom(final List<MoveInterface> legalMoves);

	void injectContext(final GameInterface context);

}