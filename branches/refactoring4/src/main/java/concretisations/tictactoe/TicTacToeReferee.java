
package concretisations.tictactoe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import abstractions.cell.ManagedCellInterface;
import abstractions.context.ContextInterface;
import abstractions.direction.DirectionInterface;
import abstractions.direction.DirectionManager;
import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.NullMutation;
import abstractions.referee.RefereeInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;

public class TicTacToeReferee implements RefereeInterface {

    /*
    private final static RefereeInterface INSTANCE = new Connect4Referee();

    private Connect4Referee() {}
    
    public final static RefereeInterface getInstance() {
        return INSTANCE;
    }
    */

    private final static int CONNECTIONS = 3;

    //TODO à améliorer
    private final static NamedDirection[][] DIRECTIONS = {
            { DirectionManager.NamedDirection.LEFT, DirectionManager.NamedDirection.RIGHT },
            { DirectionManager.NamedDirection.TOP, DirectionManager.NamedDirection.BOTTOM },
            { DirectionManager.NamedDirection.TOP_RIGHT, DirectionManager.NamedDirection.BOTTOM_LEFT },
            { DirectionManager.NamedDirection.BOTTOM_RIGHT, DirectionManager.NamedDirection.TOP_LEFT },
    };

    protected int computeConnection(final MutationInterface lastMutation, final DirectionInterface direction) {
        int connected;
        ManagedCellInterface cell = lastMutation.getCell();
        for (connected = 0; connected <= CONNECTIONS; ++connected) {
            cell = cell.getNeighbour(direction);
            if (cell.isNull() || cell.isEmpty() || cell.getPiece().getSide() != lastMutation.getCell().getPiece().getSide())
                break;
        }
        return connected;
    }

    @Override
    public boolean isNullMoveAllowed() {
        return false;
    }

    // TODO devrait en fait retourner la side du winner
    // TODO le contexte devrait suffire
    @Override
    public boolean isGameOver(ContextInterface context) {
        
        boolean isGameOver = false;
        for (final NamedDirection[] directions : DIRECTIONS) {
            // TODO getLastMove() dans context
            final int connections = this.computeConnection(context.getLastPlayerMove(context.getCurrentSide()), directions[0].value()) + 1
                    + this.computeConnection(context.getLastPlayerMove(context.getCurrentSide()), directions[1].value());
            if (connections >= CONNECTIONS) {
                //System.out.println("Game Over");
                isGameOver = true;
                break;
            }
        }

        if (!isGameOver) {
            Iterator<ManagedCellInterface> it = context.getCellManager().iterator();
            it.next();
            while (it.hasNext())
                if (it.next().isEmpty())
                    return false;
            return true;
        }

        //System.out.println(isGameOver);
        
        return isGameOver;

    }

    /*
    @Override
    public SideInterface getWinner(ContextInterface context) { // TODO ne devrait pas exister avec isGameOver() -> checkGameOver()
        MutationInterface lastMutation = context.getLastPlayerMove(context.getSideToPlay());
        
        //System.out.println(lastMutation);
        
        boolean isGameOver = false;
        for (final NamedDirection[] directions : DIRECTIONS) {
            final int connections = this.computeConnection(lastMutation, directions[0].value()) + 1
                    + this.computeConnection(lastMutation, directions[1].value());
            if (connections >= CONNECTIONS) {
                isGameOver = true;
                break;
            }
        }
        if (isGameOver)
            return context.getSideToPlay();
        return Sides.NULL;
    }
    */

    @Override
    public int getHeuristicEvaluation(ContextInterface context, SideInterface side) {
        MutationInterface lastMutation = context.getLastPlayerMove(side);
        int connections = 0;
        for (final NamedDirection[] directions : DIRECTIONS) {
            connections += this.computeConnection(lastMutation, directions[0].value()) + 1 + this.computeConnection(lastMutation, directions[1].value());
        }
        return connections;
    }

    @Override
    public List<MutationInterface> getLegalMoves(ContextInterface context, SideInterface side) {

        //TODO à améliorer
        List<MutationInterface> legalMoves = new ArrayList<MutationInterface>();
        Collection<Set<MutationInterface>> mutations = context.getCellManager().getPotentialMutations(side).values();
        for (Set<MutationInterface> e : mutations)
            legalMoves.addAll(e);

        return legalMoves;
    }

}
