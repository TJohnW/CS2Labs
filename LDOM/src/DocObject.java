/**
 * DocObject.java
 *
 * File:
 *	$Id: DocObject.java,v 1.1 2014/03/05 04:40:48 txw6529 Exp $
 *
 * Revisions:
 *	$Log: DocObject.java,v $
 *	Revision 1.1  2014/03/05 04:40:48  txw6529
 *	initial commit
 *
 */

import java.util.List;

/**
 * An element in a document. DocObjects form a tree. Some implementors can have
 * multiple children; others wrap a single other DocObject,
 * whereas the TextObject class is a leaf -- no children.
 * 
 * @author Tristan Whitcher
 */
public interface DocObject {
	
	/**
	 * Insert a new document object node into the list of children of this node.
	 * If this node's type is one that does not have children, or that has a
	 * fixed number of children when created, a BadChildException
	 * (a descendant of RuntimeException) will occur.
	 * 
	 * @param before the index that the new document object will have, i.e.,
	 * 				 its ordinal position in the child list. All children
	 * 				 formally at that or greater position will have their
	 * 				 position numbers increased by one.
	 * @param dObj the new document object to be inserted
	 */
	public void addChild(int before, DocObject dObj);
	
	
	/**
	 * How many printable characters are in this subtree of the document?
	 * 
	 * @return the number of non-whitespace characters below this node in the tree
	 */
	public long characterCount();
	
	
	/**
	 * What are the subtrees of this document node? 
	 * Postcondition: returned list is unmodifiable.
	 *
	 * @return a list of the direct descendant document object nodes of
	 * 			this node, preserving the order in which they were inserted.
	 * 			If this node's type is one that does not have children,
	 * 			an empty list is returned. If this node's type has a single
	 * 			subordinate DocObject, that single node is returned in a singleton list.
	 */
	public List<DocObject> children();
	
	
	/**
	 * Search for a given character sequence in this subtree of the document
	 * 
	 * @param s the search string
	 * @return true iff the search string is found in a single node of the 
	 * 				tree rooted at this document node
	 */
	public boolean contains(String s);
	
	
	/**
	 * Convert this subtree of the document to plain-text HTML.
	 * 
	 * @return a string containing legal XHTML that represents the doc tree rooted at this node
	 */
	public String generateHTML();
	
	
	/**
	 * Is this the root of the document tree?
	 * 
	 * @return true iff this is an instance of RootObject.
	 */
	public boolean isRoot();
	
	
	/**
	 * Replace all occurrences of the search object with a new object.
	 * The equality operator "==" (not the equals method) is used to
	 * identify the search object in the document tree. No objects are
	 * copied in the execution of this method. If this node's type is
	 * one that does not have children, this method is a no-op.
	 * 
	 * @param oldObj the search object
	 * @param newObj the object that replaces each occurrence of the search object
	 */
	public void replace(DocObject oldObj, DocObject newObj);
	
	
	/**
	 * Replace all occurrences of the search string with a new string.
	 * As in the contains method, the search string must exist completely
	 * within one document object node.
	 * 
	 * @param oldS the search string
	 * @param newS the string that replaces each occurrence of the search string
	 */
	public void replace(String oldS, String newS);
	
	
}
