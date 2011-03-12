/*
 * @(#)GamePlayersEnumeration.java	0.99
 * 
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

package main.java.games.core.types;

/**
 * This is the enumeration of players.
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
 */

public enum GamePlayersEnumeration {

	FIRST_PLAYER() {
		public GamePlayersEnumeration getOpponent() {
			return SECOND_PLAYER;
		}
		public GamePlayersEnumeration not() {
			return NOT_FIRST_PLAYER;
		}						
	},

	SECOND_PLAYER() {
		public GamePlayersEnumeration getOpponent() {
			return FIRST_PLAYER;
		}
		public GamePlayersEnumeration not() {
			return NOT_SECOND_PLAYER;
		}						
	},
	
	NOT_FIRST_PLAYER() {
		public GamePlayersEnumeration getOpponent() {
			return NOT_SECOND_PLAYER;
		}
		public GamePlayersEnumeration not() {
			return FIRST_PLAYER;
		}				
	},
	
	NOT_SECOND_PLAYER {
		public GamePlayersEnumeration getOpponent() {
			return NOT_FIRST_PLAYER;
		}
		public GamePlayersEnumeration not() {
			return SECOND_PLAYER;
		}		
	},
	
	NONE {
		public GamePlayersEnumeration getOpponent() {
			return NONE;
		}
		public GamePlayersEnumeration not() {
			return NONE;
		}
	};

	public abstract GamePlayersEnumeration getOpponent();
	public abstract GamePlayersEnumeration not();

}