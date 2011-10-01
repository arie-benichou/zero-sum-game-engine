
package fr.designpattern.zerosumgames.abstractions.context.game.board;

import fr.designpattern.zerosumgames.abstractions.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.CellInterface;
import fr.designpattern.zerosumgames.abstractions.move.mutation.MutationInterface;

public interface CellManagerInterface<ADDRESS, VALUE> extends ImmutableInterface<CellManagerInterface<ADDRESS, VALUE>> {

    CellInterface<VALUE> cell(final ADDRESS address);

    public CellManagerInterface<ADDRESS, VALUE> apply(MutationInterface<ADDRESS, VALUE> value);

    @Override
    public CellManagerInterface<ADDRESS, VALUE> apply();

}