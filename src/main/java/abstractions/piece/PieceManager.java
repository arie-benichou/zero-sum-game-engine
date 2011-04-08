
package abstractions.piece;

import java.util.Map;
import java.util.Set;

import abstractions.piece.mocks.PieceSet;
import abstractions.side.SideInterface;
import abstractions.side.Sides;

import com.google.common.collect.Maps;

public class PieceManager implements PieceManagerInterface {

    private final PieceSetFactoryInterface pieceSetFactory = new PieceSetFactory();
    private Map<Integer, PieceInterface> pieces;

    public <T extends Enum<T> & PieceTypeInterface> PieceManager(Class<T> pieceTypeSetClass) {
        //Set<PieceInterface> pieceSet = this.pieceSetFactory.newPieceSet(pieceTypeSetClass);
        /*
        this.pieces = Maps.newHashMapWithExpectedSize(pieceSet.size());
        for (PieceInterface piece : pieceSet) {
            System.out.println(piece);
            this.pieces.put(this.hash(piece.getSide(), piece.getType()), piece);
        }
        */
    }

    private final int hash(final SideInterface side, final PieceTypeInterface type) {
        return side.hashCode() + type.hashCode();
    }

    public PieceInterface getPiece(SideInterface side, PieceTypeInterface type) {
        PieceInterface piece = this.pieces.get(this.hash(side, type));
        if (piece == null) {
            throw new IllegalPieceException(side, type);
        }
        return piece;
    }

    public static void main(String[] args) {

        //PieceManager pieceManager = new PieceManager(PieceSet.class);
        
        PieceSetFactory factory = new PieceSetFactory();
        Set<PieceInterface> set = factory.newPieceSet(PieceSet.class);

        //System.out.println(pieceManager.getPiece(Sides.FIRST, PieceSet.PAWN));

    }

}
