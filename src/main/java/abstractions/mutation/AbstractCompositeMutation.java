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
import abstractions.context.ContextInterface;

public abstract class AbstractCompositeMutation extends AbstractMutation {

    private transient List<MutationInterface> sequence;

    public AbstractCompositeMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType) {
        super(cell, mutationType);
    }

    public final List<MutationInterface> getSequence(final ContextInterface context) {
        if (this.sequence == null) {
            this.sequence = this.sequence(context);
        }
        return this.sequence;
    }

    @Override
    public void computeSequence(final ContextInterface context) {
        this.getSequence(context);
    }

    @Override
    public final MutationInterface process(final ContextInterface context) {
        for (final MutationInterface mutation : this.getSequence(context)) {
            mutation.process(context);
        }
        return this;
    }

    @Override
    public final MutationInterface cancel(final ContextInterface context) {
        // TODO cut-off si la mutation n'a pas été processée.
        for (final MutationInterface mutation : this.getSequence(context)) {
            mutation.cancel(context);
        }
        return this;
    }

    @Override
    public abstract int compareTo(MutationInterface o);

    protected abstract List<MutationInterface> sequence(final ContextInterface context);

}