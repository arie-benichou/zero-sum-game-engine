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

import abstractions.mutation.MutationTypeInterface;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceManagerInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
import abstractions.position.PositionManager.DirectionInterface;
import abstractions.position.PositionManagerInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class CellManager implements CellManagerInterface {

    private final PieceManagerInterface pieceManager;
    private final PositionManagerInterface positionManager;
    private final Map<PositionInterface, ManagedCellInterface> data;
    private final ManagedCellInterface nullCell;

    private ManagedCellInterface newCell(final PositionInterface position) {
        return new ManagedCell(this, position);
    }

    private Map<PositionInterface, ManagedCellInterface> intializeData() {
        final Map<PositionInterface, ManagedCellInterface> data = Maps.newHashMap();
        for (final PositionInterface position : this.positionManager) {
            data.put(position, this.newCell(position));
        }
        // TODO regarder l'API du builder
        return ImmutableMap.copyOf(data);
    }

    public CellManager(final PositionManagerInterface positionManager, final PieceManagerInterface pieceManager) {
        this.positionManager = positionManager;
        this.pieceManager = pieceManager;
        this.data = this.intializeData();
        this.nullCell = this.data.get(this.positionManager.getNullPosition());
    }

    @Override
    public ManagedCellInterface getNullCell() {
        return this.nullCell;
    }

    @Override
    public ManagedCellInterface getCell(final int rowIndex, final int columnIndex) {
        return this.data.get(this.positionManager.getPosition(rowIndex, columnIndex));
    }

    @Override
    public ManagedCellInterface getCell(final PositionInterface position) {
        return this.data.get(position);
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

    @Override
    public Iterator<ManagedCellInterface> iterator() {
        // TODO In order to avoid this overhead, use data structure SortedMap/TreeMap instead of basic HashMap.
        final List<ManagedCellInterface> values = Lists.newArrayList(this.data.values());
        Collections.sort(values);
        return values.iterator();
    }

    @Override
    public PieceInterface getNullPiece() {
        return this.pieceManager.getNullPiece();
    }

    // TODO
    private final static Set<? extends MutationTypeInterface> NULL_POTENTIAL_MUTATION_TYPES_SET = ImmutableSet.of();

    @Override
    public Map<ManagedCellInterface, Set<? extends MutationTypeInterface>> getPotentialMutationTypes(final SideInterface side) {
        // TODO utiliser une contrainte sur la map (guava)
        // TODO utiliser le type de mutation comme clé de map
        final Map<ManagedCellInterface, Set<? extends MutationTypeInterface>> potentialMutationTypesMap = Maps.newHashMap();
        for (final ManagedCellInterface cell : this) {
            final Set<? extends MutationTypeInterface> p = cell.getPotentialMutationTypes(side);
            if (!p.equals(CellManager.NULL_POTENTIAL_MUTATION_TYPES_SET)) {
                potentialMutationTypesMap.put(cell, p);
            }

        }
        return potentialMutationTypesMap;
    }
}
