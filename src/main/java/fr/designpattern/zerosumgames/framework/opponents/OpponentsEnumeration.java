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

package fr.designpattern.zerosumgames.framework.opponents;

/**
 * This is the enumeration of players.
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
 */


//TODO à intégrer dans Opponents
public enum OpponentsEnumeration {

	FIRST_PLAYER() {
		public OpponentsEnumeration getOpponent() {
			return SECOND_PLAYER;
		}
		public OpponentsEnumeration getNegation() {
			return NOT_FIRST_PLAYER;
		}						
	},

	SECOND_PLAYER() {
		public OpponentsEnumeration getOpponent() {
			return FIRST_PLAYER;
		}
		public OpponentsEnumeration getNegation() {
			return NOT_SECOND_PLAYER;
		}						
	},
	
	NO_ONE {
		public OpponentsEnumeration getOpponent() {
			return NO_ONE;
		}
		public OpponentsEnumeration getNegation() {
			return NO_ONE;
		}
	},
	
	NOT_FIRST_PLAYER() {
		public OpponentsEnumeration getOpponent() {
			return NOT_SECOND_PLAYER;
		}
		public OpponentsEnumeration getNegation() {
			return FIRST_PLAYER;
		}				
	},
	
	NOT_SECOND_PLAYER {
		public OpponentsEnumeration getOpponent() {
			return NOT_FIRST_PLAYER;
		}
		public OpponentsEnumeration getNegation() {
			return SECOND_PLAYER;
		}		
	};

	protected abstract OpponentsEnumeration getOpponent();
	public static OpponentsEnumeration opponent(final OpponentsEnumeration side) {
		return side.getOpponent();
	}
	
	protected abstract OpponentsEnumeration getNegation();
	public static OpponentsEnumeration not(final OpponentsEnumeration side) {
		return side.getNegation();
	}
	
	public static boolean isNoOne(final OpponentsEnumeration side) {
		return side.equals(NO_ONE) ;
	}
	
	public static boolean isFirstPlayer(final OpponentsEnumeration side) {
		return side.equals(FIRST_PLAYER);   
	}
	
	public static boolean isSecondPlayer(final OpponentsEnumeration side) {
		return side.equals(SECOND_PLAYER);   
	}	
	
	public static boolean isAPlayer(final OpponentsEnumeration side) {
		return side.equals(FIRST_PLAYER) || side.equals(SECOND_PLAYER);   
	}	

}