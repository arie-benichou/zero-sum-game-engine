
package abstractions.piece;

import abstractions.side.SideInterface;

public class PieceManager implements PieceManagerInterface {

    public PieceManager(PieceSetFactoryInterface PieceSetFactory) {}
    
    private final int hash(final SideInterface side, final PieceTypeInterface pieceType) {
        return side.hashCode() + pieceType.hashCode();
    }    
    

    public PieceInterface getPiece(SideInterface Side, PieceTypeInterface PieceType) {
        PieceInterface piece = this.pieces.get(this.hash(side, pieceType));
        if (piece == null) {
            throw new IllegalPieceException(side, pieceType);
        }
        return piece;
    }

}
