package board;

import static board.API.*;
import static cell.API.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cell.API.CellInterface;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

final class Board implements BoardInterface {

	private final Map<String, CellInterface> boardCells;

	private final String computeHash(final int row, final int column) {
		return "r" + row + "c" + column;
	}

	private final String computeHash(final CellInterface cell) {
		return this.computeHash(cell.getRow(), cell.getColumn());
	}

	private Map<String, CellInterface> mapCells(final List<CellInterface> cells) {
		final Map<String, CellInterface> mappedCells = Maps.newHashMapWithExpectedSize(cells.size());
		for (final CellInterface cell : cells) {
			mappedCells.put(this.computeHash(cell), cell);
		}
		return mappedCells;
	}

	public Board(final List<CellInterface> cells) {
		this.boardCells = this.mapCells(cells);
	}

	public final CellInterface getCell(final int row, final int column) {
		CellInterface cell = this.boardCells.get(this.computeHash(row, column));
		if (cell == null) {
			cell = NULL_CELL;
		}
		return cell;
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

	// TODO ré-introduire l'objet CardinalPosition
	
	public CellInterface topOf(CellInterface cell) {
		return this.getCell(cell.getRow() - 1, cell.getColumn());
	}

	public CellInterface rightOf(CellInterface cell) {
		return this.getCell(cell.getRow(), cell.getColumn() + 1);
	}

	public CellInterface bottomOf(CellInterface cell) {
		return this.getCell(cell.getRow() + 1, cell.getColumn());
	}

	public CellInterface leftOf(CellInterface cell) {
		return this.getCell(cell.getRow(), cell.getColumn() - 1);
	}

	public CellInterface topRightOf(CellInterface cell) {
		return this.getCell(cell.getRow() - 1, cell.getColumn() + 1);
	}

	public CellInterface topLeftOf(CellInterface cell) {
		return this.getCell(cell.getRow() - 1, cell.getColumn() - 1);
	}

	public CellInterface bottomRightOf(CellInterface cell) {
		return this.getCell(cell.getRow() + 1, cell.getColumn() + 1);
	}

	public CellInterface bottomLeftOf(CellInterface cell) {
		return this.getCell(cell.getRow() + 1, cell.getColumn() - 1);
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