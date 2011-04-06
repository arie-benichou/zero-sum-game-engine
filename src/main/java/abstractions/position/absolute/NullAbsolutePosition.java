
package abstractions.position.absolute;

final class NullAbsolutePosition extends AbstractAbsolutePosition {

    public NullAbsolutePosition() {
        super(0, 0);
    }

    @Override
    public final boolean isNull() {
        return true;
    }

}