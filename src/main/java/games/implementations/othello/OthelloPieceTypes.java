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

package main.java.games.implementations.othello;

import main.java.games.core.interfaces.IGamePieceType;
import main.java.games.implementations.othello.pieces.OthelloPiece;
import main.java.games.implementations.othello.pieces.OthelloPieceNull;
import main.java.games.implementations.othello.pieces.OthelloPiecePawn;

/*
 * Remarque:
 * le type de pièce NULL est un artefact correspondant à la non-pièce, il s'agit du NullObject: PieceNull  
 */
public enum OthelloPieceTypes implements IGamePieceType {
	// ------------------------------------------------------------
	NULL(OthelloPieceNull.class),
	PAWN(OthelloPiecePawn.class);
	// ------------------------------------------------------------
	private final Class<? extends OthelloPiece> classObject;
	// ------------------------------------------------------------
	private OthelloPieceTypes(final Class<? extends OthelloPiece> classObject) {
		this.classObject = classObject;
	}
	// ------------------------------------------------------------
	public final Class<? extends OthelloPiece> getClassObject() {
		return this.classObject;
	}
	// ------------------------------------------------------------
}