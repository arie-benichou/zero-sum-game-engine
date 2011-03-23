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

package fr.designpattern.zerosumgames.framework.opponents.strategies.selectors;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import fr.designpattern.zerosumgames.framework.moves.MoveInterface;

public class HumanMoveSelector extends NullSelector {

	@Override
	public MoveInterface applySelection(final List<MoveInterface> legalMoves) {
		
		MoveInterface move;
		
		System.out.println("\nWhat is your move ?");
		
		final Scanner scanner = new Scanner(System.in);
		
		int i = 0;
		try {
			i = scanner.nextInt();
			if (i < 1 || i > legalMoves.size()) {
				System.out.println("No such move!");
				move = this.applySelection(legalMoves);
			} else {
				move = legalMoves.get(i - 1);
				System.out.println("You have choosen to play move #" + i + ": " + move);
			}			
		} catch (InputMismatchException e) {
			System.out.println("Integer expected!");
			move = this.applySelection(legalMoves);
		}
		return move;
	}

}