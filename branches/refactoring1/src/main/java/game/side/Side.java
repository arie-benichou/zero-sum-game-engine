/*
 * @(#)GamePlayersEnumeration.java 0.99
 * 
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package game.side;

/**
 * This is the enumeration of players.
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
 */

public enum Side {

    FIRST_PLAYER() {

        @Override
        public Side getOpponent() {
            return SECOND_PLAYER;
        }

        @Override
        public Side getNegation() {
            return NOT_FIRST_PLAYER;
        }
    },

    SECOND_PLAYER() {

        @Override
        public Side getOpponent() {
            return FIRST_PLAYER;
        }

        @Override
        public Side getNegation() {
            return NOT_SECOND_PLAYER;
        }
    },

    NO_ONE {

        @Override
        public Side getOpponent() {
            return NO_ONE;
        }

        @Override
        public Side getNegation() {
            return NO_ONE;
        }
    },

    NOT_FIRST_PLAYER() {

        @Override
        public Side getOpponent() {
            return NOT_SECOND_PLAYER;
        }

        @Override
        public Side getNegation() {
            return FIRST_PLAYER;
        }
    },

    NOT_SECOND_PLAYER {

        @Override
        public Side getOpponent() {
            return NOT_FIRST_PLAYER;
        }

        @Override
        public Side getNegation() {
            return SECOND_PLAYER;
        }
    };

    public abstract Side getOpponent();

    public static Side opponent(final Side side) {
        return side.getOpponent();
    }

    public abstract Side getNegation();

    public static Side not(final Side side) {
        return side.getNegation();
    }

    public static boolean isNoOne(final Side side) {
        return side.equals(NO_ONE);
    }

    public static boolean isFirstPlayer(final Side side) {
        return side.equals(FIRST_PLAYER);
    }

    public static boolean isSecondPlayer(final Side side) {
        return side.equals(SECOND_PLAYER);
    }

    public static boolean isAPlayer(final Side side) {
        return side.equals(FIRST_PLAYER) || side.equals(SECOND_PLAYER);
    }

    public boolean isAPlayer() {
        return Side.isAPlayer(this);
    }

    public boolean isNoOne() {
        return Side.isNoOne(this);
    }

}