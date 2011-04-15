/*
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

package abstractions.side;

enum Side implements SideInterface {

    FIRST_SIDE() {

        @Override
        public final Side getNextSide() {
            return SECOND_SIDE;
        }

        @Override
        public final Side getNegation() {
            return NOT_FIRST_SIDE;
        }
    },

    SECOND_SIDE() {

        @Override
        public final Side getNextSide() {
            return FIRST_SIDE;
        }

        @Override
        public final Side getNegation() {
            return NOT_SECOND_SIDE;
        }
    },

    NULL_SIDE {

        @Override
        public final Side getNextSide() {
            return this;
        }

        @Override
        public final Side getNegation() {
            return this;
        }
    },

    NOT_FIRST_SIDE() {

        @Override
        public final Side getNextSide() {
            return this;
        }

        @Override
        public final Side getNegation() {
            return FIRST_SIDE;
        }
    },

    NOT_SECOND_SIDE {

        @Override
        public final Side getNextSide() {
            return this;
        }

        @Override
        public final Side getNegation() {
            return SECOND_SIDE;
        }
    };

    public final boolean isFirstSide() {
        return this.equals(FIRST_SIDE);
    }

    public final boolean isSecondSide() {
        return this.equals(SECOND_SIDE);
    }

    public final boolean isOneSide() {
        return this.isFirstSide() || this.isSecondSide();
    }

    public final boolean isNull() {
        return this.equals(NULL_SIDE);
    }

    public abstract Side getNextSide();

    public abstract Side getNegation();

}