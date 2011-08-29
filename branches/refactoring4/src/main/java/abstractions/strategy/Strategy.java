
package abstractions.strategy;

import abstractions.evaluation.EvaluationInterface;
import abstractions.selection.SelectionInterface;

// TODO utiliser une classe abstraite ?
public class Strategy extends AbstractStrategy {

    public Strategy(final EvaluationInterface evaluator, final SelectionInterface selector) {
        super(evaluator, selector);
    }

}