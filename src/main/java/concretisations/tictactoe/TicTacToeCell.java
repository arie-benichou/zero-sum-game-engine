
package concretisations.tictactoe;

import abstractions.cell.AbstractCell;
import abstractions.position.API.PositionInterface;
import abstractions.side.API.SideInterface;

public class TicTacToeCell extends AbstractCell {

    public TicTacToeCell(PositionInterface position) {
        super(position);
    }

    @Override
    public boolean isMutable(SideInterface side) {
        return this.isMutable = this.isEmpty();
    }

    @Override
    public final String toString() {
        return this.isMutable ? " " + "." + " |" : super.toString();
    }

}