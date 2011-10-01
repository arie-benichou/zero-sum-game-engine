
package fr.designpattern.zerosumgames.abstractions.rendering.board.cell;

import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.BoardCellInterface;
import fr.designpattern.zerosumgames.abstractions.rendering.RenderingInterface;

public interface BoardCellRenderingInterface<OUTPUT> extends RenderingInterface<BoardCellInterface, OUTPUT> {

    @Override
    public OUTPUT render(BoardCellInterface cell);

}