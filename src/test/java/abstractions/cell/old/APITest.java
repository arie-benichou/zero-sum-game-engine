
package abstractions.cell.old;

import junit.framework.Assert;

import org.junit.Test;

public class APITest {

    @Test(expected = NullPointerException.class)
    public void testCellWithNull() {

        cell(null);

    }

    @Test(expected = NullPointerException.class)
    public void testCloneWithNull() {

        this.clone(null);

    }

    @Test
    public void testCell() {

        Assert.assertTrue(new Cell(Position(1, 1)).equals(cell(Position(1, 1))));

    }

    @Test
    public void testClone() {

        final Cell cell = new Cell(Position(2, 4));
        Assert.assertTrue(new Cell(Position(2, 4)).equals(this.clone(cell)));
        Assert.assertNotSame(new Cell(Position(2, 4)), this.clone(cell));

    }

}
