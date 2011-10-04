
package definitions.evaluations;

import context.ContextInterface;
import context.entity.game.board.BoardInterface;
import context.entity.game.board.cell.BoardCellInterface;
import context.entity.game.board.cell.piece.Piece;
import context.entity.game.board.cell.piece.side.SideInterface;
import context.entity.game.board.cell.piece.type.PieceType;
import context.entity.game.board.direction.Direction;
import context.entity.game.board.direction.DirectionInterface;
import context.event.MoveInterface;
import definitions.pieces.Connect4Pawn;

public class Connect4StaticEvaluation implements StaticEvaluationInterface {

    /*-------------------------------------8<-------------------------------------*/

    // TODO à mettre dans Direction
    private final static DirectionInterface[][] PLANES = {
            { Direction.TOP_LEFT, Direction.BOTTOM_RIGHT },
            { Direction.TOP, Direction.BOTTOM },
            { Direction.LEFT, Direction.RIGHT },
            { Direction.TOP_RIGHT, Direction.BOTTOM_LEFT }
    };

    /*-------------------------------------8<-------------------------------------*/

    private final static StaticEvaluationInterface INSTANCE = new Connect4StaticEvaluation();

    /*-------------------------------------8<-------------------------------------*/

    public final static Double from(final ContextInterface context) {
        return INSTANCE.evaluate(context);
    }

    public final static Double from(final MoveInterface option) {
        return INSTANCE.evaluate(option);
    }

    private Connect4StaticEvaluation() {}

    /*-------------------------------------8<-------------------------------------*/

    private static double computeCellValueInOneDirection(final SideInterface side, final BoardInterface board, final BoardCellInterface cell,
            final DirectionInterface direction, final int numberOfPawnsToConnectInSamePlane)
    {

        double value = numberOfPawnsToConnectInSamePlane;
        double delta = 0;

        int numberOfCells = 0;
        BoardCellInterface nextCell = cell;

        while (!nextCell.isNull() && numberOfCells < numberOfPawnsToConnectInSamePlane) {
            value -= delta;
            while (nextCell.value().side().equals(side)) {
                nextCell = board.cell(nextCell.position().apply(direction));
                ++numberOfCells;
            }
            if (nextCell.value().side().equals(side.opposite())) break;
            delta = 0;
            while (!nextCell.isNull() && nextCell.isEmpty() && numberOfCells < numberOfPawnsToConnectInSamePlane) {
                nextCell = board.cell(nextCell.position().apply(direction));
                ++numberOfCells;
                --value;
                delta += (0 + 1) / 2.0 * 1 / (-1.0 + numberOfPawnsToConnectInSamePlane + -1.0);
            }
        }
        return numberOfCells < numberOfPawnsToConnectInSamePlane ? 0.0 : value;
    }

    /*-------------------------------------8<-------------------------------------*/

    private double computeCellValue(final SideInterface side, final BoardInterface board, final BoardCellInterface cell,
            final int numberOfPawnsToConnectInSamePlane) {
        double cellValue = 0.0;
        for (final DirectionInterface[] plane : PLANES)
            for (final DirectionInterface direction : plane)
                cellValue = Math.max(cellValue, computeCellValueInOneDirection(side, board, cell, direction, numberOfPawnsToConnectInSamePlane));
        return cellValue;
    }

    /*-------------------------------------8<-------------------------------------*/

    //TODO à tester
    private double computeMovePotential(final SideInterface side, final BoardInterface board, final BoardCellInterface cell,
            final int numberOfPawnsToConnectInSamePlane) {
        double cellValue = 0.0;
        for (final DirectionInterface[] plane : PLANES)
            for (final DirectionInterface direction : plane)
                cellValue += computeCellValueInOneDirection(side, board, cell, direction, numberOfPawnsToConnectInSamePlane);
        return cellValue;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public Double evaluate(final ContextInterface context) {
        double boardEvaluation = 0.0;
        final int numberOfPawnsToConnectInSamePlane = 4; // TODO context.game().configuration(NUMBER_OF_PAWNS_TO_ALIGN);        
        for (final BoardCellInterface cell : context.game().board().collect(Piece.from(context.side(), PieceType.from(Connect4Pawn.class))))
            boardEvaluation = Math.max(boardEvaluation, this.computeCellValue(context.side(), context.game().board(), cell, numberOfPawnsToConnectInSamePlane));
        return boardEvaluation / numberOfPawnsToConnectInSamePlane;
    }

    //TODO à tester    
    @Override
    public Double evaluate(final MoveInterface option) {
        final int numberOfPawnsToConnectInSamePlane = 4; // TODO context.game().configuration(NUMBER_OF_PAWNS_TO_ALIGN);

        final ContextInterface context = option.context().apply(option);

        return this.computeMovePotential(
                context.side(),
                context.game().board(),
                context.game().board().cell(option.position()),
                numberOfPawnsToConnectInSamePlane);
    }
    /*-------------------------------------8<-------------------------------------*/

}