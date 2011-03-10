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

package main.java.games.core;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import main.java.games.core.interfaces.IGameBoardMove;
import main.java.games.core.interfaces.IGamePlayerStrategy;

public class GamePlayerHumanStrategy implements IGamePlayerStrategy {

	@Override
	public IGameBoardMove chooseMoveAmong(final List<IGameBoardMove> legalMoves) {
		IGameBoardMove choosenMove = null;
		final Scanner scanner = new Scanner(System.in);
		System.out.println("\nWhat is your move?"); // NOPMD by STAGIAIRE on 10/03/11 15:33
		int i = 0;
		try {
			i = scanner.nextInt();
			if (i < 1 || i > legalMoves.size()) {
				System.out.println("No such move!");
				choosenMove = this.chooseMoveAmong(legalMoves);
			}
			else {
				choosenMove = legalMoves.get(i - 1);
			}
		}
		catch(InputMismatchException e) {
			System.out.println("Integer expected!");
			choosenMove = this.chooseMoveAmong(legalMoves);
		}
		System.out.println("You have choosen to play move #" + i + ": " + choosenMove);
		return choosenMove;
	}
}