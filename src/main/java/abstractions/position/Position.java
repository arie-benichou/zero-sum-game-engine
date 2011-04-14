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

package abstractions.position;

import com.google.common.base.Preconditions;

final class Position implements PositionInterface {

    private final int row;
    private final int column;

    private volatile int hashCode;

    public Position(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public boolean isNull() {
        return this.getRow() == 0 || this.getColumn() == 0;
    }

    @Override
    public int hashCode() {
        int result = this.hashCode;
        if (result == 0) {
            result = new StringBuilder('r').append(this.getRow()).append('c').append(this.getColumn()).toString().hashCode();
            this.hashCode = result;
        }
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        final boolean isEqual;
        if (object == this) {
            isEqual = true;
        }
        else if (object == null) {
            isEqual = false;
        }
        else if (!(object instanceof PositionInterface)) {
            isEqual = false;
        }
        else {
            final PositionInterface that = (PositionInterface) object;
            if (that.hashCode() != this.hashCode()) {
                isEqual = false;
            }
            else {
                isEqual = that.isNull() == this.isNull() && that.getRow() == this.getRow() && that.getColumn() == this.getColumn();
            }
        }
        return isEqual;
    }

    public int compareTo(final PositionInterface that) {
        Preconditions.checkNotNull(that, "That argument is not intended to be null.");
        int value;
        if (this.getRow() < that.getRow()) {
            value = -1;
        }
        else if (this.getRow() > that.getRow()) {
            value = 1;
        }
        else if (this.getColumn() < that.getColumn()) {
            value = -1;
        }
        else if (this.getColumn() > that.getColumn()) {
            value = 1;
        }
        else {
            value = 0;
        }
        return value;
    }

    @Override
    public String toString() {
        return "[row = " + this.getRow() + "][column = " + this.getColumn() + "]";
    }

}