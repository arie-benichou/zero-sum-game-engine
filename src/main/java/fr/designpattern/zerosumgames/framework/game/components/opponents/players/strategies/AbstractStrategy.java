/*
 * Copyright (C) 2011 Arié Bénichou
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

package fr.designpattern.zerosumgames.framework.game.components.opponents.players.strategies;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.game.components.moves.IGameMoveSelector;

public class AbstractStrategy implements StrategyInterface {

	private final transient IGameMoveSelector selector;
	public final IGameMoveSelector getSelector() {
		return this.selector;
	}

	public AbstractStrategy(final IGameMoveSelector selector) {
		this.selector = selector;
	}
	
	public MoveInterface chooseMoveAmong(final GameInterface context, final List<MoveInterface> legalMoves) {
		return this.selector.select(context, legalMoves);
	}

}