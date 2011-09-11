
package abstractions.immutable.board;

import java.util.Map;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.board.cell.CellInterface;

public interface CellManagerInterface<ADDRESS, VALUE> extends ImmutableInterface<CellManagerInterface<ADDRESS, VALUE>> {

    CellInterface<VALUE> cell(final ADDRESS address);

    public CellManagerInterface<ADDRESS, VALUE> apply(Map<ADDRESS, VALUE> value);

    @Override
    public CellManagerInterface<ADDRESS, VALUE> apply();

}