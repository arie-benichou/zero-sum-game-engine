
package abstractions.immutable.context.board.cell;

import abstractions.immutable.ImmutableInterface;

public interface CellInterface<VALUE> extends ImmutableInterface<CellInterface<VALUE>> {

    VALUE value();

    CellInterface<VALUE> apply(VALUE value);

    @Override
    CellInterface<VALUE> apply();

}
