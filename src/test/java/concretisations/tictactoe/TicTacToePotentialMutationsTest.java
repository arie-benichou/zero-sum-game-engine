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

package concretisations.tictactoe;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.immutable.context.gameplay.game.board.cell.piece.PieceManager;
import abstractions.immutable.context.gameplay.game.board.cell.piece.PieceManagerInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.Side;
import abstractions.old.cell.CellManager;
import abstractions.old.cell.CellManagerInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.mutation.MutationInterface;
import abstractions.old.position.PositionManager;
import abstractions.old.position.PositionManagerInterface;

// TODO à compléter
public final class TicTacToePotentialMutationsTest {

    private CellManagerInterface cellManager;

    @Before
    public void setUp() throws Exception {
        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(3, 3)));
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.tictactoe.pieces.TicTacToePieceSet.class);
        this.cellManager = new CellManager(positionManager, pieceManager);
    }

    @Test
    public void testGetPotentialMutations() { // NOPMD

        //System.out.println(this.cellManager);

        final Map<ManagedCellInterface, Set<MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Side.FIRST);

        for (final Entry<ManagedCellInterface, Set<MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.process();
                //System.out.println(this.cellManager);
                mutation.cancel();
                //System.out.println(this.cellManager);
            }
        }

        // TODO à tester unitairement
    }

    @After
    public void tearDown() throws Exception {
        this.cellManager = null; // NOPMD
    }

}
