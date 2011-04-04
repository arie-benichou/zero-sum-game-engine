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


/**
 * API related to positions.
 */
public class API {

    /**
     * The null object for a position.
     */
    public final static PositionInterface NULL_POSITION = new NullPosition();

    /**
     * Class for illegal positions.
     */
    public static final class IllegalPositionException extends RuntimeException {

        private static final String MESSAGE = "Position(row=%d, column=%d) is not a legal position.";

        private static final long serialVersionUID = 1L;

        private int rowIndex;
        private int columnIndex;

        public IllegalPositionException(final int rowIndex, final int columnIndex) {
            super();
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
        }

        @Override
        public String getMessage() {
            return String.format(MESSAGE, this.rowIndex, this.columnIndex);
        }

    }

    /**
     * This is the interface for a position.
     */
    public static interface PositionInterface extends Comparable<PositionInterface> {

        /**
         * Returns the column index of this position.
         * 
         * @return the column index of this position
         */
        int getColumn();

        /**
         * Returns the row index of this position.
         * 
         * @return the row index of this position
         */
        int getRow();

        /**
         * Returns true if this position is the null object, false otherwise.
         * 
         * @return true if this position is the null object, false otherwise
         */
        boolean isNull();

    }

    /**
     * The position factory.
     */
    /*
    public static final class PositionFactory {
    */
        /**
         * Returns a new instance of a position.
         * 
         * @param rowIndex
         *            the row index related to this position
         * 
         * @param columnIndex
         *            the column index related to this position
         * 
         * @return a new instance of a position
         */
        /*
        public static PositionInterface Position(final int rowIndex, final int columnIndex) {
            try {
                return new AbsolutePosition(rowIndex, columnIndex);
            }
            catch (IllegalArgumentException e) {
                throw new IllegalPositionException(rowIndex, columnIndex);
            }
        }
        */
        /**
         * Returns an unmodifiable set of new positions for a given dimension.
         * 
         * @param dimension
         *            a given dimension
         * 
         * @return an unmodifiable set of new positions for a given dimension.
         */
        /*
        public static Set<PositionInterface> Positions(final DimensionInterface dimension) {
        	checkNotNull(dimension, "Argument 'dimension' is not intended to be null.");
            final Set<PositionInterface> positions = Sets.newHashSetWithExpectedSize(dimension.boardCapacity());
            for (int rowIndex = dimension.lowerBoundForRows(), maxRowIndex = dimension.upperBoundForRows(); rowIndex <= maxRowIndex; ++rowIndex) {
                for (int columnIndex = dimension.lowerBoundForColumns(), maxColumnIndex = dimension.upperBoundForColumns(); columnIndex <= maxColumnIndex; ++columnIndex) {
                    positions.add(Position(rowIndex, columnIndex));
                }
            }
            return Collections.unmodifiableSet(positions);
        }
        */
    /*
    }
    */
}
