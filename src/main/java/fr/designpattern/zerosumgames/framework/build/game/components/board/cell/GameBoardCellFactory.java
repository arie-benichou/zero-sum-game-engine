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

package fr.designpattern.zerosumgames.framework.build.game.components.board.cell;

import java.util.HashMap;
import java.util.Map;

import fr.designpattern.zerosumgames.framework.build.game.components.board.GameBoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.build.game.components.board.cell.piece.IGamePiece;
import fr.designpattern.zerosumgames.framework.build.game.components.board.position.IGameBoardPosition;
import fr.designpattern.zerosumgames.framework.build.game.components.board.position.IGameBoardPositionFactory;

public class GameBoardCellFactory implements IGameBoardCellFactory {
	// ---------------------------------------------------------------------
	private transient IGameBoardPositionFactory positionFactory;
	private final void setBoardPositionFactory(final IGameBoardPositionFactory positionFactory) {
		this.positionFactory = positionFactory;
	}
	public final IGameBoardPositionFactory getBoardPositionFactory() {
		return this.positionFactory;
	}
	// ---------------------------------------------------------------------
	private Map<IGameBoardPosition, IGameBoardCell> gameBoardCells;
	private final void setGameBoardCells(final Map<IGameBoardPosition, IGameBoardCell> gameBoardCells) {
		this.gameBoardCells = gameBoardCells;
	}
	public final Map<IGameBoardPosition, IGameBoardCell> getGameBoardCells() {
		return gameBoardCells;
	}
	// ---------------------------------------------------------------------
	private final transient IGameBoardCell nullCell; 
	public IGameBoardCell getNullCell() {
		return this.nullCell;
	}	
	// ---------------------------------------------------------------------
	private IGameBoardCell createCell(final IGameBoardPosition position) {
		return new GameBoardCell(position);
	}
	// ---------------------------------------------------------------------
	private final Map<IGameBoardPosition, IGameBoardCell> initializeBoardCells(final Map<IGameBoardPosition, IGameBoardCell> boardCells) {
		///int n = 0;
		IGameBoardCell cell;
		///int numberOfPositions = this.getBoardPositionFactory().getNumberOfPositions();
		///int numberOfDigitsInNumberOfPositions = numberOfPositions == 0 ? 1: (int) Math.log10(Math.abs(numberOfPositions)) + 1;
		///System.out.println("\nInitialisation des " + numberOfPositions + " cellules...");
		for (IGameBoardPosition[] line : this.getBoardPositionFactory().getBoardPositions()) {
			for (IGameBoardPosition position : line) {
					///System.out.format("%0" + numberOfDigitsInNumberOfPositions+ "d : ", ++n);
					// TODO utiliser une méthode de création
					cell = this.createCell(position);
					///System.out.print(cell + "\n");
					boardCells.put(position, cell);
			}
		}
		return boardCells;
	}
	// ---------------------------------------------------------------------
	public GameBoardCellFactory(final IGameBoardPositionFactory positionFactory) {
		this.setBoardPositionFactory(positionFactory);
		this.setGameBoardCells(this.initializeBoardCells(new HashMap<IGameBoardPosition, IGameBoardCell>(positionFactory.getNumberOfPositions())));
		
		this.nullCell = new GameBoardCell(this.getBoardPositionFactory().getNullPosition()); 
	}
	// ---------------------------------------------------------------------
	public IGameBoardCell cell(final IGameBoardPosition position) {
		return  position.isNull() ? this.nullCell : this.gameBoardCells.get(position);
	}
	// =====================================================================
	private class GameBoardCell implements IGameBoardCell {
		// ---------------------------------------------------------------------		
		private IGameBoardPosition position;
		public final IGameBoardPosition getPosition() {
			return this.position;
		}
		private final void setPosition(final IGameBoardPosition position) {
			this.position = position;
		}
		// ---------------------------------------------------------------------
		public boolean isNull() {
			return this.getPosition().isNull();
		}
		// ---------------------------------------------------------------------				
		private IGamePiece piece = null;
		public final void setPiece(final IGamePiece piece) {
			
			// TODO ? utiliser la pièce nulle
			if(this.isNull()) {
				throw new RuntimeException();
			}			
			
			this.piece = piece;
		}
		public final IGamePiece getPiece() {
			
			// TODO utiliser la pièce nulle
			if(this.isNull()) {
				throw new RuntimeException();
			}
			
			return this.piece;
		}
		// ---------------------------------------------------------------------
		public boolean isEmpty() {
			
			// TODO ? renvoyer true
			if(this.isNull()) {
				throw new RuntimeException();
			}
			
			return this.getPiece() == null;
		}
		// ---------------------------------------------------------------------
		private transient Map<GameBoardCardinalPosition, IGameBoardCell> neighbourhood = null;
		public Map<GameBoardCardinalPosition, IGameBoardCell> getNeighbourhood() {
			if (this.neighbourhood == null) {
				this.neighbourhood = new HashMap<GameBoardCardinalPosition, IGameBoardCell>(8);
				this.neighbourhood.put(GameBoardCardinalPosition.TOP, cell(getBoardPositionFactory().topOf(this.getPosition())));
				this.neighbourhood.put(GameBoardCardinalPosition.RIGHT, cell(getBoardPositionFactory().rightOf(this.getPosition())));
				this.neighbourhood.put(GameBoardCardinalPosition.BOTTOM, cell(getBoardPositionFactory().bottomOf(this.getPosition())));
				this.neighbourhood.put(GameBoardCardinalPosition.LEFT, cell(getBoardPositionFactory().leftOf(this.getPosition())));
				this.neighbourhood.put(GameBoardCardinalPosition.TOP_RIGHT, cell(getBoardPositionFactory().topRightOf(this.getPosition())));
				this.neighbourhood.put(GameBoardCardinalPosition.TOP_LEFT, cell(getBoardPositionFactory().topLeftOf(this.getPosition())));
				this.neighbourhood.put(GameBoardCardinalPosition.BOTTOM_RIGHT, cell(getBoardPositionFactory().bottomRightOf(this.getPosition())));
				this.neighbourhood.put(GameBoardCardinalPosition.BOTTOM_LEFT, cell(getBoardPositionFactory().bottomLeftOf(this.getPosition())));
			}
			return this.neighbourhood;
		}
		// ---------------------------------------------------------------------
		public GameBoardCell(final IGameBoardPosition position) {
			this.setPosition(position);
		}
		// ---------------------------------------------------------------------
		@Override
		public String toString() {
			return "[position=" + position + ", piece=" + piece + "]";
		}
		// ---------------------------------------------------------------------
		public IGameBoardCell getNeighbour(final GameBoardCardinalPosition key) {
			return this.getNeighbourhood().get(key);
		}
		// ---------------------------------------------------------------------
		public IGameBoardCell top() {
			return this.getNeighbour(GameBoardCardinalPosition.TOP);
		}
		public IGameBoardCell right() {
			return this.getNeighbour(GameBoardCardinalPosition.RIGHT);
		}
		public IGameBoardCell bottom() {
			return this.getNeighbour(GameBoardCardinalPosition.BOTTOM);
		}
		public IGameBoardCell left() {
			return this.getNeighbour(GameBoardCardinalPosition.LEFT);
		}
		public IGameBoardCell topRight() {
			return this.getNeighbour(GameBoardCardinalPosition.TOP_RIGHT);
		}
		public IGameBoardCell topLeft() {
			return this.getNeighbour(GameBoardCardinalPosition.TOP_LEFT);
		}
		public IGameBoardCell bottomRight() {
			return this.getNeighbour(GameBoardCardinalPosition.BOTTOM_RIGHT);
		}
		public IGameBoardCell bottomLeft() {
			return this.getNeighbour(GameBoardCardinalPosition.BOTTOM_LEFT);
		}
		// ---------------------------------------------------------------------		
	}
	// =====================================================================

}