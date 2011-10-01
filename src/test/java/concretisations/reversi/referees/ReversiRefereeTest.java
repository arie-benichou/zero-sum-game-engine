
package concretisations.reversi.referees;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fr.designpattern.zerosumgames.abstractions.context.game.board.Board;
import fr.designpattern.zerosumgames.abstractions.context.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.Piece;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.PieceInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.side.Side;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.type.PieceType;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.position.Position;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.referee.RefereeInterface;
import fr.designpattern.zerosumgames.abstractions.move.Move;
import fr.designpattern.zerosumgames.abstractions.move.MoveInterface;
import fr.designpattern.zerosumgames.abstractions.move.mutation.BoardMutation;
import fr.designpattern.zerosumgames.concretisations.othello.moves.OthelloMove;
import fr.designpattern.zerosumgames.concretisations.othello.moves.OthelloNullMove;
import fr.designpattern.zerosumgames.concretisations.othello.pieces.OthelloNullPiece;
import fr.designpattern.zerosumgames.concretisations.othello.pieces.OthelloPawn;
import fr.designpattern.zerosumgames.concretisations.othello.referees.OthelloReferee;

public class ReversiRefereeTest {

    private BoardInterface board;
    private SideInterface side;
    private PieceInterface blackPiece;
    private PieceInterface whitePiece;
    private PieceInterface nullPiece;
    private RefereeInterface referee;

    @Before
    public void setUp() throws Exception {
        /*-------------------------------------8<-------------------------------------*/
        this.blackPiece = Piece.from(Side.from(1), PieceType.from(OthelloPawn.class));
        this.whitePiece = this.blackPiece.apply(this.blackPiece.side().opposite());
        this.nullPiece = this.blackPiece.apply(Side.NULL, PieceType.from(OthelloNullPiece.class));
        /*-------------------------------------8<-------------------------------------*/
        this.board = Board.from(6, 6, this.nullPiece);
        this.side = Side.from(1);
        /*-------------------------------------8<-------------------------------------*/
        this.referee = OthelloReferee.from();
        /*-------------------------------------8<-------------------------------------*/
    }

    @After
    public void tearDown() throws Exception {
        this.referee = null;
        this.side = null;
        this.board = null;
        this.nullPiece = null;
        this.whitePiece = null;
        this.nullPiece = null;
    }

    @Test
    public final void testComputeLegalMoves() {

        /*-------------------------------------8<-------------------------------------*/
        // empty board
        /*-------------------------------------8<-------------------------------------*/

        final List<MoveInterface> expectedLegalMoves = Lists.newArrayList(Move.from(OthelloNullMove.class));
        Assert.assertTrue(this.referee.playableMoves(this.board, this.side).equals(expectedLegalMoves));
        Assert.assertTrue(this.referee.playableMoves(this.board, this.side.opposite()).equals(expectedLegalMoves));

        /*-------------------------------------8<-------------------------------------*/
        // simplest board having move
        /*-------------------------------------8<-------------------------------------*/

        final Map<PositionInterface, PieceInterface> map = Maps.newHashMap();
        map.put(Position.from(3, 3), this.blackPiece);
        map.put(Position.from(3, 4), this.whitePiece);

        final List<MoveInterface> expectedLegalMovesForOneSide = Lists.newArrayList();
        expectedLegalMovesForOneSide.add(Move.from(OthelloMove.from(Position.from(3, 5))));
        expectedLegalMovesForOneSide.add(Move.from(OthelloNullMove.class));

        final List<MoveInterface> legalMovesForOneSide = this.referee.playableMoves(this.board.apply(BoardMutation.from(map)), this.side);
        Assert.assertTrue(legalMovesForOneSide.equals(expectedLegalMovesForOneSide));

        final List<MoveInterface> expectedLegalMovesForOppositeSide = Lists.newArrayList();
        expectedLegalMovesForOppositeSide.add(Move.from(OthelloMove.from(Position.from(3, 2))));
        expectedLegalMovesForOppositeSide.add(Move.from(OthelloNullMove.class));

        final List<MoveInterface> legalMovesForOppositeSide = this.referee.playableMoves(this.board.apply(BoardMutation.from(map)),
                this.side.opposite());
        Assert.assertTrue(legalMovesForOppositeSide.equals(expectedLegalMovesForOppositeSide));

        Assert.assertTrue(!legalMovesForOneSide.equals(legalMovesForOppositeSide));

        /*-------------------------------------8<-------------------------------------*/
        // board not full, but no move
        /*-------------------------------------8<-------------------------------------*/

        map.clear();
        map.put(Position.from(3, 3), this.blackPiece);
        map.put(Position.from(3, 4), this.blackPiece);
        Assert.assertTrue(this.referee.playableMoves(this.board.apply(BoardMutation.from(map)), this.side).equals(expectedLegalMoves));
        Assert.assertTrue(this.referee.playableMoves(this.board.apply(BoardMutation.from(map)), this.side.opposite()).equals(expectedLegalMoves));

        /*-------------------------------------8<-------------------------------------*/
        // full board
        /*-------------------------------------8<-------------------------------------*/

        Assert.assertTrue(this.referee.playableMoves(Board.from(6, 6, this.blackPiece), this.side).equals(expectedLegalMoves));
        Assert.assertTrue(this.referee.playableMoves(Board.from(6, 6, this.blackPiece), this.side.opposite()).equals(expectedLegalMoves));

        /*-------------------------------------8<-------------------------------------*/

    }

    @Test
    public final void testHasLegalMove() {

        /*-------------------------------------8<-------------------------------------*/
        // empty board
        /*-------------------------------------8<-------------------------------------*/

        Assert.assertTrue(!this.referee.isPlayable(this.board, this.side));
        Assert.assertTrue(!this.referee.isPlayable(this.board, this.side.opposite()));

        /*-------------------------------------8<-------------------------------------*/
        // initial reversi board
        /*-------------------------------------8<-------------------------------------*/

        final Map<PositionInterface, PieceInterface> map = Maps.newHashMap();
        map.put(Position.from(3, 3), this.blackPiece);
        map.put(Position.from(3, 4), this.whitePiece);
        map.put(Position.from(4, 3), this.whitePiece);
        map.put(Position.from(4, 4), this.blackPiece);

        Assert.assertTrue(this.referee.isPlayable(this.board.apply(BoardMutation.from(map)), this.side));
        Assert.assertTrue(this.referee.isPlayable(this.board.apply(BoardMutation.from(map)), this.side.opposite()));

        /*-------------------------------------8<-------------------------------------*/
        // board not full, but no move
        /*-------------------------------------8<-------------------------------------*/

        map.clear();
        map.put(Position.from(3, 3), this.blackPiece);
        map.put(Position.from(3, 4), this.blackPiece);
        map.put(Position.from(4, 3), this.blackPiece);
        map.put(Position.from(4, 4), this.blackPiece);

        Assert.assertTrue(!this.referee.isPlayable(this.board.apply(BoardMutation.from(map)), this.side));
        Assert.assertTrue(!this.referee.isPlayable(this.board.apply(BoardMutation.from(map)), this.side.opposite()));

        map.clear();
        map.put(Position.from(3, 3), this.whitePiece);
        map.put(Position.from(3, 4), this.whitePiece);
        map.put(Position.from(4, 3), this.whitePiece);
        map.put(Position.from(4, 4), this.whitePiece);

        Assert.assertTrue(!this.referee.isPlayable(this.board.apply(BoardMutation.from(map)), this.side));
        Assert.assertTrue(!this.referee.isPlayable(this.board.apply(BoardMutation.from(map)), this.side.opposite()));

        /*-------------------------------------8<-------------------------------------*/
        // full board
        /*-------------------------------------8<-------------------------------------*/

        Assert.assertTrue(!this.referee.isPlayable(Board.from(6, 6, this.blackPiece), this.side));
        Assert.assertTrue(!this.referee.isPlayable(Board.from(6, 6, this.blackPiece), this.side.opposite()));
        Assert.assertTrue(!this.referee.isPlayable(Board.from(6, 6, this.whitePiece), this.side));
        Assert.assertTrue(!this.referee.isPlayable(Board.from(6, 6, this.whitePiece), this.side.opposite()));

        /*-------------------------------------8<-------------------------------------*/

    }

    @Test
    public final void testIsGamePlayOver() {

        /*-------------------------------------8<-------------------------------------*/
        // empty board
        /*-------------------------------------8<-------------------------------------*

        Assert.assertTrue(this.referee.isGamePlayOver(this.board, this.side));
        Assert.assertTrue(this.referee.isGamePlayOver(this.board, this.side.opposite()));

        /*-------------------------------------8<-------------------------------------*/
        // initial reversi board
        /*-------------------------------------8<-------------------------------------*

        final Map<PositionInterface, PieceInterface> map = Maps.newHashMap();
        map.put(Position.from(3, 3), this.blackPiece);
        map.put(Position.from(3, 4), this.whitePiece);
        map.put(Position.from(4, 3), this.whitePiece);
        map.put(Position.from(4, 4), this.blackPiece);

        Assert.assertTrue(!this.referee.isGamePlayOver(this.board.apply(BoardMutation.from(map)), this.side));
        Assert.assertTrue(!this.referee.isGamePlayOver(this.board.apply(BoardMutation.from(map)), this.side.opposite()));

        /*-------------------------------------8<-------------------------------------*/
        // board not full, but no move
        /*-------------------------------------8<-------------------------------------*

        map.clear();
        map.put(Position.from(3, 3), this.blackPiece);
        map.put(Position.from(3, 4), this.blackPiece);
        map.put(Position.from(4, 3), this.blackPiece);
        map.put(Position.from(4, 4), this.blackPiece);

        Assert.assertTrue(this.referee.isGamePlayOver(this.board.apply(BoardMutation.from(map)), this.side));
        Assert.assertTrue(this.referee.isGamePlayOver(this.board.apply(BoardMutation.from(map)), this.side.opposite()));

        map.clear();
        map.put(Position.from(3, 3), this.whitePiece);
        map.put(Position.from(3, 4), this.whitePiece);
        map.put(Position.from(4, 3), this.whitePiece);
        map.put(Position.from(4, 4), this.whitePiece);

        Assert.assertTrue(this.referee.isGamePlayOver(this.board.apply(BoardMutation.from(map)), this.side));
        Assert.assertTrue(this.referee.isGamePlayOver(this.board.apply(BoardMutation.from(map)), this.side.opposite()));

        /*-------------------------------------8<-------------------------------------*/
        // full board
        /*-------------------------------------8<-------------------------------------*

        Assert.assertTrue(this.referee.isGamePlayOver(Board.from(6, 6, this.blackPiece), this.side));
        Assert.assertTrue(this.referee.isGamePlayOver(Board.from(6, 6, this.blackPiece), this.side.opposite()));
        Assert.assertTrue(this.referee.isGamePlayOver(Board.from(6, 6, this.whitePiece), this.side));
        Assert.assertTrue(this.referee.isGamePlayOver(Board.from(6, 6, this.whitePiece), this.side.opposite()));

        /*-------------------------------------8<-------------------------------------*/

    }

}
