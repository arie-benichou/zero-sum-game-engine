
package cell;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;

import cell.API.CellInterface;

public class Cells extends ArrayList<CellInterface> {

    private static final long serialVersionUID = 1L;

    public Cells() {
        super();
    }

    public Cells(int initialCapacity) {
        super(initialCapacity);
    }

    public Cells(Collection<? extends CellInterface> collection) {
        super(checkNotNull(collection, "This constructor is not intended to be be called with null.").size());
        this.addAll(collection);
    }

    private CellInterface checkElement(CellInterface cell) {
        checkNotNull(cell, "List of cells must not contain null.");
        checkArgument(!cell.isNull(), "List of cells must not contain a null object.");
        return cell;
    }

    @Override
    public boolean add(CellInterface cell) {
        return super.add(checkElement(cell));
    }

    @Override
    public CellInterface set(int index, CellInterface cell) {
        return super.set(index, checkElement(cell));
    }

    @Override
    public boolean addAll(Collection<? extends CellInterface> cells) {
        checkNotNull(cells, "This method is not intended to be be called with null.");
        for (CellInterface cell : cells) {
            this.add(cell);
        }
        return true;
    }

}
