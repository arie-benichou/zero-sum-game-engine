
package game.board.dimension;

public final class API {
	
	public static class IllegalDimensionException extends RuntimeException {
		
        private static final String MESSAGE = "Dimension(numberOfRows=%d, numberOfColumns=%d) is not a legal dimension.";

        private static final long serialVersionUID = 1L;
        
        private int numberOfRows;
        private int numberOfColumns;

        public IllegalDimensionException(final int numberOfRows, final int numberOfColumns) {
            super();
            this.numberOfRows = numberOfRows;
            this.numberOfColumns = numberOfColumns;
        }
        
        @Override
        public String getMessage() {
            return String.format(IllegalDimensionException.MESSAGE, this.numberOfRows, this.numberOfColumns);
        }		
		
	}

    /**
     * This is the interface for the dimension of a board.
     */
    public static interface DimensionInterface {

        /**
         * Returns the first row index.
         * 
         * @return the first row index
         */
        int lowerBoundForRows();

        /**
         * Returns the last row index.
         * 
         * @return the last row index
         */
        int upperBoundForRows();

        /**
         * Returns the first column index.
         * 
         * @return the first column index
         */
        int lowerBoundForColumns();

        /**
         * Returns the last column index.
         * 
         * @return the last column index
         */
        int upperBoundForColumns();

        /**
         * Returns the number of rows.
         * 
         * @return the number of rows
         */
        int numberOfRows();

        /**
         * Returns the number of columns.
         * 
         * @return the number of columns
         */
        int numberOfColumns();

        /**
         * Returns the capacity of a board with these dimension.
         * 
         * @return the capacity of a board with these dimension
         */
        int boardCapacity();

        /**
         * Returns true if the row index and column index are contained in these
         * board dimension, false otherwise.
         * 
         * @param rowIndex
         *            a given row index
         * 
         * @param columnIndex
         *            a given column index
         * 
         * @return true if the row index and column index are contained in these
         *         board dimension, false otherwise
         */

        boolean contains(final int rowIndex, final int columnIndex);

    }

    public final static class DimensionFactory {

        public static  DimensionInterface Dimension(final int numberOfRows, final int numberOfColumns) {
        	
            try {
				return new Dimension(new RowsRange(1, numberOfRows), new ColumnsRange(1, numberOfColumns));
			} catch (IllegalArgumentException e) {
				throw new IllegalDimensionException(numberOfRows, numberOfColumns);
			}
            
        }
    }

}