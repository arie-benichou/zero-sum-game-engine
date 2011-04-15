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

package abstractions.piece;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import abstractions.side.SideInterface;
import abstractions.side.Sides;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public final class PieceManager implements PieceManagerInterface {

    private final PieceSetFactoryInterface factory = new PieceSetFactory();
    private final Map<Integer, PieceInterface> data;
    private final PieceInterface nullPiece;

    private int hash(final SideInterface side, final PieceTypeInterface type) {
        return side.hashCode() + type.hashCode();
    }

    // TODO utiliser le builder d'une map immutable
    private Map<Integer, PieceInterface> initializeData(final Map<SideInterface, Set<PieceInterface>> map) {
        final Map<Integer, PieceInterface> data = Maps.newHashMapWithExpectedSize(map.size());
        for (final Entry<SideInterface, Set<PieceInterface>> mapEntry : map.entrySet()) {
            for (final PieceInterface sidedPiece : mapEntry.getValue()) {
                data.put(this.hash(sidedPiece.getSide(), sidedPiece.getType()), sidedPiece);
            }
        }
        return ImmutableMap.copyOf(data);
    }

    public <T extends Enum<T> & PieceTypeInterface> PieceManager(final Class<T> pieceTypeSetClass) {
        final Map<SideInterface, Set<PieceInterface>> pieceMap = this.factory.newPieceSet(pieceTypeSetClass);
        this.data = this.initializeData(pieceMap);
        this.nullPiece = pieceMap.get(Sides.NULL).iterator().next();

    }

    public PieceInterface getNullPiece() {
        return this.nullPiece;
    }

    public PieceInterface getPiece(final SideInterface side, final PieceTypeInterface type) {
        final PieceInterface piece = this.data.get(this.hash(side, type));
        if (piece == null) {
            throw new IllegalPieceException(side, type);
        }
        return piece;
    }

}
