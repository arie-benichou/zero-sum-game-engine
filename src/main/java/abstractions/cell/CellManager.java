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
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public final class CellManager implements CellManagerInterface {

    private final PieceManagerInterface pieceManager;
    private final PositionManagerInterface positionManager;
    private final Map<PositionInterface, ManagedCellInterface> data;
    private final ManagedCellInterface nullCell;

    private ManagedCellInterface newCell(final PositionInterface position) {
        return new ManagedCell(this, position);
    }

    // TODO utiliser le builder d'une map immutable
    private Map<PositionInterface, ManagedCellInterface> intializeData() {
        final Map<PositionInterface, ManagedCellInterface> data = Maps.newHashMap();
        for (final PositionInterface position : this.positionManager) {
            data.put(position, this.newCell(position));
        }
        return ImmutableMap.copyOf(data);
    }

    public CellManager(final PositionManagerInterface positionManager, final PieceManagerInterface pieceManager) {
        this.positionManager = positionManager;
        this.pieceManager = pieceManager;
        this.data = this.intializeData();
        this.nullCell = this.data.get(this.positionManager.getNullPosition());
    }

    public ManagedCellInterface getNullCell() {
        return this.nullCell;
    }

    public ManagedCellInterface getCell(final int rowIndex, final int columnIndex) {
        return this.data.get(this.positionManager.getPosition(rowIndex, columnIndex));
    }

    public ManagedCellInterface getCell(final PositionInterface position) {
        return this.data.get(position);
    }

    public PieceInterface piece(final SideInterface side, final PieceTypeInterface pieceType) {
        return this.pieceManager.getPiece(side, pieceType);
    }

    public PositionInterface position(final int rowIndex, final int columnIndex) {
        return this.positionManager.getPosition(rowIndex, columnIndex);
    }

    public PositionInterface position(final PositionInterface position, final DirectionInterface direction) {
        return this.positionManager.getPosition(position, direction);
    }

    public PositionInterface position(final PositionInterface position, final NamedDirection direction) {
        return this.positionManager.getPosition(position, direction);
    }

    // TODO in order to avoid sorting overhead, use data structure SortedMap/TreeMap instead of basic HashMap
    public Iterator<ManagedCellInterface> iterator() {
        final List<ManagedCellInterface> values = Lists.newArrayList(this.data.values());
        Collections.sort(values);
        return values.iterator();
    }

    public PieceInterface getNullPiece() {
        return this.pieceManager.getNullPiece();
    }

    // TODO utiliser une contrainte sur la map (guava)
    // TODO utiliser le type de mutation comme clé de map
    public Map<ManagedCellInterface, Set<? extends MutationInterface>> getPotentialMutations(final SideInterface side) {
        final Map<ManagedCellInterface, Set<? extends MutationInterface>> potentialMutationTypesMap = Maps.newHashMap();
        final Iterator<ManagedCellInterface> it = this.iterator();
        ManagedCellInterface cell = it.next(); // cellule nulle
        while (it.hasNext()) {
            cell = it.next();
            final Set<? extends MutationInterface> p = cell.getPotentialMutations(side);
            if (!p.equals(MutationInterface.NULL_POTENTIAL_MUTATION_SET)) {
                potentialMutationTypesMap.put(cell, p);
            }
        }
        return potentialMutationTypesMap;
    }

    @Override
    // TODO à simplifier
    public String toString() {
        final int maximalNumberOfCellsByRow = Collections.max(this.data.values()).getColumn();
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

    public List<? extends DirectionInterface> getNamedDirections() {
        return this.positionManager.getNamedDirections();
    }

}
