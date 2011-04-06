
package abstractions.position.absolute;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Set;

import abstractions.dimension.API.DimensionInterface;

import com.google.common.collect.Sets;

/**
 * The position factory.
 */
public final class AbsolutePositions {

    /**
     * The null object for a position.
     */
    public final static AbsolutePositionInterface NULL_POSITION = new NullAbsolutePosition();

    private final static AbsolutePositions INSTANCE = new AbsolutePositions();

    public final static AbsolutePositions getInstance() {
        return INSTANCE;
    }

    private AbsolutePositions() {}

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
    public AbsolutePositionInterface getPosition(final int rowIndex, final int columnIndex) {
        try {
            return new AbsolutePosition(rowIndex, columnIndex);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalAbsolutePositionException(rowIndex, columnIndex);
        }
    }

    /**
     * Returns an unmodifiable set of new positions for a given dimension.
     * 
     * @param dimension
     *            a given dimension
     * 
     * @return an unmodifiable set of new positions for a given dimension.
     */
    public Set<AbsolutePositionInterface> getAllPositions(final DimensionInterface dimension) {
        checkNotNull(dimension, "Argument 'dimension' is not intended to be null.");
        final Set<AbsolutePositionInterface> positions = Sets.newHashSetWithExpectedSize(dimension.boardCapacity());
        for (int rowIndex = dimension.lowerBoundForRows(), maxRowIndex = dimension.upperBoundForRows(); rowIndex <= maxRowIndex; ++rowIndex) {
            for (int columnIndex = dimension.lowerBoundForColumns(), maxColumnIndex = dimension.upperBoundForColumns(); columnIndex <= maxColumnIndex; ++columnIndex) {
                positions.add(this.getPosition(rowIndex, columnIndex));
            }
        }
        return Collections.unmodifiableSet(positions);
    }

}