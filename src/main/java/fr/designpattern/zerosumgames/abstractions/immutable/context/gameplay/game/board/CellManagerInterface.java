
package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.CellInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.MutationInterface;

public interface CellManagerInterface<ADDRESS, VALUE> extends ImmutableInterface<CellManagerInterface<ADDRESS, VALUE>> {

    CellInterface<VALUE> cell(final ADDRESS address);

    public CellManagerInterface<ADDRESS, VALUE> apply(MutationInterface<ADDRESS, VALUE> value);

    @Override
    public CellManagerInterface<ADDRESS, VALUE> apply();

}