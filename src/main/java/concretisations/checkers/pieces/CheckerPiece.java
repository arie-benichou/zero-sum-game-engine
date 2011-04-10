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

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.MutationTypeInterface;
import abstractions.piece.AbstractPiece;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionManager.Direction;
import abstractions.position.PositionManager.DirectionInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

// TODO ? faire une interface
public class CheckerPiece extends AbstractPiece {

    private static final Set<? extends DirectionInterface> PATHS = ImmutableSet.of(Direction.RIGHT, Direction.LEFT);
    private final Set<DirectionInterface> legalRelativePositions;

    private static interface Predicate {

        boolean apply(ManagedCellInterface cell, SideInterface side, DirectionInterface relativePosition);
    }

    private final static Predicate CAN_WALK_THROUGH = new Predicate() {

        @Override
        public boolean apply(final ManagedCellInterface cell, final SideInterface side, final DirectionInterface relativePosition) {
            return side.equals(cell.getPiece().getSide()) && cell.getRelative(relativePosition).isEmpty();
        }
    };

    private final static Predicate CAN_JUMP_OVER = new Predicate() {

        @Override
        public boolean apply(final ManagedCellInterface cell, final SideInterface side, final DirectionInterface relativePosition) {
            return side.equals(cell.getPiece().getSide())
                    && !cell.getRelative(relativePosition).isNull() // TODO ! à améliorer
                    && side.getNextSide().equals(cell.getRelative(relativePosition).getPiece().getSide())
                    && cell.getRelative(relativePosition).getRelative(relativePosition).isEmpty();
        }
    };

    @SuppressWarnings("unchecked")
    private Set<DirectionInterface> compileLegalRelativePositions(final Set<DirectionInterface> directions) {
        final Set<DirectionInterface> legalRelativePositions = Sets.newHashSetWithExpectedSize(CheckerPiece.PATHS.size() * directions.size());
        for (final List<DirectionInterface> list : Sets.cartesianProduct(CheckerPiece.PATHS, directions)) {
            legalRelativePositions.add(RelativePositions.reduce(list)); // TODO regarder l'API Guava pour le reduce
        }
        return legalRelativePositions;
    }

    public CheckerPiece(final SideInterface side, final PieceTypeInterface type, final Set<DirectionInterface> directions) {
        super(side, type);
        this.legalRelativePositions = this.compileLegalRelativePositions(directions);
    }

    /*    
    // TODO ? piecePromotion/pieceEvolution/pieceAlteration    
    private static enum PieceAction {
        JUMP, WALK;
    }
    
    private Predicate getPredicate(final PieceAction action) {
        switch (action) {
            case JUMP:
                return CheckerPiece.CAN_JUMP_OVER;
            case WALK:
            default:
                return CheckerPiece.CAN_WALK_THROUGH;
        }
    }

    private Set<DirectionInterface> getOptions(final ManagedCellInterface cell, final SideInterface side, final PieceAction action) {
        final Set<DirectionInterface> options = Sets.newHashSetWithExpectedSize(this.legalRelativePositions.size());
        final Predicate predicate = this.getPredicate(action);
        for (final DirectionInterface relativePosition : this.legalRelativePositions) {
            if (predicate.apply(cell, side, relativePosition)) {
                options.add(relativePosition);
            }
        }
        return options;
    }

    public Set<? extends MutationInterface> computeAvailableMutations(final ManagedCellInterface cell, final SideInterface side) {
        final Set<MutationInterface> availableMutations = Sets.newHashSetWithExpectedSize(4); // TODO à affiner
        Set<DirectionInterface> options = this.getOptions(cell, side, PieceAction.JUMP);
        for (final DirectionInterface direction : options) {
            availableMutations.add(CheckersMutationFactory.jump(cell, side, direction));
        }
        if (options.size() == 0) {
            options = this.getOptions(cell, side, PieceAction.WALK);
            for (final DirectionInterface direction : options) {
                availableMutations.add(CheckersMutationFactory.walk(cell, side, direction));
            }
        }
        return availableMutations;
    }
    */

    private boolean hasPotentialJumpMutation(final ManagedCellInterface cell, final SideInterface side) {
        for (final DirectionInterface relativePosition : this.legalRelativePositions) {
            if (CheckerPiece.CAN_JUMP_OVER.apply(cell, side, relativePosition)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPotentialWalkMutation(final ManagedCellInterface cell, final SideInterface side) {
        for (final DirectionInterface relativePosition : this.legalRelativePositions) {
            if (CheckerPiece.CAN_WALK_THROUGH.apply(cell, side, relativePosition)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<? extends MutationTypeInterface> computePotentialMutationTypes(final ManagedCellInterface cell, final SideInterface side) {

        final Set<MutationTypeInterface> potentialMutationTypes = Sets.newHashSetWithExpectedSize(2);

        if (this.hasPotentialJumpMutation(cell, side)) {
            potentialMutationTypes.add(null);
        }

        if (this.hasPotentialWalkMutation(cell, side)) {
            potentialMutationTypes.add(null);
        }

        return potentialMutationTypes;
    }

}