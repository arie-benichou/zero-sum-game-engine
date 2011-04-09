
package abstractions.position;

import abstractions.position.PositionManager.DirectionInterface;

public interface PositionManagerInterface {

    PositionInterface getNullPosition();

    PositionInterface getPosition(int rowIndex, int columnIndex);

    PositionInterface getPosition(PositionInterface position, DirectionInterface direction);

}
