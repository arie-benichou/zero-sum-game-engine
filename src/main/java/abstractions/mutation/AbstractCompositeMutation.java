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

import java.util.List;

import abstractions.cell.ManagedCellInterface;

public abstract class AbstractCompositeMutation extends AbstractMutation {

    public AbstractCompositeMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType) {
        super(cell, mutationType);
    }

    private transient List<MutationInterface> sequence; // NOPMD 

    private List<MutationInterface> getSequence() {
        if (this.sequence == null) {
            this.sequence = this.sequence();
        }
        return this.sequence;
    }

    protected abstract List<MutationInterface> sequence();

    @Override
    public final MutationInterface process() {
        for (final MutationInterface mutation : this.getSequence()) {
            mutation.process();
        }
        return this;
    }

    @Override
    public final void cancel() {
        // TODO Cut-off si la mutation n'a pas été processée.
        for (final MutationInterface mutation : this.getSequence()) {
            mutation.cancel();
        }
    }

}
