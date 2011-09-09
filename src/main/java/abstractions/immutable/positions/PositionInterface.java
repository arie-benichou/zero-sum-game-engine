
package abstractions.immutable.positions;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.directions.DirectionInterface;

public interface PositionInterface extends ImmutableInterface<PositionInterface> {

    int row();

    int column();

    @Override
    PositionInterface apply();

    PositionInterface apply(final int row, final int column);

    PositionInterface apply(DirectionInterface direction);

}
