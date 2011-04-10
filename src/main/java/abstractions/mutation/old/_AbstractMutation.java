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

package abstractions.mutation.old;

import java.util.List;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

public class _AbstractMutation implements MutationInterface {

    private final ManagedCellInterface cell;
    private final SideInterface side;
    private final PieceTypeInterface pieceType;

    public _AbstractMutation(final ManagedCellInterface cell, final SideInterface side, final PieceTypeInterface pieceType) {
        this.cell = cell;
        this.side = side;
        this.pieceType = pieceType;
    }

    @Override
    public final ManagedCellInterface getCell() {
        return this.cell;
    }

    @Override
    public SideInterface getSide() {
        return this.side;
    }

    @Override
    public PieceTypeInterface getPieceType() {
        return this.pieceType;
    }

    @Override
    public void process() {
        for (final MutationInterface atomicMutation : this.getSequence()) {
            atomicMutation.process();
        }
    }

    @Override
    public void cancel() {
        for (final MutationInterface atomicMutation : this.getSequence()) {
            atomicMutation.cancel();
        }
    }

    private transient List<MutationInterface> sequence = null;

    protected abstract List<MutationInterface> generateSequence();

    @Override
    protected List<MutationInterface> getSequence() {
        if (this.sequence == null) {
            this.sequence = this.generateSequence();
        }
        return this.sequence;
    }

}
