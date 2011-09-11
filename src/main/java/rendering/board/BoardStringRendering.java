
package rendering.board;

import rendering.board.cell.BoardCellStringRendering;
import abstractions.immutable.context.board.BoardInterface;

import com.google.common.base.Strings;

public class BoardStringRendering implements BoardRenderingInterface<String> {

    private final BoardCellStringRendering boardCellRendering;

    public BoardStringRendering(final BoardCellStringRendering boardCellRendering) {
        this.boardCellRendering = boardCellRendering;
    }

    @Override
    public String render(final BoardInterface board) {
        final String lineSeparator = "\n" + " " + Strings.repeat("----", board.columns()) + "-" + "\n";
        final String columnSeparator = " | ";
        final StringBuilder sb = new StringBuilder();
        for (int y = 1; y <= board.rows(); ++y) {
            sb.append(lineSeparator);
            for (int x = 1; x <= board.columns(); ++x)
                sb.append(columnSeparator + this.boardCellRendering.render(board.cell(y, x)));
            sb.append(columnSeparator);
        }
        sb.append(lineSeparator);
        return sb.toString();
    }
}