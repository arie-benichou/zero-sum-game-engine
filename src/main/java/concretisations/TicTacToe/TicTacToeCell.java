
package concretisations.TicTacToe;

import abstractions.cell.AbstractCell;
import abstractions.position.API.PositionInterface;
import abstractions.side.API.SideInterface;

public class TicTacToeCell extends AbstractCell {

    public TicTacToeCell(PositionInterface position) {
        super(position);
    }

    @Override
    public boolean isMutable(SideInterface side) {
        return this.isEmpty();
    }

}
