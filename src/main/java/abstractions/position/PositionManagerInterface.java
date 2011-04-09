package abstractions.position;

import abstractions.position.relative.RelativePositionInterface;


public interface PositionManagerInterface {
    
    
    PositionInterface getPosition(int rowIndex, int columnIndex);
    
    PositionInterface getPosition(PositionInterface position, RelativePositionInterface relativePosition);
    

}
