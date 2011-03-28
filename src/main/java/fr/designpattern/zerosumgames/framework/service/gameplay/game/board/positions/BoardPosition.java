
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions;

// TODO réduire la visibilité de la classe
public final class BoardPosition extends AbstractBoardPosition {

    public BoardPosition(final int row, final int column) {
        super(row, column);
        if (row < 1) {
            throw new IllegalArgumentException("Argument 'row' must be greater than 0.");
        }
        if (column < 1) {
            throw new IllegalArgumentException("Argument 'column' must be greater than 0.");
        }
    }

    @Override
    public final boolean isNull() {
        return false;
    }

}