
package context.rendering;

public interface RenderingInterface<INPUT, OUTPUT> {

    OUTPUT render(INPUT input);
}
