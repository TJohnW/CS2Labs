/**
 * Sequence.java
 *
 * File:
 *	$Id: Sequence.java,v 1.1 2014/03/05 04:40:48 txw6529 Exp $
 *
 * Revisions:
 *	$Log: Sequence.java,v $
 *	Revision 1.1  2014/03/05 04:40:48  txw6529
 *	initial commit
 *
 */

import java.util.ArrayList;
import java.util.List;

/**
 * A simple ordered sequence of DocObjects.
 * This is used to string things together into
 * a paragraph or just the body of a list item.
 * 
 * @author Tristan Whitcher
 */
public class Sequence implements DocObject {

	/**
	 * List of child nodes
	 */
	private List<DocObject> children = new ArrayList<DocObject>();
	
	/**
	 * Creates an empty sequence
	 */
	public Sequence() {}
	
	/* (non-Javadoc)
	 * @see DocObject#addChild(int, DocObject)
	 */
	public void addChild(int before, DocObject dObj) {
		this.children.add(before, dObj);
	}
	
	/* (non-Javadoc)
	 * @see DocObject#characterCount()
	 */
	public long characterCount() {
		long chars = 0;
		for(DocObject o: children()) {
			chars += o.characterCount();
		}
		return chars;
	}

	/* (non-Javadoc)
	 * @see DocObject#children()
	 */
	public List<DocObject> children() {
		return children;
	}

	/* (non-Javadoc)
	 * @see DocObject#contains(java.lang.String)
	 */
	public boolean contains(String s) {
		for(DocObject o: children()) {
			if(o.contains(s)) {
				return true;
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see DocObject#generateHTML()
	 */
	public String generateHTML() {
		String html = "";
		for(DocObject obj: children()) {
			html += obj.generateHTML();
		}
		return html;
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
	public void replace(DocObject oldObj, DocObject newObj) {
		for(DocObject obj: children()) {
			if(oldObj.equals(obj)) {
				oldObj = newObj;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see DocObject#replace(java.lang.String, java.lang.String)
	 */
	public void replace(String oldS, String newS) {
		for(DocObject obj: children()) {
			obj.replace(oldS, newS);
		}
	}

}
