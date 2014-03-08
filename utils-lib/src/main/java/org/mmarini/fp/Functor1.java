/**
 * 
 */
package org.mmarini.fp;

/**
 * @author us00852
 * 
 */
public interface Functor1<R, T> {

	/**
	 * 
	 * @param p
	 * @return
	 */
	public abstract R apply(T p);
}
