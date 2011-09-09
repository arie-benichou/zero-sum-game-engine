
package abstractions.immutable.cells;

import java.util.Map;

import abstractions.immutable.ImmutableInterface;

public interface CellManagerInterface<ADDRESS, VALUE> extends ImmutableInterface<CellManagerInterface<ADDRESS, VALUE>> {

    CellInterface<VALUE> cell(final ADDRESS address);

    public CellManagerInterface<ADDRESS, VALUE> apply(Map<ADDRESS, VALUE> value);

    @Override
    public CellManagerInterface<ADDRESS, VALUE> apply();

}