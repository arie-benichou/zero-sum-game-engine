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

package research.sudokuChecker9x9;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

public class SudokuChecker9x9Test {

    @Test
    //(expected = RuntimeException.class)
    public void sudokuGridIsSupposedToBeNineSquareMatrix() {

        final Integer[][] grid = {
                { 5, 3, 4 },
                { 6, 7, 2 },
                { 1, 9, 8 },
                { 8, 5, 9 },
                { 4, 2, 6 },
                { 7, 1, 3 },
                { 9, 6, 1 },
                { 2, 8, 7 },
                { 3, 4, 5 }
              };

        assertFalse(SudokuChecker9x9.check(grid));
    }

    @Test
    public void rowTestShouldFail() {

        final Integer[][] grid = {
                { 5, 3, 4, 4, 7, 8, 9, 1, 2 }, //should be { 5, 3, 4, 6, 7, 8, 9, 1, 2 }
                { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
                { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
                { 8, 5, 9, 7, 6, 1, 4, 2, 3 },
                { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
                { 7, 1, 3, 9, 2, 4, 8, 5, 6 },
                { 9, 6, 1, 5, 3, 7, 2, 8, 4 },
                { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
                { 3, 4, 5, 2, 8, 6, 1, 7, 9 }
              };

        assertFalse(SudokuChecker9x9.check(grid));
    }

    @Test
    public void columnTestShouldFail() {

        final Integer[][] grid = {
                { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
                { 6, 7, 1, 2, 9, 5, 3, 4, 8 }, //should be { 6, 7, 2, 1, 9, 5, 3, 4, 8 }
                { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
                { 8, 5, 9, 7, 6, 1, 4, 2, 3 },
                { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
                { 7, 1, 3, 9, 2, 4, 8, 5, 6 },
                { 9, 6, 1, 5, 3, 7, 2, 8, 4 },
                { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
                { 3, 4, 5, 2, 8, 6, 1, 7, 9 }
              };

        assertFalse(SudokuChecker9x9.check(grid));
    }

    @Test
    public void regionTestShouldFail() {

        final Integer[][] grid = {
                { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
                { 6, 7, 1, 2, 9, 5, 3, 4, 8 }, //should be { 6, 7, 2, 1, 9, 5, 3, 4, 8 }
                { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
                { 8, 5, 9, 7, 6, 1, 4, 2, 3 },
                { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
                { 7, 1, 3, 9, 2, 4, 8, 5, 6 },
                { 9, 6, 5, 1, 3, 7, 2, 8, 4 }, //should be { 9, 6, 1, 5, 3, 7, 2, 8, 4 }
                { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
                { 3, 4, 2, 5, 8, 6, 1, 7, 9 } //should be { 3, 4, 5, 2, 8, 6, 1, 7, 9 }
        };

        assertFalse(SudokuChecker9x9.check(grid));
    }

    @Test
    public void allTestsShouldPass() {

        final Integer[][] grid = {
                { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
                { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
                { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
                { 8, 5, 9, 7, 6, 1, 4, 2, 3 },
                { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
                { 7, 1, 3, 9, 2, 4, 8, 5, 6 },
                { 9, 6, 1, 5, 3, 7, 2, 8, 4 },
                { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
                { 3, 4, 5, 2, 8, 6, 1, 7, 9 }
              };
        assertTrue(SudokuChecker9x9.check(grid));
    }

}
