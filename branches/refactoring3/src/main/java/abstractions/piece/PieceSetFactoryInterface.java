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
import java.util.Set;

import abstractions.side.SideInterface;

// TODO à intégrer dans le piece manager.
/**
 * This is the factory of pieces alphabet.
 */
interface PieceSetFactoryInterface {

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
    <T extends Enum<T> & PieceTypeInterface> Map<SideInterface, Set<PieceInterface>> newPieceSet(final Class<T> piecesSet);

}
