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
import abstractions.mutation.MutationInterface;
import abstractions.piece.AbstractPiece;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionManager.Direction;
import abstractions.position.PositionManager.DirectionInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import concretisations.checkers.mutations.CheckersMutationFactory;
import concretisations.checkers.mutations.CheckersMutations;

public abstract class CheckerPiece extends AbstractPiece {

    private static final Set<? extends DirectionInterface> PATHS = ImmutableSet.of(Direction.RIGHT, Direction.LEFT);
    private final Set<? extends DirectionInterface> legalDirections;

    private static interface Predicate {

        boolean apply(ManagedCellInterface cell, SideInterface side, DirectionInterface direction);
    }

    private final static Predicate CAN_WALK_THROUGH = new Predicate() {

        public boolean apply(final ManagedCellInterface cell, final SideInterface side, final DirectionInterface direction) {
            return side.equals(cell.getPiece().getSide()) && cell.getRelative(direction).isEmpty();
        }
    };

    private final static Predicate CAN_JUMP_OVER = new Predicate() {

        public boolean apply(final ManagedCellInterface cell, final SideInterface side, final DirectionInterface direction) {
            return side.equals(cell.getPiece().getSide())
                    && !cell.getRelative(direction).isNull() // TODO ! à améliorer
                    && side.getNextSide().equals(cell.getRelative(direction).getPiece().getSide())
                    && cell.getRelative(direction).getRelative(direction).isEmpty();
        }
    };

    @SuppressWarnings("unchecked")
    private Set<DirectionInterface> compileLegalRelativePositions(final Set<Direction> directions) {
        final Set<DirectionInterface> legalRelativePositions = Sets.newHashSetWithExpectedSize(CheckerPiece.PATHS.size() * directions.size());
        for (final List<DirectionInterface> list : Sets.cartesianProduct(CheckerPiece.PATHS, directions)) {
            legalRelativePositions.add(Direction.reduce(list));
        }
        return legalRelativePositions;
    }

    public CheckerPiece(final SideInterface side, final PieceTypeInterface type, final Set<Direction> directions) {
        super(side, type);
        this.legalDirections = this.compileLegalRelativePositions(directions);
    }

    private Predicate getPredicate(final CheckersMutations mutationType) {
        switch (mutationType) {
            case JUMP:
                return CheckerPiece.CAN_JUMP_OVER;
            case WALK:
            default:
                return CheckerPiece.CAN_WALK_THROUGH;
        }
    }

    private Set<? extends DirectionInterface> getMutations(final ManagedCellInterface cell, final SideInterface side, final CheckersMutations mutationType) {
        final Set<? extends DirectionInterface> mutations = Sets.newHashSetWithExpectedSize(this.legalDirections.size());
        final Predicate predicate = this.getPredicate(mutationType);
        for (final DirectionInterface relativePosition : this.legalDirections) {
            if (predicate.apply(cell, side, relativePosition)) {
                mutations.add(relativePosition);
            }
        }
        return mutations;
    }

    @Override
    public Set<? extends MutationInterface> computePotentialMutations(final ManagedCellInterface cell, final SideInterface side) {
        final Set<MutationInterface> potentialMutations = Sets.newHashSetWithExpectedSize(4);
        for (final DirectionInterface direction : this.getMutations(cell, side, CheckersMutations.JUMP)) {
            potentialMutations.add(CheckersMutationFactory.newJumpMutation(cell, direction));
        }
        for (final DirectionInterface direction : this.getMutations(cell, side, CheckersMutations.WALK)) {
            potentialMutations.add(CheckersMutationFactory.newWalkMutation(cell, direction));
        }
        return potentialMutations;
    }

}