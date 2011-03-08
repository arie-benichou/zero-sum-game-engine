package util;

public class StaticContext {

	public static class CurrentClassGetter<T> extends SecurityManager {
		@SuppressWarnings("rawtypes")
		public Class getClassObject() {
			Class[] classContext = this.getClassContext();
			return classContext[classContext.length - 1];
		}
	}

	@SuppressWarnings("rawtypes")
	public static Class thatClass() {
		return new CurrentClassGetter().getClassObject();
	}

}