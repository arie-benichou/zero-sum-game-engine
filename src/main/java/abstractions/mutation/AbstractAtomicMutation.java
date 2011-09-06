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

import abstractions.cell.ManagedCellInterface;
import abstractions.context.ContextInterface;

public abstract class AbstractAtomicMutation extends AbstractMutation {

    // TODO ! coder les mutations de bases dans la cellule
    public AbstractAtomicMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType) {
        super(cell, mutationType);
    }

    @Override
    public abstract MutationInterface process(ContextInterface context);

    @Override
    public final MutationInterface cancel(final ContextInterface context) {
        context.getCellManager().getCell(this.getPosition()).setPiece(this.getSavedSate());
        return this;
    }

    @Override
    public int compareTo(final MutationInterface o) {
        return 0;
    }

}
