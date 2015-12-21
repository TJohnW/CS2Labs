/*
 * Test1.java
 */

import java.util.LinkedList;
import java.util.List;

/**
 * "Hello world" document generator.
 * @author James Heliotis
 * @version $Id: Test1.java,v 1.1 2014/03/05 04:40:50 txw6529 Exp $
 */
public class Test1 {

    /**
     * This class should not be instantiated.
     * It contains static test functions.
     */
    private Test1() {}

    /**
     * Create and display a document that shows, "Hello, world!"
     * <br/>
     * Display a hierarchy of types, similar to the display below.
     * <pre>
     * Root
     *   Paragraph
     *     Sequence
     *       Text: "Hello, "
     *       Style bold
     *         Text: "world"
     *       Text: "!"
     * </pre>
     * Then test the characterCount, isRoot, and contains methods
     * from DocObject.
     * 
     * @param args unused
     */
    public static void main( String[] args ) {

        List< DocObject > allDocObjects = new LinkedList<>();

        DocObject sentence = new Sequence();
        allDocObjects.add( sentence );
        DocObject hello = new TextObject( "Hello, " );
        sentence.addChild( 0, hello );
        allDocObjects.add( hello );
        DocObject world = new TextObject( "world" );
        allDocObjects.add( world );
        DocObject worldBold = new StyleObject( TextStyle.bold, world );
        sentence.addChild( 1, worldBold );
        allDocObjects.add( worldBold );
        DocObject exclam = new TextObject( "!" );
        sentence.addChild( 2, exclam );
        allDocObjects.add( exclam );

        DocObject paragraph = new ParagraphObject( sentence );
        allDocObjects.add( paragraph );
        DocObject document = new RootObject( "Test 1", paragraph );
        allDocObjects.add( document );

        Diagnostics.displayDocTree( document, "" );

        System.out.println( "isRoot() tests:" );
        for ( DocObject d: allDocObjects ) {
            if ( d.isRoot() == ( d == document ) ) {
                System.out.print( "PASS " );
            }
            else {
                System.out.print( "FAIL " );
            }
        }
        System.out.println();
        
        System.out.println( document.characterCount() +
                " non white-space characters in the document text." );

        String[] words = { "hello", "world" };
        for ( String word: words ) {
            System.out.print( word + " is "  );
            System.out.print( document.contains( word ) ? "" : "not " );
            System.out.println( "in the document." );
        }

        BrowserUtil.render( document.generateHTML() );
    }
}
