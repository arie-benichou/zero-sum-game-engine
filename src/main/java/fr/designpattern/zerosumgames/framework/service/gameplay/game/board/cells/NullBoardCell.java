
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.BoardPositionFactoryInterface;

public final class NullBoardCell extends AbstractBoardCell {

    public NullBoardCell() {
        super(BoardPositionFactoryInterface.NULL_POSITION);
    }

    @Override
    public final boolean isNull() {
        return true;
    }

    @Override
    public String toString() {
        return "";
    }

}