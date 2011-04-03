package abstractions.cell.mutation;

import abstractions.cell.API.CellInterface;


public class MutationFactory {
    
    
    public static AtomicMutationInterface birth(CellInterface cell) {
        return new Birth(cell);
    }
    
    public static AtomicMutationInterface death(CellInterface cell) {
        return new Birth(cell);
    }
    
    public static AtomicMutationInterface alteration(CellInterface cell) {
        return new Alteration(cell);
    }    

}
