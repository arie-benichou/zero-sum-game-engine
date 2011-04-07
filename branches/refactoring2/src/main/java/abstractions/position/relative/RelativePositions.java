
package abstractions.position.relative;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class RelativePositions {

    // -------------
    // |   | x |   |
    // -------------
    // |   | . |   |
    // -------------
    // |   |   |   |
    // -------------    
    public final static RelativePositionInterface TOP = new RelativePosition(-1, 0);

    // -------------
    // |   |   |   |
    // -------------
    // |   | . | x |
    // -------------
    // |   |   |   |
    // -------------            
    public final static RelativePositionInterface RIGHT = new RelativePosition(0, 1);

    // -------------
    // |   |   |   |
    // -------------
    // |   | . |   |
    // -------------
    // |   | x |   |
    // -------------            
    public final static RelativePositionInterface BOTTOM = new RelativePosition(1, 0);

    // -------------
    // |   |   |   |
    // -------------
    // | x | . |   |
    // -------------
    // |   |   |   |
    // -------------            
    public final static RelativePositionInterface LEFT = new RelativePosition(0, -1);

    // -------------
    // |   |   |   |
    // -------------
    // |   | . |   |
    // -------------
    // |   |   |   |
    // -------------            
    public final static RelativePositionInterface NULL = new RelativePosition(0, 0);

    // -------------
    // |   |   | x |
    // -------------
    // |   | . |   |
    // -------------
    // |   |   |   |
    // -------------    
    public final static RelativePositionInterface TOP_RIGHT = new RelativePosition(-1, 1);

    // -------------
    // | x |   |   |
    // -------------
    // |   | . |   |
    // -------------
    // |   |   |   |
    // -------------    
    public final static RelativePositionInterface TOP_LEFT = new RelativePosition(-1, -1);

    // -------------
    // |   |   |   |
    // -------------
    // |   | . |   |
    // -------------
    // |   |   | x |
    // -------------    
    public final static RelativePositionInterface BOTTOM_RIGHT = new RelativePosition(1, 1);

    // -------------
    // |   |   |   |
    // -------------
    // |   | . |   |
    // -------------
    // | x |   |   |
    // -------------        
    public final static RelativePositionInterface BOTTOM_LEFT = new RelativePosition(1, -1);

    private static String hash(int rowDelta, int columnDelta) {
        return rowDelta + "|" + columnDelta;
    }

    private static String hash(RelativePositionInterface relativePosition) {
        return hash(relativePosition.getRowDelta(), relativePosition.getColumnDelta());
    }

    private static Map<String, RelativePositionInterface> cache = Maps.newHashMapWithExpectedSize(9);
    static {
        cache.put(hash(RelativePositions.TOP), RelativePositions.TOP);
        cache.put(hash(RelativePositions.RIGHT), RelativePositions.RIGHT);
        cache.put(hash(RelativePositions.BOTTOM), RelativePositions.BOTTOM);
        cache.put(hash(RelativePositions.LEFT), RelativePositions.LEFT);
        cache.put(hash(RelativePositions.NULL), RelativePositions.NULL);
        cache.put(hash(RelativePositions.TOP_RIGHT), RelativePositions.TOP_RIGHT);
        cache.put(hash(RelativePositions.TOP_LEFT), RelativePositions.TOP_LEFT);
        cache.put(hash(RelativePositions.BOTTOM_RIGHT), RelativePositions.BOTTOM_RIGHT);
        cache.put(hash(RelativePositions.BOTTOM_LEFT), RelativePositions.BOTTOM_LEFT);
    }

    public static RelativePositionInterface reduce(final List<RelativePositionInterface> relativePositions) {
        int rowDelta = 0, columnDelta = 0;
        for (RelativePositionInterface relativePosition : relativePositions) {
            rowDelta += relativePosition.getRowDelta();
            columnDelta += relativePosition.getColumnDelta();
        }
        String key = rowDelta + "|" + columnDelta;
        if (!cache.containsKey(hash(rowDelta, columnDelta))) {
            cache.put(key, new RelativePosition(rowDelta, columnDelta));
        }
        return cache.get(key);
    }

    public static RelativePositionInterface reduce(final RelativePositionInterface... relativePositions) {
        return reduce(Arrays.asList(relativePositions));
    }

}
