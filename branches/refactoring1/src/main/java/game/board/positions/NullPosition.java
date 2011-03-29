
package game.board.positions;

final class NullPosition extends AbstractPosition {

    public NullPosition() {
        super(0, 0);
    }

    @Override
    public final boolean isNull() {
        return true;
    }

}