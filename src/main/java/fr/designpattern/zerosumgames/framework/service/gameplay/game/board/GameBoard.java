
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells.BoardCell;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells.BoardCellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells.NullBoardCell;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardDimensions;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardDimensionsInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.BoardPositionFactory;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.BoardPositionFactoryInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.BoardPositionInterface;

// TODO ? getByColumn et getByRow
public final class GameBoard implements GameBoardInterface {

    private final static BoardCellInterface NULL_BOARD_CELL = new NullBoardCell();

    private final BoardDimensionsInterface dimensions;
    private final BoardPositionFactoryInterface positionFactory;

    private final Map<String, BoardCellInterface> boardCells;

    private final String computeHash(final int row, final int column) {
        return "r" + row + "c" + column;
    }

    private final String computeHash(final BoardCellInterface cell) {
        return this.computeHash(cell.getRow(), cell.getColumn());
    }

    private final BoardCellInterface createNewCell(final BoardPositionInterface position) {
        return new BoardCell(position);
    }

    private Map<String, BoardCellInterface> createNewCellsFrom(final List<BoardPositionInterface> positions) {
        final HashMap<String, BoardCellInterface> cells = new HashMap<String, BoardCellInterface>(positions.size());
        cells.put(null, GameBoard.NULL_BOARD_CELL);
        for (final BoardPositionInterface position : positions) {
            final BoardCellInterface newCell = this.createNewCell(position);
            cells.put(this.computeHash(newCell), newCell);
        }
        return cells;
    }

    public GameBoard(final BoardDimensionsInterface boardDimensions, final BoardPositionFactoryInterface positionFactory) {
        this.dimensions = boardDimensions;
        this.positionFactory = positionFactory;
        // TODO ? utiliser une map dont la structure n'est pas modifiable
        this.boardCells = this.createNewCellsFrom(positionFactory.computePositionsFrom(boardDimensions));
    }

    public final BoardCellInterface cell(final int row, final int column) {
        BoardCellInterface cell = this.boardCells.get(this.computeHash(row, column));
        if (cell == null) {
            cell = this.boardCells.get(null);
        }
        return cell;
    }

    public Iterator<BoardCellInterface> iterator() {
        final ArrayList<BoardCellInterface> cells = new ArrayList<BoardCellInterface>(this.boardCells.values());
        Collections.sort(cells); // TODO tester sans
        return cells.iterator();
    }

    @Override
    public String toString() {
        final int maximalNumberOfCellsByRow = Collections.max(this.boardCells.values()).getColumn();
        final StringBuilder consoleBoardView = new StringBuilder();
        final Iterator<BoardCellInterface> it = this.iterator();
        BoardCellInterface previousCell = it.next();
        while (it.hasNext()) {
            final BoardCellInterface cell = it.next();
            if (previousCell.getRow() != cell.getRow()) {
                consoleBoardView.append("\n" + Strings.repeat("----", maximalNumberOfCellsByRow) + "-" + "\n");
                consoleBoardView.append("|");
            }
            consoleBoardView.append(cell);
            previousCell = cell;
        }
        consoleBoardView.append("\n" + Strings.repeat("----", maximalNumberOfCellsByRow) + "-" + "\n");
        return consoleBoardView.toString();
    }

    public static void main(final String[] args) {
        final BoardPositionFactoryInterface positionFactory = new BoardPositionFactory();
        final BoardDimensionsInterface boardDimensions = new BoardDimensions(1, 3, 1, 3);
        final GameBoard board = new GameBoard(boardDimensions, positionFactory);
        System.out.println(board);
    }
}