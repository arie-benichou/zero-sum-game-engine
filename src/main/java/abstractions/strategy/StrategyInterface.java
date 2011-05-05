
package abstractions.strategy;

import java.util.List;

import abstractions.evaluation.EvaluationInterface;
import abstractions.mutation.MutationInterface;
import abstractions.selection.SelectionInterface;

public interface StrategyInterface {

    EvaluationInterface getEvaluator();

    SelectionInterface getSelector();

    //List<MutationInterface> applyEvaluation(final List<MutationInterface> mutations);

    //List<MutationInterface> applySelection(final List<MutationInterface> mutations);

    List<MutationInterface> applyStrategy(final List<MutationInterface> mutations);

}