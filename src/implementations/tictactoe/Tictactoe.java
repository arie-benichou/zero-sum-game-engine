/*
 * @(#)Tictactoe.java	0.99
 * 
 * Copyright 2011 Arie Benichou
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

package implementations.tictactoe;

import java.util.ArrayList;
import java.util.List;

import core.Game;
import core.GameBoard;
import core.GameBoardCellFactory;
import core.GameBoardDimension;
import core.GameBoardMove;
import core.GameBoardPositionFactory;
import core.GamePieceFactory;
import core.GamePlayer;
import core.GamePlayerRandomStrategy;
import core.interfaces.IGameBoard;
import core.interfaces.IGameBoardCell;
import core.interfaces.IGameBoardCellFactory;
import core.interfaces.IGameBoardDimension;
import core.interfaces.IGameBoardMove;
import core.interfaces.IGameBoardPositionFactory;
import core.interfaces.IGamePieceFactory;
import core.interfaces.IGamePlayer;
import core.interfaces.IGamePlayerStrategy;
import core.types.GameBoardCardinalPosition;
import core.types.GameBoardPlane;
import core.types.GamePlayerNature;
import core.types.GamePlayersEnumeration;

public class Tictactoe extends Game {
	// ------------------------------------------------------------	
	private int numberOfPawnsToConnect = 3;
	// ------------------------------------------------------------
	public Tictactoe(IGamePieceFactory pieceFactory, IGameBoard board, List<IGamePlayer> opponents) {
		super(pieceFactory, board, opponents);
	}
	public Tictactoe(IGamePieceFactory pieceFactory, IGameBoard board, List<IGamePlayer> opponents, int numberOfPawnsToConnect) {
		this(pieceFactory, board, opponents);
		this.numberOfPawnsToConnect = numberOfPawnsToConnect;
	}	
	// -----------------------------------------------------------------
	@Override
	public List<IGameBoardMove> getLegalMoves(IGameBoard board, GamePlayersEnumeration side) {
		List<IGameBoardMove> legalGameTransitions = new ArrayList<IGameBoardMove>();
		for (IGameBoardCell[] line : this.getBoard())
			for(IGameBoardCell cell : line)
				if(cell.isEmpty()) // TODO isPlayable()
					legalGameTransitions.add(new GameBoardMove(side, cell.getPosition()));
		return legalGameTransitions;
	}
	// ------------------------------------------------------------	
	protected int countConnections(IGameBoardMove justPlayedMove, GameBoardCardinalPosition direction) {
		IGameBoardCell neighbour, cell = this.getCell(justPlayedMove.getPosition());
		int n;
		neighbour = cell.getNeighbour(direction);
		for (n = 1; n < this.numberOfPawnsToConnect; n++) {
			if (neighbour.isNull() || neighbour.isEmpty()) {
				break;
			}
			if (neighbour.getPiece().getSide() != justPlayedMove.getSide()) {
				break;
			}
			neighbour = neighbour.getNeighbour(direction);
		}
		return n - 1;
	}	
	// ------------------------------------------------------------
	protected boolean isWinningMove(IGameBoardMove justPlayedMove) {
		int connections;
		for (GameBoardPlane plane : GameBoardPlane.values()) {
			connections = 1;
			connections += this.countConnections(justPlayedMove, plane.getOneWay());
			connections += this.countConnections(justPlayedMove, plane.getOppositeWay());
			if (connections >= this.numberOfPawnsToConnect) {
				return true;
			}
		}
		return false;
	}	
	// ------------------------------------------------------------
	@Override
	public boolean isGameOver(IGameBoard gameState, IGameBoardMove justPlayedMove) {
		if (this.isWinningMove(justPlayedMove)) {
			return true;
		}
		return super.isGameOver(gameState, justPlayedMove);
	}
	// -----------------------------------------------------------------	
	public GamePlayersEnumeration applyGameStateTransition(IGameBoard gameState, IGameBoardMove justPlayedMove) {
		
		if (justPlayedMove.isNull()) {
			// TODO ! à améliorer			
			return super.applyGameStateTransition(gameState, justPlayedMove);
		}
				
		// TODO ! méthode playMove
		IGameBoardCell concernedCell = this.getCell(justPlayedMove.getPosition());
		// TODO façade
		concernedCell.setPiece(this.piece(justPlayedMove.getSide(), TictactoePieceTypes.PAWN));
		
		// TODO ! à améliorer		
		if(this.isGameOver(gameState, justPlayedMove)) {
			return null;
		}
		
		// TODO ! à améliorer
		return super.applyGameStateTransition(gameState, justPlayedMove);
	}
	// ------------------------------------------------------------
	// TODO ! GameBuilder
	public static void main(String[] args) throws Exception {
		// ------------------------------------------------------------
		GamePieceFactory pieceFactory = new GamePieceFactory(TictactoePieceTypes.class);
		// ------------------------------------------------------------		
		IGameBoardDimension gbd = new GameBoardDimension(1, 3, 1, 3);
		//IGameBoardDimension gbd = new GameBoardDimension(1, 6, 1, 6);		
		IGameBoardPositionFactory gbpf = new GameBoardPositionFactory(gbd);		
		IGameBoardCellFactory gbcf = new GameBoardCellFactory(gbpf);		
		IGameBoard board = new GameBoard(gbcf);
		// ------------------------------------------------------------
		IGamePlayerStrategy player1Strategy = new GamePlayerRandomStrategy();
		IGamePlayer player1 = new GamePlayer("Player 1", GamePlayersEnumeration.FIRST_PLAYER, GamePlayerNature.COMPUTER, player1Strategy);
		IGamePlayerStrategy player2Strategy = new GamePlayerRandomStrategy();
		IGamePlayer player2 = new GamePlayer("Player 2", GamePlayersEnumeration.SECOND_PLAYER, GamePlayerNature.COMPUTER, player2Strategy);
		List<IGamePlayer> opponents = new ArrayList<IGamePlayer>();
		opponents.add(player1);
		opponents.add(player2);
		// ------------------------------------------------------------		
		new Tictactoe(pieceFactory, board, opponents).start();
		//new Tictactoe(pieceFactory, board, opponents,4).start();		
		// ------------------------------------------------------------
	}
	// ------------------------------------------------------------
}