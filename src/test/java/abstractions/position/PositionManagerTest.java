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

package abstractions.position;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.API.DimensionFactory;
import abstractions.direction.NamedDirection;

public class PositionManagerTest {

    private PositionManager manager;

    @Before
    public void setUp() throws Exception {

        this.manager = new PositionManager(DimensionFactory.Dimension(3, 3));

    }

    @Test
    public final void testGetPosition() {

        Assert.assertTrue(this.manager.getPosition(0, 0) == this.manager.getNullPosition());

        Assert.assertTrue(this.manager.getPosition(1, 1).equals(new Position(1, 1)));
        Assert.assertTrue(this.manager.getPosition(1, 2).equals(new Position(1, 2)));
        Assert.assertTrue(this.manager.getPosition(1, 3).equals(new Position(1, 3)));

        Assert.assertTrue(this.manager.getPosition(2, 1).equals(new Position(2, 1)));
        Assert.assertTrue(this.manager.getPosition(2, 2).equals(new Position(2, 2)));
        Assert.assertTrue(this.manager.getPosition(2, 3).equals(new Position(2, 3)));

        Assert.assertTrue(this.manager.getPosition(3, 1).equals(new Position(3, 1)));
        Assert.assertTrue(this.manager.getPosition(3, 2).equals(new Position(3, 2)));
        Assert.assertTrue(this.manager.getPosition(3, 3).equals(new Position(3, 3)));

        Assert.assertTrue(this.manager.getPosition(4, 1) == this.manager.getNullPosition());

    }

    @Test
    public final void testGetPositionWithOffset1() {

        final PositionInterface position = new Position(1, 1);

        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.TOP) == this.manager.getNullPosition());
        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.BOTTOM).equals(new Position(2, 1)));
        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.LEFT) == this.manager.getNullPosition());
        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.RIGHT).equals(new Position(1, 2)));

        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.TOP_LEFT) == this.manager.getNullPosition());
        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.TOP_RIGHT) == this.manager.getNullPosition());
        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.BOTTOM_LEFT) == this.manager.getNullPosition());
        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.BOTTOM_RIGHT).equals(new Position(2, 2)));

    }

    @Test
    public final void testGetPositionWithOffset2() {

        final PositionInterface position = new Position(2, 2);

        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.TOP).equals(new Position(1, 2)));
        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.BOTTOM).equals(new Position(3, 2)));
        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.LEFT).equals(new Position(2, 1)));
        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.RIGHT).equals(new Position(2, 3)));

        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.TOP_LEFT).equals(new Position(1, 1)));
        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.TOP_RIGHT).equals(new Position(1, 3)));
        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.BOTTOM_LEFT).equals(new Position(3, 1)));
        Assert.assertTrue(this.manager.getPosition(position, NamedDirection.BOTTOM_RIGHT).equals(new Position(3, 3)));

    }

    @After
    public void tearDown() throws Exception {

        this.manager = null;

    }

}
