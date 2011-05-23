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

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

/**
 * This is a 9x9 Sudoku Checker. The algorithm is based on a discussion that you
 * can find here : http://stackoverflow.com/questions/289537/a-cool-algorithm-to
 * -check-a-sudoku-field
 * 
 * @author Arie Benichou
 */
public final class SudokuChecker9x9 {

    /**
     * Side for a 9x9 Sudoku grid in order to avoid "magic number"
     */
    private final static int SIDE = 9;

    /**
     * Region side for a 9x9 Sudoku grid in order to avoid "magic number"
     */
    private final static int REGION_SIDE = 3;

    /**
     * Canonical set for a 9x9 Sudoku game.
     */
    private final static Set<Integer> CANONICAL_SET = ImmutableSet.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    /**
     * Default constructor visibility set to private since this is a static
     * utility class.
     */
    private SudokuChecker9x9() {}

    /**
     * Returns true if a given grid is a valid 9x9 Sudoku grid, false otherwise.
     * 
     * @param grid
     *            a given 9x9 Sudoku grid
     * 
     * @return true if a given grid is a valid 9x9 Sudoku grid, false otherwise
     */
    public static boolean check(final Integer[][] grid) {
        if (grid.length != SIDE || grid.length != grid[0].length)
            // TODO Utiliser une SudokuChecker9x9Exception, si besoin.
            //throw new RuntimeException("Sudoku grid is supposed to be a nine square matrix");
            return false;
        if (!checkRows(grid))
            return false;
        if (!checkColumns(grid))
            return false;
        if (!checkRegions(grid))
            return false;
        return true;
    }

    /**
     * Returns true if for each row of a given 9x9 Sudoku grid the row set is
     * equal to the canonical set, false otherwise.
     * 
     * @param grid
     *            a given 9x9 Sudoku grid
     * 
     * @return true if for each row of a given 9x9 Sudoku grid the row set is
     *         equal to the canonical set, false otherwise
     */
    private static boolean checkRows(final Integer[][] grid) {
        for (final Integer[] row : grid)
            if (!Sets.newHashSet(row).equals(SudokuChecker9x9.CANONICAL_SET))
                return false;
        return true;
    }

    /**
     * Returns true if for each column of a given 9x9 Sudoku grid the column set
     * is equal to the canonical set, false otherwise.
     * 
     * @param grid
     *            a given 9x9 Sudoku grid
     * 
     * @return true if for each column of a given 9x9 Sudoku grid the column set
     *         is equal to the canonical set, false otherwise
     */
    private static boolean checkColumns(final Integer[][] grid) {
        for (int columnIndex = 0; columnIndex < SudokuChecker9x9.SIDE; ++columnIndex)
            if (!SudokuChecker9x9.getColumnSet(grid, columnIndex).equals(SudokuChecker9x9.CANONICAL_SET))
                return false;
        return true;
    }

    /**
     * Returns true if for each region of a given 9x9 Sudoku grid the region set
     * is equal to the canonical set, false otherwise.
     * 
     * @param grid
     *            a given 9x9 Sudoku grid
     * 
     * @return true if for each region of a given 9x9 Sudoku grid the region set
     *         is equal to the canonical set, false otherwise
     */
    private static boolean checkRegions(final Integer[][] grid) {
        for (int regionIndex = 0; regionIndex < SudokuChecker9x9.SIDE; ++regionIndex)
            if (!SudokuChecker9x9.getRegionSet(grid, regionIndex).equals(SudokuChecker9x9.CANONICAL_SET))
                return false;
        return true;
    }

    /**
     * Returns the column set for a given 9x9 Sudoku grid and a given column
     * index.
     * 
     * @param grid
     *            a given 9x9 Sudoku grid
     * 
     * @param columnIndex
     *            a given column index
     * 
     * @return the column set for a given 9x9 Sudoku grid and a given column
     *         index
     */
    private static Set<Integer> getColumnSet(final Integer[][] grid, final int columnIndex) {
        final HashSet<Integer> columnSet = Sets.newHashSetWithExpectedSize(SudokuChecker9x9.SIDE);
        for (int rowIndex = 0; rowIndex < SudokuChecker9x9.SIDE; ++rowIndex)
            columnSet.add(grid[rowIndex][columnIndex]);
        return columnSet;
    }

    /**
     * Returns the region set for a given 9x9 Sudoku grid and a given region
     * index.
     * 
     * @param grid
     *            a given 9x9 Sudoku grid
     * 
     * @param regionIndex
     *            a given region index
     * 
     * @return the region set for a given 9x9 Sudoku grid and a given region
     *         index
     */
    private static Set<Integer> getRegionSet(final Integer[][] grid, final int regionIndex) {
        final HashSet<Integer> regionSet = Sets.newHashSetWithExpectedSize(SudokuChecker9x9.CANONICAL_SET.size());
        final int minRowIndex = REGION_SIDE * (regionIndex / REGION_SIDE);
        final int minColumnIndex = REGION_SIDE * (regionIndex % REGION_SIDE);
        final int maxRowIndex = minRowIndex + REGION_SIDE;
        final int maxColumnIndex = minColumnIndex + REGION_SIDE;
        for (int rowIndex = minRowIndex; rowIndex < maxRowIndex; ++rowIndex)
            for (int columnIndex = minColumnIndex; columnIndex < maxColumnIndex; ++columnIndex)
                regionSet.add(grid[rowIndex][columnIndex]);
        return regionSet;
    }

    /**
     * Main entry point of the class used as micro benchmark. Use JProbe for a
     * deeper analysis.
     * 
     * @see SudokuChecker9x9Test for unit tests
     * 
     * @param args
     *            no argument required
     */
    public static void main(final String[] args) {
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
        final long t0 = System.currentTimeMillis();
        // 10 000 iterations with a valid grid, which is the worst case.
        for (int n = 0; n < 10000; ++n)
            SudokuChecker9x9.check(grid);
        final long t1 = System.currentTimeMillis();
        // Run on ~ 330 ms on an Intel Core2 Duo E8600 @ 3.33GHz 
        System.out.println(t1 - t0 + " ms");
    }

}
