/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardDimensionsInterface;
import fr.designpattern.zerosumgames.util.Immutable;

/**
 * This is the interface for the positions factory.
 */
public interface BoardPositionFactoryInterface extends Immutable {

    public final static BoardPositionInterface NULL_POSITION = new NullBoardPosition();

    List<BoardPositionInterface> computePositionsFrom(final BoardDimensionsInterface dimensions);

    /**
     * Returns the position related to a given row and a given column.
     * 
     * @param clientRowIndex
     *            a given row
     * 
     * @param clientColumnIndex
     *            a given column
     * 
     * @return the position related to a given row index and a given column
     */
    //BoardPositionInterface position(final int clientRowIndex, final int clientColumnIndex);

    /**
     * Returns the left position of a given position.
     * 
     * @param position
     *            a given position
     * 
     * @return the left position of a given position
     */
    //BoardPositionInterface leftOf(final BoardPositionInterface position);

    /**
     * Returns the right position of a given position.
     * 
     * @param position
     *            a given position
     * 
     * @return the right position of a given position
     */
    //BoardPositionInterface rightOf(final BoardPositionInterface position);

    /**
     * Returns the top position of a given position.
     * 
     * @param position
     *            a given position
     * 
     * @return the top position of a given position
     */
    //BoardPositionInterface topOf(final BoardPositionInterface position);

    /**
     * Returns the bottom position of a given position.
     * 
     * @param position
     *            a given position
     * 
     * @return the bottom position of a given position
     */
    //BoardPositionInterface bottomOf(final BoardPositionInterface position);

    /**
     * Returns the top-left position of a given position.
     * 
     * @param position
     *            a given position
     * 
     * @return the top-left position of a given position
     */
    //BoardPositionInterface topLeftOf(final BoardPositionInterface position);

    /**
     * Returns the top-right position of a given position.
     * 
     * @param position
     *            a given position
     * 
     * @return the top-right position of a given position
     */
    //BoardPositionInterface topRightOf(final BoardPositionInterface position);

    /**
     * Returns the bottom-left position of a given position.
     * 
     * @param position
     *            a given position
     * 
     * @return the bottom-left position of a given position
     */
    //BoardPositionInterface bottomLeftOf(final BoardPositionInterface position);

    /**
     * Returns the bottom-right position of a given position.
     * 
     * @param position
     *            a given position
     * 
     * @return the bottom-right position of a given position
     */
    //BoardPositionInterface bottomRightOf(final BoardPositionInterface position);

}