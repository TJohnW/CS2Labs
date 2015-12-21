/*
 * Test2.java
 */

import java.util.Scanner;

/**
 * "Hello world" document generator.
 * @author James Heliotis
 * @version $Id: Test2.java,v 1.1 2014/03/05 04:40:49 txw6529 Exp $
 */
public class Test2 {

    /**
     * This class should not be instantiated.
     * It contains static test functions.
     */
    private Test2() {}

    /**
     * Create and display a document that shows, "Hello, world!"
     * <pre>
     * Root
     *   Paragraph
     *     Sequence
     *       Text: "Hello, "
     *       Style bold
     *         Text: "world"
     *       Text: "!"
     * </pre>
     * 
     * Modify it as follows.
     * <ol>
     * <li>Change "!" to "hello"</li>
     * <li>Append text "indeed!" to the sequence.</li>
     * <li>Create text "world;" and replace the bold style object with it.</li>
     * <li>Insert a header "GREETINGS" before the paragraph.
     *     (Requires the creation of a new sequence under the root.)</li>
     * <li>Create an italic style and put the sequence in it;
     *     Replace the sequence with the italic style.</li>
     * </ol>
     * 
     * Result:
     * <pre>
     * Root
     *   Sequence
     *     Header2
     *       Text: GREETINGS
     *     Paragraph
     *       Style italic
     *         Sequence
     *           Text: "Hello, "
     *           Text: "world; "
     *           Text: "hello "
     *           Text: "indeed!"
     * </pre>
     * 
     * @param args unused
     */
    public static void main( String[] args ) {

        Scanner in = new Scanner( System.in );

        DocObject sentence = new Sequence();
        
        DocObject ps1 = new TextObject( "Hello, " );
        sentence.addChild( 0, ps1 );
        DocObject world = new TextObject( "world" );
        DocObject ps2 = new StyleObject( TextStyle.bold, world );
        sentence.addChild( 1, ps2 );
        DocObject ps3 = new TextObject( "!" );
        sentence.addChild( 2, ps3 );

        DocObject paragraph = new ParagraphObject( sentence );
        DocObject document = new RootObject( "Test 2", paragraph );

        Diagnostics.displayDocTree( document, "" );
        Diagnostics.renderAndWait( in, document );

        // REVISION SECTION
        document.replace( "!", " hello" );
        Diagnostics.renderAndWait( in, document );
        sentence.addChild(
                sentence.children().size(), new TextObject( " indeed!" ) );
        Diagnostics.renderAndWait( in, document );
        document.replace( ps2, new TextObject( "world;" ) );
        Diagnostics.renderAndWait( in, document );
        DocObject topSequence = new Sequence();
        topSequence.addChild( 0, paragraph );
        topSequence.addChild( 0,
                new HeaderObject( 2,new TextObject( "GREETINGS" ) ) );
        document.replace( paragraph, topSequence );
        Diagnostics.renderAndWait( in, document );
        DocObject italicized = new StyleObject( TextStyle.italic, paragraph );
        topSequence.replace( paragraph, italicized );

        Diagnostics.displayDocTree( document, "" );
        BrowserUtil.render( document.generateHTML() );
    }
}
