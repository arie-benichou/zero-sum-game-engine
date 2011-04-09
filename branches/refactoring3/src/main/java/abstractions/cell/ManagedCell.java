
package abstractions.cell;

import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
import abstractions.side.SideInterface;

public class ManagedCell implements ManagedCellInterface {

    private final PositionInterface position;
    private final CellManagerInterface cellManager;

    public ManagedCell(final CellManagerInterface cellManager, final PositionInterface position) {
        this.cellManager = cellManager;
        this.position = position;
    }

    @Override
    public int compareTo(final ManagedCellInterface o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public PositionInterface getPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getRow() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getColumn() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public PieceInterface getPiece() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ManagedCellInterface setPiece(final PieceInterface piece) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ManagedCellInterface setPiece(final SideInterface side, final PieceTypeInterface pieceType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isNull() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

}
