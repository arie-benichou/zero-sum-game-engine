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

package abstractions.direction;

public final class Direction implements DirectionInterface {

    private final int rowDelta;
    private final int columnDelta;

    private volatile int hashCode; // NOPMD

    public Direction(final int rowDelta, final int columnDelta) {
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;
    }

    public int getRowDelta() {
        return this.rowDelta;
    }

    public int getColumnDelta() {
        return this.columnDelta;
    }

    @Override
    public String toString() {
        return "[rowDelta = " + this.getRowDelta() + "]" + "[columnDelta = " + this.getColumnDelta() + "]";
    }

    @Override
    public int hashCode() {
        int result = this.hashCode;
        if (result == 0) {
            result = new StringBuilder("rowDelta").append(this.getRowDelta()).append("columnDelta").append(this.getColumnDelta()).toString().hashCode();
            this.hashCode = result;
        }
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        final boolean isEqual; // NOPMD 
        if (object == this) {
            isEqual = true;
        }
        else if (object == null) {
            isEqual = false;
        }
        else if (!(object instanceof DirectionInterface)) { // NOPMD 
            isEqual = false;
        }
        else {
            final DirectionInterface that = (DirectionInterface) object;
            isEqual = that.getRowDelta() == this.getRowDelta() && that.getColumnDelta() == this.getColumnDelta();
        }
        return isEqual;
    }

}
