package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells;

import java.util.Map;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardCardinalPosition;


public interface BoardCellFactoryInterface {
    
    /**
     * Returns the neighbours cells of this cell.
     * 
     * @return the neighbours cells of this cell
     */
    Map<BoardCardinalPosition, BoardCellInterface> getNeighbourhood();

    /**
     * Returns a neighbour cell of this cell for a given direction.
     * 
     * @param direction
     *            the direction
     * 
     * @return a neighbour cell of this cell for a given direction
     */
    BoardCellInterface getNeighbour(final BoardCardinalPosition direction);

    /**
     * Returns the top-neighbour cell of this cell.
     * 
     * @return the top-neighbour cell of this cell
     */
    // -------------
    // |   | x |   |
    // -------------
    // |   | . |   |
    // -------------
    // |   |   |   |
    // -------------
    BoardCellInterface top();

    /**
     * Returns the right-neighbour cell of this cell.
     * 
     * @return the right-neighbour cell of this cell
     */
    // -------------
    // |   |   |   |
    // -------------
    // |   | . | x |
    // -------------
    // |   |   |   |
    // -------------
    BoardCellInterface right();

    /**
     * Returns the bottom-neighbour cell of this cell.
     * 
     * @return the bottom-neighbour cell of this cell
     */
    // -------------
    // |   |   |   |
    // -------------
    // |   | . |   |
    // -------------
    // |   | x |   |
    // -------------
    BoardCellInterface bottom();

    /**
     * Returns the left-neighbour cell of this cell.
     * 
     * @return the left-neighbour cell of this cell
     */
    // -------------
    // |   |   |   |
    // -------------
    // | x | . |   |
    // -------------
    // |   |   |   |
    // -------------
    BoardCellInterface left();

    /**
     * Returns the top-right-neighbour cell of this cell.
     * 
     * @return the top-right-neighbour cell of this cell
     */
    // -------------
    // |   |   | x |
    // -------------
    // |   | . |   |
    // -------------
    // |   |   |   |
    // -------------
    BoardCellInterface topRight();

    /**
     * Returns the top-left-neighbour cell of this cell.
     * 
     * @return the top-left-neighbour cell of this cell
     */
    // -------------
    // | x |   |   |
    // -------------
    // |   | . |   |
    // -------------
    // |   |   |   |
    // -------------
    BoardCellInterface topLeft();

    /**
     * Returns the bottom-right-neighbour cell of this cell.
     * 
     * @return the bottom-right-neighbour cell of this cell
     */
    // -------------
    // |   |   |   |
    // -------------
    // |   | . |   |
    // -------------
    // |   |   | x |
    // -------------
    BoardCellInterface bottomRight();

    /**
     * Returns the bottom-left-neighbour cell of this cell.
     * 
     * @return the bottom-left-neighbour cell of this cell
     */
    // -------------
    // |   |   |   |
    // -------------
    // |   | . |   |
    // -------------
    // | x |   |   |
    // -------------
    BoardCellInterface bottomLeft();

}