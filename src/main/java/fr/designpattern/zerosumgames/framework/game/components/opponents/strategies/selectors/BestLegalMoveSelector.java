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

package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors;

import java.util.Collections;
import java.util.List;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;

public class BestLegalMoveSelector implements MoveSelectorInterface {
	
	public MoveInterface select(GameInterface context,  final List<MoveInterface> legalMoves) {
		return Collections.max(legalMoves);
	}
	
	public MoveInterface select(final List<MoveInterface> legalMoves) {
		return Collections.max(legalMoves);
	}	
	
}