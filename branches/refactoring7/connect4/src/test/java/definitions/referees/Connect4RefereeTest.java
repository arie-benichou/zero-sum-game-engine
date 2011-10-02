
package definitions.referees;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.google.common.collect.Lists;

import context.Context;
import context.ContextInterface;
import context.entity.game.board.Board;
import context.entity.game.board.BoardInterface;
import context.entity.game.board.cell.piece.Piece;
import context.entity.game.board.cell.piece.side.Side;
import context.entity.game.board.cell.piece.type.PieceType;
import context.entity.game.board.cell.position.Position;
import context.entity.game.referee.RefereeInterface;
import context.event.Move;
import context.event.MoveInterface;
import definitions.moves.Connect4Move;
import definitions.pieces.Connect4Pawn;

public class Connect4RefereeTest {

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
    public void testFrom() {
        Assert.assertTrue(Connect4Referee.from() instanceof RefereeInterface);
        Assert.assertTrue(Connect4Referee.from() instanceof Connect4Referee);
    }

    @Test
    public void testApply() {
        Assert.assertTrue(Connect4Referee.from().apply() == Connect4Referee.from());
    }

    @Test
    public void testAllowedOptionsWithEmptyBoard() {

        final List<MoveInterface> expectedAllowedOptions = Lists.newArrayList();
        expectedAllowedOptions.add(Move.from(Connect4Move.from(this.context, Position.from(6, 1))));
        expectedAllowedOptions.add(Move.from(Connect4Move.from(this.context, Position.from(6, 2))));
        expectedAllowedOptions.add(Move.from(Connect4Move.from(this.context, Position.from(6, 3))));
        expectedAllowedOptions.add(Move.from(Connect4Move.from(this.context, Position.from(6, 4))));
        expectedAllowedOptions.add(Move.from(Connect4Move.from(this.context, Position.from(6, 5))));
        expectedAllowedOptions.add(Move.from(Connect4Move.from(this.context, Position.from(6, 6))));
        expectedAllowedOptions.add(Move.from(Connect4Move.from(this.context, Position.from(6, 7))));
        /*-------------------------------------8<-------------------------------------*/

        final List<MoveInterface> allowedOptions = this.context.options();

        /*-------------------------------------8<-------------------------------------*/

        Assert.assertTrue(allowedOptions.equals(expectedAllowedOptions));
    }

    @Test
    public void testAllowedOptionsWithOneColumnFull() {

        final List<MoveInterface> expectedAllowedOptions = Lists.newArrayList();
        expectedAllowedOptions.add(Move.from(Connect4Move.from(this.context, Position.from(6, 2))));
        expectedAllowedOptions.add(Move.from(Connect4Move.from(this.context, Position.from(6, 3))));
        expectedAllowedOptions.add(Move.from(Connect4Move.from(this.context, Position.from(6, 4))));
        expectedAllowedOptions.add(Move.from(Connect4Move.from(this.context, Position.from(6, 5))));
        expectedAllowedOptions.add(Move.from(Connect4Move.from(this.context, Position.from(6, 6))));
        expectedAllowedOptions.add(Move.from(Connect4Move.from(this.context, Position.from(6, 7))));

        /*-------------------------------------8<-------------------------------------*/

        ContextInterface newContext = this.context;
        for (int row = 1; row <= 7; ++row)
            newContext = newContext.apply(Move.from(Connect4Move.from(this.context, Position.from(row, 1))));
        final List<MoveInterface> allowedOptions = newContext.options();

        /*-------------------------------------8<-------------------------------------*/

        Assert.assertTrue(allowedOptions.equals(expectedAllowedOptions));
    }

    @Test
    public void testAllowedOptionsWithFullBoard() {

        final List<MoveInterface> expectedAllowedOptions = Lists.newArrayList();

        /*-------------------------------------8<-------------------------------------*/
        final BoardInterface fullBoard = Board.from(6, 7, Piece.from(Side.from(1), PieceType.from(Connect4Pawn.class)));
        final ContextInterface newContext = this.context.apply(this.context.game().apply(fullBoard));
        final List<MoveInterface> allowedOptions = newContext.options();

        /*-------------------------------------8<-------------------------------------*/

        Assert.assertTrue(allowedOptions.equals(expectedAllowedOptions));
    }

    @Test
    public void testIsOverWhenBoardIsFull() {
        final BoardInterface fullBoard = Board.from(6, 7, Piece.from(Side.from(1), PieceType.from(Connect4Pawn.class)));
        final ContextInterface newContext = this.context.apply(this.context.game().apply(fullBoard));
        Assert.assertTrue(newContext.isOver());
    }

    @Test
    public void testIsOverWhenFourPawnsAreConnected() {

        /*-------------------------------------8<-------------------------------------*/

        ContextInterface newContext1 = this.context;
        for (int row = 1; row <= 4; ++row) {
            Assert.assertTrue(!newContext1.isOver());
            newContext1 = newContext1.apply(Move.from(Connect4Move.from(newContext1, Position.from(row, 1))));
        }
        Assert.assertTrue(newContext1.isOver());

        /*-------------------------------------8<-------------------------------------*/

        ContextInterface newContext2 = this.context;
        for (int column = 1; column <= 4; ++column) {
            Assert.assertTrue(!newContext2.isOver());
            newContext2 = newContext2.apply(Move.from(Connect4Move.from(newContext2, Position.from(1, column))));
        }
        Assert.assertTrue(newContext2.isOver());

        /*-------------------------------------8<-------------------------------------*/

        ContextInterface newContext3 = this.context;
        for (int i = 0; i < 4; ++i) {
            Assert.assertTrue(!newContext3.isOver());
            newContext3 = newContext3.apply(Move.from(Connect4Move.from(newContext3, Position.from(1 + i, 1 + i))));
        }
        Assert.assertTrue(newContext3.isOver());

        /*-------------------------------------8<-------------------------------------*/

        ContextInterface newContext4 = this.context;
        for (int i = 0; i < 4; ++i) {
            Assert.assertTrue(!newContext4.isOver());
            newContext4 = newContext4.apply(Move.from(Connect4Move.from(newContext4, Position.from(6 - i, 1 + i))));
        }
        Assert.assertTrue(newContext4.isOver());

        /*-------------------------------------8<-------------------------------------*/

    }

    @Test
    public void testEstimate() {
        ContextInterface newContext1 = this.context;

        for (int i = 0; i < 4; ++i) {
            newContext1 = newContext1.apply(Move.from(Connect4Move.from(newContext1, Position.from(6, 1 + i))));
            newContext1 = newContext1.apply(Move.from(Connect4Move.from(newContext1, Position.from(6, 7 - i))));
        }
        System.out.println(newContext1.estimation());

        final BoardInterface fullBoard = Board.from(6, 7, Piece.from(Side.from(1), PieceType.from(Connect4Pawn.class)));
        ContextInterface newContext = this.context.apply(this.context.game().apply(fullBoard));
        newContext = newContext.apply(Move.from(Connect4Move.from(newContext, Position.from(3, 4))));
        System.out.println(newContext.estimation());
    }

    //@Test
    public void testEvaluate() {
        fail("Not yet implemented");
    }

}
