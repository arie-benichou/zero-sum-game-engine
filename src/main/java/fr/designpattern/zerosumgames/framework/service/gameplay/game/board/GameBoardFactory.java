
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells.BoardCell;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells.BoardCellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells.NullBoardCell;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardDimensions;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardDimensionsInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.BoardPositionFactory;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.BoardPositionFactoryInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.BoardPositionInterface;

public final class GameBoardFactory {

    private final BoardPositionFactoryInterface positionFactory;

    private static final class BoardCellFactory {

        private final static BoardCellInterface NULL_CELL = new NullBoardCell();

        private BoardCellFactory() {}

        private static BoardCellInterface createNewCell(final BoardPositionInterface position) {
            return new BoardCell(position);
        }

        public static List<BoardCellInterface> createNewCellsFrom(final List<BoardPositionInterface> positions) {
            final ArrayList<BoardCellInterface> cells = new ArrayList<BoardCellInterface>(positions.size());
            //cells.add(BoardCellFactory.NULL_CELL);
            for (final BoardPositionInterface position : positions) {
                cells.add(BoardCellFactory.createNewCell(position));
            }
            return Collections.unmodifiableList(cells);
        }

    }

    public GameBoardFactory(final BoardPositionFactoryInterface positionFactory) {
        this.positionFactory = positionFactory;
    }

    public GameBoardInterface newBoard(final BoardDimensionsInterface dimensions) {
        return new GameBoard(BoardCellFactory.createNewCellsFrom(this.positionFactory.computePositionsFrom(dimensions)));
    }

    //TODO à faire dans game
    public static void main(final String[] args) {

        final BoardPositionFactoryInterface positionFactory = new BoardPositionFactory();
        final GameBoardFactory boardFactory = new GameBoardFactory(positionFactory);

        final BoardDimensionsInterface dimensions = new BoardDimensions(1, 3, 1, 3);

        final GameBoardInterface board = boardFactory.newBoard(dimensions);

        System.out.println(board);

    }
}