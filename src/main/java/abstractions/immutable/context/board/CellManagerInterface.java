
package abstractions.immutable.context.board;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.context.board.cell.CellInterface;
import abstractions.immutable.move.mutation.MutationInterface;

public interface CellManagerInterface<ADDRESS, VALUE> extends ImmutableInterface<CellManagerInterface<ADDRESS, VALUE>> {

    CellInterface<VALUE> cell(final ADDRESS address);

    public CellManagerInterface<ADDRESS, VALUE> apply(MutationInterface<ADDRESS, VALUE> value);

    @Override
    public CellManagerInterface<ADDRESS, VALUE> apply();

}