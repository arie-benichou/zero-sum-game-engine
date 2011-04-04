
package abstractions.position;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Set;

import abstractions.dimension.API.DimensionInterface;
import abstractions.position.API.IllegalPositionException;
import abstractions.position.API.PositionInterface;

import com.google.common.collect.Sets;

/**
 * The position factory.
 */
public final class PositionFactory {
    
    private static final PositionFactory INSTANCE = new PositionFactory();
    
    public final static PositionFactory getInstance() {
        return INSTANCE;
    }
    
    private PositionFactory() {}    

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
    public PositionInterface position(final int rowIndex, final int columnIndex) {
        try {
            return new AbsolutePosition(rowIndex, columnIndex);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalPositionException(rowIndex, columnIndex);
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
    public Set<PositionInterface> positions(final DimensionInterface dimension) {
        checkNotNull(dimension, "Argument 'dimension' is not intended to be null.");
        final Set<PositionInterface> positions = Sets.newHashSetWithExpectedSize(dimension.boardCapacity());
        for (int rowIndex = dimension.lowerBoundForRows(), maxRowIndex = dimension.upperBoundForRows(); rowIndex <= maxRowIndex; ++rowIndex) {
            for (int columnIndex = dimension.lowerBoundForColumns(), maxColumnIndex = dimension.upperBoundForColumns(); columnIndex <= maxColumnIndex; ++columnIndex) {
                positions.add(this.position(rowIndex, columnIndex));
            }
        }
        return Collections.unmodifiableSet(positions);
    }

}