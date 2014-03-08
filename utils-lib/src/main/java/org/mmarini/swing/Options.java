/**
 * 
 */
package org.mmarini.swing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author us00852
 * 
 */
public class Options extends Properties {
	private static final long serialVersionUID = -4991353328980064547L;
	private static final Logger logger = LoggerFactory.getLogger(Options.class);
	private final File optionFile;

	/**
	 * @param clazz
	 */
	public Options(final Class<?> clazz) {
		this(new File(System.getProperty("user.home"), "." + clazz.getName()
				+ ".xml"));
	}

	/**
	 * @param file
	 */
	public Options(final File file) {
		this.optionFile = file;
		if (file != null) {
			try {
				final FileInputStream in = new FileInputStream(file);
				loadFromXML(in);
				in.close();
			} catch (final FileNotFoundException e) {
				logger.error(e.getMessage(), e);
				saveOptions();
			} catch (final IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 
	 */
	private void saveOptions() {
		if (optionFile != null) {
			try {
				final FileOutputStream out = new FileOutputStream(optionFile);
				storeToXML(out, null, "UTF8"); //$NON-NLS-1$
				out.close();
				logger.debug("Saved {}", optionFile); //$NON-NLS-1$				
			} catch (final IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * @see java.util.Properties#setProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public synchronized Object setProperty(final String key, final String value) {
		final Object p = super.setProperty(key, value);
		saveOptions();
		return p;
	}

}
