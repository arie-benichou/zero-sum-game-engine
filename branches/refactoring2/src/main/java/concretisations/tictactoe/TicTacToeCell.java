
package concretisations.tictactoe;

import abstractions.cell.Cell;
import abstractions.position.API.PositionInterface;
import abstractions.side.API.SideInterface;

public class TicTacToeCell extends Cell {

    public TicTacToeCell(PositionInterface position) {
        super(position);
    }

    @Override
    public boolean isMutable(SideInterface side) {
        return this.willGenerateMutations = this.isEmpty();
    }

    @Override
    public final String toString() {
        return this.willGenerateMutations ? " " + "." + " |" : super.toString();
    }

}