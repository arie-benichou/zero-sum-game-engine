
package game.board.dimensions;

public final class Dimensions implements DimensionsInterface {

    private final RowsRange rowRange;
    private final ColumnsRange columRange;

    public Dimensions(final int numberOfRows, final int numberOfColumns) {
        this(new RowsRange(1, numberOfRows), new ColumnsRange(1, numberOfColumns));
    }

    private Dimensions(final RowsRange rowsRange, final ColumnsRange columnsRange) {
        this.rowRange = rowsRange;
        this.columRange = columnsRange;
    }

    public final int lowerBoundForRows() {
        return this.rowRange.getLowerBound();
    }

    public final int upperBoundForRows() {
        return this.rowRange.getUpperBound();
    }

    public final int lowerBoundForColumns() {
        return this.columRange.getLowerBound();
    }

    public final int upperBoundForColumns() {
        return this.columRange.getUpperBound();
    }

    public final int numberOfRows() {
        return this.rowRange.getCapacity();
    }

    public final int numberOfColumns() {
        return this.columRange.getCapacity();
    }

    public final int boardCapacity() {
        return this.numberOfRows() * this.numberOfColumns();
    }

    public final boolean contains(final int rowIndex, final int columnIndex) {
        return this.rowRange.contains(rowIndex) && this.columRange.contains(columnIndex);
    }

}