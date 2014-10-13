### utils-lib

**utils-lib** is a java general purpouse utilities library.

The `org.mmarini.fp` package has basic interfaces and implementation class for
"functional programing" and collections.

The `org.mmarini.swing` has utilities to manage easly swing UI:

-  `GridLayoutHelper` create GridBagLayout containers mapping the constraint 
   attributes with string operators

-  `ActionBuilder` sets up Action properties from resource bundle file and creates
   tool bar and menu bar with
   string operators.

-  `Options` and `SwingOptions` manages properties stored in file (used to manage
   persistent application options).


##### Options

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


##### SwingOptions

Extends the Options storing the "lookAndFeelClass" for current look and feel

    /*
     * Create option properties store in a file named
     * class name "." prefixed and ".xml" suffixed in the user folder
     * Eg. ".org.mmarini.test.Test.xml"
     */
    SwingOptions options = new SwingOptions(this.getClass())
     
    /*
     * Create option properties store in a specific file
     */
    SwingOptions options = new SwingOptions(new File("name"))
    
    /*
     * Save the current look and feel status
     */
    options.saveLookAndFeel() 
