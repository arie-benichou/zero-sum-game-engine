/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package abstractions.immutable.cells;

import java.util.Map;

import abstractions.immutable.pieces.PieceInterface;
import abstractions.immutable.pieces.OldPieceTypeInterface;
import abstractions.immutable.positions.PositionInterface;
import abstractions.immutable.positions.Positions;
import abstractions.immutable.side.SideInterface;

import com.google.common.base.Strings;

public class MyCellManager implements CellManagerInterface<PositionInterface, PieceInterface> {

    public final static Cell<PieceInterface> EMPTY_CELL =
            new Cell<PieceInterface>(
                    new PieceInterface() {

                        @Override
                        public OldPieceTypeInterface type() {
                            return null;
                        }

                        @Override
                        public SideInterface side() {
                            return null;
                        }

                        @Override
                        public String toString() {
                            return " ";
                        };
                    }
            );

    private final int rows;

    public final int rows() {
        return this.rows;
    }

    private final int columns;

    public final int columns() {
        return this.columns;
    }

    private final Cell<PieceInterface>[][] cells;

    private final void init(final Cell<PieceInterface> initialDefaultValue) {
        for (int y = 0; y < this.rows(); ++y)
            for (int x = 0; x < this.columns(); ++x)
                this.cells[y][x] = initialDefaultValue;
    }

    @SuppressWarnings("unchecked")
    public MyCellManager(final int rows, final int columns, final Cell<PieceInterface> defaultCell) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new Cell[this.rows()][this.columns()];
        this.init(defaultCell);
    }

    public MyCellManager(final int rows, final int columns) {
        this(rows, columns, EMPTY_CELL);
    }

    private void copy(final Cell<PieceInterface>[][] cells) {
        for (int y = 0; y < this.rows(); ++y)
            for (int x = 0; x < this.columns(); ++x)
                this.cells[y][x] = cells[y][x];
    }

    @SuppressWarnings("unchecked")
    public MyCellManager(final Cell<PieceInterface>[][] cells) {
        this.rows = cells.length;
        this.columns = cells[0].length;
        this.cells = new Cell[this.rows()][this.columns()];
        this.copy(cells);
    }

    @SuppressWarnings("unchecked")
    public MyCellManager apply(final Map<PositionInterface, PieceInterface> mutations) {
        final Cell<PieceInterface>[][] newCells = new Cell[this.rows()][this.columns()];
        for (int y = 0; y < this.rows(); ++y)
            for (int x = 0; x < this.columns(); ++x)
                newCells[y][x] = this.cells[y][x].apply(mutations.get(Positions.get(y, x)));
        return new MyCellManager(newCells);
    }

    public Cell<PieceInterface> cell(final int row, final int column) {
        return this.cells[row][column];
    }

    @Override
    public Cell<PieceInterface> cell(final PositionInterface address) {
        return this.cells[address.row()][address.column()];
    }

    @Override
    public final String toString() {
        final String lineSeparator = "\n" + " " + Strings.repeat("----", this.columns()) + "-" + "\n";
        final String columnSeparator = " | ";
        final StringBuilder sb = new StringBuilder();
        for (int y = 0; y < this.rows(); ++y) {
            sb.append(lineSeparator);
            for (int x = 0; x < this.columns(); ++x)
                sb.append(columnSeparator + this.cells[y][x]);
            sb.append(columnSeparator);
        }
        sb.append(lineSeparator);
        return sb.toString();
    }

}