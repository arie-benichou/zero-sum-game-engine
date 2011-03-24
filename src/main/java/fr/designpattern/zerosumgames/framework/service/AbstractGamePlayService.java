/*
 * Copyright 2011 Arie Benichou
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.designpattern.zerosumgames.framework.service;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.GamePlayInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.OpponentInterface;

public abstract class AbstractGamePlayService implements GamePlayServiceInterface {
	// ------------------------------------------------------------
	protected final GamePlayInterface gamePlay;
	public final GamePlayInterface getGamePlay() {
		return this.gamePlay;
	}
	// ------------------------------------------------------------
	public AbstractGamePlayService(final GamePlayInterface gamePlay) {
		this.gamePlay = gamePlay;
	}
	// ------------------------------------------------------------
	public abstract void start();
	public abstract void reset();
	public abstract void pause();
	public abstract void resume();
	public abstract void stop();
	// ---------------------------------------------------------------------
	protected final OpponentsEnumeration getSideToPlay() {
		return this.getGamePlay().getSideToPlay();
	}
	// ---------------------------------------------------------------------
	protected final List<LegalMoveInterface> getLegalMoves(final OpponentsEnumeration sideToPlay) {
		return this.getGamePlay().getLegalMoves(sideToPlay);
	}
	// ---------------------------------------------------------------------
	protected final OpponentInterface getOpponentByOrder(final OpponentsEnumeration sideToPlay) {
		return this.getGamePlay().getOpponentByOrder(sideToPlay);
	}
	// ---------------------------------------------------------------------
	protected final void play(final LegalMoveInterface legalMove) {
		this.getGamePlay().play(legalMove);
	}
	// ---------------------------------------------------------------------
	protected final boolean isGamePlayOver() {
		return this.gamePlay.isGamePlayOver();
	}
	// ---------------------------------------------------------------------
}