
package concretisations.othello;

import abstractions.cell.AbstractCell;
import abstractions.position.API.PositionInterface;
import abstractions.side.API.SideInterface;

public class OthelloCell extends AbstractCell {

    public OthelloCell(PositionInterface position) {
        super(position);
    }

    public boolean willItBeConnected(SideInterface side, int rowDelta, int columnDelta) {
        boolean willBeConnected = false;
        if (this.getNext(rowDelta, columnDelta).getPiece().getSide().isNull()) {
            willBeConnected = false;
        }
        else if (this.getNext(rowDelta, columnDelta).getPiece().getSide().equals(side)) {
            willBeConnected = true;
        }
        else {
            willBeConnected = ((OthelloCell) this.getNext(2 * rowDelta, 2 * columnDelta)).willItBeConnected(side, rowDelta, columnDelta);
        }
        return willBeConnected;
    }

    private boolean checkNeighbourhood(SideInterface side) {
        boolean willBeConnected = false;
        for (int rowDelta = -1; rowDelta < 2 && !willBeConnected; ++rowDelta)
            for (int columnDelta = -1; columnDelta < 2 && !willBeConnected; ++columnDelta)
                willBeConnected = this.getNext(rowDelta, columnDelta).getPiece().getSide().getNextSide().equals(side)
                        && ((OthelloCell) this.getNext(rowDelta, columnDelta)).willItBeConnected(side, rowDelta, columnDelta);
        return willBeConnected;
    }

    @Override
    public boolean isMutable(SideInterface side) {
        return (this.isMutable = this.isEmpty() ? this.checkNeighbourhood(side) : false);
    }

    @Override
    public final String toString() {
        return this.isMutable ? " " + "." + " |" : super.toString();
    }

}