
package abstractions.immutable.opponents;

import abstractions.immutable.ImmutableInterface;

public interface OpponentsInterface extends ImmutableInterface<OpponentsInterface> {

    @Override
    public OpponentsInterface apply();

}
