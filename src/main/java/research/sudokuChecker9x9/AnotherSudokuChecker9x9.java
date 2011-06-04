
package research.sudokuChecker9x9;

/**
 * Implémentation optimisée adaptée du code Scala écrit David Bernard.
 */
final class AnotherSudokuChecker9x9 {

    private AnotherSudokuChecker9x9() {}

    private static boolean tag(final int v, final int idx, final int[] presence) {
        final int mask = 1 << idx;
        final int current = presence[v - 1];
        presence[v - 1] = current | mask;
        return (current & mask) == 0;
    }

    private static boolean tagLine(final int v, final int p, final int[] presence) {
        return tag(v, p + 0 * 9, presence);
    }

    private static boolean tagColumn(final int v, final int p, final int[] presence) {
        return tag(v, p + 1 * 9, presence);
    }

    private static boolean tagSubGrid(final int v, final int p, final int[] presence) {
        return tag(v, p + 2 * 9, presence);
    }

    public static boolean check(final int[][] grid) {
        final int[] presence = new int[9];
        boolean ok = true;
        for (int l = 0; l < 9; ++l)
            for (int c = 0; c < 9; ++c) {
                final int v = grid[l][c];
                ok = ok && tagLine(v, l, presence) && tagColumn(v, c, presence) && tagSubGrid(v, l / 3 * 3 + c / 3, presence);
            }
        return ok;
    }

}