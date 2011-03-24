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

package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells;

import java.util.HashMap;
import java.util.Map;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.PositionsInterface;

public class Cells implements CellsInterface {
	// ---------------------------------------------------------------------
	private transient PositionsInterface positionFactory;
	private final void setBoardPositionFactory(final PositionsInterface positionFactory) {
		this.positionFactory = positionFactory;
	}
	public final PositionsInterface getBoardPositionFactory() {
		return this.positionFactory;
	}
	// ---------------------------------------------------------------------
	private Map<PositionInterface, CellInterface> gameBoardCells;
	private final void setGameBoardCells(final Map<PositionInterface, CellInterface> gameBoardCells) {
		this.gameBoardCells = gameBoardCells;
	}
	public final Map<PositionInterface, CellInterface> getGameBoardCells() {
		return this.gameBoardCells;
	}
	// ---------------------------------------------------------------------
	private final transient CellInterface nullCell;
	public CellInterface getNullCell() {
		return this.nullCell;
	}
	// ---------------------------------------------------------------------
	private CellInterface createCell(final PositionInterface position) {
		return new GameBoardCell(position);
	}
	// ---------------------------------------------------------------------
	private final Map<PositionInterface, CellInterface> initializeBoardCells(final Map<PositionInterface, CellInterface> boardCells) {
		///int n = 0;
		CellInterface cell;
		///int numberOfPositions = this.getBoardPositionFactory().getNumberOfPositions();
		///int numberOfDigitsInNumberOfPositions = numberOfPositions == 0 ? 1: (int) Math.log10(Math.abs(numberOfPositions)) + 1;
		///System.out.println("\nInitialisation des " + numberOfPositions + " cellules...");
		for (final PositionInterface[] line : this.getBoardPositionFactory().getBoardPositions()) {
			for (final PositionInterface position : line) {
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
	public Cells(final PositionsInterface positionFactory) {
		this.setBoardPositionFactory(positionFactory);
		this.setGameBoardCells(this.initializeBoardCells(new HashMap<PositionInterface, CellInterface>(positionFactory.getNumberOfPositions())));

		this.nullCell = new GameBoardCell(this.getBoardPositionFactory().getNullPosition());
	}
	// ---------------------------------------------------------------------
	public CellInterface cell(final PositionInterface position) {
		return  position.isNull() ? this.nullCell : this.gameBoardCells.get(position);
	}
	// =====================================================================
	private class GameBoardCell implements CellInterface {
		// ---------------------------------------------------------------------
		private PositionInterface position;
		public final PositionInterface getPosition() {
			return this.position;
		}
		private final void setPosition(final PositionInterface position) {
			this.position = position;
		}
		// ---------------------------------------------------------------------
		public boolean isNull() {
			return this.getPosition().isNull();
		}
		// ---------------------------------------------------------------------
		private PieceInterface piece = null;
		public final void setPiece(final PieceInterface piece) {

			// TODO ? utiliser la pièce nulle
			if(this.isNull()) {
				throw new RuntimeException();
			}

			this.piece = piece;
		}
		public final PieceInterface getPiece() {

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
		private transient Map<BoardCardinalPosition, CellInterface> neighbourhood = null;
		public Map<BoardCardinalPosition, CellInterface> getNeighbourhood() {
			if (this.neighbourhood == null) {
				this.neighbourhood = new HashMap<BoardCardinalPosition, CellInterface>(8);
				this.neighbourhood.put(BoardCardinalPosition.TOP, Cells.this.cell(Cells.this.getBoardPositionFactory().topOf(this.getPosition())));
				this.neighbourhood.put(BoardCardinalPosition.RIGHT, Cells.this.cell(Cells.this.getBoardPositionFactory().rightOf(this.getPosition())));
				this.neighbourhood.put(BoardCardinalPosition.BOTTOM, Cells.this.cell(Cells.this.getBoardPositionFactory().bottomOf(this.getPosition())));
				this.neighbourhood.put(BoardCardinalPosition.LEFT, Cells.this.cell(Cells.this.getBoardPositionFactory().leftOf(this.getPosition())));
				this.neighbourhood.put(BoardCardinalPosition.TOP_RIGHT, Cells.this.cell(Cells.this.getBoardPositionFactory().topRightOf(this.getPosition())));
				this.neighbourhood.put(BoardCardinalPosition.TOP_LEFT, Cells.this.cell(Cells.this.getBoardPositionFactory().topLeftOf(this.getPosition())));
				this.neighbourhood.put(BoardCardinalPosition.BOTTOM_RIGHT, Cells.this.cell(Cells.this.getBoardPositionFactory().bottomRightOf(this.getPosition())));
				this.neighbourhood.put(BoardCardinalPosition.BOTTOM_LEFT, Cells.this.cell(Cells.this.getBoardPositionFactory().bottomLeftOf(this.getPosition())));
			}
			return this.neighbourhood;
		}
		// ---------------------------------------------------------------------
		public GameBoardCell(final PositionInterface position) {
			this.setPosition(position);
		}
		// ---------------------------------------------------------------------
		@Override
		public String toString() {
			return "[position=" + this.position + ", piece=" + this.piece + "]";
		}
		// ---------------------------------------------------------------------
		public CellInterface getNeighbour(final BoardCardinalPosition key) {
			return this.getNeighbourhood().get(key);
		}
		// ---------------------------------------------------------------------
		public CellInterface top() {
			return this.getNeighbour(BoardCardinalPosition.TOP);
		}
		public CellInterface right() {
			return this.getNeighbour(BoardCardinalPosition.RIGHT);
		}
		public CellInterface bottom() {
			return this.getNeighbour(BoardCardinalPosition.BOTTOM);
		}
		public CellInterface left() {
			return this.getNeighbour(BoardCardinalPosition.LEFT);
		}
		public CellInterface topRight() {
			return this.getNeighbour(BoardCardinalPosition.TOP_RIGHT);
		}
		public CellInterface topLeft() {
			return this.getNeighbour(BoardCardinalPosition.TOP_LEFT);
		}
		public CellInterface bottomRight() {
			return this.getNeighbour(BoardCardinalPosition.BOTTOM_RIGHT);
		}
		public CellInterface bottomLeft() {
			return this.getNeighbour(BoardCardinalPosition.BOTTOM_LEFT);
		}
		// ---------------------------------------------------------------------
	}
	// =====================================================================

}