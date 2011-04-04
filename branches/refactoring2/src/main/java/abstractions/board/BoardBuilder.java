
package abstractions.board;

import static abstractions.dimension.API.DimensionFactory.Dimension;

import java.util.Set;

import abstractions.board.API.BoardInterface;
import abstractions.cell.API.CellInterface;
import abstractions.cell.CellFactory;
import abstractions.dimension.API.DimensionInterface;
import abstractions.piece.PieceFactory;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.API.PositionInterface;
import abstractions.position.PositionFactory;

public class BoardBuilder {

    @SuppressWarnings("rawtypes")
    private Class pieces;

    private DimensionInterface dimension;

    private PositionFactory positionFactory;

    private CellFactory cellFactory;

    public <T extends Enum<T> & PieceTypeInterface> BoardBuilder(Class<T> pieces, DimensionInterface dimension) {
        this.dimension = dimension;
        this.pieces = pieces;
    }

    public <T extends Enum<T> & PieceTypeInterface> BoardBuilder(Class<T> pieces, int numberOfRows, int numberOfColumns) {
        this.dimension = Dimension(numberOfRows, numberOfColumns);
        this.pieces = pieces;
    }

    // TODO créer une interface pour la positionFactory
    public BoardBuilder positionFactory(PositionFactory positionFactory) {
        this.positionFactory = positionFactory;
        return this;
    }

    // TODO ? créer une interface pour la cellFactory
    public BoardBuilder cellFactory(CellFactory cellfactory) {
        this.cellFactory = cellfactory;
        return this;
    }

    @SuppressWarnings("unchecked")
    public BoardInterface build() {
        
        // Il doit être possible de fournir une factory de positions différente
        // en attendant de pouvoir gérer d'autres dimensions que des quadrilatères
        if(this.positionFactory == null) {
            this.positionFactory = PositionFactory.getInstance();
        }        
        Set<PositionInterface> positions = this.positionFactory.positions(this.dimension);
        
        PieceFactory pieceFactory = new PieceFactory(this.pieces);
        
        if(this.cellFactory == null) {
            this.cellFactory = new CellFactory(pieceFactory);
        }        
        Set<CellInterface> cells = this.cellFactory.cells(positions);
        
        Board board = new Board(cells);
        board.injectPieceFactory(pieceFactory);
        
        return board;
        
    }

}