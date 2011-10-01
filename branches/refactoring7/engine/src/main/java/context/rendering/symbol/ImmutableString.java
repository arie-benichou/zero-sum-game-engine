package context.rendering.symbol;

import util.interfaces.ImmutableInterface;

public class ImmutableString implements ImmutableInterface<ImmutableString>{

	private final String string;

	public static ImmutableString from(final String string) {
		return new ImmutableString(string);
	}

	private ImmutableString(final String string) {
		this.string = string;
	}

	@Override
	public ImmutableString apply() {
		return this;
	}

	// TODO ? Ajouter à ImmutableInterface
	public String value() {
		return this.string;
	}

}