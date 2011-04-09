
package abstractions.position;

import java.util.Map;
import java.util.Set;

import abstractions.dimension.API.DimensionInterface;
import abstractions.position.relative.RelativePositionInterface;

import com.google.common.collect.Maps;

public class PositionManager implements PositionManagerInterface {

    private final PositionSetFactoryInterface factory = new PositionSetFactory();
    private final Map<Integer, PositionInterface> data;
    private final int hashBase;

    private final PositionInterface nullPosition;

    private final int hash(final Integer rowIndex, final Integer columnIndex) {
        return this.hashBase * rowIndex.hashCode() + columnIndex.hashCode();
    }

    public PositionManager(final DimensionInterface dimension) {
        this.hashBase = Math.max(dimension.numberOfRows(), dimension.numberOfColumns());
        final Set<PositionInterface> set = this.factory.NewPositionSet(dimension);
        this.data = Maps.newHashMapWithExpectedSize(set.size());
        for (final PositionInterface element : set) {
            this.data.put(this.hash(element.getRow(), element.getColumn()), element);
        }
        this.nullPosition = this.getPosition(0, 0);
    }

    @Override
    public PositionInterface getPosition(final int rowIndex, final int columnIndex) {
        PositionInterface position = this.data.get(this.hash(rowIndex, columnIndex));
        if (position == null) {
            position = this.nullPosition;
        }
        return position;
    }

    @Override
    public PositionInterface getPosition(final PositionInterface position, final RelativePositionInterface relativePosition) {
        return null;
    }

}
