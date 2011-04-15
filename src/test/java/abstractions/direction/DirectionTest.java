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

import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.direction.DirectionManager.NamedDirection;

public final class DirectionTest {

    private DirectionInterface direction;

    @Before
    public void setUp() throws Exception {
        this.direction = new Direction(-1, 1);
    }

    @Test
    public void testGetRowDelta() {
        Assert.assertTrue(this.direction.getRowDelta() == -1);
    }

    @Test
    public void testGetColumnDelta() {
        Assert.assertTrue(this.direction.getColumnDelta() == 1);
    }

    @Test
    public void testEqualsObject() {
        Assert.assertFalse(this.direction.equals(null)); // NOPMD
        Assert.assertTrue(this.direction.equals(this.direction));
        Assert.assertTrue(this.direction.equals(new Direction(-1, 1)));
        Assert.assertFalse(this.direction.equals(new Direction(1, -1)));
        Assert.assertFalse(this.direction.equals(new Random()));
        Assert.assertTrue(this.direction.equals(NamedDirection.TOP_RIGHT));
    }

    @After
    public void tearDown() throws Exception {
        this.direction = null; // NOPMD
    }

}
