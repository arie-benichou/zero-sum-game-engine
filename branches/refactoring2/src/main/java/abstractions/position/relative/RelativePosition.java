
package abstractions.position.relative;

class RelativePosition implements RelativePositionInterface {

    private final int rowDelta;
    private final int columnDelta;

    public RelativePosition(final int rowDelta, final int columnDelta) {
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;
    }

    public final int getRowDelta() {
        return this.rowDelta;
    }

    public final int getColumnDelta() {
        return this.columnDelta;
    }

    @Override
    public String toString() {
        return "{" + this.getRowDelta() + "}" + "{" + this.getColumnDelta() + "}";
    }

    public int computeRow(int row) {
        return row + this.getRowDelta();
    }

    public int computeColumn(int column) {
        return column + this.getColumnDelta();
    }

}