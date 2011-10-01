
package context.rendering.board.cell;

import context.entity.game.board.cell.BoardCellInterface;
import context.rendering.RenderingInterface;

public interface BoardCellRenderingInterface<OUTPUT> extends RenderingInterface<BoardCellInterface, OUTPUT> {

    @Override
    public OUTPUT render(BoardCellInterface cell);

}