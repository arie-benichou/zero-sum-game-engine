
package context.entity.game.board;

import util.annotations.Application;
import util.annotations.Value;
import util.interfaces.ImmutableInterface;
import context.entity.game.board.cell.CellInterface;
import context.entity.game.board.mutation.MutationInterface;

public interface CellManagerInterface<ADDRESS, VALUE> extends ImmutableInterface<CellManagerInterface<ADDRESS, VALUE>> {

    /*-------------------------------------8<-------------------------------------*/

    @Value
    CellInterface<VALUE> cell(final ADDRESS address);

    /*-------------------------------------8<-------------------------------------*/

    @Override
    @Application
    public CellManagerInterface<ADDRESS, VALUE> apply();

    @Application
    public CellManagerInterface<ADDRESS, VALUE> apply(MutationInterface<ADDRESS, VALUE> value);

    /*-------------------------------------8<-------------------------------------*/

}