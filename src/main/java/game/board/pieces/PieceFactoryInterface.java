
package game.board.pieces;

public interface PieceFactoryInterface {

    public final static PieceInterface NULL_PIECE = new NullPiece();

    PieceInterface nullPiece();

}
