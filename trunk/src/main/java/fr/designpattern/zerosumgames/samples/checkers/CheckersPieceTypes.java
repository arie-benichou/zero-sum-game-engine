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

package fr.designpattern.zerosumgames.samples.checkers;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.pieces.PieceTypeInterface;
import fr.designpattern.zerosumgames.samples.checkers.pieces.CheckersPiece;
import fr.designpattern.zerosumgames.samples.checkers.pieces.CheckersPieceKing;
import fr.designpattern.zerosumgames.samples.checkers.pieces.CheckersPieceMan;

public enum CheckersPieceTypes implements PieceTypeInterface {

    MAN(CheckersPieceMan.class),
    KING(CheckersPieceKing.class);

    private final Class<? extends CheckersPiece> classObject;

    private CheckersPieceTypes(final Class<? extends CheckersPiece> classObject) {
        this.classObject = classObject;
    }

    public final Class<? extends CheckersPiece> getClassObject() {
        return this.classObject;
    }

}