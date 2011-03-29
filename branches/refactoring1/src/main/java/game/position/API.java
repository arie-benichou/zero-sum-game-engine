
package game.position;

import static game.dimension.API.*;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class API {

    public final static PositionInterface NULL_POSITION = new NullPosition();
    
    public static final class IllegalPositionException extends RuntimeException {
        
        private static final String MESSAGE = "Position(row=%d, column=%d) is not a legal position.";

        private static final long serialVersionUID = 1L;
        
        private int rowIndex;
        private int columnIndex;

        public IllegalPositionException(final int rowIndex, final int columnIndex) {
            super();
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
        }
        
        @Override
        public String getMessage() {
            return String.format(IllegalPositionException.MESSAGE, this.rowIndex, this.columnIndex);
        }
        
    }
    

    /**
     * This is the interface for a game board position.
     */
    public static interface PositionInterface extends Comparable<PositionInterface> {

        /**
         * Returns the column index of this position.
         * 
         * @return the column index of this position
         */
        int getColumn();

        /**
         * Returns the row index of this position.
         * 
         * @return the row index of this position
         */
        int getRow();

        boolean isNull();

    }

    /**
     * This is the interface for the positions factory.
     */
    public static interface PositionFactoryInterface {

        List<PositionInterface> positions(final DimensionInterface dimension);

        PositionInterface position(final int rowIndex, final int columnIndex);

        PositionInterface nullPosition();

    }

    public static final class PositionFactory implements PositionFactoryInterface {

        /**
         * Ma convention pour implémenter une interface "statique" en attendant
         * que ce soit un jour possible... me semble être un meilleur compromis
         * que l'abjecte ( et anti object :) convention du singleton et de sa
         * méthode getInstance(). Java devrait permettre la déclaration de
         * méthode statique dans une interface afin de ne pas avoir à créer un
         * singleton pour pouvoir implémenter une interface.
         */

        public static PositionInterface NullPosition() {
            return NULL_POSITION;
        }

        public static PositionInterface Position(final int rowIndex, final int columnIndex) {
            try {            
                return new Position(rowIndex, columnIndex);
            }
            catch (IllegalArgumentException e) {
                throw new IllegalPositionException(rowIndex, columnIndex);
            }
        }

        public static List<PositionInterface> Positions(final DimensionInterface dimension) {
            final List<PositionInterface> positions = Lists.newArrayListWithExpectedSize(dimension.boardCapacity());
            for (int rowIndex = dimension.lowerBoundForRows(), maxRowIndex = dimension.upperBoundForRows(); rowIndex <= maxRowIndex; ++rowIndex) {
                for (int columnIndex = dimension.lowerBoundForColumns(), maxColumnIndex = dimension.upperBoundForColumns(); columnIndex <= maxColumnIndex; ++columnIndex) {
                    positions.add(Position(rowIndex, columnIndex));
                }
            }
            return Collections.unmodifiableList(positions);
        }

        /**
         * L'abjecte convention en question :p
         */

        private static final PositionFactoryInterface INSTANCE = new PositionFactory();

        private PositionFactory() {}

        public static PositionFactoryInterface getInstance() {
            return INSTANCE;
        }

        public List<PositionInterface> positions(final DimensionInterface dimension) {
            return Positions(dimension);
        }

        public PositionInterface position(final int rowIndex, final int columnIndex) {
            return Position(rowIndex, columnIndex);
        }

        public PositionInterface nullPosition() {
            return NullPosition();
        }

    }

}