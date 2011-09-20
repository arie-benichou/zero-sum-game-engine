
package fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.direction.DirectionInterface;

public interface PositionInterface extends ImmutableInterface<PositionInterface>, Comparable<PositionInterface> {

    /*-------------------------------------8<-------------------------------------*/

    int row();

    int column();

    /*-------------------------------------8<-------------------------------------*/

    @Override
    PositionInterface apply();

    PositionInterface apply(final int row, final int column);

    PositionInterface apply(DirectionInterface direction);

    //TODO ?? RowObject et ColumnObject => apply(RowObject) et apply(ColumnObject)

    /*-------------------------------------8<-------------------------------------*/

}