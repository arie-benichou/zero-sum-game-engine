
package abstractions.cell;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import abstractions.piece.PieceInterface;
import abstractions.piece.PieceManagerInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
import abstractions.position.PositionManager.DirectionInterface;
import abstractions.position.PositionManagerInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class CellManager implements CellManagerInterface {

    private final PieceManagerInterface pieceManager;
    private final PositionManagerInterface positionManager;
    private final Map<PositionInterface, ManagedCellInterface> data;
    private final ManagedCellInterface nullCell;

    private ManagedCellInterface newCell(final PositionInterface position) {
        final ManagedCellInterface managedCell = new ManagedCell(this, position);
        managedCell.setPiece(this.pieceManager.getNullPiece());
        return managedCell;
    }

    private Map<PositionInterface, ManagedCellInterface> intializeData() {
        final Map<PositionInterface, ManagedCellInterface> data = Maps.newHashMap();
        for (final PositionInterface position : this.positionManager) {
            data.put(position, this.newCell(position));
        }
        // TODO regarder l'API du builder
        return ImmutableMap.copyOf(data);
    }

    public CellManager(final PositionManagerInterface positionManager, final PieceManagerInterface pieceManager) {
        this.positionManager = positionManager;
        this.pieceManager = pieceManager;
        this.data = this.intializeData();
        this.nullCell = this.data.get(this.positionManager.getNullPosition());
    }

    @Override
    public ManagedCellInterface getNullCell() {
        return this.nullCell;
    }

    @Override
    public ManagedCellInterface getCell(final int rowIndex, final int columnIndex) {
        return this.data.get(this.positionManager.getPosition(rowIndex, columnIndex));
    }

    @Override
    public ManagedCellInterface getCell(final PositionInterface position) {
        return this.data.get(position);
    }

    @Override
    public PieceInterface piece(final SideInterface side, final PieceTypeInterface pieceType) {
        return this.pieceManager.getPiece(side, pieceType);
    }

    @Override
    public PositionInterface position(final int rowIndex, final int columnIndex) {
        return this.positionManager.getPosition(rowIndex, columnIndex);
    }

    @Override
    public PositionInterface position(final PositionInterface position, final DirectionInterface direction) {
        return this.positionManager.getPosition(position, direction);
    }

    @Override
    public Iterator<ManagedCellInterface> iterator() {
        // TODO In order to avoid this overhead, use data structure SortedMap/TreeMap instead of basic HashMap.
        final List<ManagedCellInterface> values = Lists.newArrayList(this.data.values());
        Collections.sort(values);
        return values.iterator();
    }
}
