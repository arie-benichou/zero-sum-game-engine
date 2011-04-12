
package abstractions.direction;

public final class Direction implements DirectionInterface {

    private final int rowDelta;
    private final int columnDelta;

    public Direction(final int rowDelta, final int columnDelta) {
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
    public final String toString() {
        return "[rowDelta = " + this.getRowDelta() + "]" + "[columnDelta = " + this.getColumnDelta() + "]";
    }

}
