
package abstractions.immutable.rendering.board.cell;

import abstractions.immutable.context.gameplay.game.board.cell.BoardCellInterface;
import abstractions.immutable.rendering.RenderingInterface;

public interface BoardCellRenderingInterface<OUTPUT> extends RenderingInterface<BoardCellInterface, OUTPUT> {

    @Override
    public OUTPUT render(BoardCellInterface cell);

}