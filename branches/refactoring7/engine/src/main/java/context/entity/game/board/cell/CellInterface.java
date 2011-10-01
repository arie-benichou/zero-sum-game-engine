
package context.entity.game.board.cell;

import util.annotations.Application;
import util.annotations.Value;
import util.interfaces.ImmutableInterface;

public interface CellInterface<VALUE> extends ImmutableInterface<CellInterface<VALUE>> {

    /*-------------------------------------8<-------------------------------------*/

    @Value
    VALUE value();

    /*-------------------------------------8<-------------------------------------*/

    @Override
    @Application
    CellInterface<VALUE> apply();

    @Application
    CellInterface<VALUE> apply(VALUE value);

    /*-------------------------------------8<-------------------------------------*/

}
