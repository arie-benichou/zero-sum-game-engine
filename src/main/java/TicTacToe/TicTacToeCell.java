
package TicTacToe;

import abstractions.cell.AbstractCell;
import abstractions.position.API.PositionInterface;

public class TicTacToeCell extends AbstractCell {

    public TicTacToeCell(PositionInterface position) {
        super(position);
    }

    @Override
    public boolean isMutable() {
        return this.isEmpty();
    }

}
