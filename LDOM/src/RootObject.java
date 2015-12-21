/**
 * RootObject.java
 *
 * File:
 *	$Id: RootObject.java,v 1.1 2014/03/05 04:40:49 txw6529 Exp $
 *
 * Revisions:
 *	$Log: RootObject.java,v $
 *	Revision 1.1  2014/03/05 04:40:49  txw6529
 *	initial commit
 *
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The DocObject used to represent the root node of an entire document.
 * The root position is the only place at which one of these should appear.
 * 
 * @author Tristan Whitcher
 */
public class RootObject implements DocObject {

	/**
	 * The child of this node
	 */
	private DocObject child;
	
	/**
	 * The title in the head
	 */
	private String title;
	
	/**
	 * The added style of the document
	 */
	private String style = "<style>"
			+ "\n  #page_container { padding:0 10px; width:600px; "
			+ "border:1px solid #e5e5e5;"
			+ "background:#f5f5f5; color:#333; margin:30px auto; }"
			+ "\n</style>\n";
	
	/**
	 * Creates a new root object
	 * @param title title in the head
	 * @param dObj child node
	 */
	public RootObject(String title, DocObject dObj) {
		this.title = title;
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
		String html = "<html>\n<head>\n" + this.style + "<title>" + this.title + "</title>\n</head>\n<body>\n<div id=\"page_container\">\n";
		for(DocObject obj: children()) {
			html += obj.generateHTML();
		}
		html += "</div>\n</body>\n</html>";
		return html;
	}

	
	/* (non-Javadoc)
	 * @see DocObject#isRoot()
	 */
	public boolean isRoot() {
		return true;
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
