/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package definitions.moves;

import java.util.Map;

import com.google.common.collect.Maps;

import context.ContextInterface;
import context.entity.game.board.cell.piece.Piece;
import context.entity.game.board.cell.piece.PieceInterface;
import context.entity.game.board.cell.piece.type.PieceType;
import context.entity.game.board.cell.position.PositionInterface;
import context.entity.game.board.mutation.BoardMutation;
import context.entity.game.board.mutation.BoardMutationInterface;
import context.event.MoveInterface;
import definitions.evaluations.Connect4StaticEvaluation;
import definitions.pieces.Connect4Pawn;

public final class Connect4Move implements MoveInterface {

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public ContextInterface context() {
        return this.context;
    }

    private final ContextInterface context;

    /*-------------------------------------8<-------------------------------------*/

    private final PositionInterface position;

    @Override
    public PositionInterface position() {
        return this.position;
    }

    /*-------------------------------------8<-------------------------------------*/

    private BoardMutationInterface mutations = null;

    private Double heuristicEvaluation = null;

    @Override
    public BoardMutationInterface boardMutation() {
        if (this.mutations == null) {
            final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();
            mutations.put(this.position(), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
            this.mutations = BoardMutation.from(mutations);
        }
        return this.mutations;
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static Connect4Move from(final ContextInterface context, final PositionInterface position) {
        return new Connect4Move(context, position);
    }

    private Connect4Move(final ContextInterface context, final PositionInterface position) {
        this.context = context;
        this.position = position;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public Connect4Move apply() {
        return this;
    }

    @Override
    public Connect4Move apply(final PositionInterface position) { // TODO apply context
        return position == null || position.equals(this.position()) ? this.apply() : from(this.context(), position);
    }

    /*-------------------------------------8<-------------------------------------*/

    /*
    private List<BoardCellInterface> computeAvailableNeighbours() {
        final List<BoardCellInterface> availableNeighbours = Lists.newArrayList();
        for (final BoardCellInterface cell : this.context().game().board().neighbourhoodOf(this.position()).values())
            if (!cell.isNull()) availableNeighbours.add(cell);
        return availableNeighbours;
    }

    private Double computeNeighbourhoodHeuristic() {
        if (null == this.heuristicEvaluation) {
            Double heuristicEvaluation = 0.0;
            for (final BoardCellInterface cell : this.computeAvailableNeighbours()) {
                heuristicEvaluation += 1;
                if (cell.value().side().equals(this.context().side())) heuristicEvaluation += 1.0 / 8;
            }
            this.heuristicEvaluation = heuristicEvaluation;
        }
        return this.heuristicEvaluation;
    }
    */

    /*
    public int computeNeighbourhoodHeuristic() {
        if (-1 == this.heuristicEvaluation) {
            int heuristicEvaluation = 0;
            for (final BoardCellInterface cell : this.context().game().board().neighbourhoodOf(this.position()).values())
                //if (!(cell.isNull() || cell.value().side().equals(this.context().side().opposite()))) heuristicEvaluation += 1;
                if (!cell.isNull()) heuristicEvaluation += 1;
            this.heuristicEvaluation = heuristicEvaluation;
        }
        else {
            //System.err.println("Lazy init is paying off...");
        }

        return this.heuristicEvaluation;
    }
    */

    @Override
    public Double heuristicEvaluation() {
        if (null == this.heuristicEvaluation) this.heuristicEvaluation = Connect4StaticEvaluation.from(this);
        return this.heuristicEvaluation;
    }

    @Override
    public int compareTo(final MoveInterface that) {

        /*
        final int p1 = MyStaticEvaluationFunction2.computeCellPotential(this.context().side(), this.context().game().board(), this.context().game().board()
                .cell(this.position()));

        final int p2 = MyStaticEvaluationFunction2.computeCellPotential(that.context().side(), that.context().game().board(), that.context().game().board()
                .cell(that.position()));
        */

        // TODO ?? utiliser le contexte pour retrouver la fonction d'évaluation statique ou bien trier les coups en dehors et ne pas utiliser l'interface compareTo

        //return (int) (1000 * (((Connect4Move) that).computeNeighbourhoodHeuristic() - this.computeNeighbourhoodHeuristic()));
        //return ((Connect4Move) that).computeNeighbourhoodHeuristic() - this.computeNeighbourhoodHeuristic();

        //return Connect4StaticEvaluation.from(that).compareTo(Connect4StaticEvaluation.from(this));

        //return ((Connect4Move) that).computeNeighbourhoodHeuristic() - this.computeNeighbourhoodHeuristic();

        return that.heuristicEvaluation().compareTo(this.heuristicEvaluation());

    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean isNull() {
        return false;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public int hashCode() {
        return this.position().hashCode();
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof Connect4Move)) return false;
        final Connect4Move that = (Connect4Move) object;
        if (that.hashCode() != this.hashCode()) return false;
        return that.position().equals(this.position());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.position() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public MoveInterface value() { // !! TODO ConcreteMoveInterface
        return this;
    }

    @Override
    public MoveInterface apply(final MoveInterface value) { // !! TODO AbstractMoveInterface + ConcreteMoveInterface
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public MoveInterface apply(final Class<? extends MoveInterface> valueClass) { // !! TODO AbstractMoveInterface + ConcreteMoveInterface
        throw new RuntimeException("Not implemented yet");
    }

    /*-------------------------------------8<-------------------------------------*/

}