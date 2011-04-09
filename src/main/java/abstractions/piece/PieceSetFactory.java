/*
 * Copyright (C) 2011 Arié Bénichou
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import abstractions.side.SideInterface;
import abstractions.side.Sides;

import com.google.common.collect.Sets;

public final class PieceSetFactory implements PieceSetFactoryInterface {

    private final PieceInterface newPiece(final String path, final PieceTypeInterface pieceType, final SideInterface side) {
        String qualifiedName = path + "." + LOWER_UNDERSCORE.to(UPPER_CAMEL, pieceType.toString());
        PieceInterface pieceInstance = null;
        try {
            pieceInstance = (PieceInterface) Class.forName(qualifiedName).getConstructor(SideInterface.class, PieceTypeInterface.class)
                    .newInstance(side, pieceType);
        }
        catch (ClassCastException e) {
            throw new IllegalPieceSetException("Class '" + pieceType + "' must implement PieceInterface.");
        }
        catch (IllegalArgumentException e) {
            throw new IllegalPieceException(side, pieceType);
        }
        catch (SecurityException e) {
            throw new IllegalPieceException(side, pieceType);
        }
        catch (InstantiationException e) {
            throw new IllegalPieceException(side, pieceType);
        }
        catch (IllegalAccessException e) {
            throw new IllegalPieceException(side, pieceType);
        }
        catch (InvocationTargetException e) {
            throw new IllegalPieceException(side, pieceType);
        }
        catch (NoSuchMethodException e) {
            throw new IllegalPieceException(side, pieceType);
        }
        catch (ClassNotFoundException e) {
            throw new IllegalPieceSetException("Class '" + pieceType + "' not found.");
        }
        return pieceInstance;

    }

    public <T extends Enum<T> & PieceTypeInterface> Set<PieceInterface> newPieceSet(final Class<T> pieceTypeSetClass) {

        HashSet<T> pieceTypeSet = Sets.newHashSet(pieceTypeSetClass.getEnumConstants());

        if (pieceTypeSet.isEmpty()) {
            throw new IllegalPieceSetException("Set od pieces '" + pieceTypeSetClass.getSimpleName()
                    + "' must contain the NULL piece type and at least one not-NULL piece type.");
        }

        Iterator<T> piecesAlphabetIterator = pieceTypeSet.iterator();
        T nullType = null;
        try {
            while (!(nullType = piecesAlphabetIterator.next()).name().toLowerCase().equals("null"));
        }
        catch (NoSuchElementException e) {
            throw new IllegalPieceSetException("Set of pieces '" + pieceTypeSetClass.getSimpleName() + "' must contain the NULL piece type.");
        }
        pieceTypeSet.remove(nullType);

        if (pieceTypeSet.isEmpty()) {
            throw new IllegalPieceSetException("Set of pieces '" + pieceTypeSetClass.getSimpleName() + "' must contain at least one not-NULL piece type.");
        }

        HashSet<PieceInterface> pieceSet = Sets.newHashSet();

        String path = pieceTypeSetClass.getPackage().getName();
        pieceSet.add(this.newPiece(path, nullType, Sides.NULL));
        for (final PieceTypeInterface pieceType : pieceTypeSet) {
            pieceSet.add(this.newPiece(path, pieceType, Sides.FIRST));
            pieceSet.add(this.newPiece(path, pieceType, Sides.SECOND));
        }

        return pieceSet; // TODO return unmodifiable set
    }

}