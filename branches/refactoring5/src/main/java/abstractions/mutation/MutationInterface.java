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

package abstractions.mutation;

import java.util.Set;

import abstractions.context.ContextInterface;
import abstractions.piece.PieceInterface;
import abstractions.position.PositionInterface;

import com.google.common.collect.ImmutableSet;

/**
 * This is the interface for a cell mutation.
 */
public interface MutationInterface extends Comparable<MutationInterface> {

    /**
     * Constant for the empty set of potential mutations.
     */
    Set<MutationInterface> NULL_POTENTIAL_MUTATION_SET = ImmutableSet.of(); // TODO ! classe MutationManager

    /**
     * Returns the position of the cell concerned by this mutation.
     * 
     * @return the position of the cell concerned by this mutation
     */
    PositionInterface getPosition();

    /**
     * Returns the type of this mutation.
     * 
     * @return the type of this mutation
     */
    MutationTypeInterface getType();

    /**
     * Returns the state of the cell concerned by this mutation, before the
     * process of this mutation.
     * 
     * @return the state of the cell concerned by this mutation, before the
     *         process of this mutation
     */
    PieceInterface getSavedSate();

    /**
     * Process this mutation.
     */
    MutationInterface process(ContextInterface context);

    /**
     * Cancels this mutation.
     */
    MutationInterface cancel(ContextInterface context);

    boolean isNull();

    void computeSequence(ContextInterface context);

}
