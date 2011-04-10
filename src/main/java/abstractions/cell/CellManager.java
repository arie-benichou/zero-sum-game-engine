
package abstractions.cell;

import java.util.Map;

import abstractions.piece.PieceInterface;
import abstractions.piece.PieceManagerInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
import abstractions.position.PositionManager.DirectionInterface;
import abstractions.position.PositionManagerInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableMap;
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

        ManagedCellInterface cell;
        for (final PositionInterface position : this.positionManager) {
            cell = this.newCell(position);
            //System.out.println(cell);
            data.put(position, cell);
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

}
