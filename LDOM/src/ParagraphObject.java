/**
 * ParagraphObject.java
 *
 * File:
 *	$Id: ParagraphObject.java,v 1.1 2014/03/05 04:40:47 txw6529 Exp $
 *
 * Revisions:
 *	$Log: ParagraphObject.java,v $
 *	Revision 1.1  2014/03/05 04:40:47  txw6529
 *	initial commit
 *
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The document object node for an HTML paragraph
 * 
 * @author Tristan Whitcher
 */
public class ParagraphObject implements DocObject {

	/**
	 * The child of this node
	 */
	private DocObject child;
	
	/**
	 * Creates a new paragraph node
	 * @param dObj
	 */
	public ParagraphObject(DocObject dObj) {
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
		String html = "<p>";
		for(DocObject obj: children()) {
			html += obj.generateHTML();
		}
		html += "</p>";
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
