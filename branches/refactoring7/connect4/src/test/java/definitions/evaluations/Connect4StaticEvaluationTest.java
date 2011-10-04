
package definitions.evaluations;

import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.google.common.collect.Maps;

import context.Context;
import context.ContextInterface;
import context.entity.game.board.Board;
import context.entity.game.board.cell.piece.Piece;
import context.entity.game.board.cell.piece.PieceInterface;
import context.entity.game.board.cell.piece.type.PieceType;
import context.entity.game.board.cell.position.Position;
import context.entity.game.board.cell.position.PositionInterface;
import context.entity.game.board.mutation.BoardMutation;
import definitions.pieces.Connect4Pawn;

public class Connect4StaticEvaluationTest {

    private ContextInterface context;

    @Before
    public void setUp() throws Exception {
        final BeanFactory factory = new XmlBeanFactory(new ClassPathResource("Connect4.xml"));
        this.context = (Context) factory.getBean("context");
    }

    @After
    public void tearDown() throws Exception {
        this.context = null;
    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    */
    public void testEvaluateEmptyBoard() {
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));
    }

    @Test
    /*
    -----------------------------
    | . | . | . | . | . | . | . |
    -----------------------------
    | . | . | . | . | . | . | . |
    -----------------------------
    | . | . | . | . | . | . | . |
    -----------------------------
    | . | . | . | . | . | . | . |
    -----------------------------
    | . | . | . | . | . | . | . |
    -----------------------------
    | . | . | . | . | . | . | . |
    -----------------------------
    */
    public void testEvaluateFullBoard() {

        this.context = this.context.apply(this.context.game().apply(
                Board.from(6, 7, Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(1.0));

        this.context = this.context.apply(this.context.game().apply(
                Board.from(6, 7, Piece.from(this.context.side().opposite(), PieceType.from(Connect4Pawn.class)))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));
    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnEqualTo1() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(1.0 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(1.0 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   | . | . |   |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnEqualTo2_1() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(2.0 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(2.0 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnEqualTo2_2() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(2.0 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(2.0 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   | . |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnEqualTo2_3() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 5), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(2.0 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 5), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(2.0 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   | . |   |   |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnEqualTo2_4() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(2.0 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(2.0 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   | . | . | . |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnEqualTo3_1() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(6, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 5), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(3.0 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(6, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 5), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(3.0 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnEqualTo3_2() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(4, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(3.0 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(4, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(3.0 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   | . |   |
    -----------------------------
    |   |   |   |   | . |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnEqualTo3_3() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(4, 6), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 5), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(3.0 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(4, 6), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 5), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(3.0 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   | . |   |   |   |   |   |
    -----------------------------
    |   |   | . |   |   |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnEqualTo3_4() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(4, 2), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(3.0 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(4, 2), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(3.0 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   | . | . | . | . |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnEqualTo4_1() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(6, 2), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 5), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(4.0 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(6, 2), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 5), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(4.0 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnEqualTo4_2() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(3, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(4, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(4.0 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(3, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(4, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(4.0 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   | . |
    -----------------------------
    |   |   |   |   |   | . |   |
    -----------------------------
    |   |   |   |   | . |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnEqualTo4_3() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(3, 7), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(4, 6), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 5), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(4.0 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(3, 7), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(4, 6), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 5), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(4.0 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    | . |   |   |   |   |   |   |
    -----------------------------
    |   | . |   |   |   |   |   |
    -----------------------------
    |   |   | . |   |   |   |   |
    -----------------------------
    |   |   |   | . |   |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnEqualTo4_4() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(3, 1), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(4, 2), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(4.0 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(3, 1), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(4, 2), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(5, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(4.0 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    | . |   | . |   |   |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnBeetween1And2_1() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(6, 1), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(1.75 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(6, 1), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(1.75 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   | . |   | . |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnBeetween1And2_2() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(6, 1), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(1.75 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(6, 1), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(1.75 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    | . |   |   | . |   |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnBeetween1And2_3() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(6, 1), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(1.5 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(6, 1), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(1.5 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    | . |   | . | . |   |   |   |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnBeetween1And3_1() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(6, 1), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(2.75 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(6, 1), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 3), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(2.75 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   | . | . |   | . |
    -----------------------------
    */
    public void testEvaluateBoardWithBestAlignmentOfPawnBeetween1And3_2() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 6), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 7), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(2.75 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(0.0));

        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 6), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 7), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(2.75 / 4));

    }

    @Test
    /*
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   |   |   |   |   |   |
    -----------------------------
    |   |   | o | x | x |   | o |
    -----------------------------
    */
    public void testEvaluateBoard() {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        mutations.put(Position.from(6, 3), Piece.from(this.context.side().opposite(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 5), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 7), Piece.from(this.context.side().opposite(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(1.0 / 4));

        this.context = this.context.apply(this.context.side().opposite());
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(1.0 / 4));

        mutations.put(Position.from(6, 3), Piece.from(this.context.side().opposite(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 4), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 5), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
        mutations.put(Position.from(6, 7), Piece.from(this.context.side().opposite(), PieceType.from(Connect4Pawn.class)));

        this.context = this.context.apply(this.context.game().apply(this.context.game().board().apply(BoardMutation.from(mutations))));
        Assert.assertTrue(Connect4StaticEvaluation.from(this.context).equals(1.0 / 4));

    }

}
