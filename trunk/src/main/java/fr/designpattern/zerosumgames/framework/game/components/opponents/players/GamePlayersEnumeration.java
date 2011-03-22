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

package fr.designpattern.zerosumgames.framework.game.components.opponents.player;

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
		public GamePlayersEnumeration getNegation() {
			return NOT_FIRST_PLAYER;
		}						
	},

	SECOND_PLAYER() {
		public GamePlayersEnumeration getOpponent() {
			return FIRST_PLAYER;
		}
		public GamePlayersEnumeration getNegation() {
			return NOT_SECOND_PLAYER;
		}						
	},
	
	NO_ONE {
		public GamePlayersEnumeration getOpponent() {
			return NO_ONE;
		}
		public GamePlayersEnumeration getNegation() {
			return NO_ONE;
		}
	},
	
	NOT_FIRST_PLAYER() {
		public GamePlayersEnumeration getOpponent() {
			return NOT_SECOND_PLAYER;
		}
		public GamePlayersEnumeration getNegation() {
			return FIRST_PLAYER;
		}				
	},
	
	NOT_SECOND_PLAYER {
		public GamePlayersEnumeration getOpponent() {
			return NOT_FIRST_PLAYER;
		}
		public GamePlayersEnumeration getNegation() {
			return SECOND_PLAYER;
		}		
	};

	protected abstract GamePlayersEnumeration getOpponent();
	public static GamePlayersEnumeration opponent(final GamePlayersEnumeration side) {
		return side.getOpponent();
	}
	
	protected abstract GamePlayersEnumeration getNegation();
	public static GamePlayersEnumeration not(final GamePlayersEnumeration side) {
		return side.getNegation();
	}
	
	public static boolean isNoOne(final GamePlayersEnumeration side) {
		return side.equals(NO_ONE) ;
	}
	
	public static boolean isFirstPlayer(final GamePlayersEnumeration side) {
		return side.equals(FIRST_PLAYER);   
	}
	
	public static boolean isSecondPlayer(final GamePlayersEnumeration side) {
		return side.equals(SECOND_PLAYER);   
	}	
	
	public static boolean isAPlayer(final GamePlayersEnumeration side) {
		return side.equals(FIRST_PLAYER) || side.equals(SECOND_PLAYER);   
	}	

}