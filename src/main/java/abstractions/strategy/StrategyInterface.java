
package abstractions.strategy;

import java.util.List;

import abstractions.evaluator.EvaluatorInterface;
import abstractions.mutation.MutationInterface;
import abstractions.selector.SelectorInterface;

public interface StrategyInterface {

    EvaluatorInterface getEvaluator();

    SelectorInterface getSelector();

    MutationInterface applyStrategy(final List<MutationInterface> mutations);

}
