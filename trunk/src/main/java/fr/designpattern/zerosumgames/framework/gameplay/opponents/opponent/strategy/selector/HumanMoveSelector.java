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

package fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.selector;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import fr.designpattern.zerosumgames.framework.gameplay.legalMoves.legalMove.LegalMoveInterface;

public class HumanMoveSelector extends NullSelector {

	@Override
	public LegalMoveInterface applySelection(final List<LegalMoveInterface> legalMoves) {
		
		LegalMoveInterface move;

		int n = 0;
		final int numberOfDigits = (int) Math.log10(Math.abs(legalMoves.size())) + 1;
		System.out.println("\nLegal moves :");
		for (LegalMoveInterface legalMove : legalMoves) {
			System.out.format(" %0" + numberOfDigits + "d: %s\n", ++n, legalMove);
		}
			
		System.out.println("\nWhat is your move ?\n");
		
		final Scanner scanner = new Scanner(System.in);
		
		int i = 0;
		try {
			i = scanner.nextInt();
			if (i < 1 || i > legalMoves.size()) {
				System.out.println("\nThere is no such move.");
				System.out.println("Please try again...");
				move = this.applySelection(legalMoves);
			} else {
				move = legalMoves.get(i - 1);
				System.out.println("\nYou have choosen to play: " + move);
			}			
		} catch (InputMismatchException e) {
			System.out.println("\nPositive natural integer expected.");
			System.out.println("Please try again...");
			move = this.applySelection(legalMoves);
		}
		return move;
	}

}