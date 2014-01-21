/**
 * 
 */
package org.mmarini.fp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author us00852
 * 
 */
public class FPHashMap<K, V> extends HashMap<K, V> implements FPMap<K, V> {
	private static final long serialVersionUID = 2301411653494896713L;

	/**
	 * 
	 */
	public FPHashMap(final Entry<? extends K, ? extends V>... args) {
		super(args.length);
		for (final Entry<? extends K, ? extends V> e : args)
			put(e.getKey(), e.getValue());
	}

	/**
	 * 
	 */
	public FPHashMap() {
	}

	/**
	 * @param initialCapacity
	 * @param loadFactor
	 */
	public FPHashMap(final int initialCapacity, final float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	/**
	 * @param initialCapacity
	 */
	public FPHashMap(final int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * @param m
	 */
	public FPHashMap(final Map<? extends K, ? extends V> m) {
		super(m);
	}

	/**
	 * @see org.mmarini.fp.FPMap#filter(org.mmarini.fp.Functor1)
	 */
	public FPMap<K, V> filter(final Functor1<Boolean, Entry<K, V>> f) {
		return null;
	}

	/**
	 * @see org.mmarini.fp.FPMap#forEach(org.mmarini.fp.Functor1)
	 */
	public void forEach(final Functor1<Void, Entry<K, V>> f) {
		for (final Entry<K, V> e : entrySet())
			f.apply(e);
	}

	/**
	 * @see org.mmarini.fp.FPMap#count(org.mmarini.fp.Functor1)
	 */
	public int count(final Functor1<Boolean, Entry<K, V>> f) {
		int c = 0;
		for (final Entry<K, V> e : entrySet())
			if (f.apply(e))
				++c;
		return c;
	}

	/**
	 * @see org.mmarini.fp.FPMap#contains(org.mmarini.fp.Functor1)
	 */
	public boolean contains(final Functor1<Boolean, Entry<K, V>> f) {
		return find(f) != null;
	}

	/**
	 * @see org.mmarini.fp.FPMap#find(org.mmarini.fp.Functor1)
	 */
	public Entry<K, V> find(final Functor1<Boolean, Entry<K, V>> f) {
		for (final Entry<K, V> e : entrySet())
			if (f.apply(e))
				return e;
		return null;
	}

	/**
	 * @see org.mmarini.fp.FPMap#map(org.mmarini.fp.Functor1)
	 */
	public <S> FPList<S> map(final Functor1<S, Entry<K, V>> f) {
		final FPList<S> l = new FPArrayList<S>();
		for (final Entry<K, V> e : entrySet())
			l.add(f.apply(e));
		return l;
	}

	/**
	 * @see org.mmarini.fp.FPMap#fold(java.lang.Object, org.mmarini.fp.Functor2)
	 */
	public <S> S fold(final S s, final Functor2<S, S, Entry<K, V>> f) {
		S r = s;
		for (final Entry<K, V> e : entrySet())
			r = f.apply(s, e);
		return r;
	}
}
