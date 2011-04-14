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

package abstractions.direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.DimensionFactory;
import abstractions.dimension.DimensionManagerInterface;
import abstractions.direction.DirectionManager.NamedDirection;

public class DirectionManagerTest {

    private DirectionManagerInterface directionManager;
    private DimensionManagerInterface dimension;

    @Before
    public void setUp() throws Exception {
        final Random random = new Random();
        this.dimension = DimensionFactory.Dimension(random.nextInt(99), random.nextInt(99));
        this.directionManager = new DirectionManager(this.dimension);
    }

    @Test(expected = IllegalDirectionException.class)
    public void testGetIllegalDirection1() {
        new DirectionManager(DimensionFactory.Dimension(1, 2)).getDirection(-1, 0);
    }

    @Test(expected = IllegalDirectionException.class)
    public void testGetIlegalDirection2() {
        new DirectionManager(DimensionFactory.Dimension(1, 2)).getDirection(1, 0);
    }

    @Test(expected = IllegalDirectionException.class)
    public void testGetIlegalDirection3() {
        new DirectionManager(DimensionFactory.Dimension(2, 1)).getDirection(0, 1);
    }

    @Test(expected = IllegalDirectionException.class)
    public void testGetIllegalDirection4() {
        new DirectionManager(DimensionFactory.Dimension(2, 1)).getDirection(0, -1);
    }

    @Test
    public void testGetDirection() {
        DirectionInterface direction;
        final int maxRowDelta = this.dimension.upperBoundForRows() - 1;
        final int maxColumnDelta = this.dimension.upperBoundForColumns() - 1;
        for (int rowDelta = -maxRowDelta; rowDelta <= maxRowDelta; ++rowDelta) {
            for (int columnDelta = -maxColumnDelta; columnDelta <= maxColumnDelta; ++columnDelta) {
                direction = this.directionManager.getDirection(rowDelta, columnDelta);
                Assert.assertTrue(direction.getRowDelta() == rowDelta);
                Assert.assertTrue(direction.getColumnDelta() == columnDelta);
            }
        }
    }

    @Test
    public void testGetNamedDirection() {
        Assert.assertTrue(this.directionManager.getNamedDirection(NamedDirection.TOP) == this.directionManager.getDirection(-1, 0));
        Assert.assertTrue(this.directionManager.getNamedDirection(NamedDirection.TOP_RIGHT) == this.directionManager.getDirection(-1, 1));
        Assert.assertTrue(this.directionManager.getNamedDirection(NamedDirection.RIGHT) == this.directionManager.getDirection(0, 1));
        Assert.assertTrue(this.directionManager.getNamedDirection(NamedDirection.BOTTOM_RIGHT) == this.directionManager.getDirection(1, 1));
        Assert.assertTrue(this.directionManager.getNamedDirection(NamedDirection.BOTTOM) == this.directionManager.getDirection(1, 0));
        Assert.assertTrue(this.directionManager.getNamedDirection(NamedDirection.BOTTOM_LEFT) == this.directionManager.getDirection(1, -1));
        Assert.assertTrue(this.directionManager.getNamedDirection(NamedDirection.LEFT) == this.directionManager.getDirection(0, -1));
        Assert.assertTrue(this.directionManager.getNamedDirection(NamedDirection.TOP_LEFT) == this.directionManager.getDirection(-1, -1));
    }

    @Test
    public void getNamedDirections() {
        final List<NamedDirection> expectedNamedDirections = new ArrayList<NamedDirection>();
        expectedNamedDirections.add(NamedDirection.TOP);
        expectedNamedDirections.add(NamedDirection.TOP_RIGHT);
        expectedNamedDirections.add(NamedDirection.RIGHT);
        expectedNamedDirections.add(NamedDirection.BOTTOM_RIGHT);
        expectedNamedDirections.add(NamedDirection.BOTTOM);
        expectedNamedDirections.add(NamedDirection.BOTTOM_LEFT);
        expectedNamedDirections.add(NamedDirection.LEFT);
        expectedNamedDirections.add(NamedDirection.TOP_LEFT);
        Assert.assertTrue(expectedNamedDirections.equals(this.directionManager.getNamedDirections()));
    }

    @Test
    public void testReduce() {
        Assert.assertTrue(this.directionManager.reduce(this.directionManager.getNamedDirections()) == this.directionManager.getDirection(0, 0));
        final List<DirectionInterface> directions = new ArrayList<DirectionInterface>();
        directions.add(this.directionManager.getDirection(0, 0));
        Assert.assertTrue(this.directionManager.reduce(directions) == this.directionManager.getDirection(0, 0));
    }

    @After
    public void tearDown() throws Exception {
        this.directionManager = null;
    }

}
