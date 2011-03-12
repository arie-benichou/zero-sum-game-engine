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

package fr.designpattern.zerosumgames.core.strategies;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import fr.designpattern.zerosumgames.core.interfaces.IGame;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;
import fr.designpattern.zerosumgames.core.interfaces.IGamePlayerStrategy;

public class GamePlayerHumanStrategy implements IGamePlayerStrategy {

	public IGameBoardMove chooseMoveAmong(final IGame game, final List<IGameBoardMove> legalMoves) {
		IGameBoardMove choosenMove = null;
		final Scanner scanner = new Scanner(System.in);
		System.out.println("\nWhat is your move?"); 
		int i = 0;
		try {
			i = scanner.nextInt();
			if (i < 1 || i > legalMoves.size()) {
				System.out.println("No such move!");
				choosenMove = this.chooseMoveAmong(game, legalMoves);
			}
			else {
				choosenMove = legalMoves.get(i - 1);
				System.out.println("You have choosen to play move #" + i + ": " + choosenMove);
			}
		}
		catch(InputMismatchException e) {
			System.out.println("Integer expected!");
			choosenMove = this.chooseMoveAmong(game, legalMoves);
		}
		return choosenMove;
	}
}
