/**
 * StyleObject.java
 *
 * File:
 *	$Id: StyleObject.java,v 1.1 2014/03/05 04:40:50 txw6529 Exp $
 *
 * Revisions:
 *	$Log: StyleObject.java,v $
 *	Revision 1.1  2014/03/05 04:40:50  txw6529
 *	initial commit
 *
 */

import java.util.ArrayList;
import java.util.List;

/**
 * An object that modifies the style of the font used to
 * display the text within its wrapped DocObject
 * 
 * @author Tristan Whitcher
 */
public class StyleObject implements DocObject {

	/**
	 * The child node
	 */
	private DocObject child;
	
	/**
	 * The style
	 */
	private TextStyle style;
	
	/**
	 * Creates a new style object with dObj node and style
	 * 
	 * @param style the style
	 * @param dObj the child node
	 */
	public StyleObject(TextStyle style, DocObject dObj) {
		this.style = style;
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
		String tag = "";
		if(this.style == TextStyle.bold) {
			tag = "b";
		} else if(this.style == TextStyle.italic) {
			tag = "i";
		}
		String html = "<" + tag + ">";
		for(DocObject obj: children()) {
			html += obj.generateHTML();
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
