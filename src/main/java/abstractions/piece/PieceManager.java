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

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import abstractions.side.SideInterface;
import abstractions.side.Sides;

import com.google.common.collect.Maps;

public class PieceManager implements PieceManagerInterface {

    private final PieceSetFactoryInterface factory = new PieceSetFactory();
    private final Map<Integer, PieceInterface> data;
    private final Class<? extends PieceTypeInterface> pieceTypeSetClass;
    private final PieceInterface nullPiece;

    private final int hash(final SideInterface side, final PieceTypeInterface type) {
        return side.hashCode() + type.hashCode();
    }

    private Map<Integer, PieceInterface> initializeData(final Set<PieceInterface> set) {
        final Map<Integer, PieceInterface> data = Maps.newHashMapWithExpectedSize(set.size());
        for (final PieceInterface element : set) {
            data.put(this.hash(element.getSide(), element.getType()), element);
        }
        if (data.size() != set.size()) {
            throw new RuntimeException("Method hash is not valid for this set of pieces");
        }
        return Collections.unmodifiableMap(data);
    }

    // TODO la factory doir retourner une hashMap <side, piece>
    public <T extends Enum<T> & PieceTypeInterface> PieceManager(final Class<T> pieceTypeSetClass) {
        this.pieceTypeSetClass = pieceTypeSetClass;

        final Set<PieceInterface> set = this.factory.newPieceSet(pieceTypeSetClass);
        this.data = this.initializeData(set);
        PieceInterface nullpiece = null;
        try {
            nullpiece = this.getPiece(Sides.NULL, (PieceTypeInterface) pieceTypeSetClass.getDeclaredField("NULL").get(null));
        }
        catch (final Exception e1) {
            try {
                nullpiece = this.getPiece(Sides.NULL, (PieceTypeInterface) pieceTypeSetClass.getDeclaredField("Null").get(null));
            }
            catch (final Exception e2) {
                try {
                    nullpiece = this.getPiece(Sides.NULL, (PieceTypeInterface) pieceTypeSetClass.getDeclaredField("null").get(null));
                }
                catch (final Exception e3) {}
            }
        }
        this.nullPiece = nullpiece;
    }

    public PieceInterface getNullPiece() {
        return this.nullPiece;
    }

    @Override
    public PieceInterface getPiece(final SideInterface side, final PieceTypeInterface type) {
        final PieceInterface piece = this.data.get(this.hash(side, type));
        if (piece == null) {
            throw new IllegalPieceException(side, type);
        }
        return piece;
    }

}
