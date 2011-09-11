
package abstractions.immutable.board.cell;

import java.util.Map;

import abstractions.immutable.board.cell.piece.Piece;
import abstractions.immutable.board.cell.piece.PieceInterface;
import abstractions.immutable.board.cell.piece.side.Side;
import abstractions.immutable.board.cell.piece.type.PieceType;
import abstractions.immutable.board.cell.piece.type._Pawn;
import abstractions.immutable.board.cell.position.Position;
import abstractions.immutable.board.cell.position.PositionInterface;

import com.google.common.collect.Maps;

public final class BoardCell implements BoardCellInterface {

    /*-------------------------------------8<-------------------------------------*/

    public final static BoardCellInterface NULL = new BoardCell(Position.NULL, Piece.NULL);

    /*-------------------------------------8<-------------------------------------*/

    private final static int computeHashCode(final PositionInterface position, final PieceInterface piece) {
        return (17 * 31 + position.hashCode()) * 31 + piece.hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    private final static class Factory {

        private final static Map<Integer, BoardCellInterface> CACHE = Maps.newHashMap();

        public static BoardCellInterface get(PositionInterface position, PieceInterface piece) {
            if (position == null) position = Position.NULL;
            if (piece == null) piece = Piece.NULL;
            if (position.equals(Position.NULL) && piece.equals(Piece.NULL)) return NULL;
            final int address = computeHashCode(position, piece);
            BoardCellInterface instance = CACHE.get(address);
            if (instance == null) {
                instance = new BoardCell(position, piece);
                CACHE.put(address, instance);
            }
            return instance;
        }

    }

    /*-------------------------------------8<-------------------------------------*/

    private final PositionInterface position;

    @Override
    public PositionInterface position() {
        return this.position;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final PieceInterface value;

    @Override
    public PieceInterface value() {
        return this.value;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final int hashCode;

    @Override
    public final int hashCode() {
        return this.hashCode;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static BoardCellInterface from(final PositionInterface position, final PieceInterface piece) {
        return NULL.apply(position, piece);
    }

    private BoardCell(final PositionInterface position, final PieceInterface value) {
        this.value = value;
        this.position = position;
        this.hashCode = computeHashCode(position, value);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public BoardCellInterface apply() {
        return this;
    }

    @Override
    public BoardCellInterface apply(final PieceInterface value) {
        return this.value().equals(value) ? this.apply() : Factory.get(this.position(), value);
    }

    @Override
    public BoardCellInterface apply(final PositionInterface position) {
        return this.position().equals(position) ? this.apply() : Factory.get(position, this.value());
    }

    @Override
    public BoardCellInterface apply(final PositionInterface position, final PieceInterface value) {
        return this.value().equals(value) && this.position().equals(position) ? this.apply() : Factory.get(position, value);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof BoardCellInterface)) return false;
        final BoardCellInterface that = (BoardCellInterface) object;
        if (that.hashCode() != this.hashCode()) return false;
        return that.position() == this.position() && that.value() == this.value();
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.value() + ", " + this.position() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

    public static void main(final String[] args) {
        System.out.println(BoardCell.NULL);
        System.out.println(BoardCell.from(Position.from(1, 1), Piece.from(Side.from(1), PieceType.from(_Pawn.class))));
    }

}