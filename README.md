utils-lib
=========


**utils-lib** is a java general purpouse utilities library.

The `org.mmarini.fp` package has basic interfaces and implementation class for
"functional programing" and collections.

The `org.mmarini.swing` has utilities to manage easly swing UI:

-  [`GridLayoutHelper`](https://github.com/m-marini/utils-lib/blob/master/README.md#gridlayouthelper)
   create GridBagLayout containers mapping the constraint 
   attributes with string operators

-  [`ActionBuilder`](https://github.com/m-marini/utils-lib/blob/master/README.md#actionbuilder)
   sets up Action properties from resource bundle file and creates
   tool bar and menu bar with
   string operators.

-  [`Options`](https://github.com/m-marini/utils-lib/blob/master/README.md#options) and 
   [`SwingOptions`](https://github.com/m-marini/utils-lib/blob/master/README.md#swingoptions) manages properties stored in file
   (used to manage persistent application options).


Options
-------

    /*
     * Create option properties store in a file named
     * class name "." prefixed and ".xml" suffixed in the user folder
     * Eg. ".org.mmarini.test.Test.xml"
     */
    Options options = new Options(this.getClass())
     
    /*
     * Create option properties store in a specific file
     */
    Options options = new Options(new File("name"))


SwingOptions
------------

Extends the Options storing the "lookAndFeelClass" for current look and feel

    /*
     * Create option properties store in a file named
     * class name "." prefixed and ".xml" suffixed in the user folder
     * Eg. ".org.mmarini.test.Test.xml"
     */
    SwingOptions options = new SwingOptions(this.getClass());
     
    /*
     * Create option properties store in a specific file
     */
    SwingOptions options = new SwingOptions(new File("name"));
    
    /*
     * Save the current look and feel status
     */
    options.saveLookAndFeel();
    

ActionBuilder
-------------

Sets up Action properties from resource bundle file and creates
tool bar and menu bar with string operators.

    /*
     * Create an action builder based on a resource bundle to resolve the string localization.
     * The SwingOptions are used to store look and feel properties.
     * The components c1, c2 are bind to the look and feel change event to redraw
     * the components on look and feel changes.
     */
    ResourceBundle b = ...
    SwingOptions o = ...
    Component c1 = ...
    Component c2 = ...
    ActionBuilder builder = ActionBuilder.create(b, o, c1, c2);

    /*
     * If parameter listener are used a change event will be notified to it on
     * look and feel changes.
     */
    ChangeListener l = ...
    ActionBuilder builder = ActionBuilder.create(b, o, l, c1, c2);

    /*
     * If look and feel are not used (createMenuBar not used)
     * a simpler factory may be used
     */
    ActionBuilder builder = ActionBuilder.create(b);
    
    /*
     * Create a menu bar by parameters.
     * Parameters may be an action
     * or null to insert a separator
     * or the string "lookAndFeel" to insert the available look and feel entries
     * or a key string to insert a menu filled up with resource bundle properties
     *
     * The resource bundle properties may be:
     * ActionBuilder.<key>.name = menu text
     * ActionBuilder.<key>.acceleratorKey = accelerator key text (eg. "ctrl X")
     * ActionBuilder.<key>.mnemonicKey = menmonik key text ("O")
     * ActionBuilder.<key>.smallIcon = optional menu icon
     * ActionBuilder.<key>.shortDescription = optional tool tips text
     */
    Action openAction = ...
    Action exitAction = ...
    JMenuBar menuBar = builder.createMenuBar("file", openAction, null, exitAction, "options", "lookAndFeel");

    /*
     * Create a horizontal tool bar by parameters.
     * Parameters may be an action
     * or null to insert a separator
     */
    JToolBar toolBar = builder.createHorizontalToolBar(openAction, null, exitAction);

    /*
     * Set up an action
     *
     * The resource bundle properties may be:
     * ActionBuilder.<key>.name = menu text
     * ActionBuilder.<key>.acceleratorKey = accelerator key text (eg. "ctrl X")
     * ActionBuilder.<key>.mnemonicKey = menmonik key text ("O")
     * ActionBuilder.<key>.largeIcon = optional button icon
     * ActionBuilder.<key>.smallIcon = optional menu icon
     * ActionBuilder.<key>.longDescription = optional help text
     * ActionBuilder.<key>.shortDescription = optional tool tip text
     */
    Action a = ...
    Action b = setUp(a, "key");


GridLayoutHelper
----------------

Create GridBagLayout containers mapping the constraint 
attributes with string operators

    /*
     * Build the content of a JPanel.
     * The modify method sets the default constraints for the futher operations.
     * The add method adds components to the container.
     *
     * The added contents may be:
     * a component 
     * or an action that will add a JButton
     * a string thah will add a JLabel based on resource bundle key.
     * string starting with "+" char that will change temporary the constraints appliyed to next added component
     *
     * The string constraints used in modify and in add("+"...) may be:
     * 
     * at,<row>,<col>         : set position constraints
     * ipad,<x>,<y>           : set ipad constraint
     * span,<row>,<col>       : set span constraint
     * insets,<size>          : set the insets for all margins
     * insets,<bt>,<lr>       : set the insets for top/botton and left/right margins
     * insets,<t>,<l>,<b>,<r> : set the insets for top, left,botton and right margins
     * weight,<h>,<v>         : set the horizontal and vertical weights for the resizing component
     * def                    : set the default constraints
     * n                      : set north alignement
     * ne                     : set north-east alignement
     * e                      : set east alignement
     * se                     : set south-east alignement
     * s                      : set south alignement
     * sw                     : set south-west alignement
     * w                      : set west alignement
     * nw                     : set north-west alignement
     * center                 : set center alignement
     * below                  : set the gridy to RELATIVE (insert below)
     * right                  : set the gridx to RELATIVE (insert right)
     * hspan                  : set the gridwidth to REMAINDER horizontal span)
     * vspan                  : set the gridheight to REMAINDER (vertical span)
     */
    ResourceBundle b = ...
    JTextField text = new JTextField();
    JPanel panel = new GridLayoutHelper(b, new JPanel()).modify("").add("myTextLabel.text", "+", text).getContainer();
