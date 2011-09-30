
package fr.designpattern.zerosumgames.abstractions.immutable.util;

import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.Board;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.BoardCell;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.Piece;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.side.Side;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.type.PieceType;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.Position;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.direction.Direction;
import fr.designpattern.zerosumgames.abstractions.immutable.move.Move;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutation;

public class CachingFactoriesUsage {

    public static void show() {
        System.out.println("Direction.Factory     : " + Direction.Factory.cacheHits() + " / " + Direction.Factory.size());
        System.out.println("Side.Factory          : " + Side.Factory.cacheHits() + " / " + Side.Factory.size());
        System.out.println("PieceType.Factory     : " + PieceType.Factory.cacheHits() + " / " + PieceType.Factory.size());
        System.out.println("Piece.Factory         : " + Piece.Factory.cacheHits() + " / " + Piece.Factory.size());
        System.out.println("Position.Factory      : " + Position.Factory.cacheHits() + " / " + Position.Factory.size());
        System.out.println("BoardCell.Factory     : " + BoardCell.Factory.cacheHits() + " / " + BoardCell.Factory.size());
        System.out.println();
        System.out.println("BoardMutation instances : " + BoardMutation.instances);
        System.out.println();
        System.out.println("MoveType.Factory        : " + Move.Factory.cacheHits() + " / " + Move.Factory.size());
        System.out.println("MoveType instances      : " + Move.instances);
        System.out.println();
        System.out.println("Board instances         : " + Board.instances);
    }

}
