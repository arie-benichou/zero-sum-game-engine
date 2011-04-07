
package abstractions.position.absolute;

import static com.google.common.base.Preconditions.checkArgument;

final class AbsolutePosition extends AbstractAbsolutePosition {

    public AbsolutePosition(final int row, final int column) {
        super(row, column);
        checkArgument(row > 0, "Argument 'row' must be greater than 0.");
        checkArgument(column > 0, "Argument 'column' must be greater than 0.");
    }

    @Override
    public final boolean isNull() {
        return false;
    }

}