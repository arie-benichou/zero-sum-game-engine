
package abstractions.referee;

import java.util.List;

import abstractions.context.ContextInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;

// TODO ajouter une méthode lorsqu'une mutation est effectuée et annuler
// CheckersBirthMutation
public interface RefereeInterface {

    // Lorsqu'il n y a aucune mutation    
    //boolean isNullMoveAllowed();

    // Lorsqu'une mutation a été effectuée (ou plutôt lorsqu'un cycle a été effectué)
    boolean isGameOver(final ContextInterface context); //, final MutationInterface lastMutation);

    //SideInterface getSideToPlay(final SideInterface side, final MutationInterface lastMutation);

    // Lorsque la liste de mutations a été obtenue    
    //List<MutationInterface> filterMutation(final List<MutationInterface> potentialMutations); // TODO ? utiliser une map

    //SideInterface getWinner(final ContextInterface context);

    int getHeuristicEvaluation(final ContextInterface context, final SideInterface side);

    int getTerminalEvaluation(final ContextInterface context, final SideInterface side);

    // TODO !? cellManager devrait suffire
    List<MutationInterface> getLegalMoves(final ContextInterface context, final SideInterface side);

}
