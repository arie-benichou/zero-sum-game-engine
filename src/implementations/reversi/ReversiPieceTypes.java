/*
 * Copyright (C) 2011 Arié Bénichou
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.    
 */

package implementations.reversi;

import core.interfaces.IGamePieceType;
import implementations.reversi.pieces.AbstractReversiPiece;
import implementations.reversi.pieces.ReversiPieceNull;
import implementations.reversi.pieces.ReversiPiecePawn;

/*
 * Remarque:
 * le type de pièce NULL est un artefact correspondant à la non-pièce, il s'agit du NullObject: PieceNull  
 */
public enum ReversiPieceTypes implements IGamePieceType {
	// ------------------------------------------------------------
	NULL(ReversiPieceNull.class),
	PAWN(ReversiPiecePawn.class);
	// ------------------------------------------------------------
	private final Class<? extends AbstractReversiPiece> classObject;
	// ------------------------------------------------------------
	private ReversiPieceTypes(final Class<? extends AbstractReversiPiece> classObject) {
		this.classObject = classObject;
	}
	// ------------------------------------------------------------
	public final Class<? extends AbstractReversiPiece> getClassObject() {
		return this.classObject;
	}
	// ------------------------------------------------------------
}