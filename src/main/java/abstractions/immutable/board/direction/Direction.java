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

package abstractions.immutable.board.direction;

import java.util.Map;

import com.google.common.collect.Maps;

public final class Direction implements DirectionInterface {

    /*-------------------------------------8<-------------------------------------*/

    public final static DirectionInterface NULL = new Direction(0, 0);

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final int rowDelta, final int columnDelta) {
        return (17 * 31 + rowDelta) * 31 + columnDelta;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final static class Factory {

        private final static Map<Integer, DirectionInterface> CACHE = Maps.newHashMap();

        public static DirectionInterface get(final int rowDelta, final int columnDelta) {
            if (rowDelta == 0 && columnDelta == 0) return NULL;
            final int address = computeHashCode(rowDelta, columnDelta);
            DirectionInterface direction = CACHE.get(address);
            if (direction == null) {
                direction = new Direction(rowDelta, columnDelta);
                CACHE.put(address, direction);
            }
            return direction;
        }
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int rowDelta;

    @Override
    public int rowDelta() {
        return this.rowDelta;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int columnDelta;

    @Override
    public int columnDelta() {
        return this.columnDelta;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int hashCode;

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static DirectionInterface from(final int rowDelta, final int columnDelta) {
        return NULL.apply(rowDelta, columnDelta);
    }

    private Direction(final int rowDelta, final int columnDelta) {
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;
        this.hashCode = computeHashCode(rowDelta, columnDelta);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public DirectionInterface apply() {
        return this;
    }

    @Override
    public DirectionInterface apply(final int rowDelta, final int columnDelta) {
        return rowDelta == this.rowDelta() && columnDelta == this.columnDelta() ? this.apply() : Factory.get(rowDelta, columnDelta);
    }

    @Override
    public DirectionInterface apply(final DirectionInterface direction) {
        return this.apply(this.rowDelta() + direction.rowDelta(), this.columnDelta() + direction.columnDelta());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this)
            return true;
        if (object == null)
            return false;
        if (!(object instanceof DirectionInterface))
            return false;
        final DirectionInterface that = (DirectionInterface) object;
        if (that.hashCode() != this.hashCode())
            return false;
        return that.rowDelta() == this.rowDelta() && that.columnDelta() == this.columnDelta();
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.rowDelta() + ", " + this.columnDelta() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

    public static void main(final String[] args) {

        System.out.println(Direction.from(0, 0));
        System.out.println(Direction.from(1, -1));
        System.out.println(Direction.from(-1, 1));
        System.out.println(Direction.NULL.apply(1, 2).apply(Direction.from(1, 2)));

        System.out.println(Factory.CACHE.size());

    }

}