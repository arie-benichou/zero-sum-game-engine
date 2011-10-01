
package context.entity.game.board.cell.position;

import util.annotations.Application;
import util.annotations.Value;
import util.interfaces.ImmutableInterface;
import util.interfaces.NullObjectInterface;
import context.entity.game.board.direction.DirectionInterface;

public interface PositionInterface extends ImmutableInterface<PositionInterface>, NullObjectInterface, Comparable<PositionInterface> {

    /*-------------------------------------8<-------------------------------------*/

    @Value
    int row();

    @Value    
    int column();

    /*-------------------------------------8<-------------------------------------*/

    @Application    
    PositionInterface apply(final int row, final int column);

    @Application    
    PositionInterface apply(DirectionInterface direction);
    
    /*-------------------------------------8<-------------------------------------*/    

    //TODO ?? RowObject et ColumnObject => apply(RowObject) et apply(ColumnObject)

}