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

package concretisations.checkers.piece;

import abstractions.position.RelativePosition;
import abstractions.side.API.SideInterface;

import com.google.common.collect.ImmutableSet;

// TODO refactoring
// TODO pouvoir additionner les points cardinaux entre eux
public final class Man extends CheckerPiece {

    /*
    // Spécialité
    public RelativePosition getDirection() {
        return this.directions.get(0);
    }
    */

    public Man(SideInterface side) {
        super(side, ImmutableSet.of(side.isFirstSide() ? RelativePosition.TOP : RelativePosition.BOTTOM));
    }

    /*
    public boolean isPromotable(final CellInterface cell) {
        return cell.getNeighbour(this.getSideDirection()).isNull();
    }
    */

    /*
    public boolean isPromotable(CellInterface cell) {
        // TODO Auto-generated method stub
        return false;
    }
    */
    
    /*
    public static void main(String[] args) {
        new Man(abstractions.side.API.FIRST_SIDE);
    }
    */
    
    
}