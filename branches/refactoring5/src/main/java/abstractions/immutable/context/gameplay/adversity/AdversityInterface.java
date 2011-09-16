
package abstractions.immutable.context.gameplay.adversity;

import abstractions.immutable.ImmutableInterface;

public interface AdversityInterface extends ImmutableInterface<AdversityInterface> {

    @Override
    public AdversityInterface apply();

}
