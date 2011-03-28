
package game.board.positions;

import game.board.dimensions.Dimensions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class Positions {

    public final static Positions.Interface NULL_POSITION = new NullPosition();
    
    
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
    public static interface Interface extends Comparable<Positions.Interface> {

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
    public static interface FactoryInterface {

        List<Positions.Interface> positions(final Dimensions.Interface dimension);

        Positions.Interface position(final int rowIndex, final int columnIndex);

        Positions.Interface nullPosition();

    }

    public static final class Factory implements Positions.FactoryInterface {

        /**
         * Ma convention pour implémenter une interface "statique" en attendant
         * que ce soit un jour possible... me semble être un meilleur compromis
         * que l'abjecte ( et anti object :) convention du singleton et de sa
         * méthode getInstance(). Java devrait permettre la déclaration de
         * méthode statique dans une interface afin de ne pas avoir à créer un
         * singleton pour pouvoir implémenter une interface.
         */

        public static Positions.Interface NullPosition() {
            return Positions.NULL_POSITION;
        }

        public static Positions.Interface Position(final int rowIndex, final int columnIndex) {
            try {            
                return new Position(rowIndex, columnIndex);
            }
            catch (IllegalArgumentException e) {
                throw new IllegalPositionException(rowIndex, columnIndex);
            }
        }

        public static List<Positions.Interface> Positions(final Dimensions.Interface dimension) {
            final ArrayList<Positions.Interface> positions = Lists.newArrayListWithExpectedSize(dimension.boardCapacity());
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

        private static final FactoryInterface INSTANCE = new Factory();

        private Factory() {}

        public static FactoryInterface getInstance() {
            return INSTANCE;
        }

        public List<Positions.Interface> positions(final Dimensions.Interface dimension) {
            return Positions(dimension);
        }

        public Positions.Interface position(final int rowIndex, final int columnIndex) {
            return Position(rowIndex, columnIndex);
        }

        public Positions.Interface nullPosition() {
            return NullPosition();
        }

    }

}