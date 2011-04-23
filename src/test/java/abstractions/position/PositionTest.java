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

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class PositionTest { // NOPMD 

    private static final PositionInterface NULL_POSITION = new Position(0, 0);

    private transient int rowIndex;
    private transient int columnIndex;
    private transient PositionInterface position;

    @Before
    public void setUp() throws Exception {
        this.rowIndex = 2;
        this.columnIndex = 4;

        this.position = new Position(this.rowIndex, this.columnIndex);
    }

    @Test
    public void testGetRow() {
        Assert.assertTrue(this.position.getRow() == this.rowIndex);
    }

    @Test
    public void testGetColumn() {
        Assert.assertTrue(this.position.getColumn() == this.columnIndex);
    }

    @Test
    public void testIsNull() {
        Assert.assertTrue(PositionTest.NULL_POSITION.isNull());
        Assert.assertTrue(new Position(0, 0).isNull());

        Assert.assertTrue(new Position(0, 1).isNull());
        Assert.assertTrue(new Position(1, 0).isNull());

        Assert.assertFalse(this.position.isNull());
    }

    @Test
    public void testHashCodeConsistency() {
        Assert.assertTrue(this.position.hashCode() == new Position(this.rowIndex, this.columnIndex).hashCode());
        Assert.assertTrue(PositionTest.NULL_POSITION.hashCode() == new Position(0, 0).hashCode());
    }

    @Test
    public void testHashCodeUnicity() {
        final int minRowIndex = 1; // NOPMD 
        final int maxRowIndex = 99; // NOPMD 
        final int minColumnIndex = 1; // NOPMD 
        final int maxColumnIndex = 100; // NOPMD 

        final Set<Integer> set = new HashSet<Integer>();
        set.add(PositionTest.NULL_POSITION.hashCode());

        for (int y = minRowIndex; y < maxRowIndex; ++y) {
            for (int x = minColumnIndex; x < maxColumnIndex; ++x) {
                final int hashCode = new Position(y, x).hashCode(); // NOPMD 
                set.add(hashCode);
            }
        }
        Assert.assertTrue(1 + (maxRowIndex - minRowIndex) * (maxColumnIndex - minColumnIndex) == set.size());
    }

    @Test
    public void testEqualsObject() {
        Assert.assertFalse(this.position.equals(null)); //NOPMD
        Assert.assertFalse(this.position.equals(PositionTest.NULL_POSITION));

        Assert.assertTrue(this.position.equals(this.position));
        Assert.assertTrue(this.position.equals(new Position(this.rowIndex, this.columnIndex)));

        Assert.assertTrue(PositionTest.NULL_POSITION.equals(PositionTest.NULL_POSITION));
        Assert.assertTrue(PositionTest.NULL_POSITION.equals(new Position(0, 0)));

        Assert.assertFalse(this.position.equals(new Random()));
        Assert.assertFalse(this.position.equals(new Position(this.rowIndex, this.columnIndex + 1)));
        Assert.assertFalse(this.position.equals(new Position(this.rowIndex + 1, this.columnIndex)));
    }

    @Test(expected = NullPointerException.class)
    public void testCompareToNull() {
        this.position.compareTo(null);
    }

    @Test
    public void testCompareToGreater() {
        Assert.assertTrue(this.position.compareTo(new Position(this.rowIndex + 1, this.columnIndex)) == -1);
        Assert.assertTrue(this.position.compareTo(new Position(this.rowIndex, this.columnIndex + 1)) == -1);
        Assert.assertTrue(this.position.compareTo(new Position(this.rowIndex + 1, this.columnIndex + 1)) == -1);
    }

    @Test
    public void testCompareToSameOrder() {
        Assert.assertTrue(this.position.compareTo(this.position) == 0);
        Assert.assertTrue(this.position.compareTo(new Position(this.rowIndex, this.columnIndex)) == 0);
    }

    @Test
    public void testCompareToLower() {
        Assert.assertTrue(this.position.compareTo(new Position(this.rowIndex - 1, this.columnIndex)) == 1);
        Assert.assertTrue(this.position.compareTo(new Position(this.rowIndex, this.columnIndex - 1)) == 1);
        Assert.assertTrue(this.position.compareTo(new Position(this.rowIndex - 1, this.columnIndex - 1)) == 1);
    }

    @After
    public void tearDown() throws Exception {
        this.position = null; // NOPMD 
    }

}
