
package abstractions.immutable.cells;

import abstractions.immutable.pieces.PieceInterface;
import abstractions.immutable.positions.PositionInterface;

public final class BoardCell implements BoardCellInterface {

    /*-------------------------------------8<-------------------------------------*/

    public static class BoardGameCellFactory {

        public static BoardCell get(final PositionInterface position, final PieceInterface piece) {
            return new BoardCell(position, piece);
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
        return BoardGameCellFactory.get(position, piece);
    }

    private BoardCell(final PositionInterface position, final PieceInterface value) {
        this.value = value;
        this.position = position;
        this.hashCode = (17 * 31 + position.hashCode()) * 31 + value.hashCode();
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public BoardCellInterface apply() {
        return this;
    }

    @Override
    public BoardCellInterface apply(final PieceInterface value) {
        return value == null || value.equals(this.value()) ? this.apply() : from(this.position(), value);
    }

    @Override
    public BoardCellInterface apply(final PositionInterface position, final PieceInterface piece) {
        return piece.equals(this.value()) && position.equals(position) ? this.apply() : from(position, piece);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "( " + this.position() + " , " + this.value() + " ) ";
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean equals(final Object object) {
        if (object == this)
            return true;
        if (object == null)
            return false;
        if (!(object instanceof BoardCellInterface))
            return false;
        final BoardCellInterface that = (BoardCellInterface) object;
        if (that.hashCode() != this.hashCode())
            return false;
        return that.position() == this.position() && that.value() == this.value();
    }

    /*-------------------------------------8<-------------------------------------*/

}