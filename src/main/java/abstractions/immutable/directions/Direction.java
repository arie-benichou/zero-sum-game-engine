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

package abstractions.immutable.directions;

import java.util.Map;

import com.google.common.collect.Maps;

public final class Direction implements DirectionInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final int rowDelta, final int columnDelta) {
        return (17 * 31 + rowDelta) * 31 + columnDelta;
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static class DirectionFactory {

        private final static Map<Integer, DirectionInterface> CACHE = Maps.newHashMap();

        public static DirectionInterface get(final int rowDelta, final int columnDelta) {
            DirectionInterface direction;
            final int address = computeHashCode(rowDelta, columnDelta);
            if ((direction = CACHE.get(address)) == null) {
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
        return DirectionFactory.get(rowDelta, columnDelta);
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
        return rowDelta == this.rowDelta() && columnDelta == this.columnDelta() ? this.apply() : from(rowDelta, columnDelta);
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
        return this.getClass().getSimpleName() + "( " + this.rowDelta() + " , " + this.columnDelta() + " ) ";
    }
    /*-------------------------------------8<-------------------------------------*/

}