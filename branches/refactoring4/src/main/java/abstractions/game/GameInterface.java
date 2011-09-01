
package abstractions.game;

import abstractions.cell.CellManagerInterface;
import abstractions.referee.RefereeInterface;

// TODO avoir une GameBuilderInterface
public interface GameInterface {

    CellManagerInterface getCellManager();

    RefereeInterface getReferee();

    /*
    final Context context = new Context(GameInterface.adversity, GameInterface.cellManager, GameInterface.referee);
    evaluator1.injectContext(context);
    evaluator2.injectContext(context);
    final ContextManager contextManager = new ContextManager(GameInterface.context);
    Othello.onStart(cellManager);    
    */

    //contextManager.play();

    //System.out.println(context.getHeuristicEvaluation(Sides.FIRST));
    //System.out.println(context.getHeuristicEvaluation(Sides.SECOND));    

}
