package org.mmarini.fp;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

public class FPHashMapTest {
	private FPHashMap<String, String> map;

	@Before
	public void setUp() {
		map = new FPHashMap<String, String>(new Tuple2<String, String>("AK",
				"AV"), new Tuple2<String, String>("BK", "BV"),
				new Tuple2<String, String>("CK", "CV"));
	}

	@Test
	public void testCreate() {
		final FPList<Tuple2<String, String>> l = new FPArrayList<>(
				new Tuple2<>("AK", "AV"), new Tuple2<>("BK", "BV"),
				new Tuple2<>("CK", "CV"));
		assertThat(new FPHashMap<String, String>(l), hasEntry("AK", "AV"));
	}

	@Test
	public void testFilter() {
		assertThat(
				map.filter(new Functor1<Boolean, Map.Entry<String, String>>() {

					@Override
					public Boolean apply(final Entry<String, String> p) {
						return !"BK".equals(p.getKey());
					}
				}), allOf(hasEntry("AK", "AV"), hasEntry("CK", "CV")));
	}

	@Test
	public void testgetOrElse() {
		assertThat(map.getOrElse("AK", "?"), equalTo("AV"));
		assertThat(map.getOrElse("ZK", "?"), equalTo("?"));

	}

	@Test
	public void testRemap() {
		assertThat(
				map.remap(new Functor1<String, Map.Entry<String, String>>() {

					@Override
					public String apply(final Entry<String, String> p) {
						return p.getKey() + p.getValue();
					}
				}),
				allOf(hasEntry("AK", "AKAV"), hasEntry("BK", "BKBV"),
						hasEntry("CK", "CKCV")));
	}

	@Test
	public void testToList() {
		final FPList<Entry<String, String>> l = map.toList();
		assertThat(l, hasSize(3));
		assertThat(l, hasItem(new Tuple2<>("AK", "AV")));
		assertThat(l, hasItem(new Tuple2<>("BK", "BV")));
		assertThat(l, hasItem(new Tuple2<>("CK", "CV")));
	}
}
