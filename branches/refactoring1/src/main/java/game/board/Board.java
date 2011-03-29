
package game.board;

import static game.board.cell.API.*;

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

    private final Map<String,  CellInterface> boardCells;

    private final String computeHash(final int row, final int column) {
        return "r" + row + "c" + column;
    }

    private final String computeHash(final  CellInterface cell) {
        return this.computeHash(cell.getRow(), cell.getColumn());
    }

    private Map<String,  CellInterface> mapCells(final List<CellInterface> cells) {
        final HashMap<String,  CellInterface> mappedCells = Maps.newHashMapWithExpectedSize(cells.size());
        for (final  CellInterface cell : cells) {
            mappedCells.put(this.computeHash(cell), cell);
        }
        return mappedCells;
    }

    public Board(final List<CellInterface> cells) {
        this.boardCells = this.mapCells(cells);
    }

    public final  CellInterface cell(final int row, final int column) {
         CellInterface cell = this.boardCells.get(this.computeHash(row, column));
        if (cell == null) {
            cell =  NULL_CELL;
        }
        return cell;
    }

    // TODO ? mettre en cache, l'iterator (à tester)
    public Iterator<CellInterface> iterator() {
        final ArrayList<CellInterface> cells = Lists.newArrayList(this.boardCells.values());
        Collections.sort(cells); // TODO tester sans
        return cells.iterator();
    }

    @Override
    public String toString() {
        final int maximalNumberOfCellsByRow = Collections.max(this.boardCells.values()).getColumn();
        final StringBuilder consoleBoardView = new StringBuilder();
        final Iterator<CellInterface> it = this.iterator();

         CellInterface previousCell =  NULL_CELL;

        while (it.hasNext()) {
            final  CellInterface cell = it.next();
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