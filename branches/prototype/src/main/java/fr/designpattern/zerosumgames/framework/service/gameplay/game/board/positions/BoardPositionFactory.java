
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardDimensionsInterface;
import fr.designpattern.zerosumgames.util.Immutable;

public final class BoardPositionFactory implements BoardPositionFactoryInterface, Immutable {

    private final Map<String, BoardPositionInterface> cachedBoardPositions;

    public BoardPositionFactory() {
        this.cachedBoardPositions = new HashMap<String, BoardPositionInterface>();
    }

    private String computeHash(final int rowIndex, final int columnIndex) {
        return "r" + rowIndex + "c" + columnIndex;
    }

    private BoardPositionInterface position(final int rowIndex, final int columnIndex) {
        final String hashKey = this.computeHash(rowIndex, columnIndex);
        BoardPositionInterface position = this.cachedBoardPositions.get(hashKey);
        if (position == null) {
            position = new BoardPosition(rowIndex, columnIndex);
            this.cachedBoardPositions.put(hashKey, position);
        }
        return position;
    }

    public List<BoardPositionInterface> computePositionsFrom(final BoardDimensionsInterface dimensions) {
        final ArrayList<BoardPositionInterface> positions = new ArrayList<BoardPositionInterface>(dimensions.getCapacity() /*+ 1*/);
        //positions.add(BoardPositionInterface.NULL_POSITION);
        for (int rowIndex = dimensions.getMinRowIndex(), maxRowIndex = dimensions.getMaxRowIndex(); rowIndex <= maxRowIndex; ++rowIndex) {
            for (int columnIndex = dimensions.getMinColumnIndex(), maxColumnIndex = dimensions.getMaxColumnIndex(); columnIndex <= maxColumnIndex; ++columnIndex) {
                positions.add(this.position(rowIndex, columnIndex));
            }
        }
        return Collections.unmodifiableList(positions);
    }
    /*
    public BoardPositionInterface position(final int rowIndex, final int columnIndex) {
        BoardPositionInterface position = this.positionsMap.get(this.computeHash(rowIndex, columnIndex));
        if (position == null) {
            position = BoardPositionFactoryInterface.NULL_POSITION;
        }
        return position;
    }

    private BoardPositionInterface getNeighbourOf(final BoardPositionInterface position, final BoardCardinalPosition direction) {
        return this.position(position.getRow() + direction.getDeltaRowIndex(), position.getColumn() + direction.getDeltaColumnIndex());
    }

    public BoardPositionInterface leftOf(final BoardPositionInterface position) {
        return this.getNeighbourOf(position, BoardCardinalPosition.LEFT);
    }

    public BoardPositionInterface rightOf(final BoardPositionInterface position) {
        return this.getNeighbourOf(position, BoardCardinalPosition.RIGHT);
    }

    public BoardPositionInterface topOf(final BoardPositionInterface position) {
        return this.getNeighbourOf(position, BoardCardinalPosition.TOP);
    }

    public BoardPositionInterface bottomOf(final BoardPositionInterface position) {
        return this.getNeighbourOf(position, BoardCardinalPosition.BOTTOM);
    }

    public BoardPositionInterface topLeftOf(final BoardPositionInterface position) {
        return this.getNeighbourOf(position, BoardCardinalPosition.TOP_LEFT);
    }

    public BoardPositionInterface topRightOf(final BoardPositionInterface position) {
        return this.getNeighbourOf(position, BoardCardinalPosition.TOP_RIGHT);
    }

    public BoardPositionInterface bottomLeftOf(final BoardPositionInterface position) {
        return this.getNeighbourOf(position, BoardCardinalPosition.BOTTOM_LEFT);
    }

    public BoardPositionInterface bottomRightOf(final BoardPositionInterface position) {
        return this.getNeighbourOf(position, BoardCardinalPosition.BOTTOM_RIGHT);
    }
    */

}