
package concretisations.TicTacToe;

import abstractions.cell.AbstractCell;
import abstractions.position.API.PositionInterface;
import abstractions.side.API.SideInterface;

public class Connect4Cell extends AbstractCell {

    public Connect4Cell(PositionInterface position) {
        super(position);
    }

    @Override
    public boolean isMutable(SideInterface side) {
        return this.isEmpty() && !this.getNext(1, 0).isEmpty();
    }

}
