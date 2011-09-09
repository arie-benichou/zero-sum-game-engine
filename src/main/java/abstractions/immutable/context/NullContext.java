
package abstractions.immutable.context;

public class NullContext<T> extends AbstractContext<T> {

    public NullContext() {}

    @Override
    public ContextInterface<T> apply(final T option) {
        return this;
    }

}
