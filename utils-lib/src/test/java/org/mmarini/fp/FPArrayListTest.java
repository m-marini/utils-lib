package org.mmarini.fp;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FPArrayListTest {

	@Test
	public void testZip() {
		final FPList<Tuple2<String, Integer>> list = new FPArrayList<String>(
				"A", "B", "C").zip(new FPArrayList<Integer>(1, 2, 3));
		assertThat(
				list,
				hasItems(equalTo(new Tuple2<>("A", 1)), equalTo(new Tuple2<>(
						"B", 2)), equalTo(new Tuple2<>("C", 3))));
	}

	@Test
	public void testZipWithIndex() {
		final FPList<Tuple2<String, Integer>> list = new FPArrayList<String>(
				"A", "B", "C").zipWithIndex();
		assertThat(
				list,
				hasItems(equalTo(new Tuple2<>("A", 0)), equalTo(new Tuple2<>(
						"B", 1)), equalTo(new Tuple2<>("C", 2))));
	}
}
