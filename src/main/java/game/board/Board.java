
package game.board;

import static game.cell.API.*;
import static game.board.API.*;

import game.cell.API.CellInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

final class Board implements BoardInterface {

    private final Map<String,  CellInterface> boardCells;

    private final String computeHash(final int row, final int column) {
        return "r" + row + "c" + column;
    }

    private final String computeHash(final  CellInterface cell) {
        return this.computeHash(cell.getRow(), cell.getColumn());
    }

    private Map<String,  CellInterface> mapCells(final List<CellInterface> cells) {
        final Map<String,  CellInterface> mappedCells = Maps.newHashMapWithExpectedSize(cells.size());
        for (final  CellInterface cell : cells) {
            mappedCells.put(this.computeHash(cell), cell);
        }
        return mappedCells;
    }

    public Board(final List<CellInterface> cells) {
        this.boardCells = this.mapCells(cells);
    }

    public final  CellInterface getCell(final int row, final int column) {
         CellInterface cell = this.boardCells.get(this.computeHash(row, column));
        if (cell == null) {
            cell =  NULL_CELL;
        }
        return cell;
    }

    // TODO ? à mettre en cache
    public Iterator<CellInterface> iterator() {
        final List<CellInterface> cells = Lists.newArrayList(this.boardCells.values());
        Collections.sort(cells); // TODO tester sans
        return cells.iterator();
    }
    
    // TODO ? à mettre en cache    
    public CellInterface getLowerBound() {
        return Collections.min(this.boardCells.values());
    }

    // TODO ? à mettre en cache    
    public CellInterface getUpperBound() {
        return Collections.max(this.boardCells.values());
    }
    
    
    @Override
    public int hashCode() {// TODO ! à tester
        int hashCode = 17;
        hashCode *= 31;
        hashCode += this.boardCells.hashCode();
        return hashCode;
    }

    @Override
    public final boolean equals(final Object object) {
        final boolean isEqual;
        if (object == this) {
            isEqual = true;
        }
        else if (object == null) {
            isEqual = false;
        }
        else if (!(object instanceof  BoardInterface)) {
            isEqual = false;
        }
        else {
            final  BoardInterface board = (BoardInterface) object;
            if (board.hashCode() != this.hashCode()) {
                isEqual = false;
            }
            else {
                isEqual = Lists.newArrayList(this).equals(Lists.newArrayList(board));
            }
        }
        return isEqual;
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