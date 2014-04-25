package org.mmarini.fp;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
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

}
