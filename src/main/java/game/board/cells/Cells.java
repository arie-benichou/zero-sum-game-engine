
package game.board.cells;

import game.board.pieces.PieceInterface;
import game.board.positions.Positions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Cells {

    public final static Cells.Interface NULL_CELL = new NullCell();

    /**
     * This is the interface for a game board cell.
     */
    public static interface Interface extends Comparable<Cells.Interface> {

        /**
         * Returns the position of this cell.
         * 
         * @return the position of this cell
         */
        Positions.Interface getPosition();

        int getRow();

        int getColumn();

        /**
         * Returns true if this cell is empty.
         * 
         * @return true if this cell is empty
         */
        boolean isEmpty();

        /**
         * Returns the piece contained by this cell.
         * 
         * @return the piece contained by this cell
         */
        PieceInterface getPiece();

        /**
         * Assigns a piece to this cell.
         * 
         * @param piece
         *            the piece to be contained by this cell
         */
        void setPiece(final PieceInterface piece);

        boolean isNull();

    }

    public static interface FactoryInterface {

        Cells.Interface cell(final Positions.Interface position);

        List<Cells.Interface> cells(final List<Positions.Interface> positions);

        Cells.Interface clone(final Cells.Interface cellToClone);

        Cells.Interface nullCell();

    }

    public static final class Factory implements Cells.FactoryInterface {

        /**
         * Ma convention pour implémenter une interface "statique" en attendant
         * que ce soit un jour possible... me semble être un meilleur compromis
         * que l'abjecte ( et anti object :) convention du singleton et de sa
         * méthode getInstance(). Java devrait permettre la déclaration de
         * méthode statique dans une interface afin de ne pas avoir à créer un
         * singleton pour pouvoir implémenter une interface.
         */

        public static Cells.Interface NullCell() {
            return Cells.NULL_CELL;
        }

        public static final Cells.Interface Cell(final Positions.Interface position) {
            return new Cell(position);
        }

        public static final List<Cells.Interface> Cells(final List<Positions.Interface> positions) {
            final List<Cells.Interface> cells = new ArrayList<Cells.Interface>(positions.size());
            for (final Positions.Interface position : positions) {
                cells.add(Cells.Factory.Cell(position));
            }
            return Collections.unmodifiableList(cells);
        }

        public static final Cells.Interface Clone(final Cells.Interface cellToClone) {
            final Cells.Interface clone = Cells.Factory.Cell(cellToClone.getPosition());
            clone.setPiece(cellToClone.getPiece());
            return clone;
        }

        /**
         * L'abjecte convention en question :p
         */

        private static final FactoryInterface INSTANCE = new Cells.Factory();

        private Factory() {}

        public static Cells.FactoryInterface getInstance() {
            return Cells.Factory.INSTANCE;
        }

        public Cells.Interface nullCell() {
            return Cells.Factory.NullCell();
        }

        public final List<Cells.Interface> cells(final List<Positions.Interface> positions) {
            return Cells.Factory.Cells(positions);
        }

        public final Cells.Interface cell(final Positions.Interface position) {
            return Cells.Factory.Cell(position);
        }

        public final Cells.Interface clone(final Cells.Interface cellToClone) {
            return Cells.Factory.Clone(cellToClone);
        }

    }

    public static void main(final String[] args) {
        Cells.Factory.getInstance().cell(Positions.Factory.getInstance().position(1, 1));
        Cells.Factory.Cell(Positions.Factory.Position(1, 1));
    }
}