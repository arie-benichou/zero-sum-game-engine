
package rendering.board;

import rendering.RenderingInterface;
import abstractions.immutable.context.board.BoardInterface;

public interface BoardRenderingInterface<OUTPUT> extends RenderingInterface<BoardInterface, OUTPUT> {

    @Override
    public OUTPUT render(BoardInterface board);

}