/**
 * 
 */
package org.mmarini.fp;

import java.util.Map;

/**
 * @author us00852
 * 
 */
public interface FPMap<K, V> extends Map<K, V> {

	/**
	 * 
	 * @param f
	 * @return
	 */
	public boolean contains(Functor1<Boolean, Entry<K, V>> f);

	/**
	 * 
	 * @param f
	 * @return
	 */
	public int count(Functor1<Boolean, Entry<K, V>> f);

	/**
	 * 
	 * @param f
	 * @return
	 */
	public FPMap<K, V> filter(Functor1<Boolean, Entry<K, V>> f);

	/**
	 * 
	 * @param f
	 * @return
	 */
	public Entry<K, V> find(Functor1<Boolean, Entry<K, V>> f);

	/**
	 * 
	 * @param f
	 * @return
	 */
	public <S> S fold(S s, Functor2<S, S, Entry<K, V>> f);

	/**
	 * 
	 * @param f
	 */
	public void forEach(Functor1<Void, Entry<K, V>> f);

	/**
	 * 
	 * @param key
	 * @param def
	 * @return
	 */
	public V getOrElse(K key, V def);

	/**
	 * 
	 * @param f
	 * @return
	 */
	public <S> FPList<S> map(Functor1<S, Entry<K, V>> f);

	/**
	 * 
	 * @param f
	 * @return
	 */
	public Entry<K, V> reduce(Functor2<Entry<K, V>, Entry<K, V>, Entry<K, V>> f);

	/**
	 * 
	 * @param f
	 * @return
	 */
	public <S> FPMap<K, S> remap(Functor1<S, Entry<K, V>> f);

	/**
	 * 
	 * @return
	 */
	public FPList<Entry<K, V>> toList();
}
