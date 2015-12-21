/**
 * TextObject.java
 *
 * File:
 *	$Id: TextObject.java,v 1.1 2014/03/05 04:40:49 txw6529 Exp $
 *
 * Revisions:
 *	$Log: TextObject.java,v $
 *	Revision 1.1  2014/03/05 04:40:49  txw6529
 *	initial commit
 *
 */

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents DocObjects consisting of completely plain text.
 * 
 * @author Tristan Whitcher
 */
public class TextObject implements DocObject {

	/**
	 * The text of this node
	 */
	private String text;
	
	/**
	 * Creates a new text leaf node with text
	 * @param text the text
	 */
	public TextObject(String text) {
		this.text = text;
	}
	
	/**
	 * Gets the text of the node
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}
	
	/* (non-Javadoc)
	 * @see DocObject#addChild(int, DocObject)
	 */
	public void addChild(int before, DocObject dObj) {
		throw new BadChildException();
	}
	
	/* (non-Javadoc)
	 * @see DocObject#characterCount()
	 */
	public long characterCount() {
		return text.replace(" ", "").length();
	}

	/* (non-Javadoc)
	 * @see DocObject#children()
	 */
	public List<DocObject> children() {
		return new ArrayList<DocObject>();
	}

	/* (non-Javadoc)
	 * @see DocObject#contains(java.lang.String)
	 */
	public boolean contains(String s) {
		if(text.indexOf(s) != -1) {
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see DocObject#generateHTML()
	 */
	public String generateHTML() {
		return this.text;
	}

	
	/* (non-Javadoc)
	 * @see DocObject#isRoot()
	 */
	public boolean isRoot() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see DocObject#replace(DocObject, DocObject)
	 */
	public void replace(DocObject oldObj, DocObject newObj) {}
	
	/* (non-Javadoc)
	 * @see DocObject#replace(java.lang.String, java.lang.String)
	 */
	public void replace(String oldS, String newS) {
		this.text.replace(oldS, newS);
	}

}
