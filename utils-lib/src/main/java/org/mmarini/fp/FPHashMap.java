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
	public FPHashMap() {
	}

	/**
	 * 
	 */
	@SafeVarargs
	public FPHashMap(final Entry<? extends K, ? extends V>... args) {
		super(args.length);
		for (final Entry<? extends K, ? extends V> e : args)
			put(e.getKey(), e.getValue());
	}

	/**
	 * @param initialCapacity
	 */
	public FPHashMap(final int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * @param initialCapacity
	 * @param loadFactor
	 */
	public FPHashMap(final int initialCapacity, final float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	/**
	 * @param m
	 */
	public FPHashMap(final Map<? extends K, ? extends V> m) {
		super(m);
	}

	/**
	 * @see org.mmarini.fp.FPMap#contains(org.mmarini.fp.Functor1)
	 */
	@Override
	public boolean contains(final Functor1<Boolean, Entry<K, V>> f) {
		return find(f) != null;
	}

	/**
	 * @see org.mmarini.fp.FPMap#count(org.mmarini.fp.Functor1)
	 */
	@Override
	public int count(final Functor1<Boolean, Entry<K, V>> f) {
		int c = 0;
		for (final Entry<K, V> e : entrySet())
			if (f.apply(e))
				++c;
		return c;
	}

	/**
	 * @see org.mmarini.fp.FPMap#filter(org.mmarini.fp.Functor1)
	 */
	@Override
	public FPMap<K, V> filter(final Functor1<Boolean, Entry<K, V>> f) {
		final FPHashMap<K, V> r = new FPHashMap<K, V>();
		for (final Entry<K, V> e : entrySet())
			if (f.apply(e))
				r.put(e.getKey(), e.getValue());
		return r;
	}

	/**
	 * @see org.mmarini.fp.FPMap#find(org.mmarini.fp.Functor1)
	 */
	@Override
	public Entry<K, V> find(final Functor1<Boolean, Entry<K, V>> f) {
		for (final Entry<K, V> e : entrySet())
			if (f.apply(e))
				return e;
		return null;
	}

	/**
	 * @see org.mmarini.fp.FPMap#fold(java.lang.Object, org.mmarini.fp.Functor2)
	 */
	@Override
	public <S> S fold(final S s, final Functor2<S, S, Entry<K, V>> f) {
		S r = s;
		for (final Entry<K, V> e : entrySet())
			r = f.apply(s, e);
		return r;
	}

	/**
	 * @see org.mmarini.fp.FPMap#forEach(org.mmarini.fp.Functor1)
	 */
	@Override
	public void forEach(final Functor1<Void, Entry<K, V>> f) {
		for (final Entry<K, V> e : entrySet())
			f.apply(e);
	}

	/**
	 * @see org.mmarini.fp.FPMap#map(org.mmarini.fp.Functor1)
	 */
	@Override
	public <S> FPList<S> map(final Functor1<S, Entry<K, V>> f) {
		final FPList<S> l = new FPArrayList<S>();
		for (final Entry<K, V> e : entrySet())
			l.add(f.apply(e));
		return l;
	}

	/**
	 * @see org.mmarini.fp.FPMap#reduce(org.mmarini.fp.Functor2)
	 */
	@Override
	public Entry<K, V> reduce(
			final Functor2<Entry<K, V>, Entry<K, V>, Entry<K, V>> f) {
		Entry<K, V> r = null;
		for (final Entry<K, V> i : entrySet())
			r = (r == null) ? i : f.apply(r, i);
		return r;
	}

	/**
	 * @see org.mmarini.fp.FPMap#remap(org.mmarini.fp.Functor1)
	 */
	@Override
	public <S> FPMap<K, S> remap(final Functor1<S, java.util.Map.Entry<K, V>> f) {
		final FPMap<K, S> r = new FPHashMap<>();
		for (final Entry<K, V> e : entrySet())
			r.put(e.getKey(), f.apply(e));
		return r;
	}
}
