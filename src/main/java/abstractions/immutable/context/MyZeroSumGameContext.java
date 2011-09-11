
package abstractions.immutable.context;

import java.util.List;

import abstractions.immutable.context.board.Cell;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class MyZeroSumGameContext implements ZeroSumGameContextInterface {

    private final SideInterface side;

    private final List<Cell> cells;

    @Override
    public SideInterface getSide() {
        return this.side;
    }

    public MyZeroSumGameContext(final SideInterface side, final List<Cell> cells) {
        this.side = side;
        this.cells = ImmutableList.copyOf(cells);
    }

    @Override
    public ZeroSumGameContextInterface apply(final Integer option) {

        final List<Cell> cells = Lists.newArrayList(this.cells);

        final Cell cell = cells.get(option);
        cells.set(option, cell.get(cell.value() + 1));

        return new MyZeroSumGameContext(this.getSide().getNextSide(), cells);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.getSide().toString() + " :");
        for (final Cell cell : this.cells) {
            sb.append(" " + cell);
        }
        return sb.toString();
    }

}