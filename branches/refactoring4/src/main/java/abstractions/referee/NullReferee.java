package abstractions.referee;

import abstractions.mutation.MutationInterface;

// TODO à virer
public class NullReferee implements RefereeInterface {

    @Override
    public boolean isGameOver(final MutationInterface lastMutations) {
        return false;
    }

    @Override
    public boolean isNullMoveAllowed() {
        return false;
    }

}
