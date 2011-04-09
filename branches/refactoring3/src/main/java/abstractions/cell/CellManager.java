
package abstractions.cell;

import abstractions.piece.PieceManagerInterface;
import abstractions.position.PositionInterface;
import abstractions.position.PositionManagerInterface;

public class CellManager implements CellManagerInterface {

    private final PieceManagerInterface pieceManager;
    private final PositionManagerInterface positionManager;

    private ManagedCellInterface newCell(final PositionInterface position) {
        final ManagedCellInterface managedCell = new ManagedCell(this, position);
        managedCell.setPiece(this.pieceManager.getNullPiece());
        return managedCell;
    }

    public CellManager(final PositionManagerInterface positionManager, final PieceManagerInterface pieceManager) {
        this.positionManager = positionManager;
        this.pieceManager = pieceManager;

        ManagedCellInterface cell;
        for (final PositionInterface position : this.positionManager) {
            cell = this.newCell(position);
            System.out.println(cell);
        }

    }

}
