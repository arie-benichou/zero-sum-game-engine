/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package abstractions.context;

import java.util.List;

import abstractions.mutation.MutationInterface;
import abstractions.player.PlayerInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;

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
        this.context.getAdversity().getPlayer(Sides.FIRST).getStrategy().getEvaluator().injectContext(context);
        this.context.getAdversity().getPlayer(Sides.SECOND).getStrategy().getEvaluator().injectContext(context);
    }

    private final ContextInterface getContext() {
        return this.context;
    }

    private final SideInterface getCurrentSide() {
        return this.getContext().getCurrentSide();
    }

    private final PlayerInterface getCurrentPlayer() {
        return this.getContext().getCurrentPlayer();
    }

    private final void setSideToPlay(final SideInterface nextSideToPlay) {
        this.getContext().setCurrentSide(nextSideToPlay);
    }

    private final List<MutationInterface> getLegalMoves() {
        return this.getContext().getLegalMoves();
    }

    private final boolean isGameOver() {
        return this.getContext().isGameOver();
    }

    private final void applyMoveForCurrentPlayer(final MutationInterface move) {
        this.getContext().applyMove(move);
    }

    public void play() {

        do {

            this.setSideToPlay(this.getCurrentSide().getNextSide());
            System.out.println(this);

            final PlayerInterface player = this.getCurrentPlayer();
            System.out.println(player.getName());

            final MutationInterface move = player.applyStrategy(this.getLegalMoves());
            System.out.println(move);

            this.applyMoveForCurrentPlayer(move);

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