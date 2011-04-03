
package abstractions.position;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

// import static com.google.common.base.Preconditions.checkArgument;

public class RelativePosition extends AbstractPosition {

    // -------------
    // |   | x |   |
    // -------------
    // |   | . |   |
    // -------------
    // |   |   |   |
    // -------------    
    public final static RelativePosition TOP = new RelativePosition(-1, 0);

    // -------------
    // |   |   |   |
    // -------------
    // |   | . | x |
    // -------------
    // |   |   |   |
    // -------------            
    public final static RelativePosition RIGHT = new RelativePosition(0, 1);

    // -------------
    // |   |   |   |
    // -------------
    // |   | . |   |
    // -------------
    // |   | x |   |
    // -------------            
    public final static RelativePosition BOTTOM = new RelativePosition(1, 0);

    // -------------
    // |   |   |   |
    // -------------
    // | x | . |   |
    // -------------
    // |   |   |   |
    // -------------            
    public final static RelativePosition LEFT = new RelativePosition(0, -1);

    // -------------
    // |   |   |   |
    // -------------
    // |   | . |   |
    // -------------
    // |   |   |   |
    // -------------            
    public final static RelativePosition NULL = new RelativePosition(0, 0);

    // -------------
    // |   |   | x |
    // -------------
    // |   | . |   |
    // -------------
    // |   |   |   |
    // -------------    
    public final static RelativePosition TOP_RIGHT = new RelativePosition(-1, 1);

    // -------------
    // | x |   |   |
    // -------------
    // |   | . |   |
    // -------------
    // |   |   |   |
    // -------------    
    public final static RelativePosition TOP_LEFT = new RelativePosition(-1, -1);

    // -------------
    // |   |   |   |
    // -------------
    // |   | . |   |
    // -------------
    // |   |   | x |
    // -------------    
    public final static RelativePosition BOTTOM_RIGHT = new RelativePosition(1, 1);

    // -------------
    // |   |   |   |
    // -------------
    // |   | . |   |
    // -------------
    // | x |   |   |
    // -------------        
    public final static RelativePosition BOTTOM_LEFT = new RelativePosition(1, -1);

    private static Map<String, RelativePosition> cache = Maps.newHashMapWithExpectedSize(9);
    static {
        cache.put(hash(TOP), TOP);
        cache.put(hash(RIGHT), RIGHT);
        cache.put(hash(BOTTOM), BOTTOM);
        cache.put(hash(LEFT), LEFT);
        cache.put(hash(NULL), NULL);
        cache.put(hash(TOP_RIGHT), TOP_RIGHT);
        cache.put(hash(TOP_LEFT), TOP_LEFT);
        cache.put(hash(BOTTOM_RIGHT), BOTTOM_RIGHT);
        cache.put(hash(BOTTOM_LEFT), BOTTOM_LEFT);
    }

    private boolean isNull;

    private RelativePosition(final int rowDelta, final int columnDelta) {
        super(rowDelta, columnDelta);
        /*
        checkArgument(rowDelta > 1, "Argument 'rowDelta' must not be greater than 1.");
        checkArgument(rowDelta < -1, "Argument 'rowDelta' must not be lesser than -1.");
        checkArgument(columnDelta > 1, "Argument 'columnDelta' must not be greater than 1.");
        checkArgument(columnDelta < -1, "Argument 'columnDelta' must not be lesser than -1.");
        */
        this.isNull = rowDelta == 0 && columnDelta == 0;
    }

    @Override
    public final boolean isNull() {
        return this.isNull;
    }

    private static String hash(RelativePosition relativePosition) {
        return hash(relativePosition.getRow(), relativePosition.getColumn());
    }

    private static String hash(int rowDelta, int columnDelta) {
        return rowDelta + "|" + columnDelta;
    }

    // TODO ajouter de la granularité à cette méthode pour optimiser la méthode and()
    public static RelativePosition reduce(final List<RelativePosition> relativePositions) {
        int rowDelta = 0, columnDelta = 0;
        for (RelativePosition relativePosition : relativePositions) {
            rowDelta += relativePosition.getRow();
            columnDelta += relativePosition.getColumn();
        }
        String key = rowDelta + "|" + columnDelta;
        if (!cache.containsKey(hash(rowDelta, columnDelta))) {
            cache.put(key, new RelativePosition(rowDelta, columnDelta));
        }
        return cache.get(key);
    }

    public static RelativePosition sum(final RelativePosition... relativePositions) {
        return reduce(Arrays.asList(relativePositions));
    }

    public RelativePosition and(RelativePosition that) {
        return sum(this, that);
    }

    // TODO ! tests unitaires
    public static void main(String[] args) {
        System.out.println(RelativePosition.TOP);
        System.out.println(RelativePosition.LEFT);
        System.out.println(RelativePosition.sum(RelativePosition.TOP, RelativePosition.LEFT, RelativePosition.BOTTOM));
        System.out.println(RelativePosition.sum(RelativePosition.TOP, RelativePosition.LEFT, RelativePosition.BOTTOM, RelativePosition.LEFT));
        System.out.println(RelativePosition.sum(RelativePosition.TOP, RelativePosition.LEFT, RelativePosition.BOTTOM, RelativePosition.LEFT));
        System.out.println(RelativePosition.RIGHT.and(RelativePosition.BOTTOM));
    }

}