utils-lib
=========


**utils-lib** is a java general purpouse utilities library.

The `org.mmarini.fp` package has basic interfaces and implementation class for
"functional programing" and collections.

The `org.mmarini.swing` has utilities to manage easly swing UI:

-  `GridLayoutHelper` create GridBagLayout containers mapping the constraint 
   attributes with string operators
[Troubleshooting](https://github.com/scripted-editor/scripted/wiki/FAQ#wiki-Troubleshooting)

-  [`ActionBuilder`](https://github.com/m-marini/utils-lib/blob/master/README.md#actionbuilder)
   sets up Action properties from resource bundle file and creates
   tool bar and menu bar with
   string operators.

-  [[`Options` | #options]] and [`SwingOptions`](#swingoptions) manages properties stored in file (used to manage
   persistent application options).


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
    
    
