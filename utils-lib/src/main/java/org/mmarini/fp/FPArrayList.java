/**
 * 
 */
package org.mmarini.fp;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author us00852
 * 
 */
public class FPArrayList<T> extends ArrayList<T> implements FPList<T> {
	private static final long serialVersionUID = -6165081301382543295L;

	/**
	 * 
	 */
	public FPArrayList() {
	}

	/**
	 * @param c
	 */
	public FPArrayList(final Collection<? extends T> c) {
		super(c);
	}

	/**
	 * @param initialCapacity
	 */
	public FPArrayList(final int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * 
	 * @param args
	 */
	public FPArrayList(final T... args) {
		super(args.length);
		for (final T i : args)
			add(i);
	}

	/**
	 * @see org.mmarini.fp.FPList#contains(org.mmarini.fp.Functor1)
	 */
	@Override
	public boolean contains(final Functor1<Boolean, T> f) {
		return find(f) != null;
	}

	/**
	 * @see org.mmarini.fp.FPList#count(org.mmarini.fp.Functor1)
	 */
	@Override
	public int count(final Functor1<Boolean, T> f) {
		int c = 0;
		for (final T i : this)
			if (f.apply(i))
				++c;
		return c;
	}

	/**
	 * @see org.mmarini.fp.FPList#filter(org.mmarini.fp.Functor1)
	 */
	@Override
	public FPList<T> filter(final Functor1<Boolean, T> f) {
		final FPList<T> l = new FPArrayList<T>();
		for (final T i : this)
			if (f.apply(i))
				l.add(i);
		return l;
	}

	/**
	 * @see org.mmarini.fp.FPList#find(org.mmarini.fp.Functor1)
	 */
	@Override
	public T find(final Functor1<Boolean, T> f) {
		for (final T i : this)
			if (f.apply(i))
				return i;
		return null;
	}

	/**
	 * @see org.mmarini.fp.FPList#foldLeft(org.mmarini.fp.Functor2)
	 */
	@Override
	public <S> S fold(final S s, final Functor2<S, S, T> f) {
		S r = s;
		for (final T i : this)
			r = f.apply(r, i);
		return r;
	}

	/**
	 * @see org.mmarini.fp.FPList#forEach(org.mmarini.fp.Functor1)
	 */
	@Override
	public void forEach(final Functor1<Void, T> f) {
		for (final T i : this)
			f.apply(i);
	}

	/**
	 * @see org.mmarini.fp.FPList#groupBy(org.mmarini.fp.Functor1)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <K> FPMap<K, FPList<T>> groupBy(final Functor1<K, T> f) {
		final FPMap<K, FPList<T>> r = new FPHashMap<K, FPList<T>>();
		for (final T i : this) {
			final K k = f.apply(i);
			final FPList<T> l = r.get(k);
			if (l == null)
				r.put(k, new FPArrayList<T>(i));
			else
				l.add(i);
		}
		return r;
	}

	/**
	 * @see org.mmarini.fp.FPList#map(org.mmarini.fp.Functor1)
	 */
	@Override
	public <S> FPList<S> map(final Functor1<S, T> f) {
		final FPList<S> l = new FPArrayList<S>();
		for (final T i : this)
			l.add(f.apply(i));
		return l;
	}

	/**
	 * @see org.mmarini.fp.FPList#reduce(org.mmarini.fp.Functor2)
	 */
	@Override
	public T reduce(final Functor2<T, T, T> f) {
		T r = null;
		for (final T i : this)
			r = (r == null) ? i : f.apply(r, i);
		return r;
	}
}
