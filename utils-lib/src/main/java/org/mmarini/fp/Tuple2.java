/**
 * 
 */
package org.mmarini.fp;

import java.util.Map.Entry;

/**
 * @author us00852
 * 
 */
public class Tuple2<T0, T1> implements Entry<T0, T1> {

	private final T0 key;
	private final T1 value;

	/**
	 * @param key
	 * @param value
	 */
	public Tuple2(final T0 key, final T1 value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		final Tuple2<T0, T1> other = (Tuple2<T0, T1>) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	/**
	 * @see java.util.Map.Entry#getKey()
	 */
	@Override
	public T0 getKey() {
		return key;
	}

	/**
	 * @see java.util.Map.Entry#getValue()
	 */
	@Override
	public T1 getValue() {
		return value;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/**
	 * @see java.util.Map.Entry#setValue(java.lang.Object)
	 */
	@Override
	public T1 setValue(final T1 value) {
		throw new IllegalAccessError(
				"Method setValue cannot invoked on immutable Tuple2");
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("(").append(key).append(", ").append(value).append(")");
		return builder.toString();
	}
}
