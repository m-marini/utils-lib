/**
 * 
 */
package org.mmarini.fp;

/**
 * @author us00852
 * 
 */
public interface Functor3<R, T1, T2, T3> {

	/**
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 * @return
	 */
	public abstract R apply(T1 p1, T2 p2, T3 p3);
}
