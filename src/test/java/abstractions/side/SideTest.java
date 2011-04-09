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

package abstractions.side;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SideTest {

    private SideInterface side;

    @Before
    public void setUp() {

        this.side = Side.FIRST_SIDE;

    }

    @Test
    public final void testIsFirstSide() {

        Assert.assertTrue(this.side.isFirstSide());
        Assert.assertFalse(Side.SECOND_SIDE.isFirstSide());
        Assert.assertFalse(Side.NULL_SIDE.isFirstSide());

    }

    @Test
    public final void testIsSecondSide() {

        Assert.assertTrue(Side.SECOND_SIDE.isSecondSide());
        Assert.assertFalse(this.side.isSecondSide());
        Assert.assertFalse(Side.NULL_SIDE.isSecondSide());

    }

    @Test
    public final void testIsOneSide() {

        Assert.assertTrue(this.side.isOneSide());
        Assert.assertTrue(Side.SECOND_SIDE.isOneSide());
        Assert.assertFalse(Side.NULL_SIDE.isOneSide());

    }

    @Test
    public final void testIsNull() {

        Assert.assertTrue(Side.NULL_SIDE.isNull());
        Assert.assertFalse(this.side.isNull());
        Assert.assertFalse(Side.SECOND_SIDE.isNull());

    }

    @Test
    public final void testGetNextSide() {

        Assert.assertTrue(Side.SECOND_SIDE == this.side.getNextSide());
        Assert.assertTrue(this.side == Side.SECOND_SIDE.getNextSide());
        Assert.assertTrue(Side.NULL_SIDE == Side.NULL_SIDE.getNextSide());

    }

    @Test
    public final void testGetNegation() {

        Assert.assertTrue(Side.NOT_FIRST_SIDE == this.side.getNegation());
        Assert.assertTrue(Side.NOT_SECOND_SIDE == Side.SECOND_SIDE.getNegation());
        Assert.assertTrue(Side.NULL_SIDE == Side.NULL_SIDE.getNegation());

    }

    @After
    public void tearDown() {

        this.side = null;

    }

}
