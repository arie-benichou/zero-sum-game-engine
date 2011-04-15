
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.BoardPositionInterface;

public class BoardCell extends AbstractBoardCell {

    public BoardCell(final BoardPositionInterface position) {
        super(position);
        if (position.isNull()) {
            throw new IllegalArgumentException("Argument 'position' must be a legal position");
        }
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String toString() {
        //return "[row: " + this.getRow() + "][column: " + this.getColumn() + "]";
        return " " + this.getPiece() + " |";

    }
}