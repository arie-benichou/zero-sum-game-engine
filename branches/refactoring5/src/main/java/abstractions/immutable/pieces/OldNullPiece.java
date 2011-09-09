
package abstractions.immutable.pieces;

import abstractions.immutable.side.SideInterface;

public class OldNullPiece implements PieceInterface {

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
    public OldPieceTypeInterface type() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PieceInterface apply(final OldPieceTypeInterface type) {
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public PieceInterface apply(final SideInterface side, final OldPieceTypeInterface type) {
        // TODO Auto-generated method stub
        return this;
    }

}
