
public class ListObject extends DocObject {

	private boolean ordered;
	
	public ListObject(boolean ordered) {
		this.ordered = ordered;
	}
	
	public void addChild(int before, DocObject dObj) {
		super.realAddChild(before, dObj);
	}
	
	public String generateHTML() {
		String tag = "";
		if(this.ordered) tag = "ol";
		else tag = "ul";
		return super.generateHTML(tag, true);
	}

}
