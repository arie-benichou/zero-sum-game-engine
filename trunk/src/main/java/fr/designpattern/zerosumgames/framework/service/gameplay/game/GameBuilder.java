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

package fr.designpattern.zerosumgames.framework.service.gameplay.game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.Board;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.BoardInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.DimensionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.Cells;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.CellsInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.Positions;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.PositionsInterface;

// TODO ? as a Game inner-class
// TODO ! gérer l'injection de préférences spécifiques à un jeu
// TODO ? un builder abstrait pourrait retourner un builder concret d'un jeu
public class GameBuilder implements GameBuilderInterface {

    private final transient Class<? extends GameInterface> builderGameClass;
    private transient DimensionInterface builderBoardDimension;

    public GameBuilder(final Class<? extends GameInterface> gameClass) {
        this.builderGameClass = gameClass;
        try {
            this.boardDimension((DimensionInterface) this.builderGameClass
                    .getDeclaredField("BOARD_DIMENSION").get(
                            DimensionInterface.class));
        }
        catch (final IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (final SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (final IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (final NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public final GameBuilder boardDimension(
            final DimensionInterface boardDimension) {
        this.builderBoardDimension = boardDimension;
        return this;
    }

    public GameInterface build() {
        final PositionsInterface positionFactory = new Positions(
                this.builderBoardDimension);
        final CellsInterface cellFactory = new Cells(positionFactory);
        final BoardInterface board = new Board(cellFactory);
        return this.newInstance(board);
    }

    private GameInterface newInstance(final BoardInterface board) {
        Constructor<? extends GameInterface> constructor = null;
        GameInterface instance = null;
        try {
            constructor = this.builderGameClass
                    .getDeclaredConstructor(BoardInterface.class);
        }
        catch (final SecurityException e) {
            e.printStackTrace();
        }
        catch (final NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            instance = constructor.newInstance(board);
        }
        catch (final IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (final InstantiationException e) {
            e.printStackTrace();
        }
        catch (final IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (final InvocationTargetException e) {
            e.printStackTrace();
        }
        return instance;
    }

}