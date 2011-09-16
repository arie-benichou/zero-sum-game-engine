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

import abstractions.immutable.context.gameplay.GamePlay;
import abstractions.immutable.context.gameplay.GamePlayInterface;
import abstractions.immutable.context.gameplay.game.Game;
import abstractions.immutable.context.gameplay.game.GameInterface;
import abstractions.immutable.context.gameplay.game.board.Board;
import abstractions.immutable.context.gameplay.game.board.BoardInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.Piece;
import abstractions.immutable.context.gameplay.game.board.cell.piece.PieceInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.Side;
import abstractions.immutable.context.gameplay.game.board.cell.piece.type.PieceType;
import abstractions.immutable.context.gameplay.game.board.cell.position.Position;
import abstractions.immutable.context.gameplay.game.board.cell.position.PositionInterface;
import abstractions.immutable.move.Move;
import abstractions.immutable.move.MoveInterface;
import abstractions.immutable.move.mutation.BoardMutation;
import abstractions.immutable.move.type.MoveTypeInterface;
import abstractions.immutable.rendering.board.BoardConsoleRendering;
import abstractions.immutable.rendering.board.BoardRenderer;
import abstractions.immutable.rendering.board.BoardRenderingInterface;
import abstractions.immutable.rendering.board.BoardStringRendering;
import abstractions.immutable.rendering.board.cell.BoardCellStringRendering;
import abstractions.immutable.rendering.board.cell.piece.PieceStringRendering;

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

        /*-------------------------------------8<-------------------------------------*/

        final Map<Object, Object> symbols = Maps.newHashMap();
        symbols.put(black, " x ");
        symbols.put(white, " o ");
        symbols.put(none, "   ");

        /*-------------------------------------8<-------------------------------------*/

        final Map<PositionInterface, PieceInterface> map = Maps.newHashMap();
        map.put(Position.from(3, 3), black);
        map.put(Position.from(3, 4), white);
        map.put(Position.from(4, 3), white);
        map.put(Position.from(4, 4), black);

        /*-------------------------------------8<-------------------------------------*/

        final BoardInterface board = Board.from(6, 6, none).apply(BoardMutation.from(map));
        final GameInterface newGame = Game.from(board, ReversiReferee.from());
        GamePlayInterface gamePlay = GamePlay.from(Side.from(1), null, newGame); // TODO Adversity

        /*-------------------------------------8<-------------------------------------*/

        final BoardRenderingInterface<?> renderingType =
                new BoardConsoleRendering(
                        new BoardStringRendering(
                                new BoardCellStringRendering(
                                        new PieceStringRendering())));
        final BoardRenderer boardRenderer = new BoardRenderer(renderingType); // TODO ? créer une BoardRendererInterface

        /*-------------------------------------8<-------------------------------------*/

        symbols.put(board.cell(1, 1), "1,1");
        symbols.put(board.cell(1, 6), "1,6");
        symbols.put(board.cell(6, 1), "6,1");
        symbols.put(board.cell(6, 6), "6,6");
        boardRenderer.render(board, symbols);

        /*-------------------------------------8<-------------------------------------*/

        final PieceInterface potential = Piece.from(Side.NULL, PieceType.from(ReversiPawn.class));
        symbols.put(potential, " * ");

        /*-------------------------------------8<-------------------------------------*/
        for (int i = 1; i <= 2; ++i) {
            /*-------------------------------------8<-------------------------------------*/
            System.out.println();
            /*-------------------------------------8<-------------------------------------*/
            final List<MoveTypeInterface> legalMovesTypes = gamePlay.computePlayableMoves();
            /*-------------------------------------8<-------------------------------------*/
            // TODO à mettre dans Human Selector            
            final Map<PositionInterface, PieceInterface> potentials = Maps.newHashMap();
            for (final MoveTypeInterface moveType : legalMovesTypes) {
                final PositionInterface position = ((ReversiMoveTypeInterface) moveType.value()).position();
                potentials.put(position, board.cell(position).value().apply(PieceType.from(ReversiPawn.class)));
            }
            System.out.println("\nLegal moves for " + gamePlay.sideToPlay() + " : ");
            boardRenderer.render(board.apply(BoardMutation.from(potentials)), symbols);
            System.out.println();
            /*-------------------------------------8<-------------------------------------*/
            for (final MoveTypeInterface legalMoveType : legalMovesTypes) {
                final MoveInterface move = Move.from(legalMoveType, legalMoveType.value().computeBoardMutation(gamePlay.sideToPlay(), gamePlay.board()));
                // TODO faire un rendering pour GamePlay et Game                
                boardRenderer.render(gamePlay.apply(move).board(), symbols);
            }
            /*-------------------------------------8<-------------------------------------*/
            gamePlay = gamePlay.apply(gamePlay.sideToPlay().opposite());
        }
        /*-------------------------------------8<-------------------------------------*/

    }
}