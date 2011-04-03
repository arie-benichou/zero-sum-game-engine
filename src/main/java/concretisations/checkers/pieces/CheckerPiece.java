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

package concretisations.checkers.pieces;

import java.util.List;
import java.util.Set;

import abstractions.cell.API.CellInterface;
import abstractions.cell.mutation.MutationInterface;
import abstractions.piece.API.PieceInterface;
import abstractions.piece.AbstractPiece;
import abstractions.position.RelativePosition;
import abstractions.side.API.SideInterface;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import concretisations.checkers.mutations.CheckersMutation;
import concretisations.checkers.mutations.JumpMutation;
import concretisations.checkers.mutations.WalkMutation;

public abstract class CheckerPiece extends AbstractPiece {

    private static enum PieceAction { // TODO ? piecePromotion ou pieceEvolution
        JUMP, WALK,
    }

    private static final Set<RelativePosition> PATHS = ImmutableSet.of(RelativePosition.RIGHT, RelativePosition.LEFT);

    private final Set<RelativePosition> legalRelativePositions;

    private static interface Predicate {

        boolean apply(CellInterface cell, SideInterface side, RelativePosition relativePosition);
    }

    private final static Predicate CAN_WALK_THROUGH = new Predicate() {

        public boolean apply(CellInterface cell, SideInterface side, RelativePosition relativePosition) {
            return cell.getPiece().getSide().equals(side) && cell.getRelative(relativePosition).isEmpty();
        }
    };

    private final static Predicate CAN_JUMP_OVER = new Predicate() {

        public boolean apply(CellInterface cell, SideInterface side, RelativePosition relativePosition) {
            final CellInterface nextCell = cell.getRelative(relativePosition);
            return nextCell.getPiece().getSide().getNextSide().equals(side) && nextCell.getRelative(relativePosition).isEmpty();
        }
    };

    private Set<RelativePosition> compileLegalRelativePositions(Set<RelativePosition> directions) {
        Set<RelativePosition> legalRelativePositions = Sets.newHashSetWithExpectedSize(PATHS.size() * directions.size());
        for (List<RelativePosition> list : Sets.cartesianProduct(PATHS, directions)) {
            legalRelativePositions.add(RelativePosition.reduce(list)); // TODO regarder l'API Guava pour le reduce
        }
        return legalRelativePositions;
    }

    public CheckerPiece(SideInterface side, Set<RelativePosition> directions) {
        super(side);
        this.legalRelativePositions = this.compileLegalRelativePositions(directions);
    }

    private Predicate getPredicate(PieceAction action) {
        switch (action) {
            case JUMP:
                return CAN_JUMP_OVER;
            case WALK:
            default:
                return CAN_WALK_THROUGH;
        }
    }

    private Set<RelativePosition> getOptions(final CellInterface cell, SideInterface side, PieceAction action) {
        final Set<RelativePosition> options = Sets.newHashSetWithExpectedSize(this.legalRelativePositions.size());
        Predicate predicate = this.getPredicate(action);
        for (final RelativePosition relativePosition : this.legalRelativePositions) {
            if (predicate.apply(cell, side, relativePosition)) {
                options.add(relativePosition);
            }
        }
        return options;
    }

    public Set<MutationInterface> computeAvailableMutations(final CellInterface cell, SideInterface side) {

        final Set<MutationInterface> availableMutations = Sets.newHashSetWithExpectedSize(4); // TODO à affiner

        Set<RelativePosition> options = this.getOptions(cell, side, PieceAction.JUMP);
        for (RelativePosition direction : options) {
            availableMutations.add(new JumpMutation(cell).direction(direction));
        }

        if (options.size() == 0) {
            options = this.getOptions(cell, side, PieceAction.WALK);
            for (RelativePosition direction : options) {
                availableMutations.add(new WalkMutation(cell).direction(direction));
            }
        }

        return availableMutations;
    }

    // TODO ! tests unitaires
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