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

package fr.designpattern.zerosumgames.framework.build.game.components.opponents.strategies.selectors;

import java.util.List;

import fr.designpattern.zerosumgames.framework.build.game.IGame;
import fr.designpattern.zerosumgames.framework.build.game.components.move.IGameMove;
import fr.designpattern.zerosumgames.framework.build.game.components.move.IGameMoveSelector;

public class FirstMoveSelector implements IGameMoveSelector {

	public IGameMove select(IGame context,  final List<IGameMove> legalMoves) {
		return legalMoves.get(0);
	}

}