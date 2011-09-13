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

package concretisations.reversi;

import java.util.List;
import java.util.Map;

import rendering.board.BoardConsoleRendering;
import rendering.board.BoardRenderer;
import rendering.board.BoardRenderingInterface;
import rendering.board.BoardStringRendering;
import rendering.board.cell.BoardCellStringRendering;
import rendering.board.cell.piece.PieceStringRendering;
import abstractions.immutable.context.board.Board;
import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.Piece;
import abstractions.immutable.context.board.cell.piece.PieceInterface;
import abstractions.immutable.context.board.cell.piece.side.Side;
import abstractions.immutable.context.board.cell.piece.type.PieceType;
import abstractions.immutable.context.board.cell.position.Position;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.move.mutation.BoardMutation;
import abstractions.immutable.move.mutation.BoardMutationInterface;
import abstractions.immutable.move.type.MoveTypeInterface;

import com.google.common.collect.Maps;

import concretisations.reversi.moves.ReversiMoveTypeInterface;
import concretisations.reversi.pieces.ReversiNullPiece;
import concretisations.reversi.pieces.ReversiPawn;
import concretisations.reversi.referees.ReversiReferee;

class Reversi {

    public static void main(final String[] args) {

        /*-------------------------------------8<-------------------------------------*/

        final PieceInterface black = Piece.from(Side.from(1), PieceType.from(ReversiPawn.class));
        final PieceInterface white = black.apply(black.side().opposite()); // TODO rajouter une méthode oppositeSide()
        final PieceInterface none = black.apply(Side.NULL, PieceType.from(ReversiNullPiece.class));
        final PieceInterface potential = Piece.from(Side.NULL, PieceType.from(ReversiPawn.class));

        /*-------------------------------------8<-------------------------------------*/

        final Map<PositionInterface, PieceInterface> map = Maps.newHashMap();
        map.put(Position.from(3, 3), black);
        map.put(Position.from(3, 4), white);
        map.put(Position.from(4, 3), white);
        map.put(Position.from(4, 4), black);

        /*-------------------------------------8<-------------------------------------*/

        final BoardInterface board = Board.from(6, 6, none).apply(BoardMutation.from(map));

        /*-------------------------------------8<-------------------------------------*/

        final BoardRenderingInterface<?> renderingType =
                new BoardConsoleRendering(
                        new BoardStringRendering(
                                new BoardCellStringRendering(
                                        new PieceStringRendering())));

        final BoardRenderer boardRenderer = new BoardRenderer(renderingType); // TODO ? créer une interface

        /*-------------------------------------8<-------------------------------------*/

        final Map<Object, Object> symbols = Maps.newHashMap();

        symbols.put(black, " x ");
        symbols.put(white, " o ");
        symbols.put(none, "   ");
        symbols.put(potential, " * ");

        symbols.put(board.cell(1, 1), "1,1");
        symbols.put(board.cell(1, 6), "1,6");
        symbols.put(board.cell(6, 1), "6,1");
        symbols.put(board.cell(6, 6), "6,6");

        /*-------------------------------------8<-------------------------------------*/

        boardRenderer.render(board, symbols);

        /*-------------------------------------8<-------------------------------------*/

        /*
        symbols.remove(board.cell(1, 1));
        symbols.remove(board.cell(1, 6));
        symbols.remove(board.cell(6, 1));
        symbols.remove(board.cell(6, 6));
        */

        /*-------------------------------------8<-------------------------------------*/

        final List<MoveTypeInterface> legalMoves = ReversiReferee.from().computePlayableMoves(board, Side.from(1));

        /*-------------------------------------8<-------------------------------------*/

        final Map<PositionInterface, PieceInterface> potentials = Maps.newHashMap();

        /*-------------------------------------8<-------------------------------------*/

        System.out.println("\nLegal moves for " + Side.from(1) + " : ");

        /*-------------------------------------8<-------------------------------------*/

        for (final MoveTypeInterface moveType : legalMoves) {
            final PositionInterface position = ((ReversiMoveTypeInterface) moveType.value()).position();
            potentials.put(position, board.cell(position).value().apply(PieceType.from(ReversiPawn.class)));
        }
        boardRenderer.render(board.apply(BoardMutation.from(potentials)), symbols);

        /*-------------------------------------8<-------------------------------------*/

        System.out.println();

        /*-------------------------------------8<-------------------------------------*/

        for (final MoveTypeInterface moveType : legalMoves) {
            System.out.println(moveType);
            final BoardMutationInterface boardMutation = ((ReversiMoveTypeInterface) moveType.value()).computeBoardMutation(Side.from(1), board);
            //Move.from(moveType, boardMutation)
            System.out.println(boardMutation);
            boardRenderer.render(board.apply(boardMutation), symbols);
            System.out.println();
        }

        /*-------------------------------------8<-------------------------------------*/

        System.out.println(ReversiReferee.from().isPlayable(board, Side.from(1)));
        System.out.println(ReversiReferee.from().isPlayable(board, Side.from(-1)));
        System.out.println(ReversiReferee.from().isGamePlayOver(board, Side.from(1)));

        /*-------------------------------------8<-------------------------------------*/

    }
}