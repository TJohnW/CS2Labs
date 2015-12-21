import java.util.Scanner;

/*
 * Diagnostics.java
 */

/**
 * Utilities that can be useful while developing and testing this project
 * 
 * @author James Heliotis
 */
public class Diagnostics {

    /**
     * This class should not be instantiated.
     * It contains static utility functions.
     */
    private Diagnostics() {}

    /**
     * Print on standard output a multi-line, indented display of the
     * document tree.
     * 
     * @param parent the root of the subtree
     * @param indent the initial indentation of the parent node's type
     */
    public static void displayDocTree( DocObject parent, String indent ) {
        System.out.println( indent + parent.getClass().getName() );
        for ( DocObject dObj: parent.children() ) {
            displayDocTree( dObj, indent + "  " );
            try {
                TextObject tObj = (TextObject)dObj;
                System.out.println( indent + "    " + tObj.getText() );
            }
            catch( ClassCastException cce ) {}
        }
    }

    /**
     * Display the document tree in a browser window, then wait until the
     * user hits a carriage return / ENTER on the input stream before
     * returning.
     * 
     * @param in the Scanner from which the carriage return will come
     * @param doc the root of the document tree to display
     */
    public static void renderAndWait( Scanner in, DocObject doc ) {
        BrowserUtil.render( doc.generateHTML() );
        System.out.print( "Strike ENTER to continue:" );
        in.nextLine();
    }
}
