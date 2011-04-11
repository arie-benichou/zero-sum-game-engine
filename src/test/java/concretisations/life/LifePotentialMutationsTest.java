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

package concretisations.life;

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
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceManager;
import abstractions.piece.PieceManagerInterface;
import abstractions.position.PositionManager;
import abstractions.position.PositionManagerInterface;
import abstractions.side.Sides;
import concretisations.life.pieces.LifePieceSet;

public class LifePotentialMutationsTest {

    private CellManagerInterface cellManager;

    @Before
    public void setUp() throws Exception {

        final PositionManagerInterface positionManager = new PositionManager(DimensionFactory.Dimension(9, 9));
        final PieceManagerInterface pieceManager = new PieceManager(LifePieceSet.class);
        this.cellManager = new CellManager(positionManager, pieceManager);

    }

    @Test
    public void testGetPotentialMutations1() {

        this.cellManager.getCell(4, 5).setPiece(Sides.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(5, 5).setPiece(Sides.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(6, 5).setPiece(Sides.FIRST, LifePieceSet.PAWN);

        final Map<ManagedCellInterface, Set<? extends MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Sides.FIRST);
        for (final Entry<ManagedCellInterface, Set<? extends MutationInterface>> entry : potentialMutations.entrySet()) {
            System.out.println(entry.getValue());
        }

        System.out.println(this.cellManager);

        for (final Entry<ManagedCellInterface, Set<? extends MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.process();
            }
        }

        System.out.println(this.cellManager);

        for (final Entry<ManagedCellInterface, Set<? extends MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.cancel();
            }
        }

        System.out.println(this.cellManager);

    }

    @Test
    public void testGetPotentialMutations2() {

        this.cellManager.getCell(4, 5).setPiece(Sides.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(5, 4).setPiece(Sides.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(5, 5).setPiece(Sides.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(5, 6).setPiece(Sides.FIRST, LifePieceSet.PAWN);

        final Map<ManagedCellInterface, Set<? extends MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Sides.FIRST);
        for (final Entry<ManagedCellInterface, Set<? extends MutationInterface>> entry : potentialMutations.entrySet()) {
            System.out.println(entry.getValue());
        }

        System.out.println(this.cellManager);

        for (final Entry<ManagedCellInterface, Set<? extends MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.process();
            }
        }

        System.out.println(this.cellManager);

        for (final Entry<ManagedCellInterface, Set<? extends MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.cancel();
            }
        }

        System.out.println(this.cellManager);

    }

    @Test
    public void testGetPotentialMutations3() {

        this.cellManager.getCell(4, 5).setPiece(Sides.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(5, 4).setPiece(Sides.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(5, 5).setPiece(Sides.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(5, 6).setPiece(Sides.FIRST, LifePieceSet.PAWN);

        for (int n = 0; n < 12; ++n) {

            System.out.println("Génération " + n);
            System.out.println(this.cellManager);

            final Map<ManagedCellInterface, Set<? extends MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Sides.FIRST);
            for (final Entry<ManagedCellInterface, Set<? extends MutationInterface>> mutations : potentialMutations.entrySet()) {
                for (final MutationInterface mutation : mutations.getValue()) {
                    mutation.process();
                }
            }

        }

    }

    @After
    public void tearDown() throws Exception {
        this.cellManager = null;
    }

}
