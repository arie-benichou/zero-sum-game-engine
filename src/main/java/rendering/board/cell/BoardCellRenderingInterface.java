
package rendering.board.cell;

import rendering.RenderingInterface;
import abstractions.immutable.context.board.cell.BoardCellInterface;

public interface BoardCellRenderingInterface<OUTPUT> extends RenderingInterface<BoardCellInterface, OUTPUT> {

    @Override
    public OUTPUT render(BoardCellInterface cell);

}