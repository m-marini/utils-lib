/**
 * 
 */
package org.mmarini.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author us00852
 * 
 */
public class SwingTools {
	/**
	 * 
	 * @param c
	 */
	public static void centerOnScreen(final Component c) {
		final Dimension s = c.getSize();
		final Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		c.setLocation((ss.width - s.width) / 2, (ss.height - s.height) / 2);
	}

	/**
	 * 
	 * @param owner
	 * @return
	 * @throws IOException
	 */
	public static JDialog createBrowserDialog(final Frame owner,
			final String resource) {
		final JDialog d = new JDialog(owner);
		final JEditorPane hp = new JEditorPane();
		final JLabel infoBar = new JLabel();
		hp.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(final HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					final JEditorPane pane = (JEditorPane) e.getSource();
					if (e instanceof HTMLFrameHyperlinkEvent) {
						final HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;
						final HTMLDocument doc = (HTMLDocument) pane
								.getDocument();
						doc.processHTMLFrameHyperlinkEvent(evt);
					} else {
						try {
							pane.setPage(e.getURL());
						} catch (final IOException e1) {
							infoBar.setText("Error: " + e1.getMessage());
							logger.error(e1.getMessage(), e1);
						}
					}
				}
			}
		});
		hp.setEditable(false);
		final Container c = d.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(new JScrollPane(hp), BorderLayout.CENTER);
		c.add(infoBar, BorderLayout.SOUTH);
		d.setSize(800, 600);
		SwingTools.centerOnScreen(d);
		d.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		hp.setEditorKit(null);
		d.addWindowListener(new WindowAdapter() {

			@Override
			public void windowActivated(final WindowEvent e) {
				if (hp.getPage() == null) {
					try {
						final URL ru = getClass().getResource(resource);
						final URL url = ru != null ? ru : new URL(resource);
						hp.setPage(url);
					} catch (final IOException e1) {
						infoBar.setText("Error: " + e1.getMessage());
						logger.error(e1.getMessage(), e1);
					}
				}
			}

			@Override
			public void windowClosed(final WindowEvent e) {
				hp.setEditorKit(null);
			}
		});
		return d;
	}

	/**
	 * 
	 * @param fmt
	 * @param size
	 * @param s
	 */
	public static JSpinner createNumberSpinner(final SpinnerNumberModel m,
			final String fmt, final int size) {
		final JSpinner s = new JSpinner(m);
		final NumberEditor e = new JSpinner.NumberEditor(s, fmt);
		e.getTextField().setColumns(size);
		s.setEditor(e);
		return s;
	}

	private static final Logger logger = LoggerFactory
			.getLogger(SwingTools.class);

	private final ResourceBundle bundle;

	private final Component parentComponent;

	/**
	 * @param bundle
	 */
	public SwingTools(final ResourceBundle bundle) {
		this(bundle, null);
	}

	/**
	 * 
	 * @param bundle
	 * @param parentComponent
	 */
	public SwingTools(final ResourceBundle bundle,
			final Component parentComponent) {
		this.bundle = bundle;
		this.parentComponent = parentComponent;
	}

	/**
	 * @param message
	 * @param args
	 * @return
	 */
	public SwingTools alert(final String message, final Object... args) {
		JOptionPane.showMessageDialog(parentComponent,
				String.format(message, args), getString("Alert.title"), //$NON-NLS-1$
				JOptionPane.WARNING_MESSAGE);
		return this;
	}

	/**
	 * @param ex
	 * @return
	 */
	public SwingTools alert(final Throwable ex) {
		logger.error(ex.getMessage(), ex);
		JOptionPane.showMessageDialog(parentComponent, ex.getMessage(),
				getString("Alert.title"), //$NON-NLS-1$
				JOptionPane.ERROR_MESSAGE);
		return this;
	}

	/**
	 * @param key
	 * @param args
	 * @return
	 */
	public SwingTools alertLocalized(final String key, final Object... args) {
		JOptionPane.showMessageDialog(parentComponent,
				String.format(getString(key), args), getString("Alert.title"), //$NON-NLS-1$
				JOptionPane.WARNING_MESSAGE);
		return this;
	}

	/**
	 * 
	 * @param ex
	 * @param key
	 * @param args
	 * @return
	 */
	public SwingTools alertLocalized(final Throwable ex, final String key,
			final Object... args) {
		logger.error(ex.getMessage(), ex);
		JOptionPane.showMessageDialog(
				parentComponent,
				new String[] { String.format(getString(key), args),
						ex.getMessage() }, getString("Alert.title"), //$NON-NLS-1$
				JOptionPane.ERROR_MESSAGE);
		return this;
	}

	/**
	 * @param ex
	 * @return
	 */
	public SwingTools alertStackTrace(final Throwable ex) {
		logger.error(ex.getMessage(), ex);
		final StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		final JTextArea ta = new JTextArea(sw.toString());
		ta.setRows(10);
		ta.setColumns(40);
		ta.setTabSize(4);
		ta.setFont(Font.decode(Font.MONOSPACED));
		ta.setEditable(false);
		JOptionPane.showMessageDialog(parentComponent,
				new Object[] { ex.getMessage(), new JScrollPane(ta) },
				getString("Alert.title"), //$NON-NLS-1$
				JOptionPane.ERROR_MESSAGE);
		return this;
	}

	/**
	 * 
	 * @param ex
	 * @param key
	 * @param args
	 * @return
	 */
	public SwingTools alertStackTrace(final Throwable ex, final String key,
			final Object... args) {
		logger.error(ex.getMessage(), ex);
		final StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		final JTextArea ta = new JTextArea(sw.toString());
		ta.setRows(10);
		ta.setColumns(60);
		ta.setTabSize(4);
		ta.setFont(Font.decode(Font.MONOSPACED));
		ta.setEditable(false);
		JOptionPane.showMessageDialog(parentComponent,
				new Object[] { String.format(getString(key), args),
						new JScrollPane(ta) }, getString("Alert.title"), //$NON-NLS-1$
				JOptionPane.ERROR_MESSAGE);
		return this;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	private String getString(final String key) {
		try {
			return bundle.getString(key);
		} catch (final MissingResourceException e) {
			return "!!! " + key + " !!!";
		}
	}
}
