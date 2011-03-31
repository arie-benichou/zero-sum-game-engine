package strategy;

import java.util.List;

public interface StrategyInterface {

	EvaluatorInterface getEvaluator();

	SelectorInterface getSelector();

	List<LegalMoveInterface> applyEvaluator(final List<LegalMoveInterface> legalMoves);

	LegalMoveInterface applySelector(final List<LegalMoveInterface> legalMoves);

	LegalMoveInterface computeStrategicMoveFrom(final List<LegalMoveInterface> legalMoves);

	void injectContext(final GameInterface context);

}