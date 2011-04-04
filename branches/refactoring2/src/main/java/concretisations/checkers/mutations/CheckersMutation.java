
package concretisations.checkers.mutations;

import static com.google.common.base.Preconditions.checkNotNull;
import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.position.RelativePositionInterface;

public abstract class CheckersMutation implements MutationInterface {

    private final CellInterface cell;
    
    // TODO ? mettre en final et rajouter au constructeur
    private transient RelativePositionInterface direction;
    
    // TODO ? utiliser un double pour les infinis ou bien utiliser 0 comme priorité infinie
    // TODO ? mettre en final
    private transient int priority;

    public CheckersMutation(CellInterface cell, int priority) {
        this.cell = cell;
        this.priority = priority;
    }

    public final CheckersMutation direction(RelativePositionInterface direction) {
        this.direction = direction;
        return this;
    }

    public final CellInterface getCell() {
        return cell;
    }

    public final RelativePositionInterface getDirection() {
        return direction;
    }
    
    public final int getPriority() {
        return this.priority;
    }
    
    // TODO ? utiliser cette méthode lorsque la pièce a plusieurs JUMP
    // TODO ? utiliser cette méthode lors de la promotion du MAN en KING
    //public abstract boolean setPriority(priority);    
    
    // TODO à mettre dans une classe abstraite de niveau supérieur
    @Override
    public final int hashCode() {
        
        int hashCode = 17;
        
        hashCode *= 31;
        hashCode += this.getPriority();
        
        hashCode *= 31;        
        hashCode += this.getCell().hashCode();
        
        hashCode *= 31;
        hashCode += this.getDirection().hashCode();
        
        return hashCode;
        
    }
    
    // TODO à mettre dans une classe abstraite de niveau supérieur
    @Override
    public final boolean equals(final Object object) {
        final boolean isEqual;
        if (object == this) {
            isEqual = true;
        }
        else if (object == null) {
            isEqual = false;
        }
        else if (!(object instanceof CheckersMutation)) {
            isEqual = false;
        }
        else {
            final CheckersMutation that = (CheckersMutation) object;
            if (that.hashCode() != this.hashCode()) {
                isEqual = false;
            }
            else {
                isEqual = that.getPriority() == this.getPriority() && that.getCell().equals(this.getCell()) && that.getDirection().equals(this.getDirection());
            }
        }
        return isEqual;
    }
    
    // TODO à mettre dans une classe abstraite de niveau supérieur    
    public final int compareTo(MutationInterface mutation) {
        checkNotNull(mutation, "Argument 'mutation' is not intended to be null.");
        return this.getPriority() - mutation.getPriority();
    }

    @Override
    public final String toString() {
        return this.getClass().getSimpleName() + ": " + this.cell.getPiece().getSide() + " | " + this.cell.getPiece().getClass().getSimpleName() + " | ["
                + this.cell.getRow() + "][" + this.cell.getColumn() + "]" + " | " + this.direction;
    }

    public abstract void process();

}
