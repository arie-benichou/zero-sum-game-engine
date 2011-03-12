package fr.designpattern.zerosumgames.util;

public class StaticContext {
	
	private StaticContext() {}

	public static class CurrentClassGetter<T> extends SecurityManager {
		@SuppressWarnings("rawtypes")
		public Class getClassObject() {
			final Class[] classContext = this.getClassContext();
			return classContext[classContext.length - 1];
		}
	}

	@SuppressWarnings("rawtypes")
	public static Class thatClass() {
		return new CurrentClassGetter().getClassObject();
	}

}