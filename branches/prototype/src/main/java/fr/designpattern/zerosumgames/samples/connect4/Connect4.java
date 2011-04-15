/*
 * Copyright 2011 Arié Bénichou
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

package fr.designpattern.zerosumgames.samples.connect4;

import java.util.ArrayList;
import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.GameBoardInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardDimensions;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.samples.tictactoe.Tictactoe;

public class Connect4 extends Tictactoe {

    // ------------------------------------------------------------
    public static final int CONNECTIONS = 4;
    //public static final Class<Connect4PieceTypes> PIECE_TYPES = Connect4PieceTypes.class;
    public static final BoardDimensions BOARD_DIMENSION = new BoardDimensions(1, 6, 1, 7);

    // ------------------------------------------------------------
    public Connect4(final GameBoardInterface board, final int connections) {
        super(board, connections);
    }

    // ------------------------------------------------------------
    public Connect4(final GameBoardInterface board) {
        super(board, Connect4.CONNECTIONS);
    }

    // -----------------------------------------------------------------
    @Override
    public final List<LegalMoveInterface> getLegalMoves(final OpponentsEnumeration side) {

        BoardCellInterface nextBottomCell;
        final List<LegalMoveInterface> legalMoves = new ArrayList<LegalMoveInterface>();

        //System.out.println("from getLegalMoves : " + this);

        System.out.println(this.cell(6, 1));

        // TODO a améliorer
        for (final BoardCellInterface[] line : this.getBoard()) {
            for (BoardCellInterface cell : line) {
                while (cell.isEmpty()) {
                    nextBottomCell = cell.bottom();
                    if (nextBottomCell.isNull() || !nextBottomCell.isEmpty()) {
                        legalMoves.add(this.makeMove(side, cell.getPosition()));
                        break;
                    }
                    cell = nextBottomCell;
                }
            }
            break;
        }
        return legalMoves;
    }

    // ------------------------------------------------------------
    /*
     * @Override protected PieceInterface piece(final OpponentsEnumeration side)
     * { return this.pieceFactory.getPiece(side, TictactoePieceTypes.PAWN); }
     */
    // ------------------------------------------------------------
}