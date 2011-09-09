
package abstractions.immutable.mutations;

import java.util.Map;

import abstractions.immutable.cells.MyCellManager;
import abstractions.immutable.pieces.PieceInterface;
import abstractions.immutable.pieces.PieceTypeInterface;
import abstractions.immutable.positions.Position;
import abstractions.immutable.positions.PositionInterface;
import abstractions.immutable.side.SideInterface;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public class MyImmutableCellManagerMutations implements MutationsInterface<PositionInterface, PieceInterface> {

    Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

    @Override
    public Map<PositionInterface, PieceInterface> mutations() {

        final PieceInterface value1 = new PieceInterface() {

            @Override
            public PieceTypeInterface type() {
                return null;
            }

            @Override
            public SideInterface side() {
                return null;
            }

            @Override
            public String toString() {
                return "1";
            };

        };

        final PieceInterface value2 = new PieceInterface() {

            @Override
            public PieceTypeInterface type() {
                return null;
            }

            @Override
            public SideInterface side() {
                return null;
            }

            @Override
            public String toString() {
                return "2";
            };

        };

        this.mutations.put(new Position(0, 0), value1);
        this.mutations.put(new Position(0, 1), value2);

        return ImmutableMap.copyOf(this.mutations);

    }

    public static void main(final String[] args) {
        context();
    }

    private static void context() {

        // TODO Objey Move = {Type, Mutations} -> typer les groupes de mutations et non une mutation

        final MyCellManager board = new MyCellManager(2, 3);
        System.out.println(board);

        final Map<PositionInterface, PieceInterface> mutations = new MyImmutableCellManagerMutations().mutations();

        // TODO !!! ajouter un test unitaire pour apply de cell et de cellManager
        final MyCellManager newBoard = board.apply(mutations);
        // TODO context.apply(MoveInterface move)

        System.out.println(board);

        System.out.println(newBoard);

    }

}