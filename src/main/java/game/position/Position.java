
package game.position;

final class Position extends AbstractPosition {

    public Position(final int row, final int column) {
        super(row, column);
        if (row < 1) {
            throw new IllegalArgumentException("Argument 'row' must be greater than 0.");
        }
        if (column < 1) {
            throw new IllegalArgumentException("Argument 'column' must be greater than 0.");
        }
    }

    @Override
    public final boolean isNull() {
        return false;
    }

}