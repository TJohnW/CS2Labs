
import java.util.ArrayList;
import java.util.List;

public abstract class DocObject {
	
	protected List<DocObject> children = new ArrayList<DocObject>();
	
	public abstract String generateHTML();
	
	protected String generateHTML(String tag, boolean isList) {
		String html = "<" + tag + ">";
		for(DocObject obj: children()) {
			if(isList) html += "<li>";
			html += obj.generateHTML();
			if(isList) html += "</li>";
		}
		html += "</" + tag + ">";
		return html;
	}
	
	protected String generateHTMLLin() {
		String html = "";
		for(DocObject obj: children()) {
			html += obj.generateHTML();
		}
		return html;
	}
	
	public void addChild(int before, DocObject dObj) {
		this.realAddChild(0, null);
	}
	
	protected void realAddChild(int before, DocObject dObj) {
		if(dObj == null)
			throw new BadChildException();
		else {
			this.children.add(before, dObj);
		}
	}
	
	public long characterCount() {
		long chars = 0;
		for(DocObject o: children()) {
			chars += o.characterCount();
		}
		return chars;
	}
	
	public List<DocObject> children() {
		return children;
	}
	
	public boolean contains(String s) {
		for(DocObject o: children()) {
			if(o.contains(s)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isRoot() {
		return false;
	}
	
	public void replace(DocObject oldObj, DocObject newObj) {
		for(DocObject obj: children()) {
			if(oldObj.equals(obj)) {
				oldObj = newObj;
			}
		}
	}
	
	public void replace(String oldS, String newS) {
		for(DocObject obj: children()) {
			obj.replace(oldS, newS);
		}
	}
	
}
