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

package abstractions.cell;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import abstractions.direction.DirectionInterface;
import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceManagerInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
import abstractions.position.PositionManagerInterface;
import abstractions.side.SideInterface;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public final class CellManager implements CellManagerInterface {

    private final PieceManagerInterface pieceManager;
    private final PositionManagerInterface positionManager;
    private final Map<PositionInterface, ManagedCellInterface> map;
    private final ManagedCellInterface nullCell;
    private final List<ManagedCellInterface> cells;

    private ManagedCellInterface newCell(final PositionInterface position) {
        return new ManagedCell(this, position);
    }

    private Map<PositionInterface, ManagedCellInterface> intializeData() {
        //final Builder<PositionInterface, ManagedCellInterface> builder = ImmutableSortedMap.builder();
        final TreeMap<PositionInterface, ManagedCellInterface> map = new TreeMap<PositionInterface, ManagedCellInterface>();
        for (final PositionInterface position : this.positionManager) {
            map.put(position, this.newCell(position));
        }
        return map;
    }

    public CellManager(final PositionManagerInterface positionManager, final PieceManagerInterface pieceManager) {
        this.positionManager = positionManager;
        this.pieceManager = pieceManager;
        this.map = this.intializeData();
        this.nullCell = this.map.get(this.positionManager.getNullPosition());
        this.cells = Lists.newArrayList(this.map.values());
    }

    @Override
    public ManagedCellInterface getNullCell() {
        return this.nullCell;
    }

    @Override
    public ManagedCellInterface getCell(final int rowIndex, final int columnIndex) {
        return this.map.get(this.positionManager.getPosition(rowIndex, columnIndex));
    }

    @Override
    public ManagedCellInterface getCell(final PositionInterface position) {
        return this.map.get(position);
    }

    @Override
    public PieceInterface piece(final SideInterface side, final PieceTypeInterface pieceType) {
        return this.pieceManager.getPiece(side, pieceType);
    }

    @Override
    public PositionInterface position(final int rowIndex, final int columnIndex) {
        return this.positionManager.getPosition(rowIndex, columnIndex);
    }

    @Override
    public PositionInterface position(final PositionInterface position, final DirectionInterface direction) {
        return this.positionManager.getPosition(position, direction);
    }

    public PositionInterface position(final PositionInterface position, final NamedDirection namedDirection) {
        return this.positionManager.getPosition(position, namedDirection.value());
    }

    @Override
    public Iterator<ManagedCellInterface> iterator() {
        return this.cells.iterator();
    }

    @Override
    public PieceInterface getNullPiece() {
        return this.pieceManager.getNullPiece();
    }

    // TODO ? utiliser le type de mutation comme clé de map
    @Override
    public Map<ManagedCellInterface, Set<MutationInterface>> getPotentialMutations(final SideInterface side) {
        final Map<ManagedCellInterface, Set<MutationInterface>> potentialMutationsMap = Maps.newTreeMap();
        final Iterator<ManagedCellInterface> it = this.iterator();
        ManagedCellInterface cell = it.next(); // cellule nulle
        while (it.hasNext()) {
            cell = it.next();
            final Set<MutationInterface> p = cell.getPotentialMutations(side);
            if (!p.equals(MutationInterface.NULL_POTENTIAL_MUTATION_SET)) {
                potentialMutationsMap.put(cell, p);
            }
        }
        return potentialMutationsMap;
    }

    @Override
    // TODO à simplifier
    public String toString() {
        final int maximalNumberOfCellsByRow = Collections.max(this.map.values()).getColumn();
        final StringBuilder consoleBoardView = new StringBuilder();
        final Iterator<ManagedCellInterface> it = this.iterator();
        ManagedCellInterface previousCell = it.next();
        while (it.hasNext()) {
            final ManagedCellInterface cell = it.next();
            if (previousCell.getRow() != cell.getRow()) {
                consoleBoardView.append("\n" + Strings.repeat("----", maximalNumberOfCellsByRow) + "-" + "\n");
                consoleBoardView.append("|");
            }
            consoleBoardView.append(cell.render());
            previousCell = cell;
        }
        consoleBoardView.append("\n" + Strings.repeat("----", maximalNumberOfCellsByRow) + "-" + "\n");
        return consoleBoardView.toString();
    }

    @Override
    public String asString() {
        final StringBuilder sb = new StringBuilder();
        for (final ManagedCellInterface cell : this) {
            sb.append(cell.getPiece());
        }
        return sb.toString();
    }

    @Override
    public List<NamedDirection> getNamedDirections() {
        return this.positionManager.getNamedDirections();
    }

    // TODO ? utiliser un cache
    @Override
    public List<ManagedCellInterface> getRow(final int rowIndex) {
        final int maxColumnIndex = Collections.max(this.map.values()).getColumn();
        final List<ManagedCellInterface> row = Lists.newArrayListWithCapacity(maxColumnIndex);
        for (int columnIndex = 1; columnIndex <= maxColumnIndex; ++columnIndex) {
            row.add(this.getCell(rowIndex, columnIndex));
        }
        return row;
    }

    // TODO ? utiliser un cache
    @Override
    public List<ManagedCellInterface> getColumn(final int columnIndex) {
        final int maxRowIndex = Collections.max(this.map.values()).getRow();
        final List<ManagedCellInterface> column = Lists.newArrayListWithCapacity(maxRowIndex);
        for (int rowIndex = 1; rowIndex <= maxRowIndex; ++rowIndex) {
            column.add(this.getCell(rowIndex, columnIndex));
        }
        return column;
    }

    /*
    public List<PieceInterface> getPiecesByRow(final int rowIndex) {
        final int maxColumnIndex = Collections.max(this.map.values()).getColumn();
        final List<PieceInterface> row = Lists.newArrayListWithCapacity(maxColumnIndex);
        for (int columnIndex = 1; columnIndex <= maxColumnIndex; ++columnIndex) {
            row.add(this.getCell(rowIndex, columnIndex).getPiece());
        }
        return row;
    }

    public List<PieceInterface> getPiecesByColumn(final int columnIndex) {
        final int maxRowIndex = Collections.max(this.map.values()).getRow();
        final List<PieceInterface> column = Lists.newArrayListWithCapacity(maxRowIndex);
        for (int rowIndex = 1; rowIndex <= maxRowIndex; ++rowIndex) {
            column.add(this.getCell(rowIndex, columnIndex).getPiece());
        }
        return column;
    }
    */

    // TODO ? utiliser un cache    
    @Override
    public List<ManagedCellInterface> getRegion(final PositionInterface topLeftPosition, final PositionInterface bottomRightPosition) {
        final int regionCapacity = (1 + bottomRightPosition.getRow() - topLeftPosition.getRow())
                * (1 + bottomRightPosition.getColumn() - topLeftPosition.getColumn());
        final List<ManagedCellInterface> region = Lists.newArrayListWithCapacity(regionCapacity);
        for (int rowIndex = topLeftPosition.getRow(), maxRowIndex = bottomRightPosition.getRow(); rowIndex <= maxRowIndex; ++rowIndex) {
            for (int columnIndex = topLeftPosition.getColumn(), maxColumnIndex = bottomRightPosition.getColumn(); columnIndex <= maxColumnIndex; ++columnIndex) {
                region.add(this.getCell(rowIndex, columnIndex));
            }
        }
        return region;
    }

    @Override
    public boolean isFull() {
        final Iterator<ManagedCellInterface> it = this.iterator();
        it.next();
        while (it.hasNext())
            if (it.next().isEmpty())
                return false;
        return true;
    }

}