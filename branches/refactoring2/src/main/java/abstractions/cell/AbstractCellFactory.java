
package abstractions.cell;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;

import abstractions.cell.API.CellInterface;
import abstractions.piece.API.PieceInterface;
import abstractions.position.API.PositionInterface;

import com.google.common.collect.Sets;

@Deprecated
public class _AbstractCellFactory<T extends Cell> {

    private Constructor<? extends Cell> constructor = null;

    public AbstractCellFactory(Class<? extends Cell> type) {
        try {
            this.constructor = type.getDeclaredConstructor(PositionInterface.class);
        }
        catch (final SecurityException e) {
            // TODO
            e.printStackTrace();
        }
        catch (final NoSuchMethodException e) {
            // TODO
            e.printStackTrace();
        }
    }

    public final Cell newCell(final PositionInterface position) {

        Cell instance = null;

        try {
            instance = constructor.newInstance(position);
        }
        catch (IllegalArgumentException e) {
            // TODO
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            // TODO
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            // TODO
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            // TODO
            e.printStackTrace();
        }

        return instance;

    }

    public final Set<CellInterface> cells(final Set<PositionInterface> positions) {
        checkNotNull(positions, "Argument 'positions' is not intended to be null.");
        final Set<CellInterface> cells = Sets.newHashSetWithExpectedSize(positions.size());
        for (final PositionInterface position : positions) {
            cells.add(this.newCell(position));
        }
        return Collections.unmodifiableSet(cells);
    }

    public final CellInterface clone(final CellInterface cell) {
        checkNotNull(cell, "Argument 'cell' is not intended to be null.");
        final CellInterface clone = this.newCell(cell.getPosition());
        PieceInterface piece = cell.getPiece();
        if (!piece.isNull()) {
            clone.setPiece(piece);
        }
        return clone;
    }

}
