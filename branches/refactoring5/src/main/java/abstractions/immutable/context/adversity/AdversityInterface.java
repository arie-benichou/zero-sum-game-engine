
package abstractions.immutable.context.adversity;

import abstractions.immutable.ImmutableInterface;

public interface AdversityInterface extends ImmutableInterface<AdversityInterface> {

    @Override
    public AdversityInterface apply();

}
