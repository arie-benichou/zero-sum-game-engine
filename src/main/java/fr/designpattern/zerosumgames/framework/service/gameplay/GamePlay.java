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

package fr.designpattern.zerosumgames.framework.service.gameplay;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.OpponentInterface;

public final class GamePlay implements GamePlayInterface {

    // ---------------------------------------------------------------------
    private final transient OpponentsInterface opponents;

    private OpponentsInterface getOpponents() {
        return this.opponents;
    }

    // ---------------------------------------------------------------------
    private final transient GameInterface game;

    private GameInterface getGame() {
        return this.game;
    }

    // ---------------------------------------------------------------------
    private transient OpponentsEnumeration sideToPlay = OpponentsEnumeration.FIRST_PLAYER;

    private void setSideToPlay(final OpponentsEnumeration sideToPlay) {
        this.sideToPlay = sideToPlay;
    }

    public OpponentsEnumeration getSideToPlay() {
        return this.sideToPlay;
    }

    // ---------------------------------------------------------------------
    public GamePlay(final GameInterface game, final OpponentsInterface opponents) {
        this.game = game;
        opponents.injectContext(game);
        this.opponents = opponents;
    }

    // ---------------------------------------------------------------------
    public List<LegalMoveInterface> getLegalMoves(
            final OpponentsEnumeration side) {
        return this.getGame().getLegalMoves(side);
    }

    public OpponentInterface getOpponentByOrder(
            final OpponentsEnumeration side) {
        return this.getOpponents().getOpponentByOrder(side);
    }

    // ---------------------------------------------------------------------
    public void play(final LegalMoveInterface move) {
        this.setSideToPlay(this.getGame().play(move));
    }

    // ---------------------------------------------------------------------
    public boolean isGamePlayOver() {
        return !this.getSideToPlay().isAPlayer();
    }

    // ---------------------------------------------------------------------
    @Override
    public String toString() {
        return this.getGame().toString() + "\n"
                + this.getOpponents().toString();
    }
    // ---------------------------------------------------------------------
}