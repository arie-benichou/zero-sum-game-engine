
package abstractions.immutable.pieces;

import abstractions.immutable.side.SideInterface;

public class _NullPiece implements PieceInterface {

    @Override
    public PieceInterface apply() {
        return this;
    }

    @Override
    public SideInterface side() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PieceInterface apply(final SideInterface side) {
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public PieceTypeInterface type() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PieceInterface apply(final PieceTypeInterface type) {
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public PieceInterface apply(final SideInterface side, final PieceTypeInterface type) {
        // TODO Auto-generated method stub
        return this;
    }

}
