
package abstractions.direction;

public enum NamedDirection {

    TOP(-1, 0),
    TOP_RIGHT(-1, 1),
    RIGHT(0, 1),
    BOTTOM_RIGHT(1, 1),
    BOTTOM(1, 0),
    BOTTOM_LEFT(1, -1),
    LEFT(0, -1),
    TOP_LEFT(-1, -1);

    private final int rowDelta;
    private final int columnDelta;

    private NamedDirection(final int rowDelta, final int columnDelta) {
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;
    }

    public final int getRowDelta() {
        return this.rowDelta;
    }

    public final int getColumnDelta() {
        return this.columnDelta;
    }

}
