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

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.piece.AbstractPiece;
import abstractions.position.RelativePositionInterface;
import abstractions.position.RelativePositions;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import concretisations.checkers.mutations.CheckersMutationFactory;

public abstract class AbstractCheckerPiece extends AbstractPiece {

    private static enum PieceAction { // TODO ? piecePromotion/pieceEvolution/pieceAlteration
        JUMP, WALK,
    }

    private static final Set<RelativePositionInterface> PATHS = ImmutableSet.of(RelativePositions.RIGHT, RelativePositions.LEFT);

    private final Set<RelativePositionInterface> legalRelativePositions;

    private static interface Predicate {

        boolean apply(CellInterface cell, SideInterface side, RelativePositionInterface relativePosition);
    }

    private final static Predicate CAN_WALK_THROUGH = new Predicate() {

        public boolean apply(CellInterface cell, SideInterface side, RelativePositionInterface relativePosition) {
            return cell.getPiece().getSide().equals(side) && cell.getRelative(relativePosition).isEmpty();
        }
    };

    private final static Predicate CAN_JUMP_OVER = new Predicate() {

        public boolean apply(CellInterface cell, SideInterface side, RelativePositionInterface relativePosition) {
            final CellInterface nextCell = cell.getRelative(relativePosition);
            return nextCell.getPiece().getSide().getNextSide().equals(side) && nextCell.getRelative(relativePosition).isEmpty();
        }
    };

    @SuppressWarnings("unchecked")
    private Set<RelativePositionInterface> compileLegalRelativePositions(Set<RelativePositionInterface> directions) {
        Set<RelativePositionInterface> legalRelativePositions = Sets.newHashSetWithExpectedSize(PATHS.size() * directions.size());
        for (List<RelativePositionInterface> list : Sets.cartesianProduct(PATHS, directions)) {
            legalRelativePositions.add(RelativePositions.reduce(list)); // TODO regarder l'API Guava pour le reduce
        }
        return legalRelativePositions;
    }

    public AbstractCheckerPiece(SideInterface side, Set<RelativePositionInterface> directions) {
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

    private Set<RelativePositionInterface> getOptions(final CellInterface cell, SideInterface side, PieceAction action) {
        final Set<RelativePositionInterface> options = Sets.newHashSetWithExpectedSize(this.legalRelativePositions.size());
        Predicate predicate = this.getPredicate(action);
        for (final RelativePositionInterface relativePosition : this.legalRelativePositions) {
            if (predicate.apply(cell, side, relativePosition)) {
                options.add(relativePosition);
            }
        }
        return options;
    }

    public Set<MutationInterface> computeAvailableMutations(final CellInterface cell, SideInterface side) {
        final Set<MutationInterface> availableMutations = Sets.newHashSetWithExpectedSize(4); // TODO à affiner
        Set<RelativePositionInterface> options = this.getOptions(cell, side, PieceAction.JUMP);
        for (RelativePositionInterface direction : options) {
            availableMutations.add(CheckersMutationFactory.jump(cell, direction));
        }
        if (options.size() == 0) {
            options = this.getOptions(cell, side, PieceAction.WALK);
            for (RelativePositionInterface direction : options) {
                availableMutations.add(CheckersMutationFactory.walk(cell, direction));
            }
        }
        return availableMutations;
    }

}