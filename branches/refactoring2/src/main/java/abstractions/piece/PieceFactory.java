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

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import abstractions.piece.API.PieceInterface;
import abstractions.side.API.SideInterface;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import concretisations.checkers.pieces.Pieces;

public final class PieceFactory/*<PT extends PieceInterface>*/ {
    
    /**
     * Class for illegal pieces alphabet.
     */
    public static final class IllegalPiecesAlphabetException extends RuntimeException {
        
        private static final long serialVersionUID = 1L;
        
        public IllegalPiecesAlphabetException(String message) {
            super(message);
        }
        
    }        

    /**
     * Class for illegal pieces.
     */
    public static final class IllegalPieceException extends RuntimeException {

        private static final String MESSAGE = "Piece(side=%s, type=%s) is not a legal piece.";

        private static final long serialVersionUID = 1L;

        private SideInterface side;
        private PieceTypeInterface pieceType;

        public IllegalPieceException(final SideInterface side, final PieceTypeInterface pieceType) {
            super();
            this.side = side;
            this.pieceType = pieceType;
        }

        @Override
        public String getMessage() {
            return String.format(MESSAGE, this.side, this.pieceType);
        }

    }
    
    private final String path;
    private final Map<Integer, PieceInterface> pieces;
    private final PieceInterface nullPiece;
    

//    @SuppressWarnings("unchecked")
    private final /*PT*/ PieceInterface createPiece(final PieceTypeInterface pieceType, final SideInterface side) {
        String type = pieceType.toString();
        type = Character.toUpperCase(type.charAt(0)) + type.substring(1).toLowerCase();
        PieceInterface pieceInstance = null;
        try {
            if(side.isNull()) {
                pieceInstance = (PieceInterface) Class.forName(this.path + "." + type).newInstance();    
            }
            else {
                pieceInstance = (PieceInterface) Class.forName(this.path + "." + type).getConstructor(SideInterface.class).newInstance(side);
            }
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
            throw new IllegalPieceException(side, pieceType);
        }
        return /*(PT)*/ pieceInstance;
    }

    private final int hash(final SideInterface side, final PieceTypeInterface pieceType) {
        return side.hashCode() + pieceType.hashCode();
    }

    public <T extends Enum<T> & PieceTypeInterface> PieceFactory(final Class<T> piecesSet) {

        this.path = piecesSet.getPackage().getName();
        this.pieces = Maps.newHashMapWithExpectedSize(2 * piecesSet.getEnumConstants().length);
        
        HashSet<T> piecesAlphabet = Sets.newHashSet(piecesSet.getEnumConstants());
        
        if(piecesAlphabet.isEmpty()) {
            throw new IllegalPiecesAlphabetException("Alphabet " + piecesSet.getSimpleName() + " must contain the NULL piece type and at least one not-NULL piece type.");
        }
        
        Iterator<T> piecesAlphabetIterator = piecesAlphabet.iterator();
        T nullType = null;
        try {
            while(!(nullType = piecesAlphabetIterator.next()).name().toLowerCase().equals("null"));
        }
        catch (NoSuchElementException e) {
            throw new IllegalPiecesAlphabetException("Alphabet " + piecesSet.getSimpleName() + " must contain the NULL piece type.");
        }
        piecesAlphabet.remove(nullType);
        
        if(piecesAlphabet.isEmpty()) {
            throw new IllegalPiecesAlphabetException("Alphabet " + piecesSet.getSimpleName() + " must contain at least one not-NULL piece type.");
        }
        
        this.nullPiece = this.createPiece(nullType, NULL_SIDE);
        this.pieces.put(this.hash(NULL_SIDE, nullType), this.nullPiece);
        
        for (final PieceTypeInterface pieceType : piecesAlphabet) {
            this.pieces.put(this.hash(FIRST_SIDE, pieceType), this.createPiece(pieceType, FIRST_SIDE));
            this.pieces.put(this.hash(SECOND_SIDE, pieceType), this.createPiece(pieceType, SECOND_SIDE));            
        }        

    }
    
    public final PieceInterface getNullPiece() {
        return this.nullPiece;
    }    

    public PieceInterface getPiece(final SideInterface side, final PieceTypeInterface pieceType) {
        return this.pieces.get(this.hash(side, pieceType));
    }

    // TODO tests unitaires
    public static void main(String[] args) {
        
        //PieceFactory<CheckerPiece> pieceFactory = new PieceFactory<CheckerPiece>(Pieces.class);
        PieceFactory pieceFactory = new PieceFactory(Pieces.class);
        
        System.out.println(pieceFactory.getPiece(FIRST_SIDE, Pieces.MAN));
        System.out.println(pieceFactory.getPiece(FIRST_SIDE, Pieces.KING));
        
        System.out.println(pieceFactory.getPiece(NULL_SIDE, Pieces.NULL));
        System.out.println(pieceFactory.getNullPiece());
        
        System.out.println(pieceFactory.getPiece(SECOND_SIDE, Pieces.MAN));
        System.out.println(pieceFactory.getPiece(SECOND_SIDE, Pieces.KING));
        
    }

}