
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardDimensions;

public class PositionFactoryTest {

    private BoardPositionFactoryInterface positions;

    @Before
    public void setUp() {
        this.positions = new BoardPositionFactory(new BoardDimensions(1, 3, 1, 3));
    }

    @Test
    public void testPosition() {

        Assert.assertEquals(this.positions.position(1, 1), new BoardPosition(1, 1));
        Assert.assertEquals(this.positions.position(1, 2), new BoardPosition(1, 2));
        Assert.assertEquals(this.positions.position(1, 3), new BoardPosition(1, 3));

        Assert.assertEquals(this.positions.position(2, 1), new BoardPosition(2, 1));
        Assert.assertEquals(this.positions.position(2, 2), new BoardPosition(2, 2));
        Assert.assertEquals(this.positions.position(2, 3), new BoardPosition(2, 3));

        Assert.assertEquals(this.positions.position(3, 1), new BoardPosition(3, 1));
        Assert.assertEquals(this.positions.position(3, 2), new BoardPosition(3, 2));
        Assert.assertEquals(this.positions.position(3, 3), new BoardPosition(3, 3));

        Assert.assertEquals(this.positions.position(4, 3), new NullBoardPosition());

    }

    @Test
    public void testPositionsNeighbours() {

        Assert.assertEquals(this.positions.topOf(this.positions.position(2, 2)), this.positions.position(1, 2));
        Assert.assertEquals(this.positions.bottomOf(this.positions.position(2, 2)), this.positions.position(3, 2));

        Assert.assertEquals(this.positions.leftOf(this.positions.position(2, 2)), this.positions.position(2, 1));
        Assert.assertEquals(this.positions.rightOf(this.positions.position(2, 2)), this.positions.position(2, 3));

        Assert.assertEquals(this.positions.topLeftOf(this.positions.position(2, 2)), this.positions.position(1, 1));
        Assert.assertEquals(this.positions.topRightOf(this.positions.position(2, 2)), this.positions.position(1, 3));

        Assert.assertEquals(this.positions.bottomLeftOf(this.positions.position(2, 2)), this.positions.position(3, 1));
        Assert.assertEquals(this.positions.bottomRightOf(this.positions.position(2, 2)), this.positions.position(3, 3));

        Assert.assertTrue(this.positions.topOf(this.positions.position(1, 2)).equals(new NullBoardPosition()));
        Assert.assertTrue(this.positions.bottomOf(this.positions.position(3, 2)).equals(this.positions.position(4, 2)));

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetPositions() {

        final List<BoardPositionInterface> allPositions = this.positions.computePositions();
        Assert.assertTrue(allPositions.size() == 9);

        Assert.assertSame(allPositions.get(0), this.positions.position(1, 1));
        Assert.assertSame(allPositions.get(1), this.positions.position(1, 2));
        Assert.assertSame(allPositions.get(2), this.positions.position(1, 3));

        Assert.assertSame(allPositions.get(3), this.positions.position(2, 1));
        Assert.assertSame(allPositions.get(4), this.positions.position(2, 2));
        Assert.assertSame(allPositions.get(5), this.positions.position(2, 3));

        Assert.assertSame(allPositions.get(6), this.positions.position(3, 1));
        Assert.assertSame(allPositions.get(7), this.positions.position(3, 2));
        Assert.assertSame(allPositions.get(8), this.positions.position(3, 3));

        allPositions.clear();
    }

    @After
    public void tearDown() {
        this.positions = null;
    }

}