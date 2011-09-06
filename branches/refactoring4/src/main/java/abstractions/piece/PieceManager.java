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

import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import abstractions.side.SideInterface;
import abstractions.side.Sides;
import annotations.Immutable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@Immutable
public final class PieceManager implements PieceManagerInterface {

    private final Map<Integer, PieceInterface> data;
    private final PieceInterface nullPiece;

    private PieceInterface newPiece(final String path, final PieceTypeInterface pieceType, final SideInterface side) {
        final String qualifiedName = path + "." + LOWER_UNDERSCORE.to(UPPER_CAMEL, pieceType.toString());
        PieceInterface pieceInstance = null;
        try {
            pieceInstance = (PieceInterface) Class.forName(qualifiedName).getConstructor(SideInterface.class, PieceTypeInterface.class)
                    .newInstance(side, pieceType);
        }
        catch (final ClassCastException e) {
            throw new IllegalPieceSetException("Class '" + pieceType + "' must implement PieceInterface."); // NOPMD
        }
        catch (final IllegalArgumentException e) {
            throw new IllegalPieceException(side, pieceType); // NOPMD
        }
        catch (final SecurityException e) {
            throw new IllegalPieceException(side, pieceType); // NOPMD
        }
        catch (final InstantiationException e) {
            throw new IllegalPieceException(side, pieceType); // NOPMD 
        }
        catch (final IllegalAccessException e) {
            throw new IllegalPieceException(side, pieceType); // NOPMD 
        }
        catch (final InvocationTargetException e) {
            throw new IllegalPieceException(side, pieceType); // NOPMD
        }
        catch (final NoSuchMethodException e) {
            throw new IllegalPieceException(side, pieceType); // NOPMD 
        }
        catch (final ClassNotFoundException e) {
            throw new IllegalPieceSetException("Class '" + pieceType + "' not found."); // NOPMD
        }
        return pieceInstance;

    }

    /**
     * Returns the alphabet of pieces.
     * 
     * @param <T>
     *            an enumeration of the types of pieces, the enum class must
     *            implement the marker interface PieceTypeInterface.
     * 
     * @param piecesSet
     *            the class object of the enumeration of piece types
     * 
     * @return the alphabet of pieces
     */
    private <T extends Enum<T> & PieceTypeInterface> Map<SideInterface, Set<PieceInterface>> newPieceSet(final Class<T> pieceTypeSetClass) {

        final Set<T> pieceTypeSet = Sets.newHashSet(pieceTypeSetClass.getEnumConstants());

        if (pieceTypeSet.isEmpty()) {
            throw new IllegalPieceSetException("Set of pieces '" + pieceTypeSetClass.getSimpleName()
                    + "' must contain the NULL piece type and at least one not-NULL piece type.");
        }

        final Iterator<T> piecesAlphabetIterator = pieceTypeSet.iterator();
        T nullType = null;
        try {
            while (!(nullType = piecesAlphabetIterator.next()).name().equalsIgnoreCase("null")) { // NOPMD
                ;
            }
        }
        catch (final NoSuchElementException e) {
            throw new IllegalPieceSetException("Set of pieces '" + pieceTypeSetClass.getSimpleName() + "' must contain the NULL piece type."); // NOPMD
        }
        pieceTypeSet.remove(nullType);

        if (pieceTypeSet.isEmpty()) {
            throw new IllegalPieceSetException("Set of pieces '" + pieceTypeSetClass.getSimpleName() + "' must contain at least one not-NULL piece type.");
        }

        final String path = pieceTypeSetClass.getPackage().getName();

        final Map<SideInterface, Set<PieceInterface>> piecesMap = Maps.newHashMapWithExpectedSize(3);

        final Set<PieceInterface> nullPiece = Sets.newHashSet(this.newPiece(path, nullType, Sides.NULL));
        piecesMap.put(Sides.NULL, nullPiece);

        final Set<PieceInterface> firstSidePieces = Sets.newHashSetWithExpectedSize(pieceTypeSet.size());
        for (final PieceTypeInterface pieceType : pieceTypeSet) {
            firstSidePieces.add(this.newPiece(path, pieceType, Sides.FIRST));
        }
        piecesMap.put(Sides.FIRST, firstSidePieces);

        final Set<PieceInterface> secondSidePieces = Sets.newHashSetWithExpectedSize(pieceTypeSet.size());
        for (final PieceTypeInterface pieceType : pieceTypeSet) {
            secondSidePieces.add(this.newPiece(path, pieceType, Sides.SECOND));
        }
        piecesMap.put(Sides.SECOND, secondSidePieces);

        return piecesMap;
    }

    private int hash(final SideInterface side, final PieceTypeInterface type) {
        return side.hashCode() + type.hashCode();
    }

    private Map<Integer, PieceInterface> initializeData(final Map<SideInterface, Set<PieceInterface>> map) {
        final Builder<Integer, PieceInterface> mapBuilder = ImmutableMap.builder();
        for (final Entry<SideInterface, Set<PieceInterface>> mapEntry : map.entrySet()) {
            for (final PieceInterface sidedPiece : mapEntry.getValue()) {
                mapBuilder.put(this.hash(sidedPiece.getSide(), sidedPiece.getType()), sidedPiece);
            }
        }
        return mapBuilder.build();
    }

    public <T extends Enum<T> & PieceTypeInterface> PieceManager(final Class<T> pieceTypeSetClass) {
        final Map<SideInterface, Set<PieceInterface>> pieceMap = this.newPieceSet(pieceTypeSetClass);
        this.nullPiece = pieceMap.get(Sides.NULL).iterator().next();
        this.data = this.initializeData(pieceMap);
    }

    @Override
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
