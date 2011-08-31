
package abstractions.context;

import java.util.List;

import abstractions.mutation.MutationInterface;
import abstractions.player.PlayerInterface;
import abstractions.side.SideInterface;

// TODO ? play() : retourner une SideInterface

// TODO ?? gérer la NPE lorsque le jeu est null

// TODO renommer en ContextManagerForTwoPlayers
// TODO écrire ContextManagerForSinglePlayer
// TODO écrire ContextManagerForNoPlayer

public class ContextManager {

    private final ContextInterface context;

    // TODO ? c'est au manager de créer le contexte
    public ContextManager(final ContextInterface context) {
        this.context = context;
    }

    private final ContextInterface getContext() {
        return this.context;
    }

    private final SideInterface getCurrentSide() {
        return this.getContext().getCurrentSide();
    }

    private final PlayerInterface getCurrentPlayer() {
        System.out.println(this.getContext().getCurrentPlayer().getName());
        return this.getContext().getCurrentPlayer();
    }

    private final void setSideToPlay(SideInterface nextSideToPlay) {
        this.getContext().setCurrentSide(nextSideToPlay);
    }

    private final List<MutationInterface> getLegalMoves() {
        return this.getContext().getLegalMovesForCurrentPlayer();
    }

    private final boolean isGameOver() {
        return this.getContext().isGameOver();
    }

    private final void applyMoveForCurrentPlayer(MutationInterface move) {
        System.out.println(move);
        this.getContext().applyMoveForCurrentPlayer(move);
    }

    public void play() {

        do {
            this.setSideToPlay(this.getCurrentSide().getNextSide());
            System.out.println(this);
            this.applyMoveForCurrentPlayer(this.getCurrentPlayer().applyStrategy(this.getLegalMoves()));
        }
        while (!this.isGameOver());
        
        System.out.println(this);
        System.out.println("Game Over!");
        
    }

    @Override
    public String toString() {
        return this.context.toString();
    }

}