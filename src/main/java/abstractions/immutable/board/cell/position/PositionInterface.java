
package abstractions.immutable.board.cell.position;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.board.direction.DirectionInterface;

public interface PositionInterface extends ImmutableInterface<PositionInterface> {

    /*-------------------------------------8<-------------------------------------*/

    int row();

    int column();

    /*-------------------------------------8<-------------------------------------*/

    @Override
    PositionInterface apply();

    //TODO ?? RowObject et ColumnObject => apply(RowObject) et apply(ColumnObject)
    PositionInterface apply(final int row, final int column);

    PositionInterface apply(DirectionInterface direction);

    /*-------------------------------------8<-------------------------------------*/

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    /*-------------------------------------8<-------------------------------------*/

    @Override
    String toString();

    /*-------------------------------------8<-------------------------------------*/

}