
package rendering.board;

import java.util.Map;

import rendering.RenderingInterface;
import abstractions.immutable.context.board.BoardInterface;

public interface BoardRenderingInterface<OUTPUT> extends RenderingInterface<BoardInterface, OUTPUT> {

    @Override
    public OUTPUT render(BoardInterface board);

    // TODO ajouter à RenderingInterface
    OUTPUT render(BoardInterface board, Map<Object, Object> symbols);

}