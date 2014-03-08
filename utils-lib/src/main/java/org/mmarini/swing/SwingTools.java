/**
 * 
 */
package org.mmarini.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.WindowConstants;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import javax.swing.SpinnerNumberModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author us00852
 * 
 */
public class SwingTools {
	private static final Logger logger = LoggerFactory
			.getLogger(SwingTools.class);

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
}
