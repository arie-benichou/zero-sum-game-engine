
package game.board;

import game.board.cells.Cells;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

// TODO ? getByColumn et getByRow
final class Board implements BoardInterface {

    private final Map<String, Cells.Interface> boardCells;

    private final String computeHash(final int row, final int column) {
        return "r" + row + "c" + column;
    }

    private final String computeHash(final Cells.Interface cell) {
        return this.computeHash(cell.getRow(), cell.getColumn());
    }

    private Map<String, Cells.Interface> mapCells(final List<Cells.Interface> cells) {
        final HashMap<String, Cells.Interface> mappedCells = Maps.newHashMapWithExpectedSize(cells.size());
        for (final Cells.Interface cell : cells) {
            mappedCells.put(this.computeHash(cell), cell);
        }
        return mappedCells;
    }

    public Board(final List<Cells.Interface> cells) {
        this.boardCells = this.mapCells(cells);
    }

    public final Cells.Interface cell(final int row, final int column) {
        Cells.Interface cell = this.boardCells.get(this.computeHash(row, column));
        if (cell == null) {
            cell = Cells.NULL_CELL;
        }
        return cell;
    }

    // TODO ? mettre en cache, l'iterator (à tester)
    public Iterator<Cells.Interface> iterator() {
        final ArrayList<Cells.Interface> cells = Lists.newArrayList(this.boardCells.values());
        Collections.sort(cells); // TODO tester sans
        return cells.iterator();
    }

    @Override
    public String toString() {
        final int maximalNumberOfCellsByRow = Collections.max(this.boardCells.values()).getColumn();
        final StringBuilder consoleBoardView = new StringBuilder();
        final Iterator<Cells.Interface> it = this.iterator();

        Cells.Interface previousCell = Cells.NULL_CELL;

        while (it.hasNext()) {
            final Cells.Interface cell = it.next();
            if (previousCell.getRow() != cell.getRow()) {
                consoleBoardView.append("\n" + Strings.repeat("----", maximalNumberOfCellsByRow) + "-" + "\n");
                consoleBoardView.append("|");
            }
            consoleBoardView.append(cell);
            previousCell = cell;
        }
        consoleBoardView.append("\n" + Strings.repeat("----", maximalNumberOfCellsByRow) + "-" + "\n");
        return consoleBoardView.toString();
    }
}