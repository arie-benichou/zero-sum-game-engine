
package concretisations.checkers;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.DimensionManager;
import abstractions.direction.Direction;
import abstractions.direction.DirectionManager;
import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.immutable.context.board.cell.piece.PieceManager;
import abstractions.immutable.context.board.cell.piece.PieceManagerInterface;
import abstractions.immutable.context.board.cell.piece.side.Side;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.old.cell.CellManager;
import abstractions.old.cell.CellManagerInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.mutation.MutationInterface;
import abstractions.old.position.PositionManager;
import abstractions.old.position.PositionManagerInterface;
import concretisations.checkers.mutations.CheckersMutationFactory;
import concretisations.checkers.pieces.CheckersPieceSet;

// TODO à compléter
public final class CheckerPotentialMutationsTest {

    private CellManagerInterface cellManager;

    @Before
    public void setUp() throws Exception {
        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(5, 5)));
        final PieceManagerInterface pieceManager = new PieceManager(CheckersPieceSet.class);
        this.cellManager = new CellManager(positionManager, pieceManager);
    }

    /*
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   | o |   |   |
    ---------------------
    | x |   |   | x |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------    
    */
    @Test
    public void testGetPotentialMutations1() {

        final SideInterface side = Side.FIRST;

        this.cellManager.getCell(4, 1).setPiece(side, CheckersPieceSet.MAN);
        this.cellManager.getCell(4, 4).setPiece(side, CheckersPieceSet.MAN);
        this.cellManager.getCell(3, 3).setPiece(side.getNextSide(), CheckersPieceSet.MAN);

        final Set<MutationInterface> expectedPotentialMutations = new HashSet<MutationInterface>();

        expectedPotentialMutations.add(CheckersMutationFactory.newWalkMutation(this.cellManager.getCell(4, 4), new Direction(-1, 1)));
        expectedPotentialMutations.add(CheckersMutationFactory.newJumpMutation(this.cellManager.getCell(4, 4), new Direction(-1, -1)));
        expectedPotentialMutations.add(CheckersMutationFactory.newWalkMutation(this.cellManager.getCell(4, 1), new Direction(-1, 1)));

        final Map<ManagedCellInterface, Set<MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Side.FIRST);

        //System.out.println(this.cellManager);

        // TODO  ! classer par type de mutations dans CellManager        
        final Set<MutationInterface> result = new HashSet<MutationInterface>();
        for (final Set<MutationInterface> cellPotentialMutations : potentialMutations.values()) {
            result.addAll(cellPotentialMutations);
        }

        Assert.assertTrue(expectedPotentialMutations.equals(result));

    }

    /*
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   | o |   |   |
    ---------------------
    | x |   |   | x |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------    
    */
    @Test
    public void testGetPotentialMutations2() {

        final SideInterface side = Side.FIRST;

        this.cellManager.getCell(4, 1).setPiece(side, CheckersPieceSet.MAN);
        this.cellManager.getCell(4, 4).setPiece(side, CheckersPieceSet.MAN);
        this.cellManager.getCell(3, 3).setPiece(side.getNextSide(), CheckersPieceSet.MAN);

        final Set<MutationInterface> expectedPotentialMutations = new HashSet<MutationInterface>();

        expectedPotentialMutations.add(CheckersMutationFactory.newWalkMutation(this.cellManager.getCell(4, 4), NamedDirection.TOP_RIGHT.value()));
        expectedPotentialMutations.add(CheckersMutationFactory.newJumpMutation(this.cellManager.getCell(4, 4), NamedDirection.TOP_LEFT.value()));
        expectedPotentialMutations.add(CheckersMutationFactory.newWalkMutation(this.cellManager.getCell(4, 1), NamedDirection.TOP_RIGHT.value()));

        final Map<ManagedCellInterface, Set<MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Side.FIRST);

        //System.out.println(this.cellManager);

        // TODO  ! classer par type de mutations dans CellManager        
        final Set<MutationInterface> result = new HashSet<MutationInterface>();
        for (final Set<MutationInterface> cellPotentialMutations : potentialMutations.values()) {
            result.addAll(cellPotentialMutations);
        }

        Assert.assertTrue(expectedPotentialMutations.equals(result));

        // TODO à tester unitairement
        for (final Entry<ManagedCellInterface, Set<MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.process();
                //System.out.println(this.cellManager);
                mutation.cancel();
                //System.out.println(this.cellManager);
            }
        }
    }

    /*
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------
    | x |   |   | x |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------    
    */
    @Test
    public void testGetPotentialMutations3() {

        final SideInterface side = Side.FIRST;

        this.cellManager.getCell(4, 1).setPiece(side, CheckersPieceSet.MAN);
        this.cellManager.getCell(4, 4).setPiece(side, CheckersPieceSet.MAN);

        final Set<MutationInterface> expectedPotentialMutations = new HashSet<MutationInterface>();

        expectedPotentialMutations.add(CheckersMutationFactory.newWalkMutation(this.cellManager.getCell(4, 4), NamedDirection.TOP_LEFT.value()));
        expectedPotentialMutations.add(CheckersMutationFactory.newWalkMutation(this.cellManager.getCell(4, 4), NamedDirection.TOP_RIGHT.value()));
        expectedPotentialMutations.add(CheckersMutationFactory.newWalkMutation(this.cellManager.getCell(4, 1), NamedDirection.TOP_RIGHT.value()));

        final Map<ManagedCellInterface, Set<MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Side.FIRST);

        //System.out.println(this.cellManager);

        // TODO  ! classer par type de mutations dans CellManager        
        final Set<MutationInterface> result = new HashSet<MutationInterface>();
        for (final Set<MutationInterface> cellPotentialMutations : potentialMutations.values()) {
            result.addAll(cellPotentialMutations);
        }

        Assert.assertTrue(expectedPotentialMutations.equals(result));

        // TODO à tester unitairement
        for (final Entry<ManagedCellInterface, Set<MutationInterface>> mutations : potentialMutations.entrySet()) {
            for (final MutationInterface mutation : mutations.getValue()) {
                mutation.process();
                //System.out.println(this.cellManager);
                mutation.cancel();
                //System.out.println(this.cellManager);
            }
        }
    }

    /*
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   |   | o |   |
    ---------------------
    |   |   | o |   |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------
    |   |   |   |   |   |
    ---------------------
    */

    @Test
    public void testGetPotentialMutations4() {

        final SideInterface side = Side.SECOND;

        this.cellManager.getCell(2, 4).setPiece(side, CheckersPieceSet.MAN);
        this.cellManager.getCell(3, 3).setPiece(side, CheckersPieceSet.MAN);

        final Map<ManagedCellInterface, Set<MutationInterface>> potentialMutations = this.cellManager.getPotentialMutations(Side.FIRST);

        Assert.assertTrue(potentialMutations.isEmpty());

    }

    @After
    public void tearDown() throws Exception {
        this.cellManager = null; // NOPMD 
    }

}
