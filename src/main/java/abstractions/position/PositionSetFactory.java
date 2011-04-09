
package abstractions.position;

import java.util.Collections;
import java.util.Set;

import abstractions.dimension.API.DimensionInterface;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

/**
 * The position factory.
 */
public final class PositionSetFactory implements PositionSetFactoryInterface {

    private final static PositionInterface NULL_POSITION = new Position(0, 0);

    private PositionInterface newPosition(final int rowIndex, final int columnIndex) {
        return new Position(rowIndex, columnIndex);
    }

    @Override
    public Set<PositionInterface> NewPositionSet(final DimensionInterface dimension) {
        Preconditions.checkNotNull(dimension, "Argument 'dimension' is not intended to be null.");
        final Set<PositionInterface> positions = Sets.newHashSetWithExpectedSize(dimension.boardCapacity() + 1);
        positions.add(PositionSetFactory.NULL_POSITION);
        for (int rowIndex = dimension.lowerBoundForRows(), maxRowIndex = dimension.upperBoundForRows(); rowIndex <= maxRowIndex; ++rowIndex) {
            for (int columnIndex = dimension.lowerBoundForColumns(), maxColumnIndex = dimension.upperBoundForColumns(); columnIndex <= maxColumnIndex; ++columnIndex) {
                positions.add(this.newPosition(rowIndex, columnIndex));
            }
        }
        return Collections.unmodifiableSet(positions);

    }

}