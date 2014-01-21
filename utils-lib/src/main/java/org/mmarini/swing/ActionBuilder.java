/**
 * 
 */
package org.mmarini.swing;

import java.awt.Component;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author US00852
 * 
 */
public class ActionBuilder {
	private static final Logger logger = LoggerFactory
			.getLogger(ActionBuilder.class);

	/**
	 * 
	 * @param l
	 * @param c
	 * @return
	 */
	public static ActionBuilder create(final ChangeListener l,
			final Component... c) {
		return new ActionBuilder(null, null, l, c);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static ActionBuilder create(final Component... c) {
		return new ActionBuilder(null, null, null, c);
	}

	/**
	 * 
	 * @param b
	 * @param l
	 * @param c
	 * @return
	 */
	public static ActionBuilder create(final ResourceBundle b,
			final ChangeListener l, final Component... c) {
		return new ActionBuilder(b, null, l, c);
	}

	/**
	 * 
	 * @param b
	 * @param c
	 * @return
	 */
	public static ActionBuilder create(final ResourceBundle b,
			final Component... c) {
		return new ActionBuilder(b, null, null, c);
	}

	/**
	 * 
	 * @param b
	 * @param o
	 * @param l
	 * @param c
	 * @return
	 */
	public static ActionBuilder create(final ResourceBundle b,
			final SwingOptions o, final ChangeListener l, final Component... c) {
		return new ActionBuilder(b, o, l, c);
	}

	/**
	 * 
	 * @param b
	 * @param o
	 * @param c
	 * @return
	 */
	public static ActionBuilder create(final ResourceBundle b,
			final SwingOptions o, final Component... c) {
		return new ActionBuilder(b, o, null, c);
	}

	/**
	 * 
	 * @param o
	 * @param l
	 * @param c
	 * @return
	 */
	public static ActionBuilder create(final SwingOptions o,
			final ChangeListener l, final Component... c) {
		return new ActionBuilder(null, o, l, c);
	}

	/**
	 * 
	 * @param o
	 * @param c
	 * @return
	 */
	public static ActionBuilder create(final SwingOptions o,
			final Component... c) {
		return new ActionBuilder(null, o, null, c);
	}

	private final ChangeListener lookAndFeelListener;
	private final Component[] comps;
	private final ResourceBundle bundle;
	private final SwingOptions options;

	/**
	 * 
	 * @param bundle
	 * @param options
	 * @param lookAndFeelListener
	 * @param comps
	 */
	protected ActionBuilder(final ResourceBundle bundle,
			final SwingOptions options,
			final ChangeListener lookAndFeelListener, final Component[] comps) {
		this.options = options;
		this.bundle = bundle;
		this.comps = comps;
		this.lookAndFeelListener = lookAndFeelListener;
	}

	/**
	 * 
	 * @return
	 */
	public JToolBar createHorizontalToolBar(final Action... actions) {
		final JToolBar tb = new JToolBar();
		for (final Action a : actions)
			if (a != null)
				tb.add(a);
			else
				tb.add(new JSeparator(SwingConstants.VERTICAL));
		return tb;
	}

	/**
	 * @return
	 */
	private JMenu createLFMenu() {
		final JMenu mi = createMenu("lookAndFeel");
		final ButtonGroup g = new ButtonGroup();
		final ChangeListener l = new ChangeListener() {

			@Override
			public void stateChanged(final ChangeEvent e) {
				final ButtonModel s = (ButtonModel) e.getSource();
				final String lf = UIManager.getLookAndFeel().getClass()
						.getName();
				final String c = s.getActionCommand();
				if (s.isSelected() && !lf.equals(c)) {
					try {
						UIManager.setLookAndFeel(c);
						if (comps != null)
							for (final Component c1 : comps)
								SwingUtilities.updateComponentTreeUI(c1);
						logger.debug("Set look&feel to {}", c);
						if (options != null)
							options.saveLookAndFeel();
						if (lookAndFeelListener != null)
							lookAndFeelListener.stateChanged(e);
					} catch (final Exception e1) {
						logger.error(e1.getMessage(), e1);
					}
				}
			}
		};
		final String scn = UIManager.getLookAndFeel().getClass().getName();
		ButtonModel sm = null;
		for (final LookAndFeelInfo lf : UIManager.getInstalledLookAndFeels()) {
			final JRadioButtonMenuItem mlf = new JRadioButtonMenuItem(
					lf.getName());

			final ButtonModel m = mlf.getModel();
			final String cn = lf.getClassName();
			m.setActionCommand(cn);
			m.addChangeListener(l);
			if (cn == scn)
				sm = m;
			mi.add(mlf);
			g.add(mlf);
		}
		g.setSelected(sm, true);
		return mi;
	}

	/**
	 * @param id
	 * @return
	 */
	private JMenu createMenu(final String id) {
		final JMenu m = new JMenu(getString(String.format(
				"ActionBuilder.%s.name", id))); //$NON-NLS-1$

		final String ak = getString(String.format(
				"ActionBuilder.%s.acceleratorKey", id)); //$NON-NLS-1$
		if (!ak.startsWith("!")) //$NON-NLS-1$
			m.setAccelerator(KeyStroke.getKeyStroke(ak));

		final String mk = getString(String.format(
				"ActionBuilder.%s.menmonicKey", id)); //$NON-NLS-1$
		if (!mk.startsWith("!")) //$NON-NLS-1$
			m.setMnemonic((int) mk.charAt(0));

		final String si = getString(String.format(
				"ActionBuilder.%s.smallIcon", id)); //$NON-NLS-1$
		if (!si.startsWith("!")) { //$NON-NLS-1$
			final URL url = getClass().getResource(si);
			if (url != null)
				m.setIcon(new ImageIcon(url));
		}

		final String sd = getString(String.format(
				"ActionBuilder.%s.shortDescription", id)); //$NON-NLS-1$
		if (!sd.startsWith("!")) //$NON-NLS-1$
			m.setToolTipText(sd);
		return m;
	}

	/**
	 * 
	 * @return
	 */
	public JMenuBar createMenuBar(final Object... defs) {
		final JMenuBar mb = new JMenuBar();
		JMenu m = null;
		for (final Object d : defs) {
			if (d == null)
				m.add(new JSeparator());
			else if (d instanceof Action)
				m.add((Action) d);
			else if ("lookAndFeel".equals(d))
				m.add(createLFMenu());
			else if (d instanceof String) {
				m = createMenu(d.toString());
				mb.add(m);
			}
		}
		return mb;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	private String getString(final String key) {
		if (bundle != null)
			try {
				return bundle.getString(key);
			} catch (final MissingResourceException e) {
				return '!' + key + '!';
			}
		else
			return key;
	}

	public <A extends Action> A setUp(final A a, final String id) {
		a.putValue(Action.NAME,
				getString(String.format("ActionBuilder.%s.name", id))); //$NON-NLS-1$

		final String ak = getString(String.format(
				"ActionBuilder.%s.acceleratorKey", id)); //$NON-NLS-1$
		if (!ak.startsWith("!")) //$NON-NLS-1$
			a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(ak));

		final String mk = getString(String.format(
				"ActionBuilder.%s.menmonicKey", id)); //$NON-NLS-1$
		if (!mk.startsWith("!")) //$NON-NLS-1$
			a.putValue(Action.MNEMONIC_KEY, (int) mk.charAt(0));

		final String si = getString(String.format(
				"ActionBuilder.%s.smallIcon", id)); //$NON-NLS-1$
		if (!si.startsWith("!")) { //$NON-NLS-1$
			final URL url = getClass().getResource(si);
			if (url != null)
				a.putValue(Action.SMALL_ICON, new ImageIcon(url));
		}

		final String li = getString(String.format(
				"ActionBuilder.%s.largeIcon", id)); //$NON-NLS-1$
		if (!li.startsWith("!")) { //$NON-NLS-1$
			final URL url = getClass().getResource(li);
			if (url != null)
				a.putValue(Action.LARGE_ICON_KEY, new ImageIcon(url));
		}

		final String sd = getString(String.format(
				"ActionBuilder.%s.shortDescription", id)); //$NON-NLS-1$
		if (!sd.startsWith("!")) //$NON-NLS-1$
			a.putValue(Action.SHORT_DESCRIPTION, sd);

		final String ld = getString(String.format(
				"ActionBuilder.%s.longDescription", id)); //$NON-NLS-1$
		if (!ld.startsWith("!")) //$NON-NLS-1$
			a.putValue(Action.LONG_DESCRIPTION, ld);
		return a;
	}
}
