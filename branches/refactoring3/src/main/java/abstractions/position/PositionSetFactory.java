
package abstractions.position;

import java.util.Collections;
import java.util.Set;

import abstractions.dimension.API.DimensionInterface;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

/**
 * The position factory.
 */
final class PositionSetFactory implements PositionSetFactoryInterface {

    private PositionInterface newPosition(final int rowIndex, final int columnIndex) {
        return new Position(rowIndex, columnIndex);
    }

    @Override
    public Set<PositionInterface> newPositionSet(final DimensionInterface dimension) {
        Preconditions.checkNotNull(dimension, "Argument 'dimension' is not intended to be null.");
        final Set<PositionInterface> positions = Sets.newHashSetWithExpectedSize(dimension.boardCapacity() + 1);
        positions.add(this.newPosition(0, 0));
        for (int rowIndex = dimension.lowerBoundForRows(), maxRowIndex = dimension.upperBoundForRows(); rowIndex <= maxRowIndex; ++rowIndex) {
            for (int columnIndex = dimension.lowerBoundForColumns(), maxColumnIndex = dimension.upperBoundForColumns(); columnIndex <= maxColumnIndex; ++columnIndex) {
                positions.add(this.newPosition(rowIndex, columnIndex));
            }
        }
        return Collections.unmodifiableSet(positions);

    }

}