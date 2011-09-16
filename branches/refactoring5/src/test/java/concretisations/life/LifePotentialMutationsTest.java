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
import concretisations.life.pieces.LifePieceSet;

// TODO à compléter
public final class LifePotentialMutationsTest {

    private CellManagerInterface cellManager;

    @Before
    public void setUp() throws Exception {
        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(10, 10)));
        final PieceManagerInterface pieceManager = new PieceManager(LifePieceSet.class);
        this.cellManager = new CellManager(positionManager, pieceManager);
    }

    //@Test
    public void testGetPotentialMutations1() { // NOPMD
        this.cellManager.getCell(4, 5).setPiece(Side.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(5, 5).setPiece(Side.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(6, 5).setPiece(Side.FIRST, LifePieceSet.PAWN);

        final Map<ManagedCellInterface, Set<MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Side.FIRST);
        for (final Entry<ManagedCellInterface, Set<MutationInterface>> entry : potentialMutations.entrySet()) {
            System.out.println(entry.getValue());
        }

        System.out.println(this.cellManager);

        for (final Entry<ManagedCellInterface, Set<MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.process();
            }
        }

        System.out.println(this.cellManager);

        for (final Entry<ManagedCellInterface, Set<MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.cancel();
            }
        }

        System.out.println(this.cellManager);
        //TODO ! à tester unitairement
    }

    //@Test
    public void testGetPotentialMutations2() { // NOPMD
        this.cellManager.getCell(4, 5).setPiece(Side.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(5, 4).setPiece(Side.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(5, 5).setPiece(Side.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(5, 6).setPiece(Side.FIRST, LifePieceSet.PAWN);

        final Map<ManagedCellInterface, Set<MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Side.FIRST);
        for (final Entry<ManagedCellInterface, Set<MutationInterface>> entry : potentialMutations.entrySet()) {
            System.out.println(entry.getValue());
        }

        System.out.println(this.cellManager);

        for (final Entry<ManagedCellInterface, Set<MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.process();
            }
        }

        System.out.println(this.cellManager);

        for (final Entry<ManagedCellInterface, Set<MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.cancel();
            }
        }

        System.out.println(this.cellManager);

        //TODO ! à tester unitairement
    }

    //@Test
    public void testGetPotentialMutations3() { // NOPMD
        this.cellManager.getCell(4, 5).setPiece(Side.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(5, 4).setPiece(Side.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(5, 5).setPiece(Side.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(5, 6).setPiece(Side.FIRST, LifePieceSet.PAWN);

        for (int n = 0; n < 12; ++n) {

            System.out.println("Génération " + n);
            System.out.println(this.cellManager);

            final Map<ManagedCellInterface, Set<MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Side.FIRST);
            for (final Entry<ManagedCellInterface, Set<MutationInterface>> mutations : potentialMutations.entrySet()) {
                for (final MutationInterface mutation : mutations.getValue()) {
                    mutation.process();
                }
            }

        }

        //TODO ! à tester unitairement
    }

    @Test
    public void testGetPotentialMutations4() { // NOPMD
        this.cellManager.getCell(1, 2).setPiece(Side.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(2, 3).setPiece(Side.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(3, 1).setPiece(Side.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(3, 2).setPiece(Side.FIRST, LifePieceSet.PAWN);
        this.cellManager.getCell(3, 3).setPiece(Side.FIRST, LifePieceSet.PAWN);

        for (int n = 0; n < 29; ++n) {

            System.out.println("Génération " + n);
            System.out.println(this.cellManager);

            final Map<ManagedCellInterface, Set<MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Side.FIRST);
            for (final Entry<ManagedCellInterface, Set<MutationInterface>> mutations : potentialMutations.entrySet()) {
                for (final MutationInterface mutation : mutations.getValue()) {
                    mutation.process();
                }
            }

        }
        //TODO ! à tester unitairement
    }

    @After
    public void tearDown() throws Exception {
        this.cellManager = null; // NOPMD
    }

}
