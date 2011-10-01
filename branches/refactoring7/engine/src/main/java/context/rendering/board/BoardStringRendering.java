
package context.rendering.board;

import java.util.Map;


import com.google.common.base.Strings;

import context.entity.game.board.BoardInterface;
import context.rendering.board.cell.BoardCellStringRendering;

public class BoardStringRendering implements BoardRenderingInterface<String> {

    private final BoardCellStringRendering boardCellRendering;

    public BoardStringRendering(final BoardCellStringRendering boardCellRendering) {
        this.boardCellRendering = boardCellRendering;
    }

    @Override
    public String render(final BoardInterface board, final Map<Object, Object> symbols) {

        /*
        int max = 0;
        if (symbols != null) {
            for (final Object value : symbols.values()) {
                final int size = value.toString().length();
                if (size > max) max = size;
            }
        }
        final String lineSeparator = "\n" + Strings.repeat(Strings.repeat("-", max), board.columns()) + "-" + "\n";
        */

        final String lineSeparator = symbols == null ? "" : Strings.repeat("----", board.columns()) + "-";
        final String columnSeparator = "|";

        final StringBuilder sb = new StringBuilder();

        for (int y = 1; y <= board.rows(); ++y) {

            sb.append(lineSeparator);
            sb.append("\n");

            for (int x = 1; x <= board.columns(); ++x)
                sb.append(columnSeparator + this.boardCellRendering.render(board.cell(y, x), symbols));

            sb.append(columnSeparator);
            sb.append("\n");

        }

        sb.append(lineSeparator);

        return sb.toString();
    }

    @Override
    public String render(final BoardInterface board) {
        return this.render(board, null);
    }
}