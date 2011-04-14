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

import abstractions.cell.CellManager;
import abstractions.cell.CellManagerInterface;
import abstractions.cell.ManagedCellInterface;
import abstractions.dimension.API.DimensionFactory;
import abstractions.direction.DirectionManager;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceManager;
import abstractions.piece.PieceManagerInterface;
import abstractions.position.PositionManager;
import abstractions.position.PositionManagerInterface;
import abstractions.side.Sides;

public class TicTacToePotentialMutationsTest {

    private CellManagerInterface cellManager;

    @Before
    public void setUp() throws Exception {

        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(DimensionFactory.Dimension(3, 3)));
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.tictactoe.pieces.TicTacToePieceSet.class);
        this.cellManager = new CellManager(positionManager, pieceManager);

    }

    @Test
    public void testGetPotentialMutations() {

        /*
        this.cellManager.getCell(1, 1).setPiece(Sides.FIRST, TicTacToePieceSet.PAWN);
        this.cellManager.getCell(1, 2).setPiece(Sides.FIRST, TicTacToePieceSet.PAWN);
        this.cellManager.getCell(2, 1).setPiece(Sides.FIRST, TicTacToePieceSet.PAWN);
        */

        /*
        final Set<MutationInterface> expectedLegalMutations = new HashSet<MutationInterface>();
        expectedLegalMutations.add(new NewPawnMutation(this.cellManager.getCell(2, 2), Sides.FIRST));
        */

        System.out.println(this.cellManager);

        final Map<ManagedCellInterface, Set<? extends MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Sides.FIRST);

        for (final Entry<ManagedCellInterface, Set<? extends MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.process();
                System.out.println(this.cellManager);
                mutation.cancel();
                System.out.println(this.cellManager);
            }
        }

        //Assert.assertTrue(potentialMutations.equals(expectedPotentialMutations));

    }

    @After
    public void tearDown() throws Exception {
        this.cellManager = null;
    }

}
