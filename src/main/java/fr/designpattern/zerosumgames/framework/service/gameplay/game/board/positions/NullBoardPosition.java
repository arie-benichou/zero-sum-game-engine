
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions;


public final class NullBoardPosition extends AbstractBoardPosition {

    public NullBoardPosition() {
        super(0, 0);
    }

    @Override
    public final boolean isNull() {
        return true;
    }

}