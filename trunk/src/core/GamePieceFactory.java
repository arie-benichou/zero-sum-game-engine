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

package core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

import core.interfaces.IGamePiece;
import core.interfaces.IGamePieceFactory;
import core.interfaces.IGamePieceType;
import core.types.GamePlayersEnumeration;

public class GamePieceFactory implements IGamePieceFactory {

	private Hashtable<String, IGamePiece> gamePiecesCache;
	private void setGamePiecesCache(Hashtable<String, IGamePiece> gamePiecesCache) {
		this.gamePiecesCache = gamePiecesCache;
	}

	public IGamePiece createPiece(IGamePieceType type, GamePlayersEnumeration side) {
		Class<? extends IGamePiece> classObject = type.getClassObject();
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

	private String hash(GamePlayersEnumeration side, IGamePieceType gamePieceType) {
		return side + "/" + gamePieceType;
	}

	private <T extends Enum<T> & IGamePieceType> void initializeGamePiecesCache(Class<T> gamePieceTypes) {
		for (IGamePieceType gamePieceType : gamePieceTypes.getEnumConstants())
			for (GamePlayersEnumeration side : GamePlayersEnumeration.values())
				this.gamePiecesCache.put(this.hash(side, gamePieceType), this.createPiece(gamePieceType, side));
	}

	@Override
	public IGamePiece getPiece(GamePlayersEnumeration side, IGamePieceType gamePieceType) {
		return this.gamePiecesCache.get(this.hash(side, gamePieceType));
	}

	public <T extends Enum<T> & IGamePieceType> GamePieceFactory(Class<T> gamePiecesSet) {
		///System.out.println("\nInitialisation des pièces du jeu...");
		int gamePiecesCacheCapacity = GamePlayersEnumeration.values().length * gamePiecesSet.getEnumConstants().length;
		this.setGamePiecesCache(new Hashtable<String, IGamePiece>(gamePiecesCacheCapacity));
		this.initializeGamePiecesCache(gamePiecesSet);
	}

}