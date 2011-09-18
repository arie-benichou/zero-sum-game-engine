
package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.adversity;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;

public interface AdversityInterface extends ImmutableInterface<AdversityInterface> {

    @Override
    public AdversityInterface apply();

}
