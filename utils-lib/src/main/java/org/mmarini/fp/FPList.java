/**
 * 
 */
package org.mmarini.fp;

import java.util.List;

/**
 * @author us00852
 * 
 */
public interface FPList<T> extends List<T> {

	/**
	 * 
	 * @param f
	 * @return
	 */
	public boolean contains(Functor1<Boolean, T> f);

	/**
	 * 
	 * @param f
	 * @return
	 */
	public int count(Functor1<Boolean, T> f);

	/**
	 * 
	 * @param f
	 * @return
	 */
	public FPList<T> filter(Functor1<Boolean, T> f);

	/**
	 * 
	 * @param f
	 * @return
	 */
	public T find(Functor1<Boolean, T> f);

	/**
	 * 
	 * @param f
	 * @return
	 */
	public <S> S fold(S s, Functor2<S, S, T> f);

	/**
	 * 
	 * @param f
	 */
	public void forEach(Functor1<Void, T> f);

	/**
	 * 
	 * @return
	 */
	public <K> FPMap<K, FPList<T>> groupBy(Functor1<K, T> f);

	/**
	 * 
	 * @param f
	 * @return
	 */
	public <S> FPList<S> map(Functor1<S, T> f);

	/**
	 * 
	 * @param f
	 * @return
	 */
	public T reduce(Functor2<T, T, T> f);

	/**
	 * 
	 * @param other
	 * @return
	 */
	public <S> FPList<Tuple2<T, S>> zip(List<S> other);

	/**
	 * 
	 * @param other
	 * @return
	 */
	public FPList<Tuple2<T, Integer>> zipWithIndex();
}
