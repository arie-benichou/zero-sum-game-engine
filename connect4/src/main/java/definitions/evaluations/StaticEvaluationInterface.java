
package definitions.evaluations;

import context.ContextInterface;
import context.event.MoveInterface;

public interface StaticEvaluationInterface {

    Double evaluate(ContextInterface context);

    Double evaluate(MoveInterface option);

}
