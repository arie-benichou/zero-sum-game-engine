
package abstractions.immutable.context;

public abstract class AbstractContext<T> implements ContextInterface<T> {

    @Override
    public abstract ContextInterface<T> apply(final T option);

}
