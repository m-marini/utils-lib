/**
 * 
 */
package org.mmarini.fp;

/**
 * @author us00852
 * 
 */
public interface Functor4<R, T1, T2, T3, T4> {

	/**
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @return
	 */
	public abstract R apply(T1 p1, T2 p2, T3 p3, T4 p4);
}
