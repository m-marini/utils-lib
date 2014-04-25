package org.mmarini.fp;

/**
 * 
 * @author us00852
 * 
 */
public class FPUtils {
	/**
	 * 
	 * @param l
	 * @return
	 */
	public static <T> FPList<T> flatten(final FPList<FPList<T>> l) {
		final FPList<T> r = new FPArrayList<T>();
		for (final FPList<T> e : l)
			r.addAll(e);
		return r;
	}
}
