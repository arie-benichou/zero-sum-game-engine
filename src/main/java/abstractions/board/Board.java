
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
import abstractions.cell.mutation.MutationInterface;
import abstractions.side.API.SideInterface;

import com.google.common.base.Strings;
import com.google.common.collect.Constraint;
import com.google.common.collect.Constraints;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
        // TODO regarder l'API Guava du MapMaker
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

    public List<MutationInterface> getLegalMutations(SideInterface side) {

        List<MutationInterface> availableMutations = Lists.newArrayList();

        // TODO ? utiliser un prédicat "NotEmpty" en tant que contrainte sur la liste
        for (CellInterface cell : this) {
            Set<MutationInterface> result = cell.fetchAvailableMutations(side);
            if (result.isEmpty()) {
                cell.willGenerateMutations(false);
            }
            else {
                availableMutations.addAll(result);
            }
        }

        int min = Collections.min(availableMutations).getPriority();

        /*
        int max = Collections.max(availableMutations).getPriority();
        if(min == max) {
            System.out.println("Il n y a que des coups de même priorité");
        }
        else {
            System.out.println("Il va falloir garder que les mutations de priorité : " + min);
        }
        */

        // TODO utiliser un prédicat "> min" en tant que contrainte sur la liste
        List<MutationInterface> legalMutations = Lists.newArrayList();
        for (MutationInterface mutation : availableMutations) {
            if (mutation.getPriority() == min) {
                mutation.getCell().willGenerateMutations(true);
                legalMutations.add(mutation);
            }
        }

        return legalMutations;
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