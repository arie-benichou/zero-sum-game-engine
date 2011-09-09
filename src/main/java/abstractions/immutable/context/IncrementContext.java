
package abstractions.immutable.context;


public class IncrementContext extends AbstractContext<Integer> {

    private final static IncrementContext INITIAL_CONTEXT = new IncrementContext(0);

    private final Integer counter;

    private IncrementContext(final Integer initialContext) {
        this.counter = initialContext;
    }

    public IncrementContext() {
        this(INITIAL_CONTEXT);
    }

    private IncrementContext(final IncrementContext initialContext) {
        this(initialContext.counter + 1);
    }

    @Override
    public ContextInterface<Integer> apply(final Integer option) {
        return new IncrementContext(this.counter + option);
    }

    @Override
    public String toString() {
        return this.counter.toString();
    }

}
