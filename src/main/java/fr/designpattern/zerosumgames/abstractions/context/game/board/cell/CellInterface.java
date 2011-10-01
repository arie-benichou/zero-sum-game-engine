
package fr.designpattern.zerosumgames.abstractions.context.game.board.cell;

import fr.designpattern.zerosumgames.abstractions.ImmutableInterface;

public interface CellInterface<VALUE> extends ImmutableInterface<CellInterface<VALUE>> {

    VALUE value();

    CellInterface<VALUE> apply(VALUE value);

    @Override
    CellInterface<VALUE> apply();

}
