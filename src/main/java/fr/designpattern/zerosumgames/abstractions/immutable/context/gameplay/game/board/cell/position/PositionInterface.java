
package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.position;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.direction.DirectionInterface;

public interface PositionInterface extends ImmutableInterface<PositionInterface>, Comparable<PositionInterface> {

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