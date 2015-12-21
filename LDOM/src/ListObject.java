/**
 * ListObject.java
 *
 * File:
 *	$Id: ListObject.java,v 1.1 2014/03/05 04:40:49 txw6529 Exp $
 *
 * Revisions:
 *	$Log: ListObject.java,v $
 *	Revision 1.1  2014/03/05 04:40:49  txw6529
 *	initial commit
 *
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The document object representing HTML lists, ordered and unordered
 * 
 * @author Tristan Whitcher
 */
public class ListObject implements DocObject {

	/**
	 * list of children <li>'s
	 */
	private List<DocObject> children = new ArrayList<DocObject>();
	
	/**
	 * If the list is ordered
	 */
	private boolean ordered;
	
	/**
	 * Create an empty ListObject.
	 * 
	 * @param ordered if true, the list children are numbered;
	 * 			 if false, they are simply bulleted when HTML is generated.
	 */
	public ListObject(boolean ordered) {
		this.ordered = ordered;
	}
	
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
		String tag = "";
		if(this.ordered) tag = "ol";
		else tag = "ul";
		String html = "<" + tag + ">";
		for(DocObject obj: children()) {
			html += "<li>" + obj.generateHTML() + "</li>";
		}
		html += "</" + tag + ">";
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
