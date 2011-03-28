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
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.GameBoardInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.BoardDimensionsInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.Cells;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.CellsInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.positions.Positions;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.positions.PositionsInterface;

// TODO ? as a Game inner-class
// TODO ! gérer l'injection de préférences spécifiques à un jeu
// TODO ? un builder abstrait pourrait retourner un builder concret d'un jeu
public class GameBuilder implements GameBuilderInterface {

    private final transient Class<? extends GameInterface> builderGameClass;
    private transient BoardDimensionsInterface builderBoardDimension;

    public GameBuilder(final Class<? extends GameInterface> gameClass) {
        this.builderGameClass = gameClass;
        try {
            this.boardDimension((BoardDimensionsInterface) this.builderGameClass
                    .getDeclaredField("BOARD_DIMENSION").get(
                            BoardDimensionsInterface.class));
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
            final BoardDimensionsInterface boardDimension) {
        this.builderBoardDimension = boardDimension;
        return this;
    }

    public GameInterface build() {
        final PositionsInterface positionFactory = new Positions(
                this.builderBoardDimension);
        final CellsInterface cellFactory = new Cells(positionFactory);
        final GameBoardInterface board = new Board(cellFactory);
        return this.newInstance(board);
    }

    private GameInterface newInstance(final GameBoardInterface board) {
        Constructor<? extends GameInterface> constructor = null;
        GameInterface instance = null;
        try {
            constructor = this.builderGameClass
                    .getDeclaredConstructor(GameBoardInterface.class);
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