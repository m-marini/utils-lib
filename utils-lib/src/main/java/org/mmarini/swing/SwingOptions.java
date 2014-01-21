/**
 * 
 */
package org.mmarini.swing;

import java.io.File;

import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author us00852
 * 
 */
public class SwingOptions extends Options {
	private static final long serialVersionUID = -4991353328980064547L;
	private static final Logger logger = LoggerFactory
			.getLogger(SwingOptions.class);

	/**
	 * @param name
	 */
	public SwingOptions(final Class<?> clazz) {
		super(clazz);
		installLookAndFeel();
	}

	/**
	 * @param file
	 */
	public SwingOptions(final File file) {
		super(file);
		installLookAndFeel();
	}

	/**
	 * 
	 */
	private void installLookAndFeel() {
		try {
			final String c = getProperty("lookAndFeelClass", //$NON-NLS-1$
					UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel(c);
			logger.debug("Set Look&Feel to {}", c); //$NON-NLS-1$
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 */
	public void saveLookAndFeel() {
		setProperty("lookAndFeelClass", UIManager.getLookAndFeel().getClass()
				.getName());
	}

}
