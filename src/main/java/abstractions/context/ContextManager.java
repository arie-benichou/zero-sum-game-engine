
package abstractions.context;

import java.util.List;
import java.util.Map;
import java.util.Set;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.player.PlayerInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.Lists;

public class ContextManager {

    private final ContextInterface context;

    // TODO ? c'est au manager de créer le contexte
    public ContextManager(final ContextInterface context) {
        this.context = context;
    }

    public void start() {

        System.out.println(this);

        do {
            final SideInterface sideToPlay = this.context.getSideToPlay();
            final PlayerInterface player = this.context.getAdversity().getOpponent(sideToPlay);
            // TODO ? encapsuler le CellManager dans le MutationManager ( ~ game specifications)
            final Map<ManagedCellInterface, Set<? extends MutationInterface>> potentialMutations = this.context.getCellManager().getPotentialMutations(
                    sideToPlay);
            //TODO ! à améliorer
            final List<MutationInterface> mutations = Lists.newArrayList();
            for (final Set<? extends MutationInterface> set : potentialMutations.values()) {
                mutations.addAll(set);
            }
            System.out.println("");
            for (final MutationInterface mutation : mutations) {
                System.out.println(mutation);
            }
            final List<MutationInterface> strategicMutations = player.getStrategy().applyStrategy(mutations);
            for (final MutationInterface mutation : strategicMutations) {
                mutation.process();
            }
            this.context.setSideToPlay(sideToPlay.getNextSide());
            System.out.println(this);
        }
        while (true /*!this.isGamePlayOver()*/);

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
