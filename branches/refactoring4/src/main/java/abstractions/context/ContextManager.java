
package abstractions.context;

import java.util.List;
import java.util.Map;
import java.util.Set;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.NullMutation;
import abstractions.player.PlayerInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.Lists;


//TODO ! gérer la NPE lorsque le jeu est null

//TODO renommer ContextManagerForTwoPlayers
//TODO écrire ContextManagerForSinglePlayer
//TODO écrire ContextManagerForNoPlayer

public class ContextManager {

    private final ContextInterface context;

    // TODO ? c'est au manager de créer le contexte
    public ContextManager(final ContextInterface context) {
        this.context = context;
    }

    public void start() {

        int counter = 0;

        System.out.println(this);

        MutationInterface strategicMutation;

        do {

            final SideInterface sideToPlay = this.context.getSideToPlay();
            final PlayerInterface player = this.context.getAdversity().getOpponent(sideToPlay);

            // TODO ? encapsuler le CellManager dans le MutationManager ( ~ game specifications)
            final Map<ManagedCellInterface, Set<? extends MutationInterface>> potentialMutations = this.context.getCellManager().getPotentialMutations(
                    sideToPlay);

            //TODO ! à améliorer
            List<MutationInterface> mutations = Lists.newArrayList();
            for (final Set<? extends MutationInterface> set : potentialMutations.values()) {
                mutations.addAll(set);
            }

            mutations = this.context.getReferee().filterMutation(mutations);

            if (!mutations.isEmpty()) {

                strategicMutation = player.getStrategy().applyStrategy(mutations);
                strategicMutation.process();

                counter = 0;

                //this.context.setSideToPlay(this.context.getReferee().getSideToPlay(strategicMutation));
                this.context.setSideToPlay(this.context.getReferee().getSideToPlay(this.context.getSideToPlay(), strategicMutation));

                System.out.println(this);

            }
            else {

                strategicMutation = NullMutation.getInstance();
                System.out.println("no mutation available for " + this.context.getSideToPlay());

                if (++counter > 1) {
                    System.out.println("Game Over!");
                    break;
                }

                //this.context.setSideToPlay(sideToPlay.getNextSide());
                this.context.setSideToPlay(this.context.getReferee().getSideToPlay(this.context.getSideToPlay(), strategicMutation));

            }

        }
        while (!this.context.getReferee().isGameOver(strategicMutation));

        /*
        result =
                "Gameplay is over."
                        + "\n\n"
                        +
                        (this.getSideToPlay().isNoOne() ?
                                "There is no winner."
                                :
                                "And the winner is : "
                                        + this.getOpponentByOrder(this
                                                .getSideToPlay().getNegation()
                                                .getOpponent()));

        System.out.println(result);
        */

    }

    @Override
    public String toString() {
        return this.context.toString();
    }

}
