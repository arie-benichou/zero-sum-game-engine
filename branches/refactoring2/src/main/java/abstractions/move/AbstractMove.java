
package abstractions.move;

import abstractions.move.API.MoveInterface;
import abstractions.piece.API.PieceInterface;
import abstractions.position.API.PositionInterface;

import static com.google.common.base.Preconditions.checkNotNull;


abstract class AbstractMove implements MoveInterface {

    private final PieceInterface piece;
    private final PositionInterface position;
    
    private volatile int hashCode;

    public AbstractMove(final PositionInterface position, final PieceInterface piece) {
        
        checkNotNull(position, "Argument 'postion' is not intended to be null.");
        checkNotNull(piece, "Argument 'piece' is not intended to be null.");
        
        this.position = position;
        this.piece = piece;
    }

    final public PositionInterface getPosition() {
        return this.position;
    }

    final public PieceInterface getPiece() {
        return this.piece;
    }
    
    @Override
    public int hashCode() {
        int result = this.hashCode;
        if (result == 0) {
            result = 17;

            result *= 31;
            result += this.isNull() ? 0 : 1;

            result *= 31;
            result += this.getPiece().hashCode();

            result *= 31;
            result += this.getPosition().hashCode();

            this.hashCode = result;
        }
        return result;
    }

    @Override
    public final boolean equals(final Object object) {
        final boolean isEqual;
        if (object == this) {
            isEqual = true;
        }
        else if (object == null) {
            isEqual = false;
        }
        else if (!(object instanceof MoveInterface)) {
            isEqual = false;
        }
        else {
            final  MoveInterface that = (MoveInterface) object;
            if (that.hashCode() != this.hashCode()) {
                isEqual = false;
            }
            else {
                isEqual =
                    that.isNull() == this.isNull()
                    && that.getPiece().equals(this.getPiece())
                    && that.getPosition().equals(this.getPosition());
            }
        }
        return isEqual;
    }    

    public abstract boolean isNull();

}
