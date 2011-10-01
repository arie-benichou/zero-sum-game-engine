
package fr.designpattern.zerosumgames.concretisations.othello.moves;

import fr.designpattern.zerosumgames.abstractions.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.position.Position;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.move.MoveInterface;
import fr.designpattern.zerosumgames.abstractions.move.mutation.BoardMutation;
import fr.designpattern.zerosumgames.abstractions.move.mutation.BoardMutationInterface;

public final class OthelloNullMove implements OthelloMoveInterface {

    private final ContextInterface context;

    public final static OthelloMoveInterface from(final ContextInterface context) {
        return new OthelloNullMove(context);
    }

    private OthelloNullMove(final ContextInterface context) {
        this.context = context;
    }

    @Override
    public OthelloMoveInterface apply() {
        return this;
    }

    @Override
    public PositionInterface position() {
        return Position.NULL;
    }

    @Override
    public OthelloMoveInterface apply(final PositionInterface position) {
        return this;
    }

    @Override
    public BoardMutationInterface boardMutation(/*final SideInterface side, final BoardInterface board*/) {
        return BoardMutation.NULL;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.position() + ")";
    }

    @Override
    public int hashCode() {
        return this.position().hashCode();
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof OthelloNullMove)) return false;
        return true;
    }

    @Override
    public int compareTo(final MoveInterface that) {
        return 1;
    }

    @Override
    public ContextInterface context() {
        return this.context;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public MoveInterface value() {
        return this;
    }

    @Override
    public MoveInterface apply(final MoveInterface value) { // ?? TODO AbstractMoveInterface + ConcreteMoveInterface
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public MoveInterface apply(final Class<? extends MoveInterface> valueClass) { // ?? TODO AbstractMoveInterface + ConcreteMoveInterface
        throw new RuntimeException("Not implemented yet");
    }

}
