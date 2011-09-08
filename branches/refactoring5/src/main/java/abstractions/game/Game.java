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

package abstractions.game;

import abstractions.cell.CellManagerInterface;
import abstractions.referee.RefereeInterface;

public class Game implements GameInterface {

    private final RefereeInterface referee;
    private final CellManagerInterface cellManager;
    private final CellManagerInterface initialState;

    public Game(final CellManagerInterface cellManager, final RefereeInterface referee) {
        this.cellManager = cellManager;
        this.initialState = this.cellManager.duplicate();
        this.referee = referee;
    }

    @Override
    public final CellManagerInterface getCellManager() {
        return this.cellManager;
    }

    @Override
    public final RefereeInterface getReferee() {
        return this.referee;
    }

    @Override
    public GameInterface newGame() {
        return new Game(this.initialState, this.getReferee());
    }

}