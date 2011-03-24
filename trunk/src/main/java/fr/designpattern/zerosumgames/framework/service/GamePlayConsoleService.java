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

package fr.designpattern.zerosumgames.framework.service;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.GamePlayInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.OpponentInterface;

public class GamePlayConsoleService extends AbstractGamePlayService {

    // ------------------------------------------------------------
    public GamePlayConsoleService(final GamePlayInterface gamePlay) {
        super(gamePlay);
    }

    // ------------------------------------------------------------
    @Override
    public void start() {

        OpponentsEnumeration sideToPlay;
        OpponentInterface opponent;
        List<LegalMoveInterface> legalMoves;
        LegalMoveInterface legalMove;
        String result;

        System.out.println(this);

        do {
            sideToPlay = this.getSideToPlay();
            opponent = this.getOpponentByOrder(sideToPlay);
            System.out.println(opponent + " must play...");
            legalMoves = this.getLegalMoves(sideToPlay);
            legalMove = opponent.selectMove(legalMoves);
            this.play(legalMove);
            System.out.println(this);
        }
        while (!this.isGamePlayOver());

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

    }

    // ---------------------------------------------------------------------
    @Override
    public void reset() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void stop() {}

    // ------------------------------------------------------------
    @Override
    public String toString() {
        return "\n===================================================\n" +
                this.getGamePlay().toString() +
                "\n===================================================\n";
    }

    // ---------------------------------------------------------------------
}