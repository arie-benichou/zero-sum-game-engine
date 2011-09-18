
package fr.designpattern.zerosumgames.abstractions.immutable.rendering.board.cell;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.BoardCellInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.rendering.RenderingInterface;

public interface BoardCellRenderingInterface<OUTPUT> extends RenderingInterface<BoardCellInterface, OUTPUT> {

    @Override
    public OUTPUT render(BoardCellInterface cell);

}