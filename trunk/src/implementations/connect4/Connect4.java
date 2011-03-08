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

package implementations.connect4;

import java.util.ArrayList;
import java.util.List;

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
import core.types.GamePlayerNature;
import core.types.GamePlayersEnumeration;
import implementations.tictactoe.Tictactoe;

public class Connect4 extends Tictactoe {
	// ------------------------------------------------------------
	public Connect4(IGamePieceFactory pieceFactory, IGameBoard board, List<IGamePlayer> opponents) {
		super(pieceFactory, board, opponents, 4);
	}
	// -----------------------------------------------------------------	
	public Connect4(IGamePieceFactory pieceFactory, IGameBoard board, List<IGamePlayer> opponents, int numberOfPawnsToConnect) {
		super(pieceFactory, board, opponents, numberOfPawnsToConnect);
	}	
	// -----------------------------------------------------------------
	@Override
	public final List<IGameBoardMove> getLegalMoves(IGameBoard board, GamePlayersEnumeration side) {
		IGameBoardCell nextBottomCell;
		List<IGameBoardMove> legalGameTransitions = new ArrayList<IGameBoardMove>();
		// TODO a améliorer
		for (IGameBoardCell[] line : this.getBoard()) {
			for(IGameBoardCell cell : line) {
				while(cell.isEmpty()) {
					nextBottomCell = cell.bottom();
					if(nextBottomCell.isNull() || !nextBottomCell.isEmpty()) {
						legalGameTransitions.add(new GameBoardMove(side, cell.getPosition()));
						break;
					}
					cell = nextBottomCell;
				}
			}
			break;			
		}
		return legalGameTransitions;
	}
	// ------------------------------------------------------------	
	// TODO ! GameBuilder
	public static void main(String[] args) throws Exception {
		// ------------------------------------------------------------
		GamePieceFactory pieceFactory = new GamePieceFactory(Connect4PieceTypes.class);
		// ------------------------------------------------------------		
		IGameBoardDimension gbd = new GameBoardDimension(1, 6, 1, 7);		
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
		new Connect4(pieceFactory, board, opponents).start();
		// ------------------------------------------------------------
	}
	// ------------------------------------------------------------
}