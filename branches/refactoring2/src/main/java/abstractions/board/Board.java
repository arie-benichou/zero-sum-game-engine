
package abstractions.board;

import static abstractions.cell.API.NULL_CELL;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import abstractions.board.API.BoardInterface;
import abstractions.cell.API.CellInterface;
import abstractions.side.API.SideInterface;

import com.google.common.base.Strings;
import com.google.common.collect.Constraint;
import com.google.common.collect.Constraints;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

final class Board implements BoardInterface {

    private static final Constraint<CellInterface> CONSTRAINT = new Constraint<CellInterface>() {

        public CellInterface checkElement(CellInterface cell) {
            if (cell == null) {
                throw new NullPointerException("A cell of a board is not intended to be null.");
            }
            else if (cell.isNull()) {
                throw new IllegalArgumentException("A cell of a board must not be the null cell object.");
            }
            return cell;
        }
    };

    private final Map<String, CellInterface> boardCells;

    private final String computeHash(final int row, final int column) {
        return "r" + row + "c" + column;
    }

    private final String computeHash(final CellInterface cell) {
        return this.computeHash(cell.getRow(), cell.getColumn());
    }

    public Board(final Set<CellInterface> cells) {
        checkNotNull(cells, "Argument 'cells' is not intended to be null.");
        final Set<CellInterface> checkedCells = Constraints.constrainedSet(new HashSet<CellInterface>(cells.size()), CONSTRAINT);
        checkedCells.addAll(cells);
        this.boardCells = Maps.newHashMapWithExpectedSize(checkedCells.size());
        // TODO regarder l'API du MapMaker
        for (final CellInterface cell : cells) {
            cell.setBoard(this);
            this.boardCells.put(this.computeHash(cell), cell);
        }
    }

    public final CellInterface getCell(final int row, final int column) {
        CellInterface cell = this.boardCells.get(this.computeHash(row, column));
        return cell == null ? NULL_CELL : cell;
    }

    // TODO ? à mettre en cache
    public Iterator<CellInterface> iterator() {
        final List<CellInterface> cells = Lists.newArrayList(this.boardCells.values());
        Collections.sort(cells);
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

    /*
    // TODO ré-introduire l'objet CardinalPosition et utiliser une méthode privée prenant en paramètre la cellule et la position cardinale

    public CellInterface topOf(CellInterface cell) {
        checkNotNull(cell, "Argument 'cell' is not intended to be null.");
        return cell.isNull() ? NULL_CELL : this.getCell(cell.getRow() - 1, cell.getColumn());
    }

    public CellInterface rightOf(CellInterface cell) {
        checkNotNull(cell, "Argument 'cell' is not intended to be null.");
        return cell.isNull() ? NULL_CELL : this.getCell(cell.getRow(), cell.getColumn() + 1);
    }

    public CellInterface bottomOf(CellInterface cell) {
        checkNotNull(cell, "Argument 'cell' is not intended to be null.");
        return cell.isNull() ? NULL_CELL : this.getCell(cell.getRow() + 1, cell.getColumn());
    }

    public CellInterface leftOf(CellInterface cell) {
        checkNotNull(cell, "Argument 'cell' is not intended to be null.");
        return cell.isNull() ? NULL_CELL : this.getCell(cell.getRow(), cell.getColumn() - 1);
    }

    public CellInterface topRightOf(CellInterface cell) {
        checkNotNull(cell, "Argument 'cell' is not intended to be null.");
        return cell.isNull() ? NULL_CELL : this.getCell(cell.getRow() - 1, cell.getColumn() + 1);
    }

    public CellInterface topLeftOf(CellInterface cell) {
        checkNotNull(cell, "Argument 'cell' is not intended to be null.");
        return cell.isNull() ? NULL_CELL : this.getCell(cell.getRow() - 1, cell.getColumn() - 1);
    }

    public CellInterface bottomRightOf(CellInterface cell) {
        checkNotNull(cell, "Argument 'cell' is not intended to be null.");
        return cell.isNull() ? NULL_CELL : this.getCell(cell.getRow() + 1, cell.getColumn() + 1);
    }

    public CellInterface bottomLeftOf(CellInterface cell) {
        checkNotNull(cell, "Argument 'cell' is not intended to be null.");
        return cell.isNull() ? NULL_CELL : this.getCell(cell.getRow() + 1, cell.getColumn() - 1);
    }
    */

    public Set<CellInterface> getMutableCells(SideInterface side) {
        Set<CellInterface> mutableCells = Sets.newHashSet();
        for (CellInterface cell : this) {
            if (cell.isMutable(side)) {
                mutableCells.add(cell);
            }
        }
        return mutableCells;
    }

    @Override
    public int hashCode() {
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
        else if (!(object instanceof BoardInterface)) {
            isEqual = false;
        }
        else {
            final BoardInterface that = (BoardInterface) object;
            if (that.hashCode() != this.hashCode()) {
                isEqual = false;
            }
            else {
                isEqual = Lists.newArrayList(this).equals(Lists.newArrayList(that));
            }
        }
        return isEqual;
    }

    @Override
    public String toString() {
        final int maximalNumberOfCellsByRow = Collections.max(this.boardCells.values()).getColumn();
        final StringBuilder consoleBoardView = new StringBuilder();
        final Iterator<CellInterface> it = this.iterator();
        CellInterface previousCell = NULL_CELL;
        while (it.hasNext()) {
            final CellInterface cell = it.next();
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