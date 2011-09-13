
package concretisations.reversi.referees;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.immutable.context.board.Board;
import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.Piece;
import abstractions.immutable.context.board.cell.piece.PieceInterface;
import abstractions.immutable.context.board.cell.piece.side.Side;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.board.cell.piece.type.PieceType;
import abstractions.immutable.context.board.cell.position.Position;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.context.referee.RefereeInterface;
import abstractions.immutable.move.mutation.BoardMutation;
import abstractions.immutable.move.type.MoveType;
import abstractions.immutable.move.type.MoveTypeInterface;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import concretisations.reversi.moves.ReversiMove;
import concretisations.reversi.moves.ReversiNullMove;
import concretisations.reversi.pieces.ReversiNullPiece;
import concretisations.reversi.pieces.ReversiPawn;

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
        this.blackPiece = Piece.from(Side.from(1), PieceType.from(ReversiPawn.class));
        this.whitePiece = this.blackPiece.apply(this.blackPiece.side().opposite());
        this.nullPiece = this.blackPiece.apply(Side.NULL, PieceType.from(ReversiNullPiece.class));
        /*-------------------------------------8<-------------------------------------*/
        // TODO l'arbitre devrait prendre en argument uniquement un objet GamePlay        
        this.board = Board.from(6, 6, this.nullPiece);
        this.side = Side.from(1);
        /*-------------------------------------8<-------------------------------------*/
        this.referee = ReversiReferee.from();
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

        final List<MoveTypeInterface> expectedLegalMoves = Lists.newArrayList(MoveType.from(ReversiNullMove.class));
        Assert.assertTrue(this.referee.computePlayableMoves(this.board, this.side).equals(expectedLegalMoves));
        Assert.assertTrue(this.referee.computePlayableMoves(this.board, this.side.opposite()).equals(expectedLegalMoves));

        /*-------------------------------------8<-------------------------------------*/
        // simplest board having move
        /*-------------------------------------8<-------------------------------------*/

        final Map<PositionInterface, PieceInterface> map = Maps.newHashMap();
        map.put(Position.from(3, 3), this.blackPiece);
        map.put(Position.from(3, 4), this.whitePiece);

        final List<MoveTypeInterface> expectedLegalMovesForOneSide = Lists.newArrayList();
        expectedLegalMovesForOneSide.add(MoveType.from(ReversiMove.from(Position.from(3, 5))));
        expectedLegalMovesForOneSide.add(MoveType.from(ReversiNullMove.class));

        final List<MoveTypeInterface> legalMovesForOneSide = this.referee.computePlayableMoves(this.board.apply(BoardMutation.from(map)), this.side);
        Assert.assertTrue(legalMovesForOneSide.equals(expectedLegalMovesForOneSide));

        final List<MoveTypeInterface> expectedLegalMovesForOppositeSide = Lists.newArrayList();
        expectedLegalMovesForOppositeSide.add(MoveType.from(ReversiMove.from(Position.from(3, 2))));
        expectedLegalMovesForOppositeSide.add(MoveType.from(ReversiNullMove.class));

        final List<MoveTypeInterface> legalMovesForOppositeSide = this.referee.computePlayableMoves(this.board.apply(BoardMutation.from(map)),
                this.side.opposite());
        Assert.assertTrue(legalMovesForOppositeSide.equals(expectedLegalMovesForOppositeSide));

        Assert.assertTrue(!legalMovesForOneSide.equals(legalMovesForOppositeSide));

        /*-------------------------------------8<-------------------------------------*/
        // board not full, but no move        
        /*-------------------------------------8<-------------------------------------*/

        map.clear();
        map.put(Position.from(3, 3), this.blackPiece);
        map.put(Position.from(3, 4), this.blackPiece);
        Assert.assertTrue(this.referee.computePlayableMoves(this.board.apply(BoardMutation.from(map)), this.side).equals(expectedLegalMoves));
        Assert.assertTrue(this.referee.computePlayableMoves(this.board.apply(BoardMutation.from(map)), this.side.opposite()).equals(expectedLegalMoves));

        /*-------------------------------------8<-------------------------------------*/
        // full board        
        /*-------------------------------------8<-------------------------------------*/

        Assert.assertTrue(this.referee.computePlayableMoves(Board.from(6, 6, this.blackPiece), this.side).equals(expectedLegalMoves));
        Assert.assertTrue(this.referee.computePlayableMoves(Board.from(6, 6, this.blackPiece), this.side.opposite()).equals(expectedLegalMoves));

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
        /*-------------------------------------8<-------------------------------------*/

        Assert.assertTrue(this.referee.isGamePlayOver(this.board, this.side));
        Assert.assertTrue(this.referee.isGamePlayOver(this.board, this.side.opposite()));

        /*-------------------------------------8<-------------------------------------*/
        // initial reversi board        
        /*-------------------------------------8<-------------------------------------*/

        final Map<PositionInterface, PieceInterface> map = Maps.newHashMap();
        map.put(Position.from(3, 3), this.blackPiece);
        map.put(Position.from(3, 4), this.whitePiece);
        map.put(Position.from(4, 3), this.whitePiece);
        map.put(Position.from(4, 4), this.blackPiece);

        Assert.assertTrue(!this.referee.isGamePlayOver(this.board.apply(BoardMutation.from(map)), this.side));
        Assert.assertTrue(!this.referee.isGamePlayOver(this.board.apply(BoardMutation.from(map)), this.side.opposite()));

        /*-------------------------------------8<-------------------------------------*/
        // board not full, but no move        
        /*-------------------------------------8<-------------------------------------*/

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
        /*-------------------------------------8<-------------------------------------*/

        Assert.assertTrue(this.referee.isGamePlayOver(Board.from(6, 6, this.blackPiece), this.side));
        Assert.assertTrue(this.referee.isGamePlayOver(Board.from(6, 6, this.blackPiece), this.side.opposite()));
        Assert.assertTrue(this.referee.isGamePlayOver(Board.from(6, 6, this.whitePiece), this.side));
        Assert.assertTrue(this.referee.isGamePlayOver(Board.from(6, 6, this.whitePiece), this.side.opposite()));

        /*-------------------------------------8<-------------------------------------*/

    }

}
