
package abstractions.direction;

import java.util.Map;

public enum _Directions {

    TOP(-1, 0),
    TOP_RIGHT(-1, 1),
    RIGHT(0, 1),
    BOTTOM_RIGHT(1, 1),
    BOTTOM(1, 0),
    BOTTOM_LEFT(1, -1),
    LEFT(0, -1),
    TOP_LEFT(-1, -1);

    private final DirectionInterface direction;

    private Map<String, DirectionInterface> cache;

    private DirectionInterface newLabeledDirection(final int rowDelta, final int columnDelta) {
        return new Direction(rowDelta, columnDelta);
    }

    private _Directions(final int rowDelta, final int columnDelta) {
        this.direction = this.newLabeledDirection(rowDelta, columnDelta);
    }

    public DirectionInterface direction() {
        return this.direction;
    }

    public static void main(final String[] args) {
        System.out.println(TOP.direction());
        System.out.println(BOTTOM.direction());
    }
}
