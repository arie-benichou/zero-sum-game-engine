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

import static abstractions.side.API.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.google.common.collect.Maps;

import abstractions.piece.API.PieceInterface;
import abstractions.side.API.SideInterface;

//TODO ! fixer la PieceFactory
public class PieceFactory {

    private final Map<String, PieceInterface> pieces;

    private String hash(final SideInterface side, final PiecesSetInterface gamePieceType) {
        return side + "/" + gamePieceType;
    }

    private PieceInterface createPiece(final PiecesSetInterface type, final SideInterface side) {
        final Class<? extends PieceInterface> classObject = type.getClassObject();
        Constructor<? extends PieceInterface> constructor = null;
        PieceInterface instance = null;
        try {
            constructor = classObject.getDeclaredConstructor(SideInterface.class);
        }
        catch (final SecurityException e) {
            e.printStackTrace();
        }
        catch (final NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            instance = constructor.newInstance(type, side);
        }
        catch (final IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (final InstantiationException e) {
            e.printStackTrace();
        }
        catch (final IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (final InvocationTargetException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public <T extends Enum<T> & PiecesSetInterface> PieceFactory(final Class<T> piecesSet) {
        this.pieces = Maps.newHashMapWithExpectedSize(2 * piecesSet.getEnumConstants().length);
        for (final PiecesSetInterface pieceType : piecesSet.getEnumConstants()) {
            this.pieces.put(this.hash(FIRST_SIDE, pieceType), this.createPiece(pieceType, FIRST_SIDE));
            this.pieces.put(this.hash(SECOND_SIDE, pieceType), this.createPiece(pieceType, SECOND_SIDE));
        }
    }

    public PieceInterface getPiece(final SideInterface side, final PiecesSetInterface gamePieceType) {
        return this.pieces.get(this.hash(side, gamePieceType));
    }

}