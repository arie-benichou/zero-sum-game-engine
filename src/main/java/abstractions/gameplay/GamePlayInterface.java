
package abstractions.gameplay;

import abstractions.adversity.AdversityInterface;
import abstractions.game.GameInterface;

public interface GamePlayInterface {

    GameInterface getGame();

    AdversityInterface getAdversity();

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
