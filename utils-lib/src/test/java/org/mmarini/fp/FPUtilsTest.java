package org.mmarini.fp;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * 
 * @author us00852
 * 
 */
public class FPUtilsTest {

	@Test
	public void test() {
		@SuppressWarnings("unchecked")
		final FPList<FPList<String>> l = new FPArrayList<FPList<String>>(
				new FPArrayList<String>("A", "B"), new FPArrayList<String>("C",
						"D", "E"));
		assertThat(FPUtils.flatten(l), contains("A", "B", "C", "D", "E"));
	}
}
