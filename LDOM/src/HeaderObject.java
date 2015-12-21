/**
 * HeaderObject.java
 *
 * File:
 *	$Id: HeaderObject.java,v 1.1 2014/03/05 04:40:48 txw6529 Exp $
 *
 * Revisions:
 *	$Log: HeaderObject.java,v $
 *	Revision 1.1  2014/03/05 04:40:48  txw6529
 *	initial commit
 *
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The document object node representing an HTML header construct. All levels are supported.
 * 
 * @author Tristan Whitcher
 */
public class HeaderObject implements DocObject {

	/* The one and only child of the header */
	private DocObject child;
	
	/* The level of the header 1-6 */
	private int level;
	
	/**
	 * Create a HeaderObject of the given level
	 * 
	 * @param level the header level (Bounds not checked; should be 1-5.)
	 * @param dObj The underlying DocObject containing the header's text
	 */
	public HeaderObject(int level, DocObject dObj) {
		this.level = level;
		this.child = dObj;
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
		List<DocObject> children = new ArrayList<DocObject>();
		children.add(this.child);
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
		String html = "<h" + this.level + ">";
		for(DocObject obj: children()) {
			html += obj.generateHTML();
		}
		html += "</h" + this.level + ">";
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
