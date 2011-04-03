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

import java.util.List;
import java.util.Set;

import abstractions.cell.API.CellInterface;
import abstractions.piece.AbstractPiece;
import abstractions.position.RelativePosition;
import abstractions.side.API.SideInterface;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public abstract class CheckerPiece extends AbstractPiece implements PieceInterface {

    private static enum PieceAction { // TODO ? piecePromotion ou pieceEvolution
        JUMP, WALK,
    }

    private static final Set<RelativePosition> PATHS = ImmutableSet.of(RelativePosition.RIGHT, RelativePosition.LEFT);

    private final Set<RelativePosition> legalRelativePositions;

    private static interface Predicate {

        boolean apply(CellInterface cell, RelativePosition relativePosition);
    }

    protected static final Predicate CAN_WALK_THROUGH = new Predicate() {

        public boolean apply(CellInterface cell, RelativePosition relativePosition) {
            return cell.getRelative(relativePosition).isEmpty();
        }
    };
    
    protected static final Predicate CAN_JUMP_OVER = new Predicate() {
        public boolean apply(CellInterface cell, RelativePosition relativePosition) {
            final CellInterface nextCell = cell.getRelative(relativePosition);
            final SideInterface side = cell.getPiece().getSide();
            return nextCell.getPiece().getSide().getNextSide().equals(side) && nextCell.getRelative(relativePosition).isEmpty();
        }
    };    

    protected Set<RelativePosition> compileLegalRelativePositions(Set<RelativePosition> directions) {
        Set<RelativePosition> legalRelativePositions = Sets.newHashSetWithExpectedSize(PATHS.size() * directions.size());
        for (List<RelativePosition> list : Sets.cartesianProduct(PATHS, directions)) {
            legalRelativePositions.add(RelativePosition.reduce(list)); // TODO regarder l'API Guava pour le reduce
        }
        return legalRelativePositions;
    }

    public CheckerPiece(SideInterface side, Set<RelativePosition> directions) {
        super(side);
        this.legalRelativePositions = this.compileLegalRelativePositions(directions);
        //System.out.println(this.legalRelativePositions);
    }

    protected Predicate getPredicate(PieceAction action) {
        switch (action) {
            case JUMP:
                return CAN_JUMP_OVER;
            case WALK:
            default:
                return CAN_WALK_THROUGH;
        }
    }
    
    
    public Set<RelativePosition> getOptions(final CellInterface cell) {
        Set<RelativePosition> options = this.getOptions(cell, PieceAction.JUMP);
        if(options.size() == 0) {
            options = this.getOptions(cell, PieceAction.WALK);
        }
        return options;
    }    
    

    public Set<RelativePosition> getOptions(final CellInterface cell, PieceAction action) {
        final Set<RelativePosition> options = Sets.newHashSetWithExpectedSize(this.legalRelativePositions.size());
        Predicate predicate = this.getPredicate(action);
        for (final RelativePosition relativePosition : this.legalRelativePositions) {
            if (predicate.apply(cell, relativePosition)) {
                options.add(relativePosition);
            }
        }
        return options;
    }
    
    public static void main(String[] args) {
        
        new Man(abstractions.side.API.FIRST_SIDE);
        System.out.println("-----------------------");
        new Man(abstractions.side.API.SECOND_SIDE);
        System.out.println("-----------------------");
        new King(abstractions.side.API.FIRST_SIDE);
        System.out.println("-----------------------");
        new King(abstractions.side.API.SECOND_SIDE);
        
    }

}