
package abstractions.referee;

import java.util.List;

import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;

// TODO ajouter une méthode lorsqu'une mutation est effectuée et annuler CheckersBirthMutation
public interface RefereeInterface {

    // Lorsqu'une mutation a été effectuée (ou plutôt lorsqu'un cycle a été effectué)
    boolean isGameOver(final MutationInterface lastMutation);
    
    SideInterface getSideToPlay(final SideInterface currentSide, final MutationInterface lastMutation);

    // Lorsqu'il n y a aucune mutation    
    boolean isNullMoveAllowed();

    // Lorsque la liste de mutations a été obtenue    
    List<MutationInterface> filterMutation(List<MutationInterface> potentialMutations); // TODO ? utiliser une map    

}
