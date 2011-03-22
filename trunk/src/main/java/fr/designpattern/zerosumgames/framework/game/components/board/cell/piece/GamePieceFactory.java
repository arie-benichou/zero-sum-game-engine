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

package fr.designpattern.zerosumgames.framework.game.components.board.cell.piece;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Map;

import fr.designpattern.zerosumgames.framework.game.components.opponents.GamePlayersEnumeration;

public class GamePieceFactory implements IGamePieceFactory {

	private Map<String, IGamePiece> gamePiecesCache;
	private Map<String, IGamePiece> getGamePiecesCache() {
		return this.gamePiecesCache;
	}
	private void setGamePiecesCache(final Map<String, IGamePiece> gamePiecesCache) {
		this.gamePiecesCache = gamePiecesCache;
	}

	private IGamePiece createPiece(final IGamePieceType type, final GamePlayersEnumeration side) {
		final Class<? extends IGamePiece> classObject = type.getClassObject();
		Constructor<? extends IGamePiece> constructor = null;
		IGamePiece instance = null;
		try {
			constructor = classObject.getDeclaredConstructor(IGamePieceType.class, GamePlayersEnumeration.class);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		try {
			instance = constructor.newInstance(type, side);
			///System.out.println(instance);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return instance;
	}

	private String hash(final GamePlayersEnumeration side, final IGamePieceType gamePieceType) {
		return side + "/" + gamePieceType;
	}

	private <T extends Enum<T> & IGamePieceType> void initializeGamePiecesCache(final Class<T> gamePieceTypes) {
		for (IGamePieceType gamePieceType : gamePieceTypes.getEnumConstants()) {
			for (GamePlayersEnumeration side : GamePlayersEnumeration.values()) {
				this.getGamePiecesCache().put(this.hash(side, gamePieceType), this.createPiece(gamePieceType, side));
			}
		}
	}

	public IGamePiece getPiece(final GamePlayersEnumeration side, final IGamePieceType gamePieceType) {
		return this.gamePiecesCache.get(this.hash(side, gamePieceType));
	}

	public <T extends Enum<T> & IGamePieceType> GamePieceFactory(final Class<T> gamePiecesSet) {
		///System.out.println("\nInitialisation des pièces du jeu...");
		final int cacheCapacity = GamePlayersEnumeration.values().length * gamePiecesSet.getEnumConstants().length;
		this.setGamePiecesCache(new Hashtable<String, IGamePiece>(cacheCapacity));
		this.initializeGamePiecesCache(gamePiecesSet); // TODO !! à revoir
	}

}